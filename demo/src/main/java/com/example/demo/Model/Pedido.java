package com.example.demo.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente; // Referência ao cliente

    private LocalDateTime dataHoraPedido;
    private String status;  // Status do pedido, como "PENDENTE", "CONCLUÍDO", "NÃO CONCLUÍDO".

    private Long produtoId; // ID do produto
    private int quantidade;  // Quantidade do produto no pedido

    // Construtores, Getters e Setters

    public Pedido() {
        // Construtor padrão
    }

    public void setClienteId(Long clienteId) {
        // Aqui você precisa de um método para buscar o cliente pelo ID
        // Isso pode ser feito através de um repositório, mas para simplificação, vou deixar como um exemplo
        // Você precisará injetar o repositório ou usar um serviço para encontrar o cliente pelo ID

        // Exemplo:
        // this.cliente = clienteRepository.findById(clienteId).orElse(null); // Caso você tenha um repositório disponível

        // Se você não tiver um repositório disponível aqui, precisará de outra abordagem
        // Esse método deve ser implementado na camada de serviço ou onde for mais adequado
    }

    public Pedido(Cliente cliente, LocalDateTime dataHoraPedido, String status, Long produtoId, int quantidade) {
        this.cliente = cliente;
        this.dataHoraPedido = dataHoraPedido;
        this.status = status;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Método para obter o ID do cliente
    public Long getClienteId() {
        return cliente != null ? cliente.getId() : null; // Retorna o ID do cliente, se existir
    }

    public LocalDateTime getDataHoraPedido() {
        return dataHoraPedido;
    }

    public void setDataHoraPedido(LocalDateTime dataHoraPedido) {
        this.dataHoraPedido = dataHoraPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Método para obter a quantidade de estoque
    public int getQuantidadeEstoque() {
        return quantidade; // Retorna a quantidade do pedido
    }
}



