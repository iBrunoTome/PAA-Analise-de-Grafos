package planaridade;

import java.util.ArrayList;
import java.util.List;
import gude.Edge;
import gude.Graph;
import gude.Vertex;

public class K3x3 {

	private Graph g;

	public K3x3() {

	}

	public K3x3(Graph g) {
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

	public Boolean existeK3x3() {
		List<Edge> allEdgesAux = this.g.getArrayListOfEdges();
		List<Edge> allEdges = new ArrayList<>();
		List<List<Vertex>> k1x1List = new ArrayList<List<Vertex>>();
		List<List<Vertex>> k2x2List = new ArrayList<List<Vertex>>();
		List<List<Vertex>> k3x3List = new ArrayList<List<Vertex>>();

		for (Edge edge : allEdgesAux) {
			if (edge.getAlpha().getId() != edge.getOmega().getId()) {
				allEdges.add(edge);
			}
		}

		/*
		 * Preenche a lista k1x1
		 */

		for (Edge edge : allEdges) {
			List<Vertex> k1x1Aux = new ArrayList<>();
			if (edge.getAlpha().getId() != edge.getOmega().getId()) {
				k1x1Aux.add(edge.getAlpha());
				k1x1Aux.add(edge.getOmega());
				k1x1List.add(k1x1Aux);
			}
		}

		/*
		 * Preenche a lista k2x2
		 */

		for (List<Vertex> k1x1Aux : k1x1List) {
			for (Edge edgeAux : allEdges) {
				if ((k1x1Aux.get(0).getId() != edgeAux.getAlpha().getId())
						&& (k1x1Aux.get(1).getId() != edgeAux.getAlpha().getId())
						&& (k1x1Aux.get(0).getId() != edgeAux.getOmega().getId())
						&& (k1x1Aux.get(1).getId() != edgeAux.getOmega().getId())) {
					if (((this.g.linkExists(edgeAux.getAlpha().getId(), k1x1Aux.get(0).getId())
							&& !this.g.linkExists(edgeAux.getAlpha().getId(), k1x1Aux.get(1).getId()))
							&& (!this.g.linkExists(edgeAux.getOmega().getId(), k1x1Aux.get(0).getId())
									&& this.g.linkExists(edgeAux.getOmega().getId(), k1x1Aux.get(1).getId())))
							|| ((!this.g.linkExists(edgeAux.getAlpha().getId(), k1x1Aux.get(0).getId())
									&& this.g.linkExists(edgeAux.getAlpha().getId(), k1x1Aux.get(1).getId()))
									&& (this.g.linkExists(edgeAux.getOmega().getId(), k1x1Aux.get(0).getId())
											&& !this.g.linkExists(edgeAux.getOmega().getId(),
													k1x1Aux.get(1).getId())))) {
						List<Vertex> k2x2Aux = new ArrayList<>();
						k2x2Aux.add(k1x1Aux.get(0));
						k2x2Aux.add(k1x1Aux.get(1));
						k2x2Aux.add(edgeAux.getAlpha());
						k2x2Aux.add(edgeAux.getOmega());
						k2x2List.add(k2x2Aux);
					}
				}
			}
		}

		/*
		 * Preenche a lista k3x3
		 */

		for (List<Vertex> k2x2Aux : k2x2List) {
			for (Edge edgeAux : allEdges) {
				if ((k2x2Aux.get(0).getId() != edgeAux.getAlpha().getId())
						&& (k2x2Aux.get(1).getId() != edgeAux.getAlpha().getId())
						&& (k2x2Aux.get(2).getId() != edgeAux.getAlpha().getId())
						&& (k2x2Aux.get(3).getId() != edgeAux.getAlpha().getId())
						&& (k2x2Aux.get(0).getId() != edgeAux.getOmega().getId())
						&& (k2x2Aux.get(1).getId() != edgeAux.getOmega().getId())
						&& (k2x2Aux.get(2).getId() != edgeAux.getOmega().getId())
						&& (k2x2Aux.get(3).getId() != edgeAux.getOmega().getId())) {
					if (((this.g.linkExists(edgeAux.getAlpha().getId(), k2x2Aux.get(0).getId())
							&& !this.g.linkExists(edgeAux.getAlpha().getId(), k2x2Aux.get(1).getId()))
							&& (this.g.linkExists(edgeAux.getAlpha().getId(), k2x2Aux.get(2).getId())
									&& !this.g.linkExists(edgeAux.getAlpha().getId(), k2x2Aux.get(3).getId())))
							|| ((!this.g.linkExists(edgeAux.getAlpha().getId(), k2x2Aux.get(0).getId())
									&& this.g.linkExists(edgeAux.getAlpha().getId(), k2x2Aux.get(1).getId()))
									&& (!this.g.linkExists(edgeAux.getAlpha().getId(), k2x2Aux.get(2).getId())
											&& this.g.linkExists(edgeAux.getAlpha().getId(),
													k2x2Aux.get(3).getId())))
									&& ((this.g.linkExists(edgeAux.getOmega().getId(), k2x2Aux.get(0).getId())
											&& !this.g.linkExists(edgeAux.getOmega().getId(),
													k2x2Aux.get(1).getId()))
											&& (this.g.linkExists(edgeAux.getOmega().getId(),
													k2x2Aux.get(2).getId())
													&& !this.g.linkExists(edgeAux.getOmega().getId(),
															k2x2Aux.get(3).getId())))
							|| ((!this.g.linkExists(edgeAux.getOmega().getId(), k2x2Aux.get(0).getId())
									&& this.g.linkExists(edgeAux.getOmega().getId(), k2x2Aux.get(1).getId()))
									&& (!this.g.linkExists(edgeAux.getOmega().getId(), k2x2Aux.get(2).getId())
											&& this.g.linkExists(edgeAux.getOmega().getId(),
													k2x2Aux.get(3).getId())))) {
						List<Vertex> k3x3Aux = new ArrayList<>();
						k3x3Aux.add(k2x2Aux.get(0));
						k3x3Aux.add(k2x2Aux.get(1));
						k3x3Aux.add(k2x2Aux.get(2));
						k3x3Aux.add(k2x2Aux.get(3));
						k3x3Aux.add(edgeAux.getAlpha());
						k3x3Aux.add(edgeAux.getOmega());
						k3x3List.add(k3x3Aux);
					}
				}
			}
		}

		return k3x3List.isEmpty() ? false : true;
	}
}
