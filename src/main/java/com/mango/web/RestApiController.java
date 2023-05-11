package com.mango.web;

import com.mango.web.model.Contatto;
import com.mango.web.respository.Contattirepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class RestApiController {


    @Autowired
    public Contattirepo contattirepo;
    private
    @Value("${application.name}")
    String appName;
    @Value("${application.version}")
    String appVersion;

    @GetMapping("/api/v1/version")
    public String versione() {
        return "{\"applicaton\": \"" + appName + " " + appVersion + "\"}";
    }

    @GetMapping("/api/v1/contatti")
    public ArrayList<Contatto> contatti() {
        return (ArrayList<Contatto>) contattirepo.findAll();

    }

    @GetMapping("/api/v1/contatti/{id}")
    public Contatto contattoEdit(@PathVariable Long id) throws ContattoNonTrovatoExcepton {
        Optional<Contatto> cont = contattirepo.findById(id);
        if (cont.isEmpty()) {
            throw new ContattoNonTrovatoExcepton();
        }
        return cont.get();
    }

    @GetMapping("/api/v1/contatti/search")
    public ArrayList<Contatto> risultati(@RequestParam(value = "key", defaultValue = "") String key) {
        return contattirepo.findByNomeContainingOrCognomeContainingOrTelefonoContaining(key, key, key);
    }

    @PostMapping("/api/v1/contatti")
    public ResponseEntity<Contatto> aggiungiContattoPost(@RequestBody Contatto contatto) {
        contattirepo.save(contatto);
        return new ResponseEntity<>(contatto, HttpStatus.OK);

    }

    @DeleteMapping("/api/v1/contatti/{id}")

    public ResponseEntity<Void> contattoDelete(@PathVariable Long id) {
        if (contattirepo.existsById(id)) {
            contattirepo.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/v1/contatti/{id}")
    public Contatto editContatto(@PathVariable Long id, @RequestBody Contatto contatto) {
        contatto.setId(id);
        return contattirepo.save(contatto);
    }

    @ExceptionHandler(NoSuchElementException.class)

    public ResponseEntity<ErrorResponse> hFrancoECiccio(NoSuchElementException exception) {
        ErrorResponse resp = new ErrorResponse("voce non trovata", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<ErrorResponse>(resp, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ContattoNonTrovatoExcepton.class)

    public ResponseEntity<ErrorResponse> ContattoNonTrovato(ContattoNonTrovatoExcepton exception) {
        ErrorResponse resp = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<ErrorResponse>(resp, HttpStatus.NOT_FOUND);
    }
}