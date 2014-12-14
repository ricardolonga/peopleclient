package br.com.ricardolonga.peopleclient.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {
	
	public static String toString(InputStream in, String charset) throws IOException {
		byte[] bytes = toBytes(in);
		return new String(bytes, charset);
	}
	
	public static byte[] toBytes(InputStream in) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		try {
			byte[] buffer = new byte[1024];
			int len;
			while ((len = in.read(buffer)) > 0) {
				bos.write(buffer, 0, len);
			}
			byte[] bytes = bos.toByteArray();
			return bytes;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				bos.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
