package SDWork1.Tanaforja;
/*EXECUTAR
java -classpath build/classes SDWork1.Tanaforja.ClienteAdmin localhost 9000
*/

import java.util.List;
import java.util.Scanner;
import java.util.*;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteAdmin {
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
           
           int artist_id;
           LinkedList<String> l = new LinkedList<String>();
           int option = 0;
           String estado = null;
           String aux = null;
           String o = null;


           
            while (true) {

                System.out.println(" _____________	 ADMIN	 _____________");
                System.out.println("|	Insere o comando que quer executar:  |");
                System.out.println("|	1 - Listar artistas por estado	     |");
                System.out.println("|	2 - Aprovar artistas                 |");
                System.out.println("|	0 - Sair                             |");
                System.out.println("|_____________________________________ |");
                System.out.println("");

                System.out.println("Inserir opção: ");
                o = sc.nextLine();
                option = Integer.parseInt(o);
                System.out.println(" ");

                if (option == 1) { // Ver lista de artistas por estado
                    System.out.println("Lista de artistas por estado:");

                    // Adicionando loop para garantir que o usuário insira uma opção válida
                    while (true) {
                        System.out.println("Por favor, insira 'Aprovado' ou 'Nao Aprovado':");
                        estado = sc.nextLine();

                        // Convertendo para minúsculas para facilitar a comparação
                        estado = estado.toLowerCase();

                        if (estado.equals("aprovado") || estado.equals("nao aprovado")) {
                            break;
                        } else {
                            System.out.println("Opção inválida. Tente novamente.");
                        }
                    }

                    l = obj.obterListadeArtistasEstado(estado);

                    for (int i = 0; i < l.size(); i++) {
                        System.out.println(l.get(i));
                    }
                    System.out.println(" ");

                }else if(option == 2) {  //Aprovar artista
                	System.out.print("Insira o id do artista: ");
                    aux = sc.nextLine();
                    artist_id = Integer.parseInt(aux);
                    obj.aprovarArtistas(artist_id);

                }else if(option == 0) {
                    sc.close();
                    System.exit(0);   

                }else {

                    System.out.println("Opcao Inválida");
                }
               System.out.println("Deseja continuar? (s/n)");
               aux = sc.nextLine();
               if(aux.equals("s")){
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
