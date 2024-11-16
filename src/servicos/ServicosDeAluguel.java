package servicos;

import java.time.Duration;

import model.AluguelDeCarro;
import model.Fatura;

public class ServicosDeAluguel {

	private Double precoPorDia;
	private Double precoPorHora;	
	
	private TaxaServico taxaServicoBrasil;
	
	public ServicosDeAluguel(Double precoPorDia, Double precoPorHora, TaxaServico taxaServicoBrasil) {
		this.precoPorDia = precoPorDia;
		this.precoPorHora = precoPorHora;
		this.taxaServicoBrasil = taxaServicoBrasil;
	}
	
	public void processarFatura(AluguelDeCarro aluguelDeCarro) {
		
		double minutos = Duration.between(aluguelDeCarro.getStart(), aluguelDeCarro.getFinish()).toMinutes();
		double horas = minutos / 60.0;
		
		double pagamentoBasico;
		if(horas <= 12.0) {
			pagamentoBasico = precoPorHora * Math.ceil(horas);
		}
		else {
			pagamentoBasico = precoPorDia * Math.ceil(horas / 24.0);
		}
		
		double taxa = taxaServicoBrasil.taxa(pagamentoBasico);
		
		aluguelDeCarro.setFatura(new Fatura(pagamentoBasico, taxa));
	}
	

	

	public Double getPrecoPorDia() {
		return precoPorDia;
	}

	public void setPrecoPorDia(Double precoPorDia) {
		this.precoPorDia = precoPorDia;
	}

	public Double getPrecoPorHora() {
		return precoPorHora;
	}

	public void setPrecoPorHora(Double precoPorHora) {
		this.precoPorHora = precoPorHora;
	}

	public void setTaxaServicoBrasil(ServicoTaxaBrasil taxaServicoBrasil) {
		this.taxaServicoBrasil = taxaServicoBrasil;
	}
	
	public void processoFatura(AluguelDeCarro aluguelDeCarro) {
		
		aluguelDeCarro.setFatura(new Fatura(50.0, 10.0));
	}
	
}
