package com.seekartist.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.*;


@Service
public class Artista_Service {

    private Artista_Entity artista;
    private final Artista_Repository artistaRepository;

    @Autowired
    public Artista_Service(Artista_Repository artistaRepository){
        this.artistaRepository = artistaRepository;
    }

    @PostMapping
    public void addArtista(String name, String artist_type, Integer latitude, Integer longitude, String is_acting, String approval){
        artista = new Artista_Entity(name, artist_type, latitude, longitude, is_acting, approval);
        artistaRepository.save(artista);
    }

    @GetMapping
    public Optional<Artista_Entity> getArtistaById(Integer id){
       return artistaRepository.getArtistaById(id);
    }
    
    @GetMapping
    public Optional<Artista_Entity> getArtistaByName(String name){
       return artistaRepository.getArtistaByName(name);
    }

    @GetMapping
    public List<Artista_Entity> getAllArtistas(){
        return artistaRepository.findAll();
    }

    @GetMapping
    public List<Artista_Entity> getAllByApproval(String approval){
        
        List<Artista_Entity> list;
        list = artistaRepository.getAllByApproval(approval).orElseThrow( () -> new IllegalStateException("Não existem artistas com este estado no sistema de momento."));
        
        return list;
    }
    
    @GetMapping
    public List<Artista_Entity> getAllByType(String type){
        
        List<Artista_Entity> list;
        list = artistaRepository.getAllByType(type).orElseThrow( () -> new IllegalStateException("Não existem artistas deste tipo no sistema de momento."));
        
        return list;
    }
    
    @GetMapping
    public List<Artista_Entity> getAllByLatitude(Integer latitude){
        
        List<Artista_Entity> list;
        list = artistaRepository.getAllByLatitude(latitude).orElseThrow( () -> new IllegalStateException("Não existem artistas neste local no sistema de momento."));
        
        return list;
    }
    
    @GetMapping
    public List<Artista_Entity> getAllByLongitude(Integer longitude){
        
        List<Artista_Entity> list;
        list = artistaRepository.getAllByLongitude(longitude).orElseThrow( () -> new IllegalStateException("Não existem artistas neste local no sistema de momento."));
        
        return list;
    }
    
    @GetMapping
    public void change_state(Integer id){
        
        Artista_Entity a= artistaRepository.findById(id).orElseThrow( () -> new IllegalStateException("Artista não Existe!\n"));
        
        if(a.getApproval().equals("inativo"))
            a.setApproval("ativo");
        else
            a.setApproval("inativo");
        
        artistaRepository.save(a);
    }
    
    @GetMapping
    public void change_location(String name, Integer latitude, Integer longitude){
        
        Artista_Entity a= artistaRepository.getArtistaByName(name).orElseThrow( () -> new IllegalStateException("Artista não Existe!\n"));
        
        a.setLatitude(latitude);
        a.setLongitude(longitude);
        
        artistaRepository.save(a);
    }
    
    @GetMapping
    public void approve_state(Integer id){
        
        Artista_Entity a= artistaRepository.findById(id).orElseThrow( () -> new IllegalStateException("Artista não Existe!\n"));
        
        if(a.getApproval().equals("inativo"))
            a.setApproval("ativo");
        
        artistaRepository.save(a);
    }

	public List<Artista_Entity> getAllByActing(String acting) {
		List<Artista_Entity> list;
        list = artistaRepository.getAllByActing(acting).orElseThrow( () -> new IllegalStateException("Não existem artistas a atuar."));
        
        return list;
	}
   
}
