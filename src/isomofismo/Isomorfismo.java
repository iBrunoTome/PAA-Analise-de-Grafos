package isomofismo;

import java.util.ArrayList;
import java.util.List;
import gude.Graph;
import gude.Vertex;

public class Isomorfismo {

	private Graph g1;
	private Graph g2;

	public Isomorfismo() {

	}

	public Isomorfismo(Graph g1, Graph g2) {
		this.g1 = g1;
		this.g2 = g2;
	}

	public Graph getG1() {
		return this.g1;
	}

	public void setG1(Graph g1) {
		this.g1 = g1;
	}

	public Graph getG2() {
		return this.g2;
	}

	public void setG2(Graph g2) {
		this.g2 = g2;
	}

	public String checaIsomorfismo() {
		List<Vertex> pilhaG1 = new ArrayList<>();
		List<Vertex> pilhaG2 = new ArrayList<>();
		List<Vertex> vertexListAux = new ArrayList<>();
		List<Vertex> vertexGrauListG1 = new ArrayList<>();
		List<Vertex> vertexGrauListG2 = new ArrayList<>();
		// gera listas de grau decrescente
		vertexListAux = this.g1.ordenaGrau();
		vertexGrauListG1 = this.g1.ordenaGrauDecrescente(vertexListAux);
		vertexListAux = new ArrayList<>();
		vertexListAux = this.g2.ordenaGrau();
		vertexGrauListG2 = this.g2.ordenaGrauDecrescente(vertexListAux);

		/*
		 * checa se o tamanho das listas (vertex ou edges) é diferente, se for,
		 * o grafo não é isomorfo
		 */

		if ((vertexGrauListG1.size() != vertexGrauListG2.size())
				|| (this.g1.getArrayListOfEdges().size() != this.g2.getArrayListOfEdges().size())) {
			return "\t- NÃO, os grafos NÃO são isomorfos";
		}

		pilhaG1.add(vertexGrauListG1.get(0));
		pilhaG1.add(vertexGrauListG1.get(1));
		pilhaG2.add(vertexGrauListG2.get(0));
		pilhaG2.add(vertexGrauListG2.get(1));

		Integer i = 1;
		Integer j = 0;

		// enquanto a lista não estiver vazia
		while (j <= (vertexGrauListG1.size() - 1)) {
			/*
			 * compara a equivalência entre os nós dos dois grafos, ao fim do
			 * while, se o tamanho das pilhas e da vertexGrauList de ambos os
			 * grafos forem iguais, os grafos são isomorfos
			 */
			if (this.g1.linkExists(pilhaG1.get(i - 1).getId(), pilhaG1.get(i).getId())
					&& this.g2.linkExists(pilhaG2.get(i - 1).getId(), pilhaG2.get(i).getId())) {
				try {
					pilhaG1.add(vertexGrauListG1.get(i + 1));
					pilhaG2.add(vertexGrauListG2.get(i + 1));
					i++;
				} catch (Exception e) {
					/*
					 * caso tente acessar a posição size() da lista, que não
					 * existe pois a última é size() - 1
					 */
				}
			} else {
				// remove o último e pega o próximo
				pilhaG1.remove(i);
				pilhaG2.remove(i);
				try {
					pilhaG1.add(vertexGrauListG1.get(i + 1));
					pilhaG2.add(vertexGrauListG2.get(i + 1));
				} catch (Exception e) {
					/*
					 * caso tente acessar a posição size() da lista, que não
					 * existe pois a última é size() - 1
					 */
				}
			}

			j++;
		}

		if (pilhaG1.size() == vertexGrauListG1.size() && pilhaG1.size() == pilhaG2.size()
				&& pilhaG2.size() == vertexGrauListG2.size()) {
			return "\t- SIM, os grafos são isomorfos";
		} else {
			return "\t- NÃO, os grafos NÃO são isomorfos";
		}
	}

}
