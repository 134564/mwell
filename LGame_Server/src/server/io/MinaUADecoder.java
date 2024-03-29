package server.io;

import java.io.IOException;

import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class MinaUADecoder extends ProtocolDecoderAdapter {

	
	private static final String BUFFER = ".UABuffer";
	private static final ByteBuffer EMPTY = ByteBuffer.allocate(0);
	

	public void decode(IoSession session, ByteBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		boolean useSessionBuffer = false;
		ByteBuffer buf = (ByteBuffer)session
		.getAttribute(BUFFER);
		if(buf!=null){
//			buf.setAutoExpand(true);
			buf.put(in);
			buf.flip();
			useSessionBuffer = true;
		}else{
			buf = in;
		}
		for(;;){
			if(buf.remaining()>6){
				int pos = buf.position();
				if(buf.get()==85&&buf.get()==65){
					int len = buf.getInt();
					if(buf.remaining()>=(len-6)){  //去掉head以及len一共6个字节
						short opCode = buf.getShort();  
						ByteBuffer data = EMPTY;
						byte[] bytes = new byte[len - 8];
						buf.get(bytes);
						data = ByteBuffer.wrap(bytes);
						Packet packet = new Packet(opCode, data);
						out.write(packet);
					}else{
						buf.position(pos);
						break;
					}
				}else{
					session.setAttribute(BUFFER,null);
					throw new IOException("UA head error.");
				}
			}else{
				break;
			}
		}
		if (buf.hasRemaining()) {
//			if(!useSessionBuffer||consumed){
				storeRemainingInSession(buf,session);
//			}
//			if()
//			else{
//				buf.position(buf.limit());
//			}
		}else{
			if(useSessionBuffer)
				session.setAttribute(BUFFER,null);
		}
	}
	
    private void storeRemainingInSession(ByteBuffer buf, IoSession session) {
        ByteBuffer remainingBuf = ByteBuffer.allocate(buf.capacity());
        remainingBuf.setAutoExpand(true);
        remainingBuf.order(buf.order());
        remainingBuf.put(buf);
        session.setAttribute(BUFFER, remainingBuf);
    }

}
