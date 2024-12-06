import java.util.ArrayList;

public class Sistema {
    ArrayList<Aluno> alunos;
    ArrayList<Admin> adms;
    ArrayList<Produto> prods;
    ArrayList<Pedido> pedidos;
    ArrayList<Sala> salas;

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

    public String gerarCodigoProduto(){
        return "PROD-" + prods.size()+1 ;
    }

    public String gerarCodigoPedido(){
        return "PEDIDO-" + pedidos.size()+1 ;
    }
}
