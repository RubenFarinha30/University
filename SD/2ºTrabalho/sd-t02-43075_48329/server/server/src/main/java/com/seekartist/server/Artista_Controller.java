package com.seekartist.server;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/artista")

public class Artista_Controller {

    private Artista_Service artistaService;

    @Autowired
    public Artista_Controller(Artista_Service artistaService){
        this.artistaService = artistaService;
    }

    @PostMapping(value = "/addArtista", produces = "application/json")
    public void addArtista(@RequestParam("name") String name,
                           @RequestParam("type") String type,
                           @RequestParam("latitude") Integer latitude,
                           @RequestParam("longitude") Integer longitude,
                           @RequestParam("acting") String acting,
                           @RequestParam("approval") String approval) {
        artistaService.addArtista(name, type, latitude, longitude, acting, approval);
    }


    @GetMapping(value="/getArtista")
    public Optional<Artista_Entity> getArtista(@PathParam("id") Integer id){
        return artistaService.getArtistaById(id);
    }

    @GetMapping(value="/getAllArtistas")
    public List<Artista_Entity> getAllArtistas(){
        return artistaService.getAllArtistas();
    }

    @GetMapping(value="/getAllByEstado")
    public List<Artista_Entity> getAllByApproval(@PathParam("approval") String approval){
        return artistaService.getAllByApproval(approval);
    }
    
    @GetMapping(value="/getArtistaByName")
    public Optional<Artista_Entity> getArtistaByName(@PathParam("name") String name){
        return artistaService.getArtistaByName(name);
    }
    
    @GetMapping(value="/getArtistasByTipoArte")
    public List<Artista_Entity> getAllByType(@PathParam("type") String type){
        return artistaService.getAllByType(type);
    }
    
    
    @GetMapping(value="/getArtistasByLatitude")
    public List<Artista_Entity> getAllByLatitude(@PathParam("latitude") Integer latitude){
        return artistaService.getAllByLatitude(latitude);
    }
    
    @GetMapping(value="/getArtistasByLongitude")
    public List<Artista_Entity> getAllByLongitude(@PathParam("longitude") Integer longitude){
        return artistaService.getAllByLongitude(longitude);
    }
    
    @GetMapping(value="/getArtistasByActing")
    public List<Artista_Entity> getAllByActing(@PathParam("acting") String acting){
        return artistaService.getAllByActing(acting);
    }
    
    @PutMapping(value="/changestate")
    public void change_state(@PathParam("id") Integer id){
        artistaService.change_state(id);
    }
    
    @PutMapping(value="/change_location")
    public void change_state(@PathParam("name") String name, @PathParam("latitude") Integer latitude, @PathParam("longitude") Integer longitude) {
        artistaService.change_location(name, latitude, longitude);
    }
    
    @PutMapping(value="/aprovestate")
    public void aprove_state(@PathParam("id") Integer id){
        artistaService.change_state(id);
    }
}
