package server.core.map;
 
import java.util.concurrent.atomic.AtomicInteger;
 


public class UnitInstanceIdGenerator {
	
	protected static AtomicInteger id_gen = new AtomicInteger(0xF0000000);
	
	public static int next(){
		return id_gen.incrementAndGet();
	}

}
