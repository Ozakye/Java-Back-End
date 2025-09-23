import java.util.*;

public class Sudoku {
    // Matrizes para o tabuleiro
    int[][] fixed = new int[9][9];    // números fixos
    int[][] user = new int[9][9];     // números inseridos pelo usuário
    int[][] solution = new int[9][9]; // solução correta do sudoku

    Scanner sc = new Scanner(System.in);

    // Método para validar a entrada inteira e numérica
    int lerInteiroValido(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                int val = Integer.parseInt(line);
                if (val < min || val > max) {
                    System.out.println("Erro: valor deve estar entre " + min + " e " + max);
                    continue;
                }
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Erro: por favor, insira um número inteiro válido.");
            }
        }
    }

    // Método para exibir o tabuleiro
    void exibirTabuleiro() {
        System.out.println("   1 2 3   4 5 6   7 8 9");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) System.out.println("  +-------+-------+-------+");
            System.out.print((i+1) + " |");
            for (int j = 0; j < 9; j++) {
                int val = (user[i][j] != 0 ? user[i][j] : fixed[i][j]);
                System.out.print((val == 0 ? " ." : " " + val));
                if ((j+1) % 3 == 0) System.out.print(" |");
            }
            System.out.println();
        }
        System.out.println("  +-------+-------+-------+");
    }

    // Método para verificar se o número inserido é válido
    void inserirNumero() {
        int linha = lerInteiroValido("Digite a linha (1-9): ", 1, 9) - 1;
        int coluna = lerInteiroValido("Digite a coluna (1-9): ", 1, 9) - 1;
        int valor = lerInteiroValido("Digite o número (1-9): ", 1, 9);

        // Verifica se a posição é fixa
        if (fixed[linha][coluna] != 0) {
            System.out.println("Erro: posição (" + (linha+1) + "," + (coluna+1) + ") é fixa e não pode ser alterada.");
            return;
        }

        // Se o usuário já inseriu um valor ali, pergunta se deseja substituir
        if (user[linha][coluna] != 0) {
            System.out.print("Já há um número aí (" + user[linha][coluna] + "). Deseja substituir? (s/n): ");
            String resp = sc.nextLine().trim().toLowerCase();
            if (!resp.equals("s") && !resp.equals("n")) {
                System.out.println("Erro: responda apenas 's' ou 'n'.");
                return;
            }
            if (resp.equals("n")) return;
        }

        // Insere o número
        user[linha][coluna] = valor;
    }

    // Método para verificar erros no jogo do usuário
    void verificarErros() {
        List<String> erros = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (fixed[i][j] == 0 && user[i][j] != solution[i][j]) {
                    erros.add("(" + (i+1) + "," + (j+1) + ")");
                }
            }
        }
        System.out.println("Você teve " + erros.size() + " erros nas posições: " + erros);
    }

    // Método para gerar o tabuleiro com base na dificuldade
    void gerarTabuleiro(int dificuldade) {
        // Limpar o tabuleiro do jogador
        for (int i = 0; i < 9; i++) {
            Arrays.fill(user[i], 0);
        }

        // Dependendo da dificuldade, preenche o tabuleiro fixo com números
        switch (dificuldade) {
            case 1: // Fácil
                fixed = new int[][] {
                    {5, 3, 0, 0, 7, 0, 0, 0, 0},
                    {6, 0, 0, 1, 9, 5, 0, 0, 0},
                    {0, 9, 8, 0, 0, 0, 0, 6, 0},
                    {8, 0, 0, 0, 6, 0, 0, 0, 3},
                    {4, 0, 0, 8, 0, 3, 0, 0, 1},
                    {7, 0, 0, 0, 2, 0, 0, 0, 6},
                    {0, 6, 0, 0, 0, 0, 2, 8, 0},
                    {0, 0, 0, 4, 1, 9, 0, 0, 5},
                    {0, 0, 0, 0, 8, 0, 0, 7, 9}
                };
                break;
            case 2: // Médio
                fixed = new int[][] {
                    {5, 3, 0, 0, 0, 0, 0, 9, 0},
                    {6, 0, 0, 7, 0, 0, 0, 8, 0},
                    {0, 9, 0, 0, 0, 0, 0, 0, 0},
                    {8, 0, 0, 0, 0, 3, 0, 0, 4},
                    {0, 4, 0, 6, 0, 9, 0, 0, 0},
                    {7, 0, 0, 8, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 7, 0, 9},
                    {0, 0, 0, 0, 8, 0, 9, 0, 0},
                    {0, 5, 9, 0, 0, 0, 0, 0, 0}
                };
                break;
            case 3: // Difícil
                fixed = new int[][] {
                    {0, 0, 0, 9, 0, 0, 0, 1, 0},
                    {0, 2, 0, 0, 0, 0, 6, 0, 0},
                    {0, 0, 0, 0, 7, 6, 0, 5, 0},
                    {0, 0, 8, 4, 0, 2, 0, 0, 0},
                    {0, 0, 9, 0, 6, 3, 0, 0, 0},
                    {0, 0, 0, 0, 0, 9, 0, 4, 2},
                    {3, 0, 0, 0, 0, 7, 0, 9, 0},
                    {0, 4, 0, 6, 0, 0, 0, 8, 0},
                    {0, 0, 0, 8, 0, 0, 0, 0, 6}
                };
                break;
            default:
                System.out.println("Dificuldade inválida.");
                break;
        }
        
        // Solução (tabuleiro completo)
        solution = new int[][] {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
    }

    // Método para iniciar o jogo
    void iniciarJogo(int dificuldade) {
        gerarTabuleiro(dificuldade);

        // Limpar o tabuleiro do jogador
        for (int i = 0; i < 9; i++) {
            Arrays.fill(user[i], 0);
        }

        // Jogo
        while (true) {
            exibirTabuleiro();
            System.out.println("Opções: 1. Inserir número  2. Completar jogo  3. Voltar ao menu");
            int choice = lerInteiroValido("Escolha: ", 1, 3);
            if (choice == 1) {
                inserirNumero();
            } else if (choice == 2) {
                verificarErros();
                break;
            } else if (choice == 3) {
                break;
            }
        }
    }
}