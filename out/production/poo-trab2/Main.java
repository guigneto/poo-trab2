public class Main {
    public static void main(String[] args) {
        Sistema s = new Sistema();
        Entrada e = new Entrada();

        try {
            e.carregarDadosDoArquivo(s); // Tenta carregar os dados antes de abrir o menu
            System.out.println("Dados carregados com sucesso!");
        } catch (Exception ex) {
            System.out.println("Erro ao carregar dados: " + ex.getMessage());
            ex.printStackTrace(); // Mostra o erro para depuração
        }

        e.menu(s); // Só chama o menu depois que os dados são carregados corretamente
    }
}
