package com.example.AppPastelaria.controller;


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



import com.example.AppPastelaria.dto.PratoForm;

import com.example.AppPastelaria.model.Pratos;

import com.example.AppPastelaria.repository.ItemComandaRepository;

import com.example.AppPastelaria.repository.PratoRepository;
import com.example.AppPastelaria.repository.ProdutoRepository;






@Controller
@RequestMapping(value="pratos")
public class PratosController {
	@Autowired
	private PratoRepository pratoRepo;
	
	@Autowired 
	private ProdutoRepository produtoRepository;
	
	
	@Autowired
	private ItemComandaRepository itemComandaRepository;
	
	@GetMapping("/listarPratos")
	public String index(Model model) {
		List<Pratos> pratos = (List<Pratos>)pratoRepo.findAll();
		model.addAttribute("pratos", pratos);
		return "pratos/index";
	}
	
	@GetMapping("/registrarForm")
		public String registrarForm(Model model) {
		 	PratoForm pratoForm = new PratoForm();
	        pratoForm.setProdutos(produtoRepository.findAll());
	        model.addAttribute("pratoForm", pratoForm);
			return "pratos/cadastrarPrato";
	}
	
	@GetMapping("/editar/{id}")
	public String formEditarPrato(@PathVariable Long id, Model model) {
		Pratos prato = pratoRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
		    model.addAttribute("prato", prato);
		    return "pratos/editarPrato";
	}

	
	
	@PostMapping("/registrar")
	public String cadastrarPrato(@ModelAttribute("pratoForm") PratoForm pratoForm, BindingResult result) {
	    if (result.hasErrors()) {
	        return "pratos/cadastrarPrato";
	    }
	    Pratos prato = pratoForm.converter();
	    pratoRepo.save(prato);
	    
	    return"redirect:/pratos/listarPratos";
	}



	 @PostMapping("/editarPrato/{id}")
	 public String editarProduto(@PathVariable Long id, @ModelAttribute Pratos pratoAtualizado) {
	     
	     Pratos prato = pratoRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
	     prato.setNome(pratoAtualizado.getNome());
	     prato.setPreco(pratoAtualizado.getPreco());
	     prato.setDescricao(pratoAtualizado.getDescricao());
	   
	     
	     
	     pratoRepo.save(prato);

	     return "redirect:/pratos/listarPratos";
	 }
	 
	 @GetMapping("/excluir/{id}")
	 public String excluirPrato(@PathVariable Long id) {
		 itemComandaRepository.deleteByPratoId(id);
		 pratoRepo.deleteById(id);
	     return "redirect:/pratos/listarPratos";
	 }
	
	
}
