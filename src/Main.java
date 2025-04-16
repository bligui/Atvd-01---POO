import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;

public class Main {
    private static Banco banco = new Banco();

    public static void main(String[] args) {
        int opcao;
        do {
            String input = showInputDialog(
                "SISTEMA BANCÁRIO\n\n" +
                "1 - Cadastrar cliente\n" +
                "2 - Cadastrar conta\n" +
                "3 - Depositar\n" +
                "4 - Sacar\n" +
                "5 - Listar clientes e contas\n" +
                "6 - Remover cliente\n" +
                "7 - Sair\n\n" +
                "Escolha uma opção:");

            if (input == null) return;

            try {
                opcao = parseInt(input);
            } catch (NumberFormatException e) {
                showMessageDialog(null, "Entrada inválida!");
                continue;
            }

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> cadastrarConta();
                case 3 -> depositar();
                case 4 -> sacar();
                case 5 -> listar();
                case 6 -> removerCliente();
                case 7 -> System.exit(0);
                default -> showMessageDialog(null, "Opção inválida!");
            }
        } while (true);
    }

    private static void cadastrarCliente() {
        String[] tipos = {"Pessoa Física", "Pessoa Jurídica"};
        int tipo = showOptionDialog(null, "Tipo de cliente:", "Cadastro",
                DEFAULT_OPTION, INFORMATION_MESSAGE, null, tipos, tipos[0]);

        String nome = showInputDialog("Nome:");
        if (nome == null) return;

        String documento = showInputDialog("CPF/CNPJ:");
        if (documento == null) return;

        Cliente cliente;
        if (tipo == 0) cliente = new PessoaFisica(nome, documento);
        else cliente = new PessoaJuridica(nome, documento);

        banco.adicionarCliente(cliente);
        showMessageDialog(null, "Cliente cadastrado!");
    }

    private static void cadastrarConta() {
        String documento = showInputDialog("CPF/CNPJ do cliente:");
        if (documento == null) return;

        Cliente cliente = banco.buscarCliente(documento);
        if (cliente == null) {
            showMessageDialog(null, "Cliente não encontrado.");
            return;
        }

        String[] tipos = {"Conta Corrente", "Conta Poupança", "Conta Rendimento"};
        int tipo = showOptionDialog(null, "Tipo da conta:", "Conta",
                DEFAULT_OPTION, INFORMATION_MESSAGE, null, tipos, tipos[0]);

        Conta conta;
        if (tipo == 0) {
            String resp = showInputDialog("Deseja limite? (s/n):");
            boolean temLimite = resp != null && resp.equalsIgnoreCase("s");
            conta = new ContaCorrente(temLimite);
        } else if (tipo == 1) {
            conta = new ContaPoupanca();
        } else {
            conta = new ContaRendimento();
        }

        cliente.adicionarConta(conta);
        showMessageDialog(null, "Conta criada com sucesso!");
    }

    private static void depositar() {
        Conta conta = localizarConta();
        if (conta == null) return;
        String valorStr = showInputDialog("Valor do depósito:");
        if (valorStr == null) return;
        double valor = parseDouble(valorStr);
        conta.depositar(valor);
        showMessageDialog(null, "Depósito realizado.");
    }

    private static void sacar() {
        Conta conta = localizarConta();
        if (conta == null) return;
        String valorStr = showInputDialog("Valor do saque:");
        if (valorStr == null) return;
        double valor = parseDouble(valorStr);

        try {
            conta.sacar(valor);
            showMessageDialog(null, "Saque realizado.");
        } catch (RuntimeException e) {
            showMessageDialog(null, e.getMessage());
        }
    }

    private static void listar() {
        StringBuilder sb = new StringBuilder();
        for (Cliente c : banco.getClientes()) {
            sb.append(c).append("\n");
            c.getContas().forEach(conta -> sb.append(" → ").append(conta).append("\n"));
            sb.append("\n");
        }
        showMessageDialog(null, sb.length() > 0 ? sb.toString() : "Nenhum cliente cadastrado.");
    }

    private static void removerCliente() {
        String doc = showInputDialog("CPF/CNPJ do cliente a remover:");
        if (doc == null) return;
        banco.removerCliente(doc);
        showMessageDialog(null, "Cliente removido.");
    }

    private static Conta localizarConta() {
        String doc = showInputDialog("CPF/CNPJ do cliente:");
        if (doc == null) return null;
        Cliente cliente = banco.buscarCliente(doc);
        if (cliente == null) {
            showMessageDialog(null, "Cliente não encontrado.");
            return null;
        }

        String numeroStr = showInputDialog("Número da conta:");
        if (numeroStr == null) return null;
        int numero = parseInt(numeroStr);
        Conta conta = cliente.buscarConta(numero);
        if (conta == null) showMessageDialog(null, "Conta não encontrada.");
        return conta;
    }
}
