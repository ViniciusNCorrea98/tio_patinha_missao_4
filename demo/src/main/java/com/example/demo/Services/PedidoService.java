package com.example.demo.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Model.Pedido;
import com.example.demo.Repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido salvarPedido(Pedido pedido) {
        validarPedido(pedido);
        pedido.setDataHoraPedido(LocalDateTime.now()); // Define a data/hora do pedido automaticamente
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido obterPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
    }

    public void excluirPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado para exclusão.");
        }
        pedidoRepository.deleteById(id);
    }

    public Pedido atualizarPedido(Long id, Pedido pedido) {
        Pedido pedidoExistente = obterPorId(id); // Busca o pedido existente
        pedidoExistente.setStatus(pedido.getStatus()); // Atualiza o status
        // Adicione outras atualizações conforme necessário
        return pedidoRepository.save(pedidoExistente); // Salva as alterações
    }

    private void validarPedido(Pedido pedido) {
        // Aqui você pode adicionar validações, por exemplo:
        if (pedido.getStatus() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O status do pedido é obrigatório.");
        }
        // Adicione outras validações conforme necessário
    }
}


