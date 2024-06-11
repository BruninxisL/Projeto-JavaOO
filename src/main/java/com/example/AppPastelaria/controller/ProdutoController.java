package com.example.AppPastelaria.controller;


import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.AppPastelaria.dto.ProdutoForm;

import com.example.AppPastelaria.model.MovimentacaoEstoque;
import com.example.AppPastelaria.model.Produto;

import com.example.AppPastelaria.repository.MovimentacaoRepository;
import com.example.AppPastelaria.repository.ProdutoRepository;

import jakarta.validation.Valid;



@Controller
@RequestMapping("produtos")
public class ProdutoController {
	
	@Autowired
	private MovimentacaoRepository moviRepo;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	
	
	@GetMapping("/listarProdutos")
	public String index(Model model) {
		List<Produto> produto = (List<Produto>)produtoRepository.findAll();	
		model.addAttribute("produtos",produto);
		
		
		return "/estoque/listarProdutos";
	}
	
	@GetMapping("/cadastrarProduto")
	public String produtoNovo() {
		return "estoque/cadastrarProduto";
	}
	
	@GetMapping("/editar/{id}")
		public String formEditarProduto(@PathVariable Long id, Model model) {
			Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
			    model.addAttribute("produto", produto);
			    return "estoque/editarProduto";
		}
	
	
	@PostMapping("/registrarProduto")
	public String cadastrarProduto(@Valid ProdutoForm produtoForm, BindingResult result) {
		if(result.hasErrors()) {
			return "RESULT"+result;
		}
		
		Produto produto = produtoForm.converter();
		
		produtoRepository.save(produto);

		MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
		movimentacao.setTipoMovimentacao("ENTRADA");
		movimentacao.setProduto_id(produto);
		movimentacao.setQuantidade(produto.getQuantidade());
		movimentacao.setDataHora(LocalDateTime.now());
		moviRepo.save(movimentacao);
		return "redirect:/produtos/listarProdutos";
	}
	
	
	
	 @PostMapping("/editarProduto/{id}")
	 public String editarProduto(@PathVariable Long id,@RequestParam("quantidade") int novaQuantidade, @ModelAttribute Produto produtoAtualizado) {
	     
	     Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
	
	     int quantidadeAnterior = produto.getQuantidade();

	     produto.setNome(produtoAtualizado.getNome());
	     produto.setQuantidade(novaQuantidade);
	     produto.setPreco_custo(produtoAtualizado.getPreco_custo());
	     produto.setUnidadeMedida(produtoAtualizado.getUnidadeMedida());
	     produto.setDataCadastro(LocalDateTime.now());
	     
	     produtoRepository.save(produto);

	   
	     MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
	     movimentacao.setTipoMovimentacao("ATUALIZAÇÃO");
	     movimentacao.setProduto_id(produto);
	     movimentacao.setQuantidade(novaQuantidade - quantidadeAnterior);
	     movimentacao.setDataHora(LocalDateTime.now());
	     moviRepo.save(movimentacao);

	     return "redirect:/produtos/listarProdutos";
	 }
	 
	 @GetMapping("/excluir/{id}")
	 public String excluirProduto(@PathVariable Long id) {
		 moviRepo.deleteByProdutoId(id);
	     produtoRepository.deleteById(id);
	     return "redirect:/produtos/listarProdutos";
	 }
	
}
