package com.validar.cpf.validadorCPF.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.validar.cpf.validadorCPF.models.Cliente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(){
      return "home/index";
    }
    @PostMapping("/valida-cpf")
    public String validarCpf(Cliente cliente, Model model) {
      model.addAttribute("cliente", cliente);
      model.addAttribute("validado", cliente.validarCPF());
      return "home/valida-cpf";
    }
}
