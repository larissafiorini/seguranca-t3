import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/*
 * HashFunction
 * 
 * Classe que realiza a divisao do video em blocos de 1KB e calcula o hash H0 utilizando SHA-256.
 * 
 * Autora: Larissa Fiorini Martins 
 * Disciplina: Seguranca de Sistemas 
 * Professor: Avelino Zorzo
 * 
 */
public class HashFunction {

	public HashFunction() {

	}

	/*
	 * Funcao que divide o arquivo de video em blocos de 1KB (1024 bytes). Os blocos
	 * sao armazenados em um arraylist retornado pela funcao.
	 */
	public static ArrayList<byte[]> divideBlocos(String fileName) {
		try {
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(fileName));
			System.out.println(input.hashCode());

			ArrayList<byte[]> array_bytes = new ArrayList<byte[]>();
			byte[] buffer;
			
			while (input.available() > 0) {
				
				// Se o bloco for menor que 1024 bytes, entao o bloco sera o tamanho disponivel
				if (input.available() < 1024) {
					buffer = new byte[input.available()];
				} 
				// Todos os demais blocos serao exatamente de tamanho 1024 bytes
				else {
					buffer = new byte[1024];
				}				
				// Realiza a divisao do video em blocos
				input.read(buffer);
				// Armazena bloco em uma posicao do arraylist
				array_bytes.add(buffer);
			}

			input.close();

			return array_bytes;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/*
	 * Funcao que recebe os blocos do video e inicia calculando o hash SHA-256 do
	 * ultimo bloco, apos anexa esse valor no bloco anterior a ele, e assim
	 * sucessivamente ate chegar no primeiro bloco HO. Por fim, imprime o hash HO
	 * calculado.
	 */
	public byte[] calculaHash(ArrayList<byte[]> array_bytes) throws NoSuchAlgorithmException {
		byte[] bloco_hash_final = null;

		// Percorre arraylist de forma invertida
		for (int i = array_bytes.size() - 1; i >= 0; i--) {

			// Funcao Hash SHA256
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			if (i == 0) {
				bloco_hash_final = md.digest(array_bytes.get(i));
				break;
			}
			// Calcula hash do bloco atual
			byte[] bloco_hash_atual = md.digest(array_bytes.get(i));

			// Anexa valor do hash calculado para o bloco atual no bloco anterior a ele
			byte[] bloco_hash_anterior = array_bytes.get(i - 1);
			byte[] anexa_no_anterior = anexa_array(bloco_hash_anterior, bloco_hash_atual);

			array_bytes.set(i - 1, anexa_no_anterior);
		}
		// Imprime o hash calculado H0
		System.out.println("H0: " + toHexString(bloco_hash_final));

		return bloco_hash_final;

	}

	/*
	 * Funcao que combina dois arrays. Anexa o array2 no final de array1.
	 */
	public static byte[] anexa_array(byte[] array1, byte[] array2) {
		byte[] resultado = new byte[array1.length + array2.length];
		System.arraycopy(array1, 0, resultado, 0, array1.length);
		System.arraycopy(array2, 0, resultado, array1.length, array2.length);
		return resultado;
	}

	/*
	 * Funcao para converter um array de bytes para uma String em hexadecimal.
	 */
	public static String toHexString(byte[] array) {
		return javax.xml.bind.DatatypeConverter.printHexBinary(array).toLowerCase();
	}

}
