package client.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UTFDataFormatException;

public class ByteStream {
	
    /**
    * 从字节流中得到一个short值。
    */
    public static short getShort(byte[] data, int off){
        return (short) (((data[off] & 0xFF) << 8) | (data[off + 1] & 0xFF));
    }

    /**
     * 保存一个short值到字节流中。
     */
    public static void setShort(byte[] data, int off, short value){
        data[off] = (byte) ((value >> 8) & 0xFF);
        data[off + 1] = (byte) (value & 0xFF);
    }

    /**
     * 从字节流中得到一个int值。
     */
    public static int getInt(byte[] data, int off){
        return ((data[off] & 0xFF) << 24) | ((data[off + 1] & 0xFF) << 16) | ((data[off + 2] & 0xFF) << 8) | (data[off + 3] & 0xFF);
    }

    /**
     * 保存一个int值到字节流中。
     */
    public static void setInt(byte[] data, int off, int value){
        data[off] = (byte) ((value >> 24) & 0xFF);
        data[off + 1] = (byte) ((value >> 16) & 0xFF);
        data[off + 2] = (byte) ((value >> 8) & 0xFF);
        data[off + 3] = (byte) (value & 0xFF);
    }

	/**
	 * 从流中读取一个UTF-16BE字符串。
	 */
	public static String readUTF16(DataInputStream is) throws IOException {
		int slen = is.readByte();
		if ((slen & 0x80) != 0) { // 字符串长度大于128
			int slen2 = is.readByte();
			slen = ((slen & 0x7F) << 8) + (slen2 & 0xFF);
		}
		char[] buf = new char[slen];
		for (int i = 0; i < slen; i++) {
			buf[i] = is.readChar();
		}
		return new String(buf);
	}

	public final static String readUTF(DataInputStream in) throws IOException {
		int utflen = in.readUnsignedShort();
		byte[] bytearr = new byte[utflen];
		char[] chararr = new char[utflen];

		int c, char2 = 0, char3 = 0;
		int count = 0;
		int chararr_count = 0;

		in.readFully(bytearr, 0, utflen);

		while (count < utflen) {
			c = (int) bytearr[count] & 0xff;
			if (c > 127)
				break;
			count++;
			chararr[chararr_count++] = (char) c;
		}

		while (count < utflen) {
			c = (int) bytearr[count] & 0xff;
			if (c < 0x80) {
				count++;
				chararr[chararr_count++] = (char) c;
			} else if (c < 0xe0) {
				count += 2;
				if (count > utflen)
					throw new UTFDataFormatException("malformed input: partial character at end");
				char2 = (int) bytearr[count - 1];
				if ((char2 & 0xC0) != 0x80)
					throw new UTFDataFormatException("malformed input around byte " + count);
				chararr[chararr_count++] = (char) (((c & 0x1F) << 6) | (c & 0x3F));
			} else if (c < 0xf0) {
				/* 1110 xxxx 10xx xxxx 10xx xxxx */
				count += 3;
				if (count > utflen)
					throw new UTFDataFormatException("malformed input: partial character at end");
				char2 = (int) bytearr[count - 2];
				char3 = (int) bytearr[count - 1];
				if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80))
					throw new UTFDataFormatException("malformed input around byte " + (count - 1));
				chararr[chararr_count++] = (char) (((c & 0x0F) << 12) | ((char2 & 0x3F) << 6) | ((char3 & 0x3F) << 0));
			}
		}

		// The number of chars produced may be less than utflen
		return new String(chararr, 0, chararr_count);
	}

	public static byte[] getBytesFromInput(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int rd = 0;
		int len = 0;
		byte[] buf = new byte[64];

		while ((rd = in.read(buf)) != -1) {
			len += rd;
			out.write(buf, 0, rd);
		}

		byte[] rt = out.toByteArray();
		out.close();

		return rt;
	}
}
