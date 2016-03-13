package isomofismo;
import gude.Graph;

public class Main {

	public static void main(String[] args) {
		Graph g1 = Graph.loadXML("grafo-iso1.xml");
		Graph g2 = Graph.loadXML("grafo-iso2.xml");

		Isomorfismo isomorfismo = new Isomorfismo(g1, g2);
		System.out.println(isomorfismo.checaIsomorfismo());
	}

}
