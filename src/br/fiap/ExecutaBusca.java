package br.fiap;

public class ExecutaBusca {

	public static void main(String[] args) {
		
		Busca busca = new Busca();
		
		//Digitar nome das cidades sem com letras Maiusculas e Minusculas e sem acento
		String comeco =	"Sao Bernardino";
		String fim = "Santa Helena";
		
		try{
			busca.setEstadoInicial(comeco);
			busca.resolver(fim);
			
			if(busca.getSolucao() == null) {
				System.out.println("Nao ha solucao para o problema");
			} else {
				
				System.out.println("Rota da solucao encontrada com "+busca.getCustoSolucao()+" KM: ");

				for(Vertex v : busca.getSolucao()) {
					if(v.nome.equals(fim)){
						System.out.println("Destino final alcançado. Cidade "+v.nome);
					}
					else if(v.nome.equals(comeco)){
						System.out.println("Inicio na cidade "+v.nome);
					}
					else
						System.out.println("Movendo-se para cidade "+v.nome);
				}
			}
			
		}
		catch(Exception ex){
			System.out.println("Erro: "+ ex.toString());
		}
	}

}
