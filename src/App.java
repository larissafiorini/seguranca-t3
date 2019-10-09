import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class App {

	public static void main(String[] args) throws Exception {

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		String fileName = "video_03.mp4";
		BufferedInputStream input = new BufferedInputStream(new FileInputStream(fileName));

		byte[] buffer= new byte[8192];
	    int count;
	    
		while ((count = input.read(buffer)) > 0) {
			md.update(buffer, 0, count);
		}
		input.close();

		byte[] hash = md.digest();
		//encodeToString()
		System.out.println(Base64.getEncoder().encodeToString(hash));
	
	}

}
