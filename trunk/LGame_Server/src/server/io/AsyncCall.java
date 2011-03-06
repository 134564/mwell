package server.io;

public interface AsyncCall extends Runnable{
	public void callFinish() throws Exception;
}
