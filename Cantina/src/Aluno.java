public class Aluno extends Usuario{
    private double saldo;

    public Aluno(String cpf, String nome, String senha) {
        super(cpf, nome, senha);
        this.saldo = 0;
    }

    public double getSaldo() {
        return saldo;
    }

    public void inserirSaldo(double valor) throws IllegalArgumentException {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor de depósito deve ser positivo.");
        }
        this.saldo += valor;
    }

    public boolean retirarSaldo(double valor) throws IllegalArgumentException {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser positivo.");
        }

        if(valor <= this.saldo){
            this.saldo -= valor;
            System.out.println("Saque na conta do aluno " + this.nome + " realizado com sucesso. Novo saldo: " + this.saldo);
            return true;
        }
        else{
            System.out.println("Erro: Saque na conta do aluno  " + this.nome + " não foi realizado. Valor disponível: " + this.saldo);
            return false;
        }
    }

    public String toString() {
        return super.toString() + " (Saldo: R$" + String.format("%.2f", this.saldo) + ")";
    }
}
