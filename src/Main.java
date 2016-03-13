import gude.Graph;
import isomofismo.Isomorfismo;
import planaridade.K3x3;
import planaridade.K5;
import clique.Clique;
import coloracao.Coloracao;

/**
 * Resolvendo 4 problemas de grafos utilizando força bruta, guloso, backtracking
 * e programação dinâmica Testes realizados em um MacBook Pro Retina 2012,
 * Processador i7 RAM: 16GB, placa dedicada 650m
 * 
 * @author Bruno Tomé e Cláudio Menezes.
 */
public class Main {

	/**
	 * Verifica a existência de um clique de tamanho k no grafo
	 * 
	 * @param g
	 * @param k
	 * @return String
	 */

	private static String clique(Graph g, Integer k) {
		Clique clique = new Clique(g, k);
		if (clique.temClique() == true) {
			return "\t- SIM, EXISTE um clique de tamanho " + k + "\n";
		} else {
			return "\t- NÃO, NÃO EXISTE um clique de tamanho " + k + "\n";
		}
	}

	/**
	 * Busca por uma coloração no grafo e imprime a coloração e o número de
	 * cores gastas
	 * 
	 * @param g
	 * @return String
	 */

	private static String coloracao(Graph g) {
		Coloracao coloracao = new Coloracao(g);
		return "\t- São necessárias " + coloracao.colorir() + " cores para a coloração do grafo\n";
	}

	/**
	 * Verifica a planaridade entre dois grafos
	 * 
	 * @param teste1
	 * @param teste2
	 * @return String
	 */

	public static String planaridade(Boolean teste1, Boolean teste2) {
		return ((teste1 || teste2) == true) ? "\t- NÃO, o grafo não é planar\n" : "\t- SIM, o grafo é planar\n";
	}

	public static void main(String[] args) {

		// Graph.txt2xml("benchmark1.txt");
		Graph g = Graph.loadXML("grafo-exemplo.xml");
		Integer k = 3;

		// chamada 1: Clique, força bruta
		System.out.println("1: Encontrar se existe clique de tamanho k em um grafo: (decisão, força bruta)");
		System.out.println(clique(g, k));

		// chamada 2: Coloração, busca
		System.out.println("2: Encontrar coloração: (busca, guloso)");
		System.out.println(coloracao(g));
		K5 k5 = new K5(g);
		K3x3 k3x3 = new K3x3(g);

		// chamada 3: Planaridade por programação dinâmica
		System.out.println("3: Planaridade (decisão, programação dinâmica)");
		System.out.println("Contém k5: " + k5.existeK5());
		System.out.println("Contém k3x3: " + k3x3.existeK3x3());
		System.out.println(planaridade(k5.existeK5(), k3x3.existeK3x3()));

		// chamada 4: Isomorfismo por backtracking
		System.out.println("4: Isomorfismo (decisão, backtraking)");
		Graph g1 = Graph.loadXML("grafo-iso1.xml");
		Graph g2 = Graph.loadXML("grafo-iso2.xml");
		Isomorfismo isomorfismo = new Isomorfismo(g1, g2);
		System.out.println(isomorfismo.checaIsomorfismo());
	}

}
