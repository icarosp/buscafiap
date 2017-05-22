package br.fiap;

public class ExecutaBusca {

	public static void main(String[] args) {
		
		Busca busca = new Busca();
		
		//Digitar nome das cidades sem com letras Maiusculas e Minusculas e sem acento
		String comeco =	"Dionisio Cerqueira";
		String fim = "Palma Sola";
		
		try{
			busca.setEstadoInicial(comeco);
			busca.resolver(fim);
			
			if(busca.getSolucao() == null) {
				System.out.println("Nao ha solucao para o problema");
			} else {
				
				System.out.println("Rota da solucao encontrada com "+busca.getCustoSolucao()+" KM: ");

				for(Vertex v : busca.getSolucao()) {
					if(v.name.equals(fim)){
						System.out.println("Destino final alcançado. Cidade "+v.name);
					}
					else if(v.name.equals(comeco)){
						System.out.println("Inicio na cidade "+v.name);
					}
					else
						System.out.println("Movendo-se para cidade "+v.name);
				}
			}
			
		}
		catch(Exception ex){
			System.out.println("Erro: "+ ex.toString());
		}
		 

		
		/*if(busca.getSolucao() == null) {
			System.out.println("Nao ha solucao para o problema");
		} else {
			System.out.println("A solucao: ");
			for(Acao a : busca.getSolucao()) {
				System.out.println(a);
			}
		}*/

	}

}
