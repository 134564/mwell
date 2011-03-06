package server.core;

import server.io.AsyncCall;
 

public interface ThreadPool {
	
	public void execute(AsyncCall call);
}
