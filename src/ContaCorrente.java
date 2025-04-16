public class ContaCorrente extends Conta {
    private boolean temLimite;

    public ContaCorrente(boolean temLimite) {
        this.temLimite = temLimite;
    }

    @Override
    public void sacar(double valor) {
        double limite = temLimite ? 500 : 0;
        if (valor > saldo + limite) {
            throw new RuntimeException("Saldo insuficiente!");
        }
        saldo -= valor;
    }
}