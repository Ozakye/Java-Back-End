public class ContaTerminal {
    private int numero;
    private String agencia;
    private String nomeCliente;
    private double saldo;

    public ContaTerminal(int numero, String agencia, String nomeCliente, double saldo) {
        this.numero = numero;
        this.agencia = agencia;
        this.nomeCliente = formatarNome(nomeCliente);
        this.saldo = saldo;
    }

    public void exibirMensagem() {
        System.out.printf("Olá %s, obrigado por criar uma conta em nosso banco, sua agência é %s, conta %d e seu saldo %.2f já está disponível para saque.%n", nomeCliente, agencia, numero, saldo);
    }

    private String formatarNome(String nome) {
        String[] palavras = nome.trim().split("\\s+");
        StringBuilder nomeFormatado = new StringBuilder();

        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                String primeiraLetra = palavra.substring(0, 1).toUpperCase();
                String restante = palavra.length() > 1 ? palavra.substring(1).toLowerCase() : "";
                nomeFormatado.append(primeiraLetra).append(restante).append(" ");
            }
        }

        return nomeFormatado.toString().trim();
    }
}