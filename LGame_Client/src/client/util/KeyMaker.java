package client.util;

public class KeyMaker {
	private int k = 1;

	public synchronized int nextKey(){
		return k++;
    }
}
