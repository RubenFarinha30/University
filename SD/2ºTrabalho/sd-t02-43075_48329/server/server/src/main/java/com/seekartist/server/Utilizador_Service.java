package com.seekartist.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.*;

@Service
public class Utilizador_Service {

    private Utilizador_Entity Utilizador;
    private final Utilizador_Repository UtilizadorRepository;

    @Autowired
    public Utilizador_Service(Utilizador_Repository UtilizadorRepository){
        this.UtilizadorRepository = UtilizadorRepository;
    }

    @PostMapping
    public void addUtilizador(String username, String password,  String email, String type){
        Utilizador = new Utilizador_Entity(username, password, email, type);
        UtilizadorRepository.save(Utilizador);
    }

    @GetMapping
    public List<Utilizador_Entity> getAllUtilizador(){
        return UtilizadorRepository.findAll();
    }

    
    @GetMapping
    public List<Utilizador_Entity> getAllByType(String type){
        
        List<Utilizador_Entity> list;
        list = UtilizadorRepository.getAllByType(type).orElseThrow( () -> new IllegalStateException("N達o existem Utilizadors deste tipo no sistema de momento."));
        
        return list;
    }

    @GetMapping
    public Optional<Utilizador_Entity> getUtilizadorByUsername(String username){
       return UtilizadorRepository.getUtilizadorByUsername(username);
    }
    
    @GetMapping
    public Optional<Utilizador_Entity> getUtilizadorByPassword(String password){
       return UtilizadorRepository.getUtilizadorByPassword(password);
    }
    
    @GetMapping
    public Optional<Utilizador_Entity> getUtilizadorByEmail(String email){
       return UtilizadorRepository.getUtilizadorByEmail(email);
    }

    @GetMapping
    public void changetype(String username){
        
        Utilizador_Entity u= UtilizadorRepository.getUtilizadorByUsername(username).orElseThrow( () -> new IllegalStateException("N達o existem utilizadores deste tipo!\n"));
        
        if(u.getType().equals("geral"))
            u.setType("admin");
        else
            u.setType("geral");
        
        UtilizadorRepository.save(u);
    }
    

   
}