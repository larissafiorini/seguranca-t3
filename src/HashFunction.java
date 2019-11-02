import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Collections;

public class HashFunction {

	public HashFunction() {

	}

	// Divide arquivo de video em blocos de 1024 bytes
	public static ArrayList<byte[]> divideBlocos(String fileName) {
		try {
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(fileName));
			System.out.println(input.hashCode());

			ArrayList<byte[]> array_bytes = new ArrayList<byte[]>();
			byte[] buffer;
			while (input.available() > 0) {
				if (input.available() < 1024) {
					buffer = new byte[input.available()];
				} else {
					buffer = new byte[1024];
				}
				input.read(buffer);
				array_bytes.add(buffer);
				//System.out.println(Base64.getEncoder().encodeToString(buffer));
			}

			input.close();
			System.out.println(array_bytes.size());

			return array_bytes;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public byte[] calculaHash(ArrayList<byte[]> array_bytes) throws NoSuchAlgorithmException {
		byte[] bloco_hash_final=null ;
		System.out.println("vai imprimir invertido..");
		//percorrer arraylist de forma invertida
		for (int i = array_bytes.size() - 1; i >= 0; i--) { 
			//System.out.println(Base64.getEncoder().encodeToString(array_bytes.get(i)));
			
			/* calcula o hash do ultimo bloco, anexa esse valor no bloco anterior a ele
			 * assim sucessivamente, ate chegar no primeiro bloco H0
			*/
			
			// SHA256 as the hash function
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			//array_bytes.get(i-1) == null
			if (i == 0) {
				bloco_hash_final = md.digest(array_bytes.get(i));
				break;
			}
			
			
			//md.update(array_bytes.get(i));
			byte[] bloco_hash_atual = md.digest(array_bytes.get(i));
			// When appending the hash value to each block, please append it as
			// binary data, that is, as 32 unencoded bytes (which is 256 bits)
			byte[] bloco_hash_anterior = array_bytes.get(i-1);
			byte[] anexa_no_anterior = anexa_array(bloco_hash_anterior, bloco_hash_atual);
			
			array_bytes.set(i-1, anexa_no_anterior);
			
			
		}
		System.out.println("H0: "+toHexString(bloco_hash_final));
		
		return bloco_hash_final;
		
	
	}
	
	// anexa array2 no final de array1
	public static byte[] anexa_array(byte[] array1, byte[] array2){
        byte[] resultado = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, resultado, 0, array1.length);
        System.arraycopy(array2, 0, resultado, array1.length, array2.length);
        return resultado;
    }

	// Funcao para converter um array de bytes para uma String em hexadecimal
	public static String toHexString(byte[] array) {
		return javax.xml.bind.DatatypeConverter.printHexBinary(array).toLowerCase();
	}
	
	/*
	 * Fontes:
	 * https://www.geeksforgeeks.org/reverse-an-arraylist-in-java/
	 * https://www.devmedia.com.br/como-funciona-a-criptografia-hash-em-java/31139
	 * https://docs.oracle.com/javase/7/docs/api/java/security/MessageDigest.html
	 * */

}
