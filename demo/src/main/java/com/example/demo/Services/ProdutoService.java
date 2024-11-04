package com.example.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.demo.Model.Produto;
import com.example.demo.Repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvarProduto(Produto produto) {
        validarProduto(produto);
        return produtoRepository.save(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto obterPorId(Long id) {
        return produtoRepository.findById(id).orElse(null); // Retorna null se não encontrar o produto
    }
    

    public void excluirProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado para exclusão.");
        }
        produtoRepository.deleteById(id);
    }

    public Produto atualizarProduto(Long id, Produto produto) {
        Produto produtoExistente = obterPorId(id);
        produtoExistente.setNome(produto.getNome());
        produtoExistente.setDescricao(produto.getDescricao());
        produtoExistente.setPreco(produto.getPreco());
        produtoExistente.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        return produtoRepository.save(produtoExistente);
    }

    private void validarProduto(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome do produto é obrigatório.");
        }
        if (produto.getPreco() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O preço do produto não pode ser negativo.");
        }
        if (produto.getQuantidadeEstoque() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A quantidade em estoque não pode ser negativa.");
        }
        
    }
}
