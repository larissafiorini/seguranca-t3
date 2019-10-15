import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Collections;

public class HashFunction {

	public HashFunction() {

	}

	// Divide arquivo de video em blocos de 1024bytes
	public void divideBlocos(String fileName) {
		try {
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(fileName));

			ArrayList<byte[]> array_bytes = new ArrayList<byte[]>();

			byte[] buffer= new byte[1024];
			int count;			
			while ((count = input.read(buffer)) > 0) {
				System.out.println(Base64.getEncoder().encodeToString(buffer));
				array_bytes.add(buffer);
				if (count < 1024)
					buffer = new byte[count];
			}
			
			input.close();
			System.out.println(array_bytes.size());

			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void calculaHash() {
		
	}

}
