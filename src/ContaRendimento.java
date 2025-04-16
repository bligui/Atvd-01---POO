public class ContaRendimento extends Conta {
    @Override
    public void sacar(double valor) {
        throw new RuntimeException("Conta rendimento não permite saques!");
    }
}