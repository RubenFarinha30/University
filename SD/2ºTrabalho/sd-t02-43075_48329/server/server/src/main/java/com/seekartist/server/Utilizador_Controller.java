package com.seekartist.server;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/utilizador")

public class Utilizador_Controller {

    private Utilizador_Service utilizadorService;

    @Autowired
    public Utilizador_Controller(Utilizador_Service utilizadorService){
        this.utilizadorService = utilizadorService;
    }

    @PostMapping(value = "/addUtilizador", produces = "application/json")
    public void addUtilizador(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email,
                           @RequestParam("type") String type){
        utilizadorService.addUtilizador(username, password, email, type);
    }


    @GetMapping(value="/getUtilizadorByUsername")
    public Optional<Utilizador_Entity> getUtilizadorByUsername(@PathParam("username") String username){
        return utilizadorService.getUtilizadorByUsername(username);
    }
    
    @GetMapping(value="/getUtilizadorByPassword")
    public Optional<Utilizador_Entity> getUtilizadorByPassword(@PathParam("password") String password){
        return utilizadorService.getUtilizadorByPassword(password);
    }

    @GetMapping(value="/getAllUtilizador")
    public List<Utilizador_Entity> getAllUtilizador(){
        return utilizadorService.getAllUtilizador();
    }

    @GetMapping(value="/getAllByType")
    public List<Utilizador_Entity> getAllByType(@PathParam("type") String type){
        return utilizadorService.getAllByType(type);
    }

    @GetMapping(value="/getUtilizadorsByEmail")
    public Optional<Utilizador_Entity> getUtilizadorByEmail(@PathParam("email") String email){
        return utilizadorService.getUtilizadorByEmail(email);
    }


    @PutMapping(value="/changetype")
    public void change_state(@PathParam("username") String username){
        utilizadorService.changetype(username);
    }

}