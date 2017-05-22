package br.fiap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;


/*Classe que defines os Nos*/
class Vertex implements Comparable<Vertex>
{
    public final String name;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;
    public Vertex(String argName) { name = argName; }
    public String toString() { return name; }
    public int compareTo(Vertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }
}

/*Classe que defines os Arcos*/
class Edge
{
   public final Vertex target;
   public final double weight;
   public Edge(Vertex argTarget, double argWeight)
   { target = argTarget; weight = argWeight; }
}








/**
 * Enumeração que declara todas as possíveis ações,
 * bem como suas consequências. Caso uma ação não
 * possa ser aplicada em determinada situação,
 * o estado resultante deve ser <code>null</code> 
 * @author antonio
 */
enum Acao {
	DIREITA,
	ESQUERDA,
	ASPIRAR
	;
	
	/**
	 * @param pai O nó ao qual a ação será aplicada
	 * @return O estado resultante de se aplicar a ação ao nó pai
	 */
	Estado aplica(No pai) {
		
		//Estado anterior = pai.estado;
		/*if(this == DIREITA) {
			return new Estado(anterior.Asujo, anterior.Bsujo, false /*quartoA);
		} else if(this == Acao.ESQUERDA) {
			return new Estado(anterior.Asujo, anterior.Bsujo, true /*quartoA);
		} else { //acao = ASPIRAR
			if(anterior.quartoA) {
				return new Estado(false, anterior.Bsujo, anterior.quartoA);
			} else {
				return new Estado(anterior.Asujo, false, anterior.quartoA);
			}
		}*/
		
		return null;
	}
	
	/**
	 * @return O custo da ação
	 */
	double custo() {
		return 1.0;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}

/**
 * Classe que define o estado do problema
 * 
 * @author antonio
 *
 */
class Estado {

	/**
	 * Variáveis de estado
	 */
	public final boolean Asujo, Bsujo, quartoA;
	
	public Estado(boolean Asujo, boolean Bsujo, boolean quartoA) {
		this.Asujo = Asujo;
		this.Bsujo = Bsujo;
		this.quartoA = quartoA;
	}
	
	
	
	
	
	/**
	 * @return O resultado do teste de término
	 */
	boolean terminal() {
		return !Asujo && !Bsujo;
	}
	
}

/**
 * Estrutura contendo o nó de busca, como 
 * explicado em sala de aula.
 * O custo é calculado diretamente a partir da ação.
 * 
 * @author antonio
 *
 */
class No {
	//public final Estado estado;
	private No pai;
	private String nome; 
	//public final Acao acao;
	private LinkedList<No> vizinhos = new LinkedList<No>();
	private double custo;
	
	/*public No(Estado e, No pai, Acao a) {
		this.estado = e;
		this.pai = pai;
		this.acao = a;
		this.custo = pai == null ? 0 : pai.custo + a.custo();
	}*/
	
	public double getCusto() {return custo;}
	public void setCusto(double custo) {this.custo = custo;}
}

/**
 * Classe que implementa a lista de fronteira.
 * è ela quem define a estratégia de busca.
 * @author antonio
 *
 */
class Fronteira {
	
	private Deque<No> lista = new LinkedList<No>();

	/**
	 * @return Se a fronteira está vazia
	 */
	public boolean vazio() {
		return lista.isEmpty();
	}

	/**
	 * Insere o nó na lista de fronteira
	 * @param no 
	 */
	public void insere(No no) {
		lista.addLast(no);
	}

	/**
	 * Retira o próximo nó de acordo com a política
	 * da lista de fronteira
	 * 
	 * @return O proximo nó da lista
	 */
	public No retiraProximo() {
		//return lista.pollLast(); //LIFO
		return lista.pollFirst(); //FIFO
	}
}







/**
 * Classe que implementa o algoritmo de busca
 * @author antonio
 *
 */
public class Busca {

	private Estado estadoInicial;
	private Fronteira fronteira = new Fronteira();
	
	private Acao[] solucao = null;
	private double custoSolucao = 0;
	
	public Acao[] getSolucao() {
		return solucao;
	}
	
	public double getCustoSolucao() {
		return custoSolucao;
	}
	
	public void setEstadoInicial(Estado inicial) {
		estadoInicial = inicial;
	}			

	private No buscar() {
		
		Vertex source = new Vertex("Palma Sola");
		
		source.minDistance = 0.;
	    
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
	    vertexQueue.add(source);

	    while (!vertexQueue.isEmpty()) {
	        Vertex u = vertexQueue.poll();

	            // Visit each edge exiting u
	        for (Edge e : u.adjacencies){
	            Vertex v = e.target;
	            double weight = e.weight;
	            double distanceThroughU = u.minDistance + weight;
			    if (distanceThroughU < v.minDistance) {
			       vertexQueue.remove(v);
		
			       v.minDistance = distanceThroughU ;
			       v.previous = u;
			       vertexQueue.add(v);
			    }
	        }
	     }
		/*No aberto = new No(estadoInicial, null, null);
		fronteira.insere(aberto);
		
		while(!fronteira.vazio()) {
			aberto = fronteira.retiraProximo();
			
			if(aberto.estado.terminal()) {
				return aberto;
			} else for(Acao acao : Acao.values()) {
				Estado vizinho = acao.aplica(aberto);
				if(vizinho != null) {
					No filho = new No(vizinho,aberto,acao);
					fronteira.insere(filho);
				}
			}
		}*/
		
	return null;
	}
	
    public static List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }
	
	
	/**
	 * Resolve o problema com base em busca, realizando
	 * o backtracking após chegar ao estado meta
	 * 
	 * @return O custo da solução encontrada
	 */
	public double resolver() {
		
		No destino = buscar();
		if(destino == null) {
			this.solucao = null;
			this.custoSolucao = 0;
			return 0;
		}
		
		No atual = destino;
		Deque<Acao> caminho = new LinkedList<Acao>();
		//while(atual.acao != null) {
			//caminho.addFirst(atual.acao);
			//atual = atual.pai;
		//}
		
		this.solucao = (Acao[]) caminho.toArray(new Acao[caminho.size()]);
		this.custoSolucao = destino.getCusto();
		return this.custoSolucao;
	}
}
