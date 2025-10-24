package strategy;

public class FretePAC implements CalculadoraFrete {
    @Override
    public double calcular(double peso) {
        return peso * 8.0;
    }
}