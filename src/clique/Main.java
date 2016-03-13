package clique;

import gude.Graph;

/**
 *
 * @author iBrunoTome
 */
public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		Graph g = Graph.loadXML("grafo-exemplo3.xml");
		Integer k = 4;

		Clique clique = new Clique(g, k);
		if (clique.temClique() == true) {
			System.out.println("\nSIM, EXISTE um clique de tamanho " + k);
		} else {
			System.out.println("\nNÃO, NÃO EXISTE um clique de tamanho " + k);
		}
	}

}
