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
				// System.out.println(Base64.getEncoder().encodeToString(buffer));
			}

			input.close();
			System.out.println(array_bytes.size());

			return array_bytes;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public void calculaHash(ArrayList<byte[]> array_bytes) throws NoSuchAlgorithmException {
		
		System.out.println("vai imprimir invertido..");
		//percorrer arraylist de forma invertida
		for (int i = array_bytes.size() - 1; i >= 0; i--) { 
			
			//System.out.println(Base64.getEncoder().encodeToString(array_bytes.get(i)));
			
			
		}
		
		
		
		StringBuilder sb = new StringBuilder();
		// append arrays
		byte[] ret = append(array_bytes.get(array_bytes.size() - 2), array_bytes.get(array_bytes.size() - 1));
		System.out.println(Base64.getEncoder().encodeToString(ret));

		//
		byte[] hash_bloco_anterior = new byte[1024];
		byte[] hash_bloco_atual = new byte[1024];

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		hash_bloco_anterior = md.digest(array_bytes.get(array_bytes.size() - 1));

		for (int i = array_bytes.size() - 2; i > 0; i--) {
			byte[] result = append(hash_bloco_anterior, array_bytes.get(i));
			sb.append(Base64.getEncoder().encodeToString(result));
			hash_bloco_anterior = md.digest(result);
		}
		System.out.println(sb.toString());

	}

	public static byte[] append(byte[] a1, byte[] a2) {
		byte[] ret = new byte[a1.length + a2.length];
		System.arraycopy(a1, 0, ret, 0, a1.length);
		System.arraycopy(a2, 0, ret, a1.length, a2.length);
		return ret;
	}

	// Funcao para converter um array de bytes para uma String em hexadecimal
	public static String toHexString(byte[] array) {
		return javax.xml.bind.DatatypeConverter.printHexBinary(array);
	}
	
	/*
	 * Fontes:
	 * https://www.geeksforgeeks.org/reverse-an-arraylist-in-java/
	 * */

}
