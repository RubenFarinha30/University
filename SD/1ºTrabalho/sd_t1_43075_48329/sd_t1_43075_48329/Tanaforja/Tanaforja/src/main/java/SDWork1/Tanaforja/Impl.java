package SDWork1.Tanaforja;

import java.util.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Impl extends UnicastRemoteObject implements Tanaforja, java.io.Serializable {
    static Statement stmt;
    // deve existir um construtor
    public Impl(Statement stmt) throws java.rmi.RemoteException {
        this.stmt = stmt;
        
    }
    LinkedList<String> list = new LinkedList<String>();
    
    public LinkedList<String> obterListadeArtistas() throws java.rmi.RemoteException {
        try {
            ResultSet rs = stmt.executeQuery("SELECT name from artista");
            list.clear();
            while (rs.next())
                {
                list.add(String.format("Name: %s ",rs.getString(1)));
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public LinkedList<String> obterListadeArtistasPorArte(String artist_type) throws java.rmi.RemoteException {
        try {
            ResultSet rs = stmt.executeQuery("SELECT name FROM artista WHERE artist_type = '" + artist_type + "'");
            list.clear();
            while (rs.next())
                {
                list.add(String.format("Name: %s ",rs.getString(1)));
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }  
    
    public LinkedList<String> obterListaLocal() throws java.rmi.RemoteException {
        try {
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT location FROM artista WHERE is_acting='Sim'");
            list.clear();
            while (rs.next())
                {
                list.add(String.format("Location: %s ",rs.getString(3)));
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    } 

    @Override
    public void registarArtista(String name, String artist_type, String location, String is_acting) throws java.rmi.RemoteException {
        try {
        	stmt.executeUpdate("INSERT INTO artista VALUES(DEFAULT, '" + name + "','" + artist_type + "','" + location + "','" + is_acting + "')"); 
        } catch (SQLException ex) {
            Logger.getLogger(Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviarDonativo(int artist_id, int donation_amount) throws java.rmi.RemoteException {
        try {
        	stmt.executeUpdate("INSERT INTO donation(id, donation_amount) VALUES('" + artist_id + "','" + donation_amount + "')"); 
        } catch (SQLException ex) {
            Logger.getLogger(Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LinkedList<String> obterListadeDonativos(int artist_id) throws java.rmi.RemoteException {
        try {
            ResultSet rs = stmt.executeQuery("SELECT (id, donation_id, donation_amount) FROM donation WHERE id = '" + artist_id + "'");
            list.clear();
            while (rs.next()) {
                list.add(String.format("id: %d | donation_id: %d | donation_amount: %d", rs.getInt(2),rs.getInt(1),rs.getInt(3)));
            }
    
        } catch (SQLException ex) {
            Logger.getLogger(Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    	}

        public LinkedList<String> obterListadeArtistasEstado(String estado) throws java.rmi.RemoteException {
        try {
            //definir melhor os estados
        	ResultSet rs = stmt.executeQuery("SELECT * FROM artista WHERE approval_status = '" + estado + "'");
            list.clear();
            while (rs.next())
                {
                list.add(String.format("Id: %s | Nome: %s | Estado: %s",rs.getString(1), rs.getString(2), rs.getString(6))); //finalizar
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void aprovarArtistas(int artist_id) throws java.rmi.RemoteException {
        try {
        	stmt.executeUpdate("UPDATE artista SET approval_status = 'Aprovado' WHERE artist_id = '" + artist_id + "'");
        } catch (SQLException ex) {
            Logger.getLogger(Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
            public LinkedList<String> obterListadeArtistasLocalizacao(String location) throws java.rmi.RemoteException {
        try {
            //definir melhor os estados
        	ResultSet rs = stmt.executeQuery("SELECT name FROM artista WHERE location = '" + location + "' and is_acting = sim");
            list.clear();
            while (rs.next())
                {
                list.add(String.format("Nome: %s ", rs.getString(2))); //finalizar
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    

}
