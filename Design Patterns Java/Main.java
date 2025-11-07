import facade.LojaFacade;
import singleton.ConfigManager;
import strategy.*;

public class Main {
    public static void main(String[] args) {
        // Configurando o ambiente com Singleton
        ConfigManager.getInstance().setAmbiente("Homologação");

        //Estratégia de frete (Strategy)
        CalculadoraFrete freteSedex = new FreteSedex();
        CalculadoraFrete fretePAC = new FretePAC();

        // Facade simplificando a compra
        LojaFacade lojaSedex = new LojaFacade(freteSedex);
        lojaSedex.realizarCompra("Notebook", 2.5);

        LojaFacade lojaPAC = new LojaFacade(fretePAC);
        lojaPAC.realizarCompra("Celular", 1.0);
    }
}