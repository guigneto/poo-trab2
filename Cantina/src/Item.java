import java.util.ArrayList;

public class Item implements Comparable<Item> {
    private Produto p;
    private int qnt;

    public Item(Produto p, int qnt) {
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
        // Primeiro critério: Ordenação pela quantidade de produtos diferentes (mais produtos vêm primeiro)
        int qntCompare = Integer.compare(outroItem.getQnt(), this.qnt);
        if (qntCompare != 0) {
            return qntCompare; // Se houver diferença na quantidade, retornamos essa diferença
        }

        // Segundo critério: Ordenação pelo valor total do pedido (mais caro vem primeiro)
        return Double.compare(outroItem.valorTotal(), this.valorTotal());
    }
}
