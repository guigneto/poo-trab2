import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Pedido {
    private String cod;
    private Aluno cliente;
    private Aluno entregador;
    private Sala s;
    private ArrayList<Item> carrinho;
    private boolean entregue;

    public Pedido(Aluno cliente, Sala sala, Sistema s) {
        this.cod = Objects.requireNonNull(s, "O sistema não pode ser nulo.").gerarCodigoPedido();
        this.cliente = Objects.requireNonNull(cliente, "O cliente não pode ser nulo.");
        this.s = Objects.requireNonNull(sala, "A sala não pode ser nula.");
        this.carrinho = new ArrayList<>();
        this.entregue = false;
    }

    public String toString(){ //TERMINAR DEPOIS

        Collections.sort(carrinho);

        String msg = "\nCodigo do pedido: " + cod;

        msg+="\nPrdutos: ";
        for(Item i : this.carrinho){
            msg += "\n"+i.getP() + " (QTD: "+i.getQnt()+")";
        }

        msg+="\nStatus: ";

        if(this.isEntregue()){
            msg+="Entregue\n";
        }
        else if(this.isEntregador()){
            msg+="Em andamento\n";
        }
        else{
            msg+="Em aberto\n";
        }

        msg += "Valor Total: R$" + String.format("%.2f", this.valorTotal());

        return msg;
    }

    public void atribuirEntregador(Aluno a){
        this.entregador = Objects.requireNonNull(a, "O entregador não pode ser nulo.");
    }

    public boolean disponivel(){ //TESTAR SE TA CERTO
        return !(this.entregue) && this.entregador == null;
    }

    public double valorTotal(){
        double valor = 0;
        double taxa = 1;
        for(Item i : carrinho){
            valor += i.getP().getValor() * i.getQnt();
        }
        return valor+taxa;
    }

    public void confirmar(){
        if (cliente == null) {
            throw new IllegalStateException("O cliente não pode ser nulo ao confirmar o pedido.");
        }
        if (carrinho.isEmpty()) {
            throw new IllegalStateException("O carrinho não pode estar vazio ao confirmar o pedido.");
        }
        for(Item i : carrinho){
            i.getP().retirarDeEstoque(i.getQnt());
        }
        cliente.retirarSaldo(this.valorTotal());
    }

    public void marcarComoEntregue() {
        if (this.entregador == null) {
            throw new IllegalStateException("O pedido não pode ser marcado como entregue sem um entregador.");
        }
        this.entregue = true;
    }

    public String getCod() {
        return cod;
    }

    public Aluno getCliente() {
        return cliente;
    }

    public boolean isEntregue() {
        return entregue;
    }

    public boolean isEntregador(){
        return this.entregador != null;
    }

    public ArrayList<Item> getCarrinho(){
        return this.carrinho;
    }

    public Aluno getEntregador(){
        return entregador;
    }
}
