import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Base64.Encoder;
import java.util.Arrays;

public class App {

	public static void main(String[] args) throws Exception {

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		String fileName = "video05.mp4";
		
		HashFunction hash_function = new HashFunction();
		hash_function.divideBlocos(fileName);
		
		BufferedInputStream input = new BufferedInputStream(new FileInputStream(fileName));
		
		ArrayList<byte[]> array_bytes=new ArrayList<byte[]>();

		byte[] buffer= new byte[1024];
	    int count;
	    byte[] hash_bloco_anterior=new byte[1024];
	    byte[] hash_bloco_atual=new byte[1024];
	    
	    //le blocos hash de 1024bytes do arquivo
		while ((count = input.read(buffer)) > 0) {
			//md.update(buffer, 0, count);
			md.update(buffer, 0, count);
			byte[] hash = md.digest();
			
			array_bytes.add(hash);
			System.out.println(Base64.getEncoder().encodeToString(hash));
		}
		input.close();
		System.out.println("\n\n\n\n\n\n\n\nAGORA INVERTE\n\n\n\n\n\n");

		// pega do ultimo byte ao primeiro
		Collections.reverse(array_bytes);
		hash_bloco_anterior=array_bytes.get(array_bytes.size()-1);
		for (byte[] b : array_bytes) {
			System.out.println(Base64.getEncoder().encodeToString(b));
			//add two arraylists
			//hash_bloco_atual = add(hash_bloco_anterior, b);
		}
		
		//byte[] hash = md.digest();
		//encodeToString()
		//System.out.println(Base64.getEncoder().encodeToString(hash));
	
		//Instead of computing a hash of the entire file, the web site breaks the file into 1KB blocks (1024 bytes).
		
		//When appending the hash value to each block, please append it as
		//binary data, that is, as 32 unencoded bytes (which is 256 bits).
		
		System.out.println("\n\nresposta esperada: 8e423302209494d266a7ab7e1a58ca8502c9bfdaa31dfba70aa8805d20c087bd");
	}

}
