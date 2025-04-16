public class ContaPoupanca extends Conta {
    @Override
    public void sacar(double valor) {
        if (valor > saldo) throw new RuntimeException("Saldo insuficiente!");
        saldo -= valor;
    }
}