package com.projeto.sistemaDeVendas.controller;

import com.projeto.sistemaDeVendas.models.Estado;
import com.projeto.sistemaDeVendas.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping("/cadastroEstado")
    public ModelAndView cadastrar(Estado estado){
        ModelAndView mv = new ModelAndView("/cadastroEstado");
        mv.addObject("estado",estado);
        return mv;
    }

    @PostMapping("/salvarEstado")
    public ModelAndView salvar(Estado estado, BindingResult result){

        if(result.hasErrors()){
            return cadastrar(estado);
        }
        estadoRepository.saveAndFlush(estado);
        return cadastrar(new Estado());
        }


    @GetMapping("/listaEstados")
    public String listaEstados(Model model) {
        model.addAttribute("listaEstados", estadoRepository.findAll());
        return "listaEstados";
    }


    @GetMapping("/removerEstado/{id}")
    public String removerEstado(@PathVariable Long id){
        estadoRepository.deleteById(id);
        return "redirect:/listaEstados";

    }

    @GetMapping("/editarEstado/{id}")
    public ModelAndView editarEstado(@PathVariable Long id){
        ModelAndView mv = new ModelAndView("cadastroEstado");
        Estado estado = estadoRepository.findById(id).orElse(null);
        mv.addObject("estado",estado);
        return mv;
    }



}
