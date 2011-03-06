package server.io;

public interface PacketHandler {
	public void handle(Packet packet,ClientSession session) throws Exception; 
}
