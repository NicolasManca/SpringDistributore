package com.mango.web;

public class ContattoNonTrovatoExcepton extends Exception {
    @Override
    public String getMessage() {

        return "Contatto non trovato";
    }

}
