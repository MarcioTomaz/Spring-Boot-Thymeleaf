package com.marzio.curso.boot.web.controller;

import com.marzio.curso.boot.domain.Cargo;
import com.marzio.curso.boot.domain.UF;
import java.time.format.DateTimeFormatter;

import com.marzio.curso.boot.web.validator.FuncionarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.marzio.curso.boot.domain.Funcionario;
import com.marzio.curso.boot.service.CargoService;
import com.marzio.curso.boot.service.FuncionarioService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioControler {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private CargoService cargoService;

	@InitBinder
	public void initBinder(WebDataBinder binder){

		binder.addValidators(new FuncionarioValidator());
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Funcionario funcionario) {

		ModelAndView mv = new ModelAndView("funcionario/cadastro");

		return mv;
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {

		model.addAttribute("funcionarios",funcionarioService.buscarTodos());
		return "funcionario/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr ) {

		if(result.hasErrors()){
			return "funcionario/cadastro";
		}

		funcionarioService.salvar(funcionario);
		attr.addFlashAttribute("success","Funcionário inserido com sucesso.");

		return "redirect:/funcionarios/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model){
		model.addAttribute("funcionario",funcionarioService.buscarPorId(id));
		return "funcionario/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Funcionario funcionario,BindingResult result, RedirectAttributes attr) {

		if(result.hasErrors()){
			return "funcionario/cadastro";
		}

		funcionarioService.editar(funcionario);
		attr.addFlashAttribute("success","Funcionario editado com sucesso");

		return "redirect:/funcionarios/cadastrar";

	}


	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {

		funcionarioService.excluir(id);
		attr.addFlashAttribute("success", "Funcionario removido com sucesso.");

		return "redirect:/funcionarios/listar";
	}

	@GetMapping("/buscar/nome")
	public ModelAndView getPorNome(@RequestParam("nome") String nome){

		ModelAndView mv = new ModelAndView("funcionario/lista");

		mv.addObject("funcionarios",funcionarioService.buscarPorNome(nome));

		return mv;
	}

	@GetMapping("/buscar/cargo")
	public ModelAndView getPorCargo(@RequestParam("id") Long id){

		ModelAndView mv = new ModelAndView("funcionario/lista");

		mv.addObject("funcionarios",funcionarioService.buscarPorCargo(id));

		return mv;
	}

	@GetMapping("buscar/data")
	public ModelAndView getPorDatas(@RequestParam(name = "entrada",  required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate entrada,
									@RequestParam(name = "saida",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saida){

		ModelAndView mv = new ModelAndView("funcionario/lista");

		mv.addObject("funcionarios",funcionarioService.buscarPorDatas(entrada,saida));

		return mv;
	}

	@ModelAttribute("cargos")
	public List<Cargo> getCargos(){
		return cargoService.buscarTodos();
	}

	@ModelAttribute("ufs")
	public UF[] getUFs(){
		return UF.values();
	}
	
	
	
	
	
}
