package com.mango.web;

import com.mango.web.model.Contatto;
import com.mango.web.respository.Contattirepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.ArrayList;
import java.util.NoSuchElementException;

@Controller
public class AppController implements WebMvcConfigurer {

    @Autowired
    Contattirepo contattiRepo;


    @GetMapping("/contatti")
    public String contatti(Model model) {
        ArrayList<Contatto> contatti = (ArrayList<Contatto>) contattiRepo.findAll();
        model.addAttribute("contatti", contatti);
        return "contatti";
    }

    @GetMapping("/contatti/{id}")
    public String contatto(@PathVariable Long id, Model model) {
        Contatto contatto = contattiRepo.findById(id).get();
        model.addAttribute("contatto", contatto);
        return "contatto";
    }

    @GetMapping("/contatti/{id}/edit")
    public String contattoEdit(@PathVariable Long id, Model model) {
        Contatto contatto = contattiRepo.findById(id).get();
        model.addAttribute("contatto", contatto);


        return "edit";

    }

    @PostMapping("/contatti/{id}/edit")
    public String contattoEditPost(@PathVariable Long id, Contatto contatto) {
        contatto.setId(id);
        contattiRepo.save(contatto);
        return "redirect:/contatti/" + contatto.getId();
    }

    @GetMapping("/contatti/add")
    public String aggungiContatto(Contatto contatto, Model model) {
        model.addAttribute("contatto", contatto);
        return "add";
    }

    @PostMapping("/contatti/add")
    public String aggiungiContattoPost(@Valid Contatto contatto, BindingResult result) {
        if (result.hasErrors()) {
            return "add";
        }

        contattiRepo.save(contatto);
        return "redirect:/contatti/" + contatto.getId();
    }

    @GetMapping("/contatti/{id}/delete")
    public String cancellaContatto(@PathVariable Long id) {
        contattiRepo.deleteById(id);
        return "redirect:/contatti";

    }


    @GetMapping("/search")
    public String search(@RequestParam(value = "key", defaultValue = "") String key, Model model) {
        ArrayList<Contatto> results = contattiRepo.findByNomeContainingOrCognomeContainingOrTelefonoContaining(key, key, key);

        model.addAttribute("contatti", results);
        return "search";


    }


}
