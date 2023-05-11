package com.mango.web.respository;

import com.mango.web.model.Contatto;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;


public interface Contattirepo extends CrudRepository<Contatto, Long> {

    ArrayList<Contatto> findByNomeContainingOrCognomeContainingOrTelefonoContaining(String nome, String cognome, String telefono);
}
