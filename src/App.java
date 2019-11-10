import java.util.ArrayList;

public class App {
	/*
	 * Hash Function
	 * 
	 * Esse programa realiza a 
	 * 
	 * Autora: Larissa Fiorini Martins 
	 * Disciplina: Seguranca de Sistemas 
	 * Professor: Avelino Zorzo
	 * 
	 */
	public static void main(String[] args) throws Exception {
		
		if (args.length < 1) {
			System.out.println("Run: java -jar Hash.jar <nome_video>");
			System.exit(1);
		}
		// Nome do arquivo de entrada
		String fileName = args[0];

		HashFunction hash_function = new HashFunction();

		// Divide video em blocos de 1024 bytes 
		ArrayList<byte[]> array_bytes = hash_function.divideBlocos(fileName);
		
		// Calcula hash do primeiro bloco H0
		hash_function.calculaHash(array_bytes);

	}
	
	/*
	 * Fontes:
	 * https://www.geeksforgeeks.org/reverse-an-arraylist-in-java/
	 * https://www.devmedia.com.br/como-funciona-a-criptografia-hash-em-java/31139
	 * https://docs.oracle.com/javase/7/docs/api/java/security/MessageDigest.html
	 * */
}
