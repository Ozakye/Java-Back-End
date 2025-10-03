package facade;

import singleton.ConfigManager;
import strategy.CalculadoraFrete;

public class LojaFacade {
    private final CalculadoraFrete frete;

    public LojaFacade(CalculadoraFrete frete) {
        this.frete = frete;
    }

    public void realizarCompra(String produto, double peso) {
        double valorFrete = frete.calcular(peso);
        String freteFormatado = String.format("%.2f", valorFrete);
        String ambiente = ConfigManager.getInstance().getAmbiente();

        System.out.println("======================================");
        System.out.println("      DETALHES DA COMPRA");
        System.out.println("======================================");
        System.out.println("Ambiente: " + ambiente);
        System.out.println("Produto: " + produto);
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Frete: R$ " + freteFormatado);
        System.out.println("Obrigado pela compra!");
        System.out.println("======================================\n");
    }
}