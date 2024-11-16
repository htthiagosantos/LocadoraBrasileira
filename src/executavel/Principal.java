package executavel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.AluguelDeCarro;
import model.Veiculo;
import servicos.ServicoTaxaBrasil;
import servicos.ServicosDeAluguel;


public class Principal {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel");
		
		System.out.print("Modelo do carro: ");
		String modeloCarro = sc.nextLine();
		
		System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
		LocalDateTime star = LocalDateTime.parse(sc.nextLine(), fmt);
		
		System.out.print("Retorno (dd/MM/yyyy hh:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
		
		AluguelDeCarro cr = new AluguelDeCarro(star, finish, new Veiculo(modeloCarro));
		
		
		System.out.print("Entre com o preço por hora: ");
		double precoPorHora = sc.nextDouble();
		System.out.print("Entre com o preço por dia: ");
		double precoPorDia = sc.nextDouble();
		
		ServicosDeAluguel aluguelDeServico = new ServicosDeAluguel(precoPorHora, precoPorDia, new ServicoTaxaBrasil());
		
		aluguelDeServico.processarFatura(cr);
		
		System.out.println("FATURA: ");
		System.out.println("Pagamento basico: " + String.format("%.2f", cr.getFatura().getPagamentoBasico()));
		System.out.println("Imposto: " + String.format("%.2f", cr.getFatura().getTaxa()));
		System.out.println("Pagamento total: " + String.format("%.2f", cr.getFatura().pagamentoTotal()));
		
		sc.close();
		
	}	
}
		

 

