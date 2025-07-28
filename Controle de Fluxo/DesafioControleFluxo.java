import java.util.Scanner;

public class DesafioControleFluxo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o primeiro parâmetro:");
        int parametroUm = scanner.nextInt();

        System.out.println("Digite o segundo parâmetro:");
        int parametroDois = scanner.nextInt();

        try {
            Contador.contar(parametroUm, parametroDois);    
        } catch (ParametrosInvalidosException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}