package strategy;

public class FreteSedex implements CalculadoraFrete {
    @Override
    public double calcular(double peso) {
        return peso * 12.0;
    }
}