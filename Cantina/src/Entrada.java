import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Entrada {
    /**
     * Classe com as rotinas de entrada e saída do projeto
     * @author Hilario Seibel Junior, Allicia Rocha e Guilherme Gomes
     */

    public Scanner input;

    /**
     * Construtor da classe InputOutput
     * Se houver um arquivo input.txt, define que o Scanner vai ler deste arquivo.
     * Se o arquivo não existir, define que o Scanner vai ler da entrada padrão (teclado)
     */
    public Entrada() {
        try {
            // Se houver um arquivo input.txt na pasta corrente, o Scanner vai ler dele.
            this.input = new Scanner(new FileInputStream("Cantina/input.txt")).useLocale(Locale.US);
            // NAO ALTERE A LOCALICAÇÃO DO ARQUIVO!!
        } catch (FileNotFoundException e) {
            // Caso contrário, vai ler do teclado.
            this.input = new Scanner(System.in).useLocale(Locale.US);
        }
    }

    /**
     * Carrega dados.txt
     * Se houver um arquivo dados.txt, carrega eles no sistema
    */

    public void carregarDadosDoArquivo(Sistema s) {
        File arquivo = new File("Cantina/dados.txt");
        try (Scanner scanner = new Scanner(arquivo)) {
            while (scanner.hasNextLine()) {
                String tipo = scanner.nextLine();
                if (tipo.equals("ADM")) {
                    String cpf = scanner.nextLine();
                    String nome = scanner.nextLine();
                    String senha = scanner.nextLine();
                    String email = scanner.nextLine();
                    Admin admin = new Admin(cpf, nome, senha, email);
                    s.addAdmin(admin);
                } else if (tipo.equals("ALU")) {
                    String cpf = scanner.nextLine();
                    String nome = scanner.nextLine();
                    String senha = scanner.nextLine();
                    Aluno aluno = new Aluno(cpf, nome, senha);
                    s.addAluno(aluno);
                } else if (tipo.equals("FIM")) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo dados.txt não encontrado.");
        }
    }


    /**
     * Faz a leitura de uma linha inteira
     * Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
     * @param msg: Mensagem que será exibida ao usuário
     * @return Uma String contendo a linha que foi lida
     */
    private String lerLinha(String msg) {
        // Imprime uma mensagem ao usuário, lê uma e retorna esta linha
        System.out.print(msg);
        String linha = this.input.nextLine();

        // Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
        while (linha.charAt(0) == '#') linha = this.input.nextLine();
        return linha;
    }

    /**
     * Faz a leitura de um número inteiro
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para int
     */
    private int lerInteiro(String msg) {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um inteiro e retorna este inteiro
        String linha = this.lerLinha(msg);
        return Integer.parseInt(linha);
    }

    /**
     * Faz a leitura de um ponto flutuante
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para double
     */
    private double lerDouble(String msg) {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um ponto flutuante e retorna este número
        String linha = this.lerLinha(msg);
        return Double.parseDouble(linha);
    }

    /**********************/
    /** MENUS DO SISTEMA **/
    /**********************/
    /**
     * Exibe o menu principal até que o usuário opte por sair do programa.
     * @param s: Objeto a classe Sistema.
     */
    public void menu(Sistema s) {
        if (s.sistemaVazio()) {
            System.out.println("** Inicializando o sistema **");
            this.cadAdmin(s);
        }

        int op = -1; // Inicializa com valor inválido para garantir a entrada no loop

        do {
            try {
                // Exibe a mensagem apenas quando não houver exceção
                System.out.println("\n*********************");
                System.out.println("Escolha uma opção:");
                System.out.println("1) Login.");
                System.out.println("0) Sair.");

                op = this.lerInteiro("Digite sua opção: "); // Apenas a entrada

                switch (op) {
                    case 1:
                        login(s);
                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número válido.");
            }
        } while (op != 0);
    }

    /**
     * Exibe o menu do administrador até que o usuário deslogue.
     * @param a: Objeto a classe Admin.
     * @param s: Objeto a classe Sistema.
     */
    public void menu(Admin a, Sistema s) {
        String msg = "\n*********************\n" +
                "Escolha uma opção:\n" +
                "1) Cadastrar novo administrador.\n" +
                "2) Cadastrar aluno.\n" +
                "3) Cadastrar produto.\n" +
                "4) Cadastrar sala.\n" +
                "0) Logout.\n";

        int op = this.lerInteiro(msg);

        while (op != 0) {
            if (op == 1) cadAdmin(s);
            if (op == 2) cadAluno(s);
            if (op == 3) cadProduto(s);
            if (op == 4) cadSala(s);
            if (op < 0 || op > 4) System.out.println("Opção inválida. Tente novamente: ");

            op = this.lerInteiro(msg);
        }
    }

    /**
     * Exibe o menu do aluno até que o usuário deslogue.
     * @param a: Objeto a classe Aluno.
     * @param s: Objeto a classe Sistema.
     */
    public void menu(Aluno a, Sistema s) {
        String msg = "\n*********************\n" +
                "Escolha uma opção:\n" +
                "1) Fazer pedido.\n" +
                "2) Fazer entrega.\n" +
                "3) Meus pedidos.\n" +
                "4) Inserir crédito.\n" +
                "0) Logout.\n";

        int op = this.lerInteiro(msg);

        while (op != 0) {
            if (op == 1) { fazerPedido(a, s);}
            if (op == 2) {entregarPedido(a, s);}
            if (op == 3) {listarPedidos(a, s);}
            if (op == 4) {inserirCredito(a, s);}
            if (op < 0 || op > 4) System.out.println("Opção inválida. Tente novamente: ");

            op = this.lerInteiro(msg);
        }
    }

    /**
     * Realiza o login de um administrador no sistema.
     * @param s: Um objeto da classe Sistema
     */
    public void login(Sistema s) {
        System.out.println("\nBem vindo! Digite seus dados de login:");
        String cpf = this.lerLinha("CPF:");
        String senha = this.lerLinha("Senha:");

        Admin adm = s.getAdmin(cpf);
        if (adm != null) {
            if (adm.validarAcesso(senha)) {
                this.menu(adm, s);
            }
            else System.out.println("Senha inválida.");
        }
        else {
            Aluno a = s.getAluno(cpf);
            if (a != null) {
                if (a.validarAcesso(senha)) {
                    this.menu(a, s);
                }
                else System.out.println("Senha inválida");
            }
            else {
                System.out.println("Usuário inexistente");
            }
        }
    }

    /***************/
    /** CADASTROS **/
    /***************/

    /**
     * Lê os dados de um novo administrador e cadastra-a no sistema.
     * @param s: Um objeto da classe Sistema
     */
    public void cadAdmin(Sistema s) {
        System.out.println("\n** Cadastrando um novo administrador **\n");
        String cpf = this.lerLinha("Digite o cpf: ");

        while (s.getAdmin(cpf) != null) {
            cpf = this.lerLinha("Usuário já existente. Escolha outro cpf: ");
        }

        String nome = this.lerLinha("Digite o nome: ");
        String senha = this.lerLinha("Digite a senha: ");
        String email = this.lerLinha("Digite o email: ");

        Admin a = new Admin(cpf, nome, senha, email);
        s.addAdmin(a);

        System.out.println("Usuário " + a + " criado com sucesso.");
    }

    /**
     * Lê os dados de um novo aluno e cadastra no sistema.
     * @param s: Um objeto da classe Sistema
     */
    public void cadAluno(Sistema s){
        System.out.println("\n** Cadastrando um novo aluno **\n");
        String cpf = this.lerLinha("Digite o cpf: ");

        while (s.getAluno(cpf) != null) {
            cpf = this.lerLinha("Usuário já existente. Escolha outro cpf: ");
        }

        String nome = this.lerLinha("Digite o nome: ");
        String senha = this.lerLinha("Digite a senha: ");

        Aluno a = new Aluno(cpf, nome, senha);
        s.addAluno(a);

        System.out.println("Usuário " + a + " criado com sucesso.");
    }

    /**
     * Lê os dados de um novo produto e cadastra no sistema.
     * @param s: Um objeto da classe Sistema
     */
    public void cadProduto(Sistema s){
        System.out.println("\n** Cadastrando um novo produto **\n");
        String nome = this.lerLinha("Digite o nome do produto: ");

        while (s.getProd(nome) != null) {
            nome = this.lerLinha("Produto já existente. Indique outro produto: ");
        }

        int qtd = this.lerInteiro("Digite a quantidade em estoque: ");
        double valor = this.lerDouble("Digite o valor unitário do produto: ");

        Produto p = new Produto(nome, qtd, valor,s);
        s.addProd(p);

        System.out.println(p + " criado com sucesso.");
    }

    /**
     * Lê os dados de uma nova sala e cadastra no sistema.
     * @param s: Um objeto da classe Sistema
     */
    public void cadSala(Sistema s){
        System.out.println("\n** Cadastrando uma nova sala **\n");
        String bloco = this.lerLinha("Digite o bloco (ex: para 904T, digite 9): ");

        String sala = this.lerLinha("Digite a sala (ex: para 904T, digite 04): ");
        String andar = this.lerLinha("Digite o andar (ex: para 904T, digite T): ");

        Sala c = new Sala(bloco, sala, andar);
        s.addSala(c);

        System.out.println("Sala " + c + " criada com sucesso.");
    }

    /***************/
    /*** ALUNOS ****/
    /***************/

    /**
     * Permite que um aluno faça um pedido no sistema.
     * @param a: Um objeto da classe Aluno.
     * @param s: Um objeto da classe Sistema.
     */
    public void fazerPedido(Aluno a, Sistema s){
        s.listarSalas();
        Sala salaPedido = lerSala(s);


        Pedido pedido = new Pedido(a, salaPedido, s);
        ArrayList<Item> carrinho = pedido.getCarrinho();

        String msg = "\n*********************\n" +
                "Escolha uma opção:\n" +
                "1) Inserir produto no carrinho.\n" +
                "2) Fechar pedido.\n";

        int op = this.lerInteiro(msg);

        while (op != 2) {
            if (op == 1) {


                s.listarProdutos();

                Item item = lerItem(s);

                carrinho.add(item);


            }
            if (op > 2 || op < 1) System.out.println("Opção inválida. Tente novamente: ");

            op = this.lerInteiro(msg);
        }



        if(pedido.valorTotal() > a.getSaldo()){
            System.out.println("O pedido não pode ser inserido, porque o aluno não tem mais crédito suficiente");
        }
        else{
            pedido.confirmar();
            s.addPedido(pedido);
        }


    }

    private Item lerItem(Sistema s){

        String codProduto = this.lerLinha("Digite o código do produto: ");
        Produto p = s.getProd(codProduto);

        int qntProds = s.getProd(codProduto).getQnt();
        int qntProdCarrinho = this.lerInteiro("Digite a quantidade de " + p.toString());

        while (qntProds<qntProdCarrinho) {
            System.out.println("Erro não há" + qntProdCarrinho + "itens disponíveis no estoque.\nQuantidade disponível: "+qntProds);
            qntProdCarrinho = this.lerInteiro("Digite a quantidade: ");
        }

        return new Item(p, qntProdCarrinho);
    }

    private Sala lerSala(Sistema s){

        String nomeSala = this.lerLinha("Digite a sala: ");
        Sala salaEscolhida = s.getSala(nomeSala);

        if (salaEscolhida != null) {
            System.out.println("Sala escolhida: " + salaEscolhida);
            return salaEscolhida;
        } else {
            System.out.println("Sala não encontrada.");
            return null;
        }
    }

    private Pedido lerPedido(Sistema s){

        String codPedido = this.lerLinha("Digite o código do pedido: ");
        Pedido pedidoEscolhido = s.getPedido(codPedido);

        if (pedidoEscolhido != null) {
            System.out.println("Pedido escolhido: " + pedidoEscolhido.getCod());
            return pedidoEscolhido;
        } else {
            System.out.println("Pedido não encontrado.");
            return null;
        }

    }

    public void listarPedidos(Aluno a, Sistema s){
        System.out.println("Pedidos de  "+a);
        ArrayList<Pedido> pedidos = s.filtrarPedidos(a);
        for (Pedido pedido : pedidos) {
            System.out.println(pedido.toString());
        }
    }

    public void entregarPedido(Aluno a,Sistema s){

        ArrayList<Pedido> pedidos = s.filtrarPedidos(true);
        for (Pedido pedido : pedidos) {
            System.out.println(pedido.toString());
        }

        Pedido pedido = lerPedido(s);
        pedido.atribuirEntregador(a);
        pedido.marcarComoEntregue();

        double valor = 0.80;
        pedido.getEntregador().inserirSaldo(valor);

        System.out.println("Pedido entregue pelo entregador: "+a);

    }


    public void inserirCredito(Aluno a, Sistema s){
        try {
            double valor = this.lerDouble("Digite um valor para adicionar: ");
            a.inserirSaldo(valor);
            System.out.printf("Saldo de R$%.2f adicionado na conta de %s com sucesso.",valor,a.nome);
        } catch (IllegalArgumentException e) {
            System.out.println("Valor inválido, transação incompleta");
        }
    }

}
