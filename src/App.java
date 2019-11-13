import java.util.ArrayList;

public class App {
	/*
	 * 
	 * Esse programa realiza a implementacao de um programa que dado um arquivo de
	 * video, seja garantida a integridade dos dados do video, dividido em blocos de
	 * 1KB, utilizando o algoritmo de hash seguro SHA-256.
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
		String arquivo_video = args[0];

		Hash hash = new Hash();

		// Divide video em blocos de 1024 bytes
		ArrayList<byte[]> array_bytes = hash.divideBlocos(arquivo_video);

		// Calcula hash do primeiro bloco H0
		hash.calculaHash(array_bytes);

	}

}
