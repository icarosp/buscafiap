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
    public Edge[] vizinhos;
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

enum Rota{
	INICIO,
	FIM;
}

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
	
	//n� inicial
	private Vertex _inicio;
	//n� final
	private Vertex _fim;
	//rota
	private List<Vertex> _rota = new ArrayList<Vertex>();
	
	private Vertex _dion;
	private Vertex _guaruja;
	private Vertex _saoj;
	private Vertex _prin;
	private Vertex _guaraciba;
	private Vertex _saom;
	private Vertex _para;
	private Vertex _desc;
	
	public Busca(){

		_dion = new Vertex("Dionisio Cerqueira");
		_guaruja = new Vertex("Guaruja do Sul");
		_saoj = new Vertex("Sao Jose do Cedro");
		_prin = new Vertex("Princesa");
		_guaraciba = new Vertex("Guaraciba");
		_saom = new Vertex("Sao Miguel do Oeste");
		_para = new Vertex("Paraiso");
		_desc = new Vertex("Descanso");
		
		//relacoes de divida/vizinhos
		_dion.vizinhos = new Edge[]{ new Edge(_guaruja, 23)};
		_guaruja.vizinhos = new Edge[]{ new Edge(_dion, 23), new Edge(_saoj, 17.5)};
		_saoj.vizinhos = new Edge[]{ new Edge(_guaruja, 17.5), new Edge(_prin, 11.7), new Edge(_guaraciba, 15.2)};
		_prin.vizinhos = new Edge[]{ new Edge(_saoj, 11.7)};
		_guaraciba.vizinhos = new Edge[]{ new Edge(_saoj, 15.2),new Edge(_saom, 22.7)};
		_saom.vizinhos = new Edge[]{ new Edge(_guaraciba, 22.7), new Edge(_para, 29.5), new Edge(_desc, 16.7)};
		_para.vizinhos = new Edge[]{ new Edge(_saom, 29.5)};
		_desc.vizinhos = new Edge[]{ new Edge(_saom, 16.7)};
		
	}
	
	public List<Vertex> getSolucao() {
		return _rota;
	}
	
	public double getCustoSolucao() {
		return _fim.minDistance;
	}
	
	public void setEstadoInicial(String cidadeInicial) throws Exception{
		setVertice(Rota.INICIO,cidadeInicial);
	}
	
	private void setVertice(Rota r, String cidade) throws Exception{
		switch(cidade){
			case "Dionisio Cerqueira":
				if(r.equals(Rota.INICIO))
					_inicio = _dion;
				else
					_fim = _dion;
				break;
			case "Guaruja do Sul":
				if(r.equals(Rota.INICIO))
					_inicio = _guaruja;
				else
					_fim = _guaruja;
				break;
			case "Sao Jose do Cedro":
				if(r.equals(Rota.INICIO))
					_inicio = _saoj;
				else
					_fim = _saoj;
				break;
			case "Princesa":
				if(r.equals(Rota.INICIO))
					_inicio = _prin;
				else
					_fim = _prin;
				break;
			case "Guaraciba":
				if(r.equals(Rota.INICIO))
					_inicio = _guaraciba;
				else
					_fim = _guaraciba;
				break;
			case "Sao Miguel do Oeste":
				if(r.equals(Rota.INICIO))
					_inicio = _saom;
				else
					_fim = _saom;
				break;
			case "Paraiso":
				if(r.equals(Rota.INICIO))
					_inicio = _para;
				else
					_fim = _para;
				break;
			case "Descanso":
				if(r.equals(Rota.INICIO))
					_inicio = _desc;
				else
					_fim = _desc;
				break;
			default:
				throw new Exception("Cidade: "+cidade+ " n�o cadastrada! Imposs�vel definir "+r.toString()+".");
		}
	}
	
	
	private void computePaths()
    {
		
    }

	private void buscar() {
_inicio.minDistance = 0.;
	    
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
	    vertexQueue.add(_inicio);

	    while (!vertexQueue.isEmpty()) {
	        Vertex u = vertexQueue.poll();

	            // Visit each edge exiting u
	        for (Edge e : u.vizinhos){
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

		
		
		//Vertex source = new Vertex("Palma Sola");
		

	    
        //System.out.println("Distance to " + Z + ": " + Z.minDistance);
        //List<Vertex> path = getShortestPathTo(Z);
        //System.out.println("Path: " + path);
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
        

		
        //return null;
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
	public double resolver(String cidadeDestino) throws Exception {
		setVertice(Rota.FIM,cidadeDestino);
		buscar(); //Executa busca
        _rota = getShortestPathTo(_fim);
		return getCustoSolucao();
	}
}
