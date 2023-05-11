package com.mango.web.model;

import com.mango.web.ContactNumberConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "contatti")
public class Contatto {
    @Size(min = 2, max = 1024, message = " il campo può contenere massimo 1024 caratteri")
    @NotBlank(message = "il campo non può essere vuoto")
    String nome;
    @Size(min = 2, max = 1024, message = " il campo può contenere massimo 1024 caratteri")
    @NotBlank(message = "il campo non può essere vuoto")
    String cognome;

    @Size(min = 2, max = 1024, message = " il campo può contenere massimo 1024 caratteri")
    @NotBlank(message = "il campo non può essere vuoto")
    @ContactNumberConstraint
    String telefono;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    public Contatto(String nome, String cognome, String telefono, Long id) {
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.id = id;

    }

    public Contatto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return nome + " " + cognome + " " + telefono;

    }
}
