package br.fiap;

public class ExecutaBusca {
	
	
	//PROJETO 4º SIR
	//GRUPO:
	//ICARO GUSELIAN RM 66694
	//LARISSA MYAMOTO RM 71388
	//OTAVIO EIJI RM 71188
	//EDUARDO BATISTA 71792
	

	public static void main(String[] args) {
		
		Busca busca = new Busca();
		
		//Digitar nome das cidades sem com letras Maiusculas e Minusculas e sem acento
		String comeco =	"Campos Ere";
		String fim = "Descanso";
		
		try{
			busca.setEstadoInicial(comeco);
			busca.resolver(fim);
			
			if(busca.getSolucao() == null) {
				System.out.println("Nao ha solucao para o problema");
			} else {
				
				System.out.println("Rota da solucao encontrada com "+busca.getCustoSolucao()+" KM: ");

				for(No n : busca.getSolucao()) {
					if(n.nome.equals(fim)){
						System.out.println("Destino final alcançado. Cidade "+n.nome);
					}
					else if(n.nome.equals(comeco)){
						System.out.println("Inicio na cidade "+n.nome);
					}
					else
						System.out.println("Movendo-se para cidade "+n.nome);
				}
			}
			
		}
		catch(Exception ex){
			System.out.println("Erro: "+ ex.toString());
		}
	}

}
