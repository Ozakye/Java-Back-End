import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Conta> contas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean executando = true;

        while (executando) {
            System.out.println("\n=== Banco Digital ===");
            System.out.println("1 - Adicionar conta");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Transferir");
            System.out.println("5 - Imprimir extrato");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = lerInt("");

            switch (opcao) {
                case 1:
                    adicionarConta();
                    break;
                case 2:
                    depositar();
                    break;
                case 3:
                    sacar();
                    break;
                case 4:
                    transferir();
                    break;
                case 5:
                    imprimirExtrato();
                    break;
                case 6:
                    executando = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }

    private static void adicionarConta() {
        System.out.print("Nome do titular: ");
        String nome = scanner.nextLine();

        int tipo = lerInt("Tipo da conta (1 - Corrente, 2 - Poupança): ");
        int agencia = lerInt("Número da agência: ");
        int numero = lerInt("Número da conta: ");

        if (contaExiste(agencia, numero)) {
            System.out.println("Já existe uma conta com essa agência e número!");
            return;
        }

        Cliente cliente = new Cliente();
        cliente.setNome(nome);

        Conta novaConta;
        if (tipo == 1) {
            novaConta = new ContaCorrente(agencia, numero, cliente);
        } else if (tipo == 2) {
            novaConta = new ContaPoupanca(agencia, numero, cliente);
        } else {
            System.out.println("Tipo de conta inválido!");
            return;
        }

        contas.add(novaConta);
        System.out.println("Conta criada com sucesso!");
    }

    private static boolean contaExiste(int agencia, int numero) {
        for (Conta c : contas) {
            if (c.getAgencia() == agencia && c.getNumero() == numero) {
                return true;
            }
        }
        return false;
    }

    private static Conta buscarConta(int agencia, int numero) {
        for (Conta c : contas) {
            if (c.getAgencia() == agencia && c.getNumero() == numero) {
                return c;
            }
        }
        return null;
    }

    private static void depositar() {
        Conta conta = solicitarConta();
        if (conta == null) return;

        double valor = lerDouble("Valor para depositar (use ponto para decimal): ");

        conta.depositar(valor);
        System.out.println("Depósito realizado.");
    }

    private static void sacar() {
        Conta conta = solicitarConta();
        if (conta == null) return;

        double valor = lerDouble("Valor para sacar (use ponto para decimal): ");

        if (conta.getSaldo() < valor) {
            System.out.println("Saldo insuficiente!");
            return;
        }

        conta.sacar(valor);
        System.out.println("Saque realizado.");
    }

    private static void transferir() {
        System.out.println("Conta de origem:");
        Conta origem = solicitarConta();
        if (origem == null) return;

        System.out.println("Conta de destino:");
        Conta destino = solicitarConta();
        if (destino == null) return;

        double valor = lerDouble("Valor para transferir (use ponto para decimal): ");

        if (origem.getSaldo() < valor) {
            System.out.println("Saldo insuficiente na conta de origem!");
            return;
        }

        origem.transferir(valor, destino);
        System.out.println("Transferência realizada.");
    }

    private static void imprimirExtrato() {
        Conta conta = solicitarConta();
        if (conta == null) return;

        conta.imprimirExtrato();
    }

    private static Conta solicitarConta() {
        int agencia = lerInt("Número da agência: ");
        int numero = lerInt("Número da conta: ");

        Conta conta = buscarConta(agencia, numero);

        if (conta == null) {
            System.out.println("Conta não encontrada!");
        }

        return conta;
    }

    // Métodos para ler int e double com validação para evitar InputMismatchException
    private static int lerInt(String mensagem) {
        int valor;
        while (true) {
            if (!mensagem.isEmpty()) {
                System.out.print(mensagem);
            }
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                scanner.nextLine(); // consumir o resto da linha
                break;
            } else {
                System.out.println("Entrada inválida! Digite um número inteiro.");
                scanner.nextLine(); // descartar entrada errada
            }
        }
        return valor;
    }

    private static double lerDouble(String mensagem) {
        double valor;
        while (true) {
            if (!mensagem.isEmpty()) {
                System.out.print(mensagem);
            }
            if (scanner.hasNextDouble()) {
                valor = scanner.nextDouble();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Entrada inválida! Digite um número decimal válido (use ponto ao invés de vírgula).");
                scanner.nextLine();
            }
        }
        return valor;
    }
}