public abstract class Conta {
    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente titular;

public Conta(int agencia, int numero, Cliente titular) {
        this.agencia = agencia;
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0.0;
}

public void sacar(double valor) {
    saldo -= valor;
}

public void depositar(double valor) {
    saldo += valor;
}

public void transferir(double valor, Conta destino) {
    this.sacar(valor);
    destino.depositar(valor);
}

public int getAgencia() {
        return agencia;
}

public int getNumero() {
        return numero;
}

public double getSaldo() {
        return saldo;
}

protected void imprimirInfosComuns() {
        System.out.println("Titular: " + titular.getNome());
        System.out.println("Agência: " + agencia);
        System.out.println("Número: " + numero);
        System.out.printf("Saldo: R$ %.2f\n", saldo);
}

public abstract void imprimirExtrato();
}