package clique;

import gude.Graph;
import gude.Vertex;

public class Clique {

	private Graph g;
	private Integer k;

	public Clique() {

	}

	public Clique(Graph g, Integer k) {
		this.g = g;
		this.k = k;
	}

	public Graph getG() {
		return g;
	}

	public void setG(Graph g) {
		this.g = g;
	}

	public Integer getK() {
		return k;
	}

	public void setK(Integer k) {
		this.k = k;
	}

	/**
	 * Verifica se existe um clique de tamanho k no grafo informado
	 * 
	 * @return Boolean
	 */

	public Boolean temClique() {

		// se k é maior que o número de vértices, return false
		if (this.k > this.g.getNumVertex()) {
			return false;
		}

		Graph g = this.getG();
		Vertex[] subGrafo;
		Vertex[] todosVertices = new Vertex[g.getNumVertex()];
		Integer indexAnterior = 0;
		Integer i, j, r = this.k;

		todosVertices[0] = g.firstVertex();
		for (Integer index = 1; index < todosVertices.length; index++) {
			indexAnterior = index - 1;
			todosVertices[index] = g.nextVertex(todosVertices[indexAnterior].getId());
		}

		Permutacao p = new Permutacao(todosVertices, r);

		while_loop: while (p.hasNext()) {
			subGrafo = p.next();

			/*
			 * Pesquisa no vetor 'saida' se ha elementos idênticos. Caso
			 * positivo, essa permutacao é ignorada.
			 */

			for (i = 0; i < r; i++) {
				for (j = 1; j < r && i != j; j++) {
					if (subGrafo[i].equals(subGrafo[j])) {
						continue while_loop;
					}
				}
			}

			if (this.estaoLigados(subGrafo) == true) {
				/* Mostra a permutacao na tela. */
				for (Vertex e : subGrafo) {
					System.out.print(e.getId() + "\t");
				}
				System.out.println();

				return true;
			}
		}

		return false;
	}

	/**
	 * Verifica se o subgrafo é completo
	 * 
	 * @param subGrafo
	 * @return Boolean
	 */

	public Boolean estaoLigados(Vertex[] subGrafo) {

		for (Vertex v : subGrafo) {
			for (Vertex x : subGrafo) {
				if (v.getId() == x.getId()) {
					continue;
				} else if (!this.g.linkExists(v.getId(), x.getId())) {
					return false;
				}
			}
		}

		return true;
	}

}
