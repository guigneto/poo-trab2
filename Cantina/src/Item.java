public class Item {
    private Produto p;
    private int qnt;

    public String toString() {
        return "Nome do Item: " + p.getNome() + " - Quantidade: " + this.qnt;
    }

    public double valorTotal(){
        return p.getValor() * this.qnt;
    }
}
