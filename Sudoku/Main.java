public class Main {
    public static void main(String[] args) {
        Sudoku jogo = new Sudoku();
        Menu menu = new Menu();
        while (true) {
            menu.mostrarMenu(jogo);
        }
    }
}