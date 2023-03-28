package utilsFile;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

public class CryptoData {
	
	public static String encriptacionMD5(String clave4Encript)  {
	    MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error e encripatcion en guardarNuevoUsuario usuario registrado sin encriptacion de clave");
		}
	      md.update(clave4Encript.getBytes());
	      byte[] digest = md.digest();
	      // Se escribe byte a byte en hexadecimal
	      for (byte b : digest) {
	    	  
	         System.out.print(Integer.toHexString(0xFF & b));
	      }
	      System.out.println();

	      // Se escribe codificado base 64. Se necesita la librería
	      // commons-codec-x.x.x.jar de Apache
	      byte[] encoded = Base64.encodeBase64(digest);
	      
	      System.out.println(new String(encoded));
	      
	      if(encoded==null) {
	    	  encoded= clave4Encript.getBytes();
	      }
	      
	      return new String(encoded);
	}
	
}
