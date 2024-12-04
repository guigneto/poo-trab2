public class Produto {

    private String cod;
    private String nome;
    private int qnt;
    private double valor;

    public Produto(String cod,String nome, int qnt, double valor) {
        this.cod = cod;
        this.qnt = qnt;
        this.nome = nome;
        this.valor = valor;
    }

    public String toString() {
        return "Nome do produto: " + this.nome + " - Quantidade: " + this.qnt + " - Valor: " + this.valor;
    }

    public void retirarDeEstoque(int qnt){ this.qnt--; }
}
