import java.io.FileNotFoundException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {


        Sistema s = new Sistema();
        Entrada e = new Entrada();

        e.carregarDadosDoArquivo(s);
        try {
            e.menu(s);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
