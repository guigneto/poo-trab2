import java.util.ArrayList;

public class Item implements Comparable<Item> {
    private Produto p;
    private int qnt;

    public Item(Produto p, int qnt) {
        if (p == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo.");
        }
        if (qnt <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        this.p = p;
        this.qnt = qnt;
    }

    public Produto getP() {
        return p;
    }

    public int getQnt() {
        return qnt;
    }

    public double valorTotal(){
        return this.p.getValor() * this.qnt;
    }

    public String toString() {
        return "Nome do Item: " + p.getNome() + " - Quantidade: " + this.qnt;
    }

    public int compareTo(Item outroItem) {
        if (outroItem == null) {
            throw new NullPointerException("O item de comparação não pode ser nulo.");
        }

        // ordenação pela quantidade de produtos diferentes (mais produtos vêm primeiro)
        int qntCompare = Integer.compare(outroItem.getQnt(), this.qnt);
        if (qntCompare != 0) {
            return qntCompare;
        }

        // ordenação pelo valor total do pedido (mais caro vem primeiro)
        return Double.compare(outroItem.valorTotal(), this.valorTotal());
    }
}
