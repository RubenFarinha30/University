package SDWork1.Tanaforja;

import java.util.LinkedList;
import java.util.List;
import java.util.*;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface Tanaforja extends java.rmi.Remote{
    
    public void registarArtista(String name, String artist_type, String location, String is_acting) throws java.rmi.RemoteException; 
    
    public LinkedList<String> obterListadeArtistas() throws java.rmi.RemoteException; 

    public LinkedList<String> obterListaLocal() throws java.rmi.RemoteException; 
    
    public LinkedList<String> obterListadeArtistasPorArte(String artist_type) throws java.rmi.RemoteException; 
    
    public void enviarDonativo(int artist_id, int donation_amount) throws java.rmi.RemoteException;   
    
    public LinkedList<String> obterListadeDonativos(int artist_id) throws java.rmi.RemoteException;  

    public LinkedList<String> obterListadeArtistasEstado(String estado) throws java.rmi.RemoteException;

    public void aprovarArtistas(int artist_id ) throws java.rmi.RemoteException;  
    
    public LinkedList<String> obterListadeArtistasLocalizacao(String location) throws java.rmi.RemoteException;

}