package SDWork1.Tanaforja;

/*EXECUTAR
java -classpath build/classes SDWork1.Tanaforja.ClienteGeral localhost 9000
*/
import java.util.List;
import java.util.Scanner;
import java.util.*;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteGeral {
    public static void main(String args[]) {
	String regHost = "localhost";
	String regPort = "9000";  // porto do binder
	regHost= args[0];
	regPort= args[1];

	try {
	    // objeto que fica associado ao proxy para objeto remoto
	    Tanaforja obj =
	      (Tanaforja) java.rmi.Naming.lookup("rmi://" 
                      + regHost +":"+regPort +"/tanaforja");
            
           Scanner sc = new Scanner(System.in);
           
           String name = null;
           String location = null;
           String is_acting = null;
           String artist_type = null;
           String aux = null;
           int artist_id;
           int donation_amount;
           
           
          
           String o = null;
           int option = 0;
           LinkedList<String> l = new LinkedList();
           
            while (true) {

                System.out.println("------MENU------");
                System.out.println("1- Ver lista de artistas");
                System.out.println("2- Registar novo artista");
                System.out.println("3- Ver lista de artistas por local");
                System.out.println("4- Ver lista de artistas por arte");
                System.out.println("5- Ver lista de artistas por local e arte");
                System.out.println("6- Enviar donativo");
                System.out.println("7- Ver a lista de donativos");
                System.out.println("8- Sair");
                System.out.println("----------------");

                System.out.println("Inserir opção: ");
                o = sc.nextLine();
                option = Integer.parseInt(o);
                System.out.println(" ");

		if (option == 1) { //Ver lista de artistas
                    System.out.println("Lista de artistas: ");
                    l = obj.obterListadeArtistas();
                    for (int i = 0; i < l. size(); i++) {
                        System. out. println(l. get(i));
                    }
                    System.out.println(" ");
                    
                }else if(option == 2) {  //Registar novo artista

                    System.out.println("Insira o nome do artista: ");
                    name = sc.nextLine();
                    System.out.println("Insira o estilo de música do artista: ");
                    artist_type = sc.nextLine();
                    System.out.println("Onde está a atuar o artista: ");
                    location = sc.nextLine();
                    System.out.println("O artista está a atuar? (Sim ou Nao): ");
                    is_acting = sc.nextLine();
                    obj.registarArtista(name, artist_type, location, is_acting);

                    
                }else if(option == 3) {  //Obter lista localizações
                    l = obj.obterListaLocal();
                    for (int i = 0; i < l.size(); i++) {
                        System. out. println(l. get(i));
                    }
                    System.out.println(" ");
                    
                }else if(option == 4) {  //Ver lista de artistas por arte
                    System.out.println("Insira o nome do tipo de arte que quer consultar: ");
                    artist_type = sc.nextLine();
                    l = obj.obterListadeArtistasPorArte(artist_type);
                    for (int i = 0; i < l. size(); i++) {
                        System. out. println(l. get(i));
                    }
                    System.out.println(" ");
                }else if(option == 5) { //Ver lista de artistas por arte e arte
                    System.out.println("Insira o nome do local que quer consultar: ");
                    location = sc.nextLine();
                    l = obj.obterListadeArtistasLocalizacao(location);
                    for (int i = 0; i < l. size(); i++) {
                        System. out. println(l. get(i));
                    }
                    System.out.println(" ");
                }else if(option == 6) {  //Enviar Donativo
                    System.out.print("Insira o id do artista: ");
                    aux = sc.nextLine();
                    artist_id = Integer.parseInt(aux);
                
                    System.out.print("Insira o valor do donativo: ");
                    aux = sc.nextLine();
                    donation_amount = Integer.parseInt(aux);
                    obj.enviarDonativo(artist_id, donation_amount);

                }else if(option == 7) {  //Ver a lista de donativos
                    System.out.print("Insira o id do artista: ");
                    aux = sc.nextLine();
                    artist_id = Integer.parseInt(aux);
                    l = obj.obterListadeDonativos(artist_id);
                    for (int i = 0; i < l. size(); i++) {
                        System. out. println(l. get(i));
                    }
                    System.out.println(" ");

                }else if(option == 8) {
                    sc.close();
                    System.exit(0);   

                }else {

                    System.out.println("Invalid Option");
                }
               System.out.println("Deseja continuar? (y/n)");
               aux = sc.nextLine();
               if(aux.equals("y")){
                   continue;
               } else if(aux.equals("n")){
                   sc.close();
                   System.exit(0); 
               } else{
                   System.out.println("Invalid Option");
               }
            }
	} 
	catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
}