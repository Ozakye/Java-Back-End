import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);

    public void mostrarMenu(Sudoku jogo) {
        System.out.println("1. Iniciar Jogo");
        System.out.println("2. Continuar Jogo");
        System.out.println("3. Novo Jogo");
        System.out.println("4. Tutorial");
        System.out.println("5. Sair");

        int opc = jogo.lerInteiroValido("Escolha uma opção: ", 1, 5);

        switch (opc) {
            case 1:
                iniciarJogo(jogo);
                break;
            case 2:
                System.out.println("Opção de continuar jogo não implementada.");
                break;
            case 3:
                novoJogo(jogo);
                break;
            case 4:
                mostrarTutorial();
                break;
            case 5:
                System.out.println("Saindo...");
                System.exit(0);
                break;
        }
    }

    public void iniciarJogo(Sudoku jogo) {
        System.out.println("Escolha dificuldade: 1. Fácil 2. Médio 3. Difícil");
        int dificuldade = jogo.lerInteiroValido("Opção: ", 1, 3);
        jogo.iniciarJogo(dificuldade);
    }

    public void novoJogo(Sudoku jogo) {
        System.out.println("Novo jogo iniciado.");
        iniciarJogo(jogo);
    }

    public void mostrarTutorial() {
        System.out.println("Tutorial:");
        System.out.println("Sudoku é um jogo de lógica onde você deve preencher os números de 1 a 9 nas células");
        System.out.println("de modo que cada linha, cada coluna e cada bloco 3x3 contenham todos os números de 1 a 9, sem repetição.");
        System.out.println("Para inserir um número, informe a linha, coluna e o número a ser colocado.");
    }
}