import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Sistema {
    private ArrayList<Aluno> alunos;
    private ArrayList<Admin> adms;
    private ArrayList<Produto> prods;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Sala> salas;

    public Sistema() {
        this.alunos = new ArrayList<>();
        this.adms = new ArrayList<>();
        this.prods = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.salas = new ArrayList<>();
    }



    public void addAdmin(Admin a) {
        this.adms.add(a);
    }

    public void addAluno(Aluno a){
        this.alunos.add(a);
    }

    public void addProd(Produto p){
        this.prods.add(p);
    }

    public void addSala(Sala s){
        this.salas.add(s);
    }

    public void addPedido(Pedido p){
        this.pedidos.add(p);
    }

    public boolean sistemaVazio() {
        return this.adms.size() == 0;
    }

    public Aluno getAluno(String cpf) {
        for(Aluno a : this.alunos) {
            if (cpf.equals(a.getCPF())) return a;
        }

        return null;
    }

    public Admin getAdmin(String cpf) {
        for(Admin a : this.adms) {
            if (cpf.equals(a.getCPF())) return a;
        }

        return null;
    }

    public Produto getProd(String cod){
        for(Produto p : this.prods) {
            if (p.getCod().equals(cod)) return p;
        }

        return null;
    }

    public Pedido getPedido(String cod){
        for(Pedido p : this.pedidos) {
            if (p.getCod().equals(cod)) return p;
        }

        return null;
    }

    public Sala getSala(String nome){
        for(Sala c : this.salas) {
            if (c.toString().equals(nome)) return c;
        }

        return null;
    }

    public String gerarCodigoProduto(){
        return "PROD-" + (prods.size()+1);
    }

    public String gerarCodigoPedido(){
        return "PEDIDO-" + (pedidos.size()+1) ;
    }

    public void listarProdutos(){
        System.out.println("Produtos disponíveis:");
        for(Produto p : prods) {
            System.out.println(p);
        }
    }

    public void listarSalas(){
        System.out.println("Salas disponíveis:");
        for(Sala s : salas) {
            System.out.println(s);
        }
    }

    public ArrayList<Pedido> filtrarPedidos(boolean disponivel) {
        ArrayList<Pedido> pedidosFiltrados = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.disponivel() == disponivel) {
                pedidosFiltrados.add(pedido);
            }
        }
        return pedidosFiltrados;
    }

    public ArrayList<Pedido> filtrarPedidos(Aluno a) {
        ArrayList<Pedido> pedidosFiltrados = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getCliente() == a) {
                pedidosFiltrados.add(pedido);
            }
        }
        return pedidosFiltrados;
    }
}
