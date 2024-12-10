import java.util.ArrayList;

public class Pedido {
    private String cod;
    private Aluno cliente;
    private Aluno entregador;
    private Sala s;
    private ArrayList<Item> carrinho;
    private boolean entregue;

    public Pedido(Aluno cliente, Sala sala, Sistema s) {
        this.cod = s.gerarCodigoPedido();
        this.cliente = cliente;
        this.s = sala;
        this.carrinho = new ArrayList<>();
        this.entregue = false;
    }

    public String toString(){ //TERMINAR DEPOIS


        String msg = "Codigo do pedido: " + cod;

        msg+="\nPrdutos: ";
        for(Item i : this.carrinho){
            msg += "\n"+i.getP() + " (QTD: "+i.getQnt()+")";
        }

        msg+="\nStatus: ";

        if(this.isEntregue()){
            msg+="Entregue";
        }
        else if(this.isEntregador()){
            msg+="Em andamento";
        }
        else{
            msg+="Em aberto";
        }

        msg += "Valor Total: R$" + String.format("%.2f", this.valorTotal());

        return msg;
    }

    public void atribuirEntregador(Aluno a){
        this.entregador = a;
    }

    public boolean disponivel(){ //TESTAR SE TA CERTO
        return !(this.entregue) && this.entregador == null;
    }

    public double valorTotal(){
        double valor = 0;
        for(Item i : carrinho){
            valor += i.getP().getValor() * i.getQnt();
        }
        return valor+1;
    }

    public void confirmar(){
        for(Item i : carrinho){
            i.getP().retirarDeEstoque(i.getQnt());
        }
        cliente.retirarSaldo(this.valorTotal());
    }

    public void marcarComoEntregue() {
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
}
