public abstract class Conta {
    private static int contador = 1;
    private int numero;
    protected double saldo;

    public Conta() {
        this.numero = contador++;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public abstract void sacar(double valor);

    public int getNumero() {
        return numero;
    }

    public String toString() {
        return getClass().getSimpleName() + " #" + numero + " - Saldo: R$ " + String.format("%.2f", saldo);
    }
}