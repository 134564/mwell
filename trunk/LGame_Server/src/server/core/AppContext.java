package server.core;

public interface AppContext {
	
//	public Service getService(String name);
	
	public <T> T get(Class<T> clazz);
	
	public <T> T get(Class<T> clazz,String name);
	
	public <X,Y extends X> void create(Class<Y> clazz,Class<X> inter) throws Exception;
	
	public <X,Y extends X> void create(Class<Y> clazz,Class<X> inter,String name) throws Exception;
	
	public <T> void add(Object service,Class<T> inter);
	
	public <T> void add(Object service,Class<T> inter,String name);
	
//	public void addService(String name,Service service);
//	
//	public void addService(String name,Class<? extends Service> className) throws Exception;
//	
//	public void addService(Service service);
	
	public void shutdown();
	
//	public List<Service> getServices();
}
