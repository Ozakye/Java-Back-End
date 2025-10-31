package strategy;

public class FreteTransportadora implements CalculadoraFrete {
    @Override
    public double calcular(double peso) {
        return peso * 10.0;
    }
}