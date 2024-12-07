import java.util.ArrayList;

public class Item {
    private Produto p;
    private int qnt;

    public Item(Produto p, int qnt) {
        this.p = p;
        this.qnt = qnt;
    }

    public String toString() {
        return "Nome do Item: " + p.getNome() + " - Quantidade: " + this.qnt;
    }

    public double valorTotal(){
        return this.p.getValor() * this.qnt;
    }
}
