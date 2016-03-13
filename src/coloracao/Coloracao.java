package coloracao;

import java.util.ArrayList;
import java.util.List;
import gude.Graph;
import gude.Vertex;

public class Coloracao {

	private Graph g;

	public Coloracao() {

	}

	public Coloracao(Graph g) {
		this.g = g;
	}

	public Graph getG() {
		return this.g;
	}

	public void setG(Graph g) {
		this.g = g;
	}

	/**
	 * Um algoritmo guloso considera os vértices em uma ordem específica v1, ·
	 * · · , vn e atribui a vi a menor cor disponível não usada pelos
	 * vértices adjacentes, adicionando uma nova cor se necessário.
	 * http://wiki.icmc.usp.br/images/0/01/Alg2_04.Grafos_caminhos-coloracao.pdf
	 * 
	 * @return Integer qtdCores
	 */

	public Integer colorir() {

		Integer qtdCores = 1;
		Boolean verticeTemCor = true;
		Boolean setouNovaCor = false;
		Boolean mesmaCor = false;

		// ordena os vertices do grafo pelo seu grau, em ordem crescente.
		List<Vertex> vertexList = this.g.ordenaGrau();
		// armazena os vértices adjacentes a um vértice determinado
		List<Vertex> adjacentes = new ArrayList<Vertex>();
		// grafo com os vertices em ordem decrescente de grau
		List<Vertex> grauList = this.g.ordenaGrauDecrescente(vertexList);

		// para colorir o grafo, primeiro colorimos o vertice de maior grau
		grauList.get(0).setColor(1);

		// andando nos vertices PARA V DE 2 ATÉ N
		for (Integer i = 1; i < grauList.size(); i++) {
			setouNovaCor = false;
			adjacentes = this.g.getAdjacentes(grauList.get(i).getId());

			// PARA K DE 1 ATE C
			for (Integer cor = 1; cor <= qtdCores; cor++) {
				// andando nas cores para ver qual pode ser usada pelo vertice
				// em questao anda nos adjacentes ao vertice da posição 'i' e ve
				// a cor dos adjacentes dele
				for (Vertex adj : adjacentes) {
					// se o adjacente ao vértice tem cor igual a atual, então a
					// cor não pode ser a mesma
					if (adj.getColor() == cor) {
						verticeTemCor = false;
						break;
					}
				}
			}

			if (!verticeTemCor) {
				// a cor do vertice nao pode ser nenhuma das que já tem na
				// lista, entao tem que colocar uma nova cor
				for (Integer index = 0; index < i; index++) {
					mesmaCor = false;
					if (!this.g.linkExists(grauList.get(i).getId(), grauList.get(index).getId())) {

						for (Vertex adj : adjacentes) {
							if (adj.getColor() == grauList.get(index).getColor()) {
								mesmaCor = true;
								break;
							}
						}

						if (mesmaCor) {
							continue;
						} else {
							grauList.get(i).setColor(grauList.get(index).getColor());
							setouNovaCor = true;
							break;
						}
					}
				}

				if (!setouNovaCor) {
					qtdCores++;
					grauList.get(i).setColor(qtdCores);
				}
			}
		}

		for (Vertex vertex : grauList) { // Imprime coloração
			System.out.println("Vértice: " + vertex.getId() + ", cor: " + vertex.getColor());
		}

		return qtdCores;

	}
}