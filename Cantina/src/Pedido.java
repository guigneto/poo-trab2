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

        return "Código do Pedido: "+ this.cod+"\n"
                +"Produtos: "
                + "Cliente: "+ this.cliente+"\n"
                + "Entregador: "+ this.entregador+"\n"
                + "Sala: "+ this.s+"\n"+
                "Carrinho: "+ this.carrinho+"\n"+
                "Entregue: "+ this.entregue;
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

    public String getCod() {
        return cod;
    }

    public Aluno getCliente() {
        return cliente;
    }

    public void setEntregador(Aluno entregador) {
        this.entregador = entregador;
    }

    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
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
