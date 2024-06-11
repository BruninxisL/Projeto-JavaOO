package com.example.AppPastelaria.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.AppPastelaria.model.Comanda;
import com.example.AppPastelaria.model.ItemComanda;
import com.example.AppPastelaria.model.Pratos;
import com.example.AppPastelaria.model.Status;
import com.example.AppPastelaria.model.User;
import com.example.AppPastelaria.repository.ComandaRepository;
import com.example.AppPastelaria.repository.ItemComandaRepository;
import com.example.AppPastelaria.repository.PratoRepository;
import com.example.AppPastelaria.repository.UserRepository;
import com.example.AppPastelaria.service.DataService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
@RequestMapping(value = "pedidos")
public class UserPedidoController {

	
	@Autowired
	private ComandaRepository comandaRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PratoRepository pratoRepository;
	
	@Autowired
	private ItemComandaRepository itemComandaRepository;
	
	@Autowired
	private DataService dataService;
	

    private static final Logger logger = LoggerFactory.getLogger(UserPedidoController.class);
	
	private List<ItemComanda> itensComanda = new ArrayList<>();
	
	 @GetMapping("/listarOpcoes")
	    public String index(Model model, @RequestParam(required = false) String status) {
	        List<Comanda> comandas = comandaRepository.findByStatus(Status.RECEBIDO);

	        
	        
	        List<LocalDateTime> datasAbertura = new ArrayList<>();
	        for (Comanda comanda : comandas) {
	            datasAbertura.add(comanda.getDataAbertura());
	        }

	        
	        List<String> datasAberturaFormatadas = dataService.formatDates(datasAbertura);

	        
	        model.addAttribute("comandas", comandas);
	        model.addAttribute("datasAberturaFormatadas", datasAberturaFormatadas);

	        return "pedido/gerarpedido";
	    }

	
	@GetMapping("/preparando")
	public String listarPedidosPreparando(Model model) {
		List<Comanda> comandas = comandaRepository.findByStatus(Status.PREPARANDO);

        
        
        List<LocalDateTime> datasAbertura = new ArrayList<>();
        for (Comanda comanda : comandas) {
            datasAbertura.add(comanda.getDataAbertura());
        }

        
        List<String> datasAberturaFormatadas = dataService.formatDates(datasAbertura);

        
        model.addAttribute("comandas", comandas);
        model.addAttribute("datasAberturaFormatadas", datasAberturaFormatadas);

	    return "pedido/pedidoPreparando"; 
	}
	

	@GetMapping("/cancelados")
	public String listarPedidosCancelados(Model model) {
		List<Comanda> comandas = comandaRepository.findByStatus(Status.CANCELADO);

        
        
        List<LocalDateTime> datasAbertura = new ArrayList<>();
        for (Comanda comanda : comandas) {
            datasAbertura.add(comanda.getDataAbertura());
        }

        
        List<String> datasAberturaFormatadas = dataService.formatDates(datasAbertura);

        
        model.addAttribute("comandas", comandas);
        model.addAttribute("datasAberturaFormatadas", datasAberturaFormatadas);

	    return "pedido/pedidoCancelado"; 
	}

	@GetMapping("/prontos")
	public String listarPedidosProntos(Model model) {
		List<Comanda> comandas = comandaRepository.findByStatus(Status.PRONTO);

        
        
        List<LocalDateTime> datasAbertura = new ArrayList<>();
        for (Comanda comanda : comandas) {
            datasAbertura.add(comanda.getDataAbertura());
        }

        
        List<String> datasAberturaFormatadas = dataService.formatDates(datasAbertura);

        
        model.addAttribute("comandas", comandas);
        model.addAttribute("datasAberturaFormatadas", datasAberturaFormatadas);

	    return "pedido/pedidoPronto"; 
	}

	@GetMapping("/finalizados")
	public String listarPedidosFinalizados(Model model) {
		List<Comanda> comandas = comandaRepository.findByStatus(Status.FINALIZADO);

        
        
        List<LocalDateTime> datasAbertura = new ArrayList<>();
        for (Comanda comanda : comandas) {
            datasAbertura.add(comanda.getDataAbertura());
        }

        
        List<String> datasAberturaFormatadas = dataService.formatDates(datasAbertura);

        
        model.addAttribute("comandas", comandas);
        model.addAttribute("datasAberturaFormatadas", datasAberturaFormatadas);

	    return "pedido/pedidoFinalizado"; 
	}


	
	 @GetMapping("/gerarpedido")
	    public String mostrarPaginaDePedido(Model model) {
	        List<Pratos> listaDePratos = pratoRepository.findAll();
	        model.addAttribute("pratos", listaDePratos);
	        model.addAttribute("itensComanda", itensComanda);
	       
	        
	      
	        return "pedido/cadastrarPedido";
	    }
	
	 @PostMapping("/adicionarItem")
	    public String adicionarItem(@RequestParam Long pratoId, @RequestParam int quantidade,@RequestParam String observacoes, Model model) {
		 Optional<Pratos> optionalPrato = pratoRepository.findById(pratoId);
	        if (optionalPrato.isPresent()) {
	            Pratos prato = optionalPrato.get();
	            ItemComanda item = new ItemComanda();
	            item.setPrato_id(prato);
	            item.setQuantidade(quantidade);
	            item.setPrecoUnitario(prato.getPreco());
	            item.setObservacoes(observacoes);
	            itensComanda.add(item);
	        }

	        model.addAttribute("pratos", pratoRepository.findAll());
	        model.addAttribute("itensComanda", itensComanda);
	        model.addAttribute("mensagem", "Pedido adicionado com sucesso!");
	        return "pedido/cadastrarPedido";
	    }
	 @PostMapping("/finalizar")
	 public String finalizarPedido(@ModelAttribute Comanda comanda, @RequestParam("nome") String nome ,Model model, Principal principal) {
		System.out.println("identificador" + nome);
		 String username = principal.getName();
		 model.addAttribute("username", username);
			
		 User user = userRepository.findByEmail(username);
		 model.addAttribute("user", user);
		 
		 comanda = new Comanda();
	     comanda.setDataAbertura(LocalDateTime.now());
	     comanda.setNome(nome);
	     comanda.setUserId(user);
	     comanda.setDataFechamento(LocalDateTime.now());
	     
	     comandaRepository.save(comanda);
	     
	     for (ItemComanda item : itensComanda) {
	         item.setComanda_id(comanda);
	         itemComandaRepository.save(item);
	     }
	    
	     itensComanda.clear();
	     return "/pedido/pedidoSucess";
	 }



	 @GetMapping("/editar/{id}")
	 public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
	    
	     Comanda comanda = comandaRepository.findById(id)
	         .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
	     
	     
	     List<ItemComanda> itensComanda = comanda.getItensComanda();
	     
	     
	     ItemComanda itemComanda = itensComanda.isEmpty() ? new ItemComanda() : itensComanda.get(0);
	     logger.info("Valor de observacoes: {}", itemComanda.getObservacoes());
	   
	     model.addAttribute("comanda", comanda);
	     model.addAttribute("itemComanda", itemComanda); 
	     model.addAttribute("itensComanda",itensComanda);
	     model.addAttribute("statusList", Status.values());
	     model.addAttribute("pratos", pratoRepository.findAll()); 

	     return "pedido/editarPedido";
	 }



	 @PostMapping("/editar/{id}")
	 public String editarPedido(@PathVariable Long id, @ModelAttribute Comanda comandaAtualizada,Model model) {
		 logger.info("ID do pedido recebido: {}", id);
	        
	   
	        logger.info("Comanda atualizada recebida: {}", comandaAtualizada);
	        logger.info("Quantidade de itens na comanda atualizada: {}", comandaAtualizada.getItensComanda().size());
	       

	     Comanda comanda = comandaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
	     comanda.setNome(comandaAtualizada.getNome());
	     comanda.setStatus(comandaAtualizada.getStatus());
	     logger.info("Quantidade de itens na comanda atualizada: {}", comandaAtualizada.getItensComanda().size());

	     List<ItemComanda> itensAtualizados = comandaAtualizada.getItensComanda();

	   
	     for (ItemComanda itemAtualizado : itensAtualizados) {
	         
	         ItemComanda item = itemComandaRepository.findById(itemAtualizado.getId())
	                 .orElseThrow(() -> new IllegalArgumentException("Item de pedido não encontrado"));

	        
	         item.setPrato_id(itemAtualizado.getPrato_id());
	         item.setQuantidade(itemAtualizado.getQuantidade());
	         item.setObservacoes(itemAtualizado.getObservacoes());

	         
	         itemComandaRepository.save(item);
	     }

	   
	     comandaRepository.save(comanda);

	     return "/pedido/pedidoUpdateSucess";
	 }
	 
	 @GetMapping("/verificarPedido")
	 public String verificar(Model model) {
		 List<Pratos> listaDePratos = pratoRepository.findAll();
		 
		 model.addAttribute("itensComanda", itensComanda);
		 model.addAttribute("pratos", listaDePratos);
		 return"/pedido/finalizarPedido";
	 }
	 
	 

	 @PostMapping("/excluir/{id}")
	 public String excluirPedido(@PathVariable Long id, Comanda comanda) {
	     
		 itemComandaRepository.deleteByComandaId(id);
	     comandaRepository.deleteById(id);
	     return "redirect:/pedidos/listarOpcoes";
	 }

}
