package com.example.demo;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.Model.Cliente;
import com.example.demo.Model.Pedido;
import com.example.demo.Model.Produto;
import com.example.demo.Services.ClienteService;
import com.example.demo.Services.PedidoService;
import com.example.demo.Services.ProdutoService;

@Component
public class Main implements CommandLineRunner {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PedidoService pedidoService;

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) {
        try {
            while (true) {
                System.out.println("===== Sistema Voltz - Missão Tio Patinhas =====");
                System.out.println("1. Gerenciar Clientes");
                System.out.println("2. Gerenciar Produtos");
                System.out.println("3. Gerenciar Pedidos");
                System.out.println("4. Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = obterOpcaoValida();
                switch (opcao) {
                    case 1:
                        menuClientes();
                        break;
                    case 2:
                        menuProdutos();
                        break;
                    case 3:
                        menuPedidos();
                        break;
                    case 4:
                        System.out.println("Saindo do sistema...");
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } finally {
            scanner.close(); // Garante que o scanner seja fechado ao sair
        }
    }

    private int obterOpcaoValida() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
            }
        }
    }

    private void menuClientes() {
        while (true) {
            System.out.println("===== Menu Clientes =====");
            System.out.println("1. Incluir Cliente");
            System.out.println("2. Alterar Cliente");
            System.out.println("3. Excluir Cliente");
            System.out.println("4. Exibir Todos Clientes");
            System.out.println("5. Exibir Cliente por ID");
            System.out.println("6. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int opcao = obterOpcaoValida();

            switch (opcao) {
                case 1:
                    incluirCliente();
                    break;
                case 2:
                    alterarCliente();
                    break;
                case 3:
                    excluirCliente();
                    break;
                case 4:
                    exibirTodosClientes();
                    break;
                case 5:
                    exibirClientePorId();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void menuProdutos() {
        while (true) {
            System.out.println("===== Menu Produtos =====");
            System.out.println("1. Incluir Produto");
            System.out.println("2. Alterar Produto");
            System.out.println("3. Excluir Produto");
            System.out.println("4. Exibir Todos Produtos");
            System.out.println("5. Exibir Produto por ID");
            System.out.println("6. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int opcao = obterOpcaoValida();

            switch (opcao) {
                case 1:
                    incluirProduto();
                    break;
                case 2:
                    alterarProduto();
                    break;
                case 3:
                    excluirProduto();
                    break;
                case 4:
                    exibirTodosProdutos();
                    break;
                case 5:
                    exibirProdutoPorId();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void menuPedidos() {
        while (true) {
            System.out.println("===== Menu Pedidos =====");
            System.out.println("1. Incluir Pedido");
            System.out.println("2. Alterar Pedido");
            System.out.println("3. Excluir Pedido");
            System.out.println("4. Exibir Todos Pedidos");
            System.out.println("5. Exibir Pedido por ID");
            System.out.println("6. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int opcao = obterOpcaoValida();

            switch (opcao) {
                case 1:
                    incluirPedido();
                    break;
                case 2:
                    alterarPedido();
                    break;
                case 3:
                    excluirPedido();
                    break;
                case 4:
                    exibirTodosPedidos();
                    break;
                case 5:
                    exibirPedidoPorId();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void incluirCliente() {
        System.out.println("Incluir cliente...");
        
        // Capturar dados do cliente
        System.out.print("Informe o nome do cliente: ");
        String nome = scanner.nextLine();
    
        System.out.print("Informe o telefone do cliente: ");
        String telefone = scanner.nextLine();
    
        System.out.print("Informe o email do cliente: ");
        String email = scanner.nextLine();
    
        // Criar um novo objeto Cliente
        Cliente novoCliente = new Cliente(nome, telefone, email);
    
        // Chamar o serviço para salvar o cliente
        Cliente clienteSalvo = clienteService.salvar(novoCliente);
        System.out.println("Cliente incluído com sucesso! ID do cliente: " + clienteSalvo.getId());
    }

    private void alterarCliente() {
        System.out.println("Alterar cliente...");
        
        System.out.print("ID do cliente a ser alterado: ");
        Long id = obterIdValido();
        
        Cliente cliente = clienteService.buscarPorId(id).orElse(null);
        if (cliente != null) {
            System.out.print("Novo nome do cliente (atual: " + cliente.getNome() + "): ");
            String nome = scanner.nextLine();
            System.out.print("Novo email do cliente (atual: " + cliente.getEmail() + "): ");
            String email = scanner.nextLine();
            System.out.print("Novo telefone do cliente (atual: " + cliente.getTelefone() + "): ");
            String telefone = scanner.nextLine();

            // Atualizar cliente com novos dados
            cliente.setNome(nome.isEmpty() ? cliente.getNome() : nome);
            cliente.setEmail(email.isEmpty() ? cliente.getEmail() : email);
            cliente.setTelefone(telefone.isEmpty() ? cliente.getTelefone() : telefone);
                
            clienteService.atualizar(id, cliente);
            System.out.println("Cliente alterado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    private void excluirCliente() {
        System.out.println("Excluir cliente...");
        
        System.out.print("ID do cliente a ser excluído: ");
        Long id = obterIdValido();
        
        clienteService.deletar(id);
        System.out.println("Cliente excluído com sucesso!");
    }

    private void exibirTodosClientes() {
        System.out.println("Exibir todos os clientes...");
        clienteService.listarTodos().forEach(System.out::println);
    }

    private void exibirClientePorId() {
        System.out.print("Informe o ID do cliente: ");
        Long id = obterIdValido();

        // Chama o método buscarPorId que retorna Optional<Cliente>
        Optional<Cliente> optionalCliente = clienteService.buscarPorId(id);

        // Verifica se o cliente está presente no Optional
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get(); // Extrai o Cliente do Optional
            System.out.println("Cliente encontrado: " + cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private void incluirProduto() {
        System.out.println("Incluir produto...");
        
        // Capturar dados do produto
        System.out.print("Informe o nome do produto: ");
        String nome = scanner.nextLine();

		System.out.print("Informe a descrição do produto: ");
        String descricao = scanner.nextLine();
    
        System.out.print("Informe a quantidade do produto: ");
        int quantidade = obterQuantidadeValida();
    
        System.out.print("Informe o preço do produto: ");
        double preco = obterPrecoValido();
    
        // Criar um novo objeto Produto
        Produto novoProduto = new Produto(nome, descricao , preco,quantidade);
    
        // Chamar o serviço para salvar o produto
        Produto produtoSalvo = produtoService.salvarProduto(novoProduto);
        System.out.println("Produto incluído com sucesso! ID do produto: " + produtoSalvo.getId());
    }

    private void alterarProduto() {
        System.out.println("Alterar produto...");
        
        System.out.print("ID do produto a ser alterado: ");
        Long id = obterIdValido();
        
        Produto produto = produtoService.obterPorId(id);
        if (produto != null) {
            System.out.print("Novo nome do produto (atual: " + produto.getNome() + "): ");
            String nome = scanner.nextLine();
            System.out.print("Nova quantidade do produto (atual: " + produto.getQuantidadeEstoque() + "): ");
            int quantidade = obterQuantidadeValida();
            System.out.print("Novo preço do produto (atual: " + produto.getPreco() + "): ");
            double preco = obterPrecoValido();

            // Atualizar produto com novos dados
            produto.setNome(nome.isEmpty() ? produto.getNome() : nome);
            produto.setQuantidadeEstoque(quantidade == 0 ? produto.getQuantidadeEstoque() : quantidade);
            produto.setPreco(preco == 0 ? produto.getPreco() : preco);
                
            produtoService.atualizarProduto(id, produto);
            System.out.println("Produto alterado com sucesso!");
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    private void excluirProduto() {
        System.out.println("Excluir produto...");
        
        System.out.print("ID do produto a ser excluído: ");
        Long id = obterIdValido();
        
        produtoService.excluirProduto(id);
        System.out.println("Produto excluído com sucesso!");
    }

    private void exibirTodosProdutos() {
        System.out.println("Exibir todos os produtos...");
        produtoService.listarTodos().forEach(System.out::println);
    }

    private void exibirProdutoPorId() {
        System.out.print("Informe o ID do produto: ");
        Long id = obterIdValido();

        Produto produtoServ = produtoService.obterPorId(id);

        // Verifica se o produto está presente no Optional
        if (produtoServ != null) {
            Produto produto = produtoServ.getNome(); // Extrai o Produto do Optional
            System.out.println("Produto encontrado: " + produto);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void incluirPedido() {
        System.out.println("Incluir pedido...");
        
        System.out.print("Informe o ID do cliente: ");
        Long clienteId = obterIdValido();
        
        System.out.print("Informe o ID do produto: ");
        Long produtoId = obterIdValido();
        
        System.out.print("Informe a quantidade do produto: ");
        int quantidade = obterQuantidadeValida();

		System.out.print("Informe o status do pedido: ");
        String status = scanner.nextLine();
        
        // Criar um novo objeto Pedido
        Pedido novoPedido = new Pedido(clienteId, LocalDateTime.now(), status, produtoId, quantidade);
        
        // Chamar o serviço para salvar o pedido
        Pedido pedidoSalvo = pedidoService.salvarPedido(novoPedido);
        System.out.println("Pedido incluído com sucesso! ID do pedido: " + pedidoSalvo.getId());
    }

    private void alterarPedido() {
        System.out.println("Alterar pedido...");
        
        System.out.print("ID do pedido a ser alterado: ");
        Long id = obterIdValido();
        
        Pedido pedido = pedidoService.obterPorId(id);
        if (pedido != null) {
            System.out.print("Novo ID do cliente (atual: " + pedido.getClienteId() + "): ");
            Long clienteId = obterIdValido();
            System.out.print("Novo ID do produto (atual: " + pedido.getProdutoId() + "): ");
            Long produtoId = obterIdValido();
            System.out.print("Nova quantidade do produto (atual: " + pedido.getQuantidadeEstoque() + "): ");
            int quantidade = obterQuantidadeValida();

            // Atualizar pedido com novos dados
            pedido.setClienteId(clienteId);
            pedido.setProdutoId(produtoId);
            pedido.setQuantidade(quantidade);
                
            pedidoService.atualizarPedido(id, pedido);
            System.out.println("Pedido alterado com sucesso!");
        } else {
            System.out.println("Pedido não encontrado!");
        }
    }

    private void excluirPedido() {
        System.out.println("Excluir pedido...");
        
        System.out.print("ID do pedido a ser excluído: ");
        Long id = obterIdValido();
        
        pedidoService.excluirPedido(id);
        System.out.println("Pedido excluído com sucesso!");
    }

    private void exibirTodosPedidos() {
        System.out.println("Exibir todos os pedidos...");
        pedidoService.listarPedidos().forEach(System.out::println);
    }

    private void exibirPedidoPorId() {
        System.out.print("Informe o ID do pedido: ");
        Long id = obterIdValido();

        // Chama o método buscarPorId que retorna Optional<Pedido>
        Pedido pedidoServ = pedidoService.obterPorId(id);

        // Verifica se o pedido está presente no Optional
        if (pedidoServ != null) {
            Pedido pedido = optionalPedido.get(); // Extrai o Pedido do Optional
            System.out.println("Pedido encontrado: " + pedido);
        } else {
            System.out.println("Pedido não encontrado.");
        }
    }

    private Long obterIdValido() {
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido para o ID.");
            }
        }
    }

    private int obterQuantidadeValida() {
        while (true) {
            try {
                int quantidade = Integer.parseInt(scanner.nextLine());
                if (quantidade < 0) {
                    System.out.println("A quantidade não pode ser negativa. Tente novamente.");
                } else {
                    return quantidade;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido para a quantidade.");
            }
        }
    }

    private double obterPrecoValido() {
        while (true) {
            try {
                double preco = Double.parseDouble(scanner.nextLine());
                if (preco < 0) {
                    System.out.println("O preço não pode ser negativo. Tente novamente.");
                } else {
                    return preco;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido para o preço.");
            }
        }
    }
}
