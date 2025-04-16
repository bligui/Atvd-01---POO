import java.util.*;

public class Banco {
    private List<Cliente> clientes = new ArrayList<>();

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente buscarCliente(String documento) {
        for (Cliente c : clientes) {
            if (c.getDocumento().equals(documento)) return c;
        }
        return null;
    }

    public void removerCliente(String documento) {
        clientes.removeIf(c -> c.getDocumento().equals(documento));
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}