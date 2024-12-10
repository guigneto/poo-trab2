public class Produto {

    private String cod, nome;
    private int qnt;
    private double valor;

    public Produto(String nome, int qnt, double valor, Sistema s) {
        this.cod = s.gerarCodigoProduto();
        this.qnt = qnt;
        this.nome = nome;
        this.valor = valor;
    }

    public String toString() {
        return this.cod + ": " + this.nome;
    }

    public void retirarDeEstoque(int qnt){
        this.qnt -= qnt;
    }

    public String getCod() {
        return cod;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public int getQnt() {
        return qnt;
    }
}
