package br.fai.dogs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.entities.FormaDePagamento;
import br.fai.dogs.model.entities.Passeio;
import br.fai.dogs.model.entities.Recebimento;
import br.fai.dogs.service.FormaDePagamentoService;
import br.fai.dogs.service.PasseioService;
import br.fai.dogs.service.PessoaService;
import br.fai.dogs.service.RecebimentoService;

@Controller
@RequestMapping("/recebimento")
public class RecebimentoController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private RecebimentoService recebimentoService;
	
	@Autowired
	private PasseioService passeioService;
	
	@Autowired
	private FormaDePagamentoService formaDePagamentoService;
	
	@GetMapping("/profissional/a-receber")
	public String getPageAReceber(Model model) {
		
		Long profissional_id = pessoaService.sessaoAtual().getId();
		
		List<Passeio> passeios = recebimentoService.readPasseiosSemRecebimentoPorProfissional(profissional_id);
		List<FormaDePagamento> formasDePagamento = formaDePagamentoService.readAll();
		
		model.addAttribute("passeios", passeios);
		model.addAttribute("formasDePagamento", formasDePagamento);
		
		return "/profissional/recebimento/a-receber";
		
	}
	
	@GetMapping("/profissional/informar-recebimento/{id}")
	public String getPageInformarRecebimento(@PathVariable("id") Long id, Model model) {
		
		Passeio passeio = passeioService.readById(id);
		List<FormaDePagamento> formasDePagamento = formaDePagamentoService.readAll();
				
		model.addAttribute("passeio", passeio);
		model.addAttribute("formasDePagamento", formasDePagamento);
		model.addAttribute("passeioId", id);
		model.addAttribute("dataAtual", Helper.getDataAtual("yyyy-MM-dd"));
		
		return "/profissional/recebimento/informar-recebimento";
		
	}
	
	@PostMapping("/profissional/post-informar-recebimento")
	public String postInformarRecebimento(Recebimento recebimento, RedirectAttributes redirect) {
		
		boolean response = recebimentoService.create(recebimento);
		
		if(response) {
			redirect.addFlashAttribute("message", "Recebimento informado com sucesso.");
			return "redirect:/recebimento/profissional/a-receber";
		}else {
			redirect.addFlashAttribute("message", "Ocorreu um problema ao informar o recebimento.");
			return "redirect:/recebimento/profissional/informar-recebimento/" + recebimento.getPasseioId();
		}
		
	}
	
	@GetMapping("/profissional/recebido")
	public String getPageRecebido(Model model) {
		
		Long profissional_id = pessoaService.sessaoAtual().getId();
		
		List<Recebimento> recebimentos = recebimentoService.readAllByProfissionalId(profissional_id);
		
		model.addAttribute("recebimentos", recebimentos);
		
		return "/profissional/recebimento/recebido";
		
	}
	
}
