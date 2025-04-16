import java.util.*;

public abstract class Cliente {
    private String nome;
    private String documento;
    private List<Conta> contas = new ArrayList<>();

    public Cliente(String nome, String documento) {
        this.nome = nome;
        this.documento = documento;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public Conta buscarConta(int numero) {
        for (Conta c : contas) {
            if (c.getNumero() == numero) return c;
        }
        return null;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public String getDocumento() {
        return documento;
    }

    public String toString() {
        return nome + " - " + documento;
    }
}