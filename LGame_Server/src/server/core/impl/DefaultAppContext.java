package server.core.impl;

import gnu.trove.list.array.TShortArrayList;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.Descriptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import server.core.AppContext;
import server.core.OP;
import server.core.OPHandler;
import server.core.Service;
import server.io.ClientSession;
import server.io.Packet;
import server.io.PacketHandler;
import server.packethandler.HandlerDispatch;
import server.packethandler.HandlerDispatchManager;
 
public class DefaultAppContext implements AppContext{
//	protected static  Map<String,Service> services = new HashMap<String,Service>();
	protected static Map<String,Object> services = new HashMap<String,Object>();
	protected static final Logger log = LoggerFactory.getLogger(DefaultAppContext.class);
	
	@Override
	public <X,Y extends X> void create(Class<Y> clazz,Class<X> inter,String name) throws Exception{
		TShortArrayList opcodes =  new TShortArrayList(10);
		if(clazz.getAnnotation(OPHandler.class) != null){ //�����Serviceͬʱ����һ���������࣬��ôҪ����ת��������ʵ��PacketHandler�ӿڣ���������handler����
			clazz = generatePacketHandlerClass(clazz, opcodes);
		}
		Object o = clazz.newInstance();
		if(o instanceof Service){
			((Service)o).startup();
		}
		add(o,inter,name);
		if(opcodes.size() > 0){  //���opcodes��Ϊ�գ���ô�ʹ����Service��һ����������
			for(int i=0;i<opcodes.size();i++){
				short op = opcodes.get(i);
				HandlerDispatchManager.register(HandlerDispatch.PLAYER,op, (PacketHandler)o);
			}
		}
	}
	
	protected Class generatePacketHandlerClass(Class clazz, TShortArrayList opcodes) throws Exception{
		Map<Short,String> opMethods = new TreeMap<Short,String>();
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method:methods){ //�������з����������б�ע���ǰ��������ķ��������뵽opMethods��
			OP op = method.getAnnotation(OP.class);
			if(op != null){  
				Class[] parameterTypes= method.getParameterTypes();
				//��鷽���ĺϷ���
				if(parameterTypes.length != 2){
					throw new IllegalStateException("Method "+method.getName()+" Parameter Error");
				}
				if(parameterTypes[0] != Packet.class || parameterTypes[1] != ClientSession.class){
					throw new IllegalStateException("Method "+method.getName()+" Parameter Error");
				}
				opMethods.put(op.code(), method.getName());
			}
		}
		if(opMethods.size() > 0){
			ClassPool pool = ClassPool.getDefault();
			CtClass oldClass =  pool.get(clazz.getName());
			CtClass ct = pool.makeClass(oldClass.getName()+"$Proxy", oldClass); //������Ҫ����һ�����࣬���Ҽ̳���ԭ������
			CtClass superCt = pool.get(PacketHandler.class.getName());  //��Ҫʵ��PacketHandler�ӿ�
			ct.addInterface(superCt);
			//���handler����������������switch...case��
			StringBuilder sb = new StringBuilder("public void handle(server.io.Packet packet,server.io.ClientSession session) throws Exception{");
			sb.append("short opCode = $1.getOpCode();");
			sb.append("switch (opCode) {");
			Iterator<Map.Entry<Short,String>> ite = opMethods.entrySet().iterator();
			while(ite.hasNext()){
				Map.Entry<Short, String> entry = ite.next();
				sb.append("case ").append(entry.getKey()).append(":");
				sb.append(entry.getValue()).append("($$);"); //ע�⣬�������еķ�����������protected������public�ģ�����˲����ɻ����
				sb.append("break;");
				opcodes.add(entry.getKey());
			}
			sb.append("}");
			sb.append("}");
			CtMethod method = CtMethod.make(sb.toString(), ct);
			ct.addMethod(method);
			return ct.toClass();
		}else{
			return clazz;
		}
	}

	@Override
	public <X,Y extends X> void create(Class<Y> clazz,Class<X> inter) throws Exception{
		create(clazz,inter,inter.getSimpleName());
	}

	@Override
	public <T> void add(Object service, Class<T> inter, String name) {
		if(service.getClass()!=inter&&!inter.isAssignableFrom(service.getClass())) //�ӿں�ʵ���������Ȼ��߼̳й�ϵ
			throw new IllegalArgumentException();
		if(name==null||name.length()==0)
			throw new IllegalArgumentException();
		services.put(name, service);
	}

	@Override
	public <T> void add(Object service, Class<T> inter) {
		add(service,inter,inter.getSimpleName());
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> clazz, String name) {
		return (T)services.get(name);
	}

	@Override
	public <T> T get(Class<T> clazz) {
		return (T)get(null,clazz.getSimpleName());
	}

	
	public void shutdown(){
		for(Object service:services.values()){
			try {
				if(service instanceof Service)
					((Service)service).shutdown();
			} catch (Exception e) {
				log.error(e.toString(),e);
			}
		}
	}
}
