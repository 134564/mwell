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
		if(clazz.getAnnotation(OPHandler.class) != null){ //如果是Service同时又是一个包处理类，那么要做类转换，让类实现PacketHandler接口，并且生成handler方法
			clazz = generatePacketHandlerClass(clazz, opcodes);
		}
		Object o = clazz.newInstance();
		if(o instanceof Service){
			((Service)o).startup();
		}
		add(o,inter,name);
		if(opcodes.size() > 0){  //如果opcodes不为空，那么就代表此Service是一个包处理类
			for(int i=0;i<opcodes.size();i++){
				short op = opcodes.get(i);
				HandlerDispatchManager.register(HandlerDispatch.PLAYER,op, (PacketHandler)o);
			}
		}
	}
	
	protected Class generatePacketHandlerClass(Class clazz, TShortArrayList opcodes) throws Exception{
		Map<Short,String> opMethods = new TreeMap<Short,String>();
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method:methods){ //遍历所有方法，将其中标注了是包处理方法的方法名加入到opMethods中
			OP op = method.getAnnotation(OP.class);
			if(op != null){  
				Class[] parameterTypes= method.getParameterTypes();
				//检查方法的合法性
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
			CtClass ct = pool.makeClass(oldClass.getName()+"$Proxy", oldClass); //这里需要生成一个新类，并且继承自原来的类
			CtClass superCt = pool.get(PacketHandler.class.getName());  //需要实现PacketHandler接口
			ct.addInterface(superCt);
			//添加handler方法，在其中添上switch...case段
			StringBuilder sb = new StringBuilder("public void handle(server.io.Packet packet,server.io.ClientSession session) throws Exception{");
			sb.append("short opCode = $1.getOpCode();");
			sb.append("switch (opCode) {");
			Iterator<Map.Entry<Short,String>> ite = opMethods.entrySet().iterator();
			while(ite.hasNext()){
				Map.Entry<Short, String> entry = ite.next();
				sb.append("case ").append(entry.getKey()).append(":");
				sb.append(entry.getValue()).append("($$);"); //注意，这里所有的方法都必须是protected或者是public的，否则此部生成会出错
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
		if(service.getClass()!=inter&&!inter.isAssignableFrom(service.getClass())) //接口和实现类必须相等或者继承关系
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
