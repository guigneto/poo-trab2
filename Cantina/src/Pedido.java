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
        return "\n Informações do Pedido: \n"+
                "Código: "+ this.cod+"\n"
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

}
