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

		// When appending the hash value to each block, please append it as
		// binary data, that is, as 32 unencoded bytes (which is 256 bits)

		// If the file size is not a multiple of 1KB then the very last block
		// will be shorter than 1KB, but all other blocks will be exactly 1KB

		// quebra o arquivo em blocos de 1KB (1024 bytes)

		String fileName = "video_03.mp4";

		// SHA256 as the hash function
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		HashFunction hash_function = new HashFunction();

		/*
		 * algorithm: quebrar video em blocos de 1024 bytes CHECK calcula o hash do
		 * ultimo bloco, anexa esse valor no bloco anterior a ele assim sucessivamente,
		 * ate chegar no primeiro bloco H0
		 * 
		 * 
		 */

		ArrayList<byte[]> array_bytes = hash_function.divideBlocos(fileName);
		hash_function.calculaHash(array_bytes);

		System.out.println(
				"\n\nresposta esperada video05: 8e423302209494d266a7ab7e1a58ca8502c9bfdaa31dfba70aa8805d20c087bd");
		// video_03 : ee24473e4a369a305c9c3d54629eff01f609b8e2f61ca9cf6f3084f13fe346d6
	}

}
