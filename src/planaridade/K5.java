package planaridade;

import java.util.ArrayList;
import java.util.List;
import gude.Graph;
import gude.Vertex;
import gude.Edge;

public class K5 {

	private Graph g;

	public K5() {

	}

	public K5(Graph g) {
		this.g = g;
	}

	public Graph getG() {
		return this.g;
	}

	public void setG(Graph g) {
		this.g = g;
	}

	/**
	 * Algoritmo dinâmico baseado na ideia passada via e-mail, montando uma
	 * solução ótima baseada em solução sub-ótimas, reaproveitando sub-problemas
	 * 
	 * @return Boolean
	 */

	public Boolean existeK5() {
		List<Vertex> allVertex = this.g.getArrayListOfVertexes();
		List<Edge> edgeList = this.g.getArrayListOfEdges();
		List<List<Vertex>> k2List = new ArrayList<List<Vertex>>();
		List<List<Vertex>> k3List = new ArrayList<List<Vertex>>();
		List<List<Vertex>> k4List = new ArrayList<List<Vertex>>();
		List<List<Vertex>> k5List = new ArrayList<List<Vertex>>();

		/*
		 * Preenche a lista k2
		 */
		for (Edge edge : edgeList) {
			List<Vertex> k2Aux = new ArrayList<>();
			if (edge.getAlpha().getId() != edge.getOmega().getId()) {
				k2Aux.add(edge.getAlpha());
				k2Aux.add(edge.getOmega());
				k2List.add(k2Aux);
			}
		}

		/*
		 * Preenche a lista k3
		 */

		for (List<Vertex> k2Aux : k2List) {
			for (Vertex vertexAux : allVertex) {
				if ((k2Aux.get(0).getId() != vertexAux.getId()) && (k2Aux.get(1).getId() != vertexAux.getId())) {
					if ((this.g.linkExists(k2Aux.get(0).getId(), vertexAux.getId()) == true)
							&& (this.g.linkExists(k2Aux.get(1).getId(), vertexAux.getId()) == true)) {
						List<Vertex> k3Aux = new ArrayList<>();
						k3Aux.add(k2Aux.get(0));
						k3Aux.add(k2Aux.get(1));
						k3Aux.add(vertexAux);
						k3List.add(k3Aux);
					}
				}
			}
		}

		/*
		 * Preenche a lista k4
		 */

		for (List<Vertex> k3Aux : k3List) {
			for (Vertex vertexAux : allVertex) {
				if ((k3Aux.get(0).getId() != vertexAux.getId()) && (k3Aux.get(1).getId() != vertexAux.getId())
						&& (k3Aux.get(2).getId() != vertexAux.getId())) {
					if ((this.g.linkExists(k3Aux.get(0).getId(), vertexAux.getId()) == true)
							&& (this.g.linkExists(k3Aux.get(1).getId(), vertexAux.getId()) == true)
							&& (this.g.linkExists(k3Aux.get(2).getId(), vertexAux.getId()) == true)) {
						List<Vertex> k4Aux = new ArrayList<>();
						k4Aux.add(k3Aux.get(0));
						k4Aux.add(k3Aux.get(1));
						k4Aux.add(k3Aux.get(2));
						k4Aux.add(vertexAux);
						k4List.add(k4Aux);
					}
				}
			}
		}

		/*
		 * Preenche a lista k5
		 */

		for (List<Vertex> k4Aux : k4List) {
			for (Vertex vertexAux : allVertex) {
				if ((k4Aux.get(0).getId() != vertexAux.getId()) && (k4Aux.get(1).getId() != vertexAux.getId())
						&& (k4Aux.get(2).getId() != vertexAux.getId()) && (k4Aux.get(3).getId() != vertexAux.getId())) {
					if ((this.g.linkExists(k4Aux.get(0).getId(), vertexAux.getId()) == true)
							&& (this.g.linkExists(k4Aux.get(1).getId(), vertexAux.getId()) == true)
							&& (this.g.linkExists(k4Aux.get(2).getId(), vertexAux.getId()) == true)
							&& (this.g.linkExists(k4Aux.get(3).getId(), vertexAux.getId()) == true)) {
						List<Vertex> k5Aux = new ArrayList<>();
						k5Aux.add(k4Aux.get(0));
						k5Aux.add(k4Aux.get(1));
						k5Aux.add(k4Aux.get(2));
						k5Aux.add(k4Aux.get(3));
						k5Aux.add(vertexAux);
						k5List.add(k5Aux);
					}
				}
			}
		}

		/*
		 * for (List<Vertex> k2Aux : k5List) { for (Vertex vertexAux: k2Aux) {
		 * System.out.print(vertexAux.getId() + "\t"); } System.out.println(); }
		 */

		/*
		 * se a lista de vértices que formam o k5 estiver vazia, não possui um
		 * subgrafo k5
		 */

		return k5List.isEmpty() ? false : true;
	}

}
