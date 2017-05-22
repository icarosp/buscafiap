package br.fiap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*Classe que defines os Nos*/
class No implements Comparable<No>
{
    public final String nome;
    public Arco[] vizinhos;
    public double distanciaMinima = Double.POSITIVE_INFINITY;
    public No anterior;
    public No(String noNome) { nome = noNome; }
    public String toString() { return nome; }
    public int compareTo(No o)
    {
        return Double.compare(distanciaMinima, o.distanciaMinima);
    }
}

/*Classe que defines os Arcos*/
class Arco
{
   public final No noDestino;
   public final double arcCusto;
   public Arco(No noDestino, double arcCusto)
   { this.noDestino = noDestino; this.arcCusto = arcCusto; }
}

enum Rota{
	INICIO,
	FIM;
}

/**
 * Classe que implementa o algoritmo de busca
 * @author Icaro
 *
 */
public class Busca {
	
	//n� inicial
	private No _inicio;
	//n� final
	private No _fim;
	//rota
	private List<No> _rota = new ArrayList<No>();
	
	private No _dion;
	private No _guaruja;
	private No _saoj;
	private No _prin;
	private No _guaraciba;
	private No _saom;
	private No _para;
	private No _desc;
	private No _belm;
	private No _sant;
	private No _irac;
	private No _flor;
	private No _rome;
	private No _cunh;
	private No _mara;
	private No _tigr;
	private No _bomj;
	private No _serr;
	private No _mode;
	private No _salt;
	private No _camp;
	private No _saob;
	private No _palm;
	private No _anch;
	
	public Busca(){

		_dion = new No("Dionisio Cerqueira");
		_guaruja = new No("Guaruja do Sul");
		_saoj = new No("Sao Jose do Cedro");
		_prin = new No("Princesa");
		_guaraciba = new No("Guaraciba");
		_saom = new No("Sao Miguel do Oeste");
		_para = new No("Paraiso");
		_desc = new No("Descanso");
		_belm = new No("Belmonte");
		_sant = new No("Santa Helena");
		_irac = new No("Iraceminha");
		_flor = new No("Flor do Sertao");
		_rome = new No("Romelandia");
		_cunh = new No("Cunha Pora");
		_mara = new No("Maravilha");
		_tigr = new No("Tigrinhos");
		_bomj = new No("Bom Jesus do Oeste");
		_serr = new No("Serra Alta");
		_mode = new No("Modelo");
		_salt = new No("Saltinho");
		_camp = new No("Campos Ere");
		_saob = new No("Sao Bernardino");
		_palm = new No("Palma Sola");
		_anch = new No("Anchieta");
		
		//relacoes de divida/vizinhos
		_dion.vizinhos = new Arco[]{ new Arco(_guaruja, 23),new Arco(_palm, 50.9)};
		_guaruja.vizinhos = new Arco[]{ new Arco(_dion, 23), new Arco(_saoj, 17.5)};
		_saoj.vizinhos = new Arco[]{ new Arco(_guaruja, 17.5), new Arco(_prin, 11.7), new Arco(_guaraciba, 15.2)};
		_prin.vizinhos = new Arco[]{ new Arco(_saoj, 11.7)};
		_guaraciba.vizinhos = new Arco[]{ new Arco(_saoj, 15.2),new Arco(_saom, 22.7),new Arco(_anch, 38.1)};
		_saom.vizinhos = new Arco[]{ new Arco(_guaraciba, 22.7), new Arco(_para, 29.5), new Arco(_desc, 16.7)};
		_para.vizinhos = new Arco[]{ new Arco(_saom, 29.5)};
		_desc.vizinhos = new Arco[]{ new Arco(_saom, 16.7),new Arco(_belm, 18.7), new Arco(_irac, 26.4)};
		_belm.vizinhos = new Arco[]{ new Arco(_desc, 18.7), new Arco(_sant, 13.7)};
		_sant.vizinhos = new Arco[]{ new Arco(_belm, 13.7)};
		_irac.vizinhos = new Arco[]{ new Arco(_desc, 26.4),new Arco(_flor, 14),new Arco(_cunh, 36.4),new Arco(_mara, 23.7)};
		_flor.vizinhos = new Arco[]{ new Arco(_irac, 14),new Arco(_rome, 25.4)};
		_rome.vizinhos = new Arco[]{ new Arco(_flor, 25.4)};
		_cunh.vizinhos = new Arco[]{ new Arco(_irac, 36.4),new Arco(_mara, 18.3)};
		_mara.vizinhos = new Arco[]{ new Arco(_cunh, 18.3), new Arco(_irac, 23.7), new Arco(_tigr, 12.6)};
		_tigr.vizinhos = new Arco[]{ new Arco(_mara, 12.6),new Arco(_bomj, 9.6)};
		_bomj.vizinhos = new Arco[]{ new Arco(_tigr, 9.6),new Arco(_serr, 14.6)};
		_serr.vizinhos = new Arco[]{ new Arco(_bomj, 14.6),new Arco(_mode, 12), new Arco(_salt, 18.5)};
		_mode.vizinhos = new Arco[]{ new Arco(_serr, 12)};
		_salt.vizinhos = new Arco[]{ new Arco(_serr, 18.5),new Arco(_camp, 39.3)};
		_camp.vizinhos = new Arco[]{ new Arco(_salt, 39.3),new Arco(_saob, 38.2), new Arco(_anch, 28.9),new Arco(_palm, 34)};
		_saob.vizinhos = new Arco[]{ new Arco(_camp, 38.2)};
		_anch.vizinhos = new Arco[]{ new Arco(_camp, 28.9),new Arco(_palm, 29.7),new Arco(_guaraciba, 38.1)};
		_palm.vizinhos = new Arco[]{ new Arco(_camp, 34),new Arco(_anch, 29.7),new Arco(_dion, 50.9)};
	}
	
	public List<No> getSolucao() {
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
	    
		PriorityQueue<No> filaDeNos = new PriorityQueue<No>();
		filaDeNos.add(_inicio);

	    while (!filaDeNos.isEmpty()) {
	        No u = filaDeNos.poll();

	            // Visit each Arco exiting u
	        for (Arco e : u.vizinhos){
	            No v = e.noDestino;
	            double custo = e.arcCusto;
	            double distanciaNosVizinho = u.distanciaMinima + custo;
			    if (distanciaNosVizinho < v.distanciaMinima) {
			    	filaDeNos.remove(v);
		
			       v.distanciaMinima = distanciaNosVizinho ;
			       v.anterior = u;
			       filaDeNos.add(v);
			    }
	        }
	     }
	}
	
    public static List<No> encontrarMenorCaminhoParaNo(No fim)
    {
        List<No> caminho = new ArrayList<No>();
        for (No no = fim; no != null; no = no.anterior)
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
