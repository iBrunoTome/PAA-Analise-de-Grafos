package coloracao;

import gude.Graph;

/**
 * Classe Main para o problema de coloração de um grafo
 * 
 * @author Bruno Tomé e Cláudio Menezes
 */
public class Main {

	public static void main(String[] args) {

		Graph g = Graph.loadXML("benchmark1.xml");
		Coloracao coloracao = new Coloracao(g);
		System.out.println("São necessárias " + coloracao.colorir() + " cores para a coloração do grafo");
	}

}
