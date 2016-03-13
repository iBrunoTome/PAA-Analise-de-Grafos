package planaridade;

import gude.Graph;

public class Main {
	
	public static String planaridade(Boolean teste1, Boolean teste2) {
		return ((teste1 || teste2) == true) ? "\t- NÃO, o grafo não é planar\n" : "\t- SIM, o grafo é planar\n";
	}

	public static void main(String[] args) {
		Graph g = Graph.loadXML("grafo-exemplo.xml");

		K5 k5 = new K5(g);
		K3x3 k3x3 = new K3x3(g);
		
		System.out.println("Contém k5: " + k5.existeK5());
		System.out.println("Contém k3x3: " + k3x3.existeK3x3());
		System.out.println(planaridade(k5.existeK5(), k3x3.existeK3x3()));
		
	}

}
