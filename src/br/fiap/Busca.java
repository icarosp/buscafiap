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
    public final String nome;
    public Edge[] vizinhos;
    public double distanciaMinima = Double.POSITIVE_INFINITY;
    public Vertex anterior;
    public Vertex(String noNome) { nome = noNome; }
    public String toString() { return nome; }
    public int compareTo(Vertex o)
    {
        return Double.compare(distanciaMinima, o.distanciaMinima);
    }
}

/*Classe que defines os Arcos*/
class Edge
{
   public final Vertex noDestino;
   public final double arcCusto;
   public Edge(Vertex noDestino, double arcCusto)
   { this.noDestino = noDestino; this.arcCusto = arcCusto; }
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
	private Vertex _belm;
	private Vertex _sant;
	private Vertex _irac;
	private Vertex _flor;
	private Vertex _rome;
	private Vertex _cunh;
	private Vertex _mara;
	private Vertex _tigr;
	private Vertex _bomj;
	private Vertex _serr;
	private Vertex _mode;
	private Vertex _salt;
	private Vertex _camp;
	private Vertex _saob;
	private Vertex _palm;
	private Vertex _anch;
	
	public Busca(){

		_dion = new Vertex("Dionisio Cerqueira");
		_guaruja = new Vertex("Guaruja do Sul");
		_saoj = new Vertex("Sao Jose do Cedro");
		_prin = new Vertex("Princesa");
		_guaraciba = new Vertex("Guaraciba");
		_saom = new Vertex("Sao Miguel do Oeste");
		_para = new Vertex("Paraiso");
		_desc = new Vertex("Descanso");
		_belm = new Vertex("Belmonte");
		_sant = new Vertex("Santa Helena");
		_irac = new Vertex("Iraceminha");
		_flor = new Vertex("Flor do Sertao");
		_rome = new Vertex("Romelandia");
		_cunh = new Vertex("Cunha Pora");
		_mara = new Vertex("Maravilha");
		_tigr = new Vertex("Tigrinhos");
		_bomj = new Vertex("Bom Jesus do Oeste");
		_serr = new Vertex("Serra Alta");
		_mode = new Vertex("Modelo");
		_salt = new Vertex("Saltinho");
		_camp = new Vertex("Campos Ere");
		_saob = new Vertex("Sao Bernardino");
		_palm = new Vertex("Palma Sola");
		_anch = new Vertex("Anchieta");
		
		//relacoes de divida/vizinhos
		_dion.vizinhos = new Edge[]{ new Edge(_guaruja, 23),new Edge(_palm, 50.9)};
		_guaruja.vizinhos = new Edge[]{ new Edge(_dion, 23), new Edge(_saoj, 17.5)};
		_saoj.vizinhos = new Edge[]{ new Edge(_guaruja, 17.5), new Edge(_prin, 11.7), new Edge(_guaraciba, 15.2)};
		_prin.vizinhos = new Edge[]{ new Edge(_saoj, 11.7)};
		_guaraciba.vizinhos = new Edge[]{ new Edge(_saoj, 15.2),new Edge(_saom, 22.7),new Edge(_anch, 38.1)};
		_saom.vizinhos = new Edge[]{ new Edge(_guaraciba, 22.7), new Edge(_para, 29.5), new Edge(_desc, 16.7)};
		_para.vizinhos = new Edge[]{ new Edge(_saom, 29.5)};
		_desc.vizinhos = new Edge[]{ new Edge(_saom, 16.7),new Edge(_belm, 18.7), new Edge(_irac, 26.4)};
		_belm.vizinhos = new Edge[]{ new Edge(_desc, 18.7), new Edge(_sant, 13.7)};
		_sant.vizinhos = new Edge[]{ new Edge(_belm, 13.7)};
		_irac.vizinhos = new Edge[]{ new Edge(_desc, 26.4),new Edge(_flor, 14),new Edge(_cunh, 36.4),new Edge(_mara, 23.7)};
		_flor.vizinhos = new Edge[]{ new Edge(_irac, 14),new Edge(_rome, 25.4)};
		_rome.vizinhos = new Edge[]{ new Edge(_flor, 25.4)};
		_cunh.vizinhos = new Edge[]{ new Edge(_irac, 36.4),new Edge(_mara, 18.3)};
		_mara.vizinhos = new Edge[]{ new Edge(_cunh, 18.3), new Edge(_irac, 23.7), new Edge(_tigr, 12.6)};
		_tigr.vizinhos = new Edge[]{ new Edge(_mara, 12.6),new Edge(_bomj, 9.6)};
		_bomj.vizinhos = new Edge[]{ new Edge(_tigr, 9.6),new Edge(_serr, 14.6)};
		_serr.vizinhos = new Edge[]{ new Edge(_bomj, 14.6),new Edge(_mode, 12), new Edge(_salt, 18.5)};
		_mode.vizinhos = new Edge[]{ new Edge(_serr, 12)};
		_salt.vizinhos = new Edge[]{ new Edge(_serr, 18.5),new Edge(_camp, 39.3)};
		_camp.vizinhos = new Edge[]{ new Edge(_salt, 39.3),new Edge(_saob, 38.2), new Edge(_anch, 28.9),new Edge(_palm, 34)};
		_saob.vizinhos = new Edge[]{ new Edge(_camp, 38.2)};
		_anch.vizinhos = new Edge[]{ new Edge(_camp, 28.9),new Edge(_palm, 29.7),new Edge(_guaraciba, 38.1)};
		_palm.vizinhos = new Edge[]{ new Edge(_camp, 34),new Edge(_anch, 29.7),new Edge(_dion, 50.9)};
	}
	
	public List<Vertex> getSolucao() {
		return _rota;
	}
	
	public double getCustoSolucao() {
		return _fim.distanciaMinima;
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
			case "Belmonte":
				if(r.equals(Rota.INICIO))
					_inicio = _belm;
				else
					_fim = _belm;
				break;
			case "Santa Helena":
				if(r.equals(Rota.INICIO))
					_inicio = _sant;
				else
					_fim = _sant;
				break;
			case "Iraceminha":
				if(r.equals(Rota.INICIO))
					_inicio = _irac;
				else
					_fim = _irac;
				break;
			case "Flor do Sertao":
				if(r.equals(Rota.INICIO))
					_inicio = _flor;
				else
					_fim = _flor;
				break;
			case "Romelandia":
				if(r.equals(Rota.INICIO))
					_inicio = _rome;
				else
					_fim = _rome;
				break;
			case "Cunha Pora":
				if(r.equals(Rota.INICIO))
					_inicio = _cunh;
				else
					_fim = _cunh;
				break;
			case "Maravilha":
				if(r.equals(Rota.INICIO))
					_inicio = _mara;
				else
					_fim = _mara;
				break;
			case "Tigrinhos":
				if(r.equals(Rota.INICIO))
					_inicio = _tigr;
				else
					_fim = _tigr;
				break;
			case "Bom Jesus do Oeste":
				if(r.equals(Rota.INICIO))
					_inicio = _bomj;
				else
					_fim = _bomj;
				break;
			case "Serra Alta":
				if(r.equals(Rota.INICIO))
					_inicio = _serr;
				else
					_fim = _serr;
				break;
			case "Modelo":
				if(r.equals(Rota.INICIO))
					_inicio = _mode;
				else
					_fim = _mode;
				break;
			case "Saltinho":
				if(r.equals(Rota.INICIO))
					_inicio = _salt;
				else
					_fim = _salt;
				break;
			case "Campos Ere":
				if(r.equals(Rota.INICIO))
					_inicio = _camp;
				else
					_fim = _camp;
				break;
			case "Sao Bernardino":
				if(r.equals(Rota.INICIO))
					_inicio = _saob;
				else
					_fim = _saob;
				break;
			case "Anchieta":
				if(r.equals(Rota.INICIO))
					_inicio = _anch;
				else
					_fim = _anch;
				break;
			case "Palma Sola":
				if(r.equals(Rota.INICIO))
					_inicio = _palm;
				else
					_fim = _palm;
				break;
			default:
				throw new Exception("Cidade: "+cidade+ " n�o cadastrada! Imposs�vel definir "+r.toString()+".");
		}
	}

	private void buscar() {
		_inicio.distanciaMinima = 0.;
	    
		PriorityQueue<Vertex> filaDeNos = new PriorityQueue<Vertex>();
		filaDeNos.add(_inicio);

	    while (!filaDeNos.isEmpty()) {
	        Vertex u = filaDeNos.poll();

	            // Visit each edge exiting u
	        for (Edge e : u.vizinhos){
	            Vertex v = e.noDestino;
	            double custo = e.arcCusto;
	            double distanceThroughU = u.distanciaMinima + custo;
			    if (distanceThroughU < v.distanciaMinima) {
			    	filaDeNos.remove(v);
		
			       v.distanciaMinima = distanceThroughU ;
			       v.anterior = u;
			       filaDeNos.add(v);
			    }
	        }
	     }
	}
	
    public static List<Vertex> encontrarMenorCaminhoParaNo(Vertex fim)
    {
        List<Vertex> caminho = new ArrayList<Vertex>();
        for (Vertex no = fim; no != null; no = no.anterior)
        	caminho.add(no);

        Collections.reverse(caminho);
        return caminho;
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
        _rota = encontrarMenorCaminhoParaNo(_fim);
		return getCustoSolucao();
	}
}
