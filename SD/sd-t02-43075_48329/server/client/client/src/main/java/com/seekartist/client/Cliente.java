package com.seekartist.client;

import com.jayway.jsonpath.JsonPath;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.*;

public class Cliente {
	
	private final static CloseableHttpClient httpClient = HttpClients.createDefault();

    public String sendGet(String url) {

        HttpGet request = new HttpGet(url);
        String result = "";

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                result = EntityUtils.toString(entity);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String sendPost(String url) {
        HttpPost post = new HttpPost(url);
        String s = "";

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            s = EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;

    }
    
    public void sendPut(String url) {
        HttpPut put = new HttpPut(url);

        try (CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse response = httpClient.execute(put)) {
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public static String sendGetWithParams(String baseUrl, Map<String, Object> params) {
        try {
            // Create the HTTP GET request with parameters
            URIBuilder uriBuilder = new URIBuilder(baseUrl);
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().toString(); // Convert to String (or handle other types as needed)
                uriBuilder.addParameter(key, value);
            }
            URI uri = uriBuilder.build();
            HttpGet get = new HttpGet(uri);

            // Perform the HTTP GET request
            try (CloseableHttpResponse response = httpClient.execute(get)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ""; // Handle the exception appropriately in your application
    }

    
    public static String sendPostWithParams(String baseUrl, Map<String, Object> params) {
        try {
            // Create the HTTP POST request
            HttpPost post = new HttpPost(baseUrl);

            // Add parameters to the request body
            List<NameValuePair> urlParameters = new ArrayList<>();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().toString(); // Convert to String (or handle other types as needed)
                urlParameters.add(new BasicNameValuePair(key, value));
            }
            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            // Perform the HTTP POST request
            try (CloseableHttpResponse response = httpClient.execute(post)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ""; // Handle the exception appropriately in your application
    }
    
    public static String sendPutWithParams(String baseUrl, Map<String, Object> params) {
        try {
            // Create the HTTP PUT request
            HttpPut put = new HttpPut(baseUrl);

            // Add parameters to the request body
            List<NameValuePair> urlParameters = new ArrayList<>();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().toString(); // Convert to String (or handle other types as needed)
                urlParameters.add(new BasicNameValuePair(key, value));
            }
            put.setEntity(new UrlEncodedFormEntity(urlParameters));

            // Perform the HTTP PUT request
            try (CloseableHttpResponse response = httpClient.execute(put)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ""; // Handle the exception appropriately in your application
    }




    public static void loginMenu() {
        System.out.println("");
        System.out.println("___________ Bem-Vindo __________");
        System.out.println("|	Insira a opção desejada:	|");
        System.out.println("|	l - Login                   |");
        System.out.println("|	r - Registar               |");
        System.out.println("|	s - Sair                    |");
        System.out.println("|_______________________________|");;
        System.out.println("");
    }

    public static void ClienteMenu() {
        System.out.println("");
        System.out.println("________________ Menu Cliente _______________");
        System.out.println("|   Insira a opção desejada:                |");
        System.out.println("|   1 - Registar artista                    |");
        System.out.println("|   2 - Listar artista por localização      |");
        System.out.println("|   3 - Listar artista por arte             |");
        System.out.println("|   4 - Listar localizações com artistas    |");
        System.out.println("|   5 - Listar locais atuados de artista    |");
        System.out.println("|   6 - Listar próximas atuações de artista |");
        System.out.println("|   7 - Enviar Donativo                     |");
        System.out.println("|   8 - Listar Donativos de artista         |");
        System.out.println("|   s - Sair                                |");
        System.out.println("|___________________________________________|");
        System.out.println("");
    }

    public static void adminMenu() {
        System.out.println("");
        System.out.println("________________ Menu Admin _______________");
        System.out.println("|   Insira a opção desejada:                |");
        System.out.println("|   1 - Registar artista                    |");
        System.out.println("|   2 - Listar artista por localização      |");
        System.out.println("|   3 - Listar artista por arte             |");
        System.out.println("|   4 - Listar localizações com artistas    |");
        System.out.println("|   5 - Listar locais atuados de artista    |");
        System.out.println("|   6 - Listar próximas atuações de artista |");
        System.out.println("|   7 - Enviar Donativo                     |");
        System.out.println("|   8 - Listar Donativos de artista         |");
        System.out.println("|	A1 - Dar permissões admin a user        |");
        System.out.println("|	A2 - Listar artistas por estado         |");
        System.out.println("|	A3 - Aprovar artista                    |");
        System.out.println("|	A4 - Alterar artista                    |");
        System.out.println("|	s - Sair                                |");
        System.out.println("|___________________________________________|");;
        System.out.println("");
    }
    
    public static void main(String args[]) {
    	
    	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    	
    	try {
    		Map<String, Object> params = new HashMap<>();
    		Cliente client = new Cliente();
            String command_choice;
            String baseUrl;
            String str = "";
            String str1 = "";
            String response = "";
            String response1 = "";
            String pw = "";
            String uname = "";
            int num;
            Object obj = new Object();
            String usertype = "";
            
            while(true) {
            	if (usertype.equals("admin")) {
            		adminMenu();
            	}else if(usertype.equals("geral")){
            		ClienteMenu();
            	}else {
            		loginMenu();
            	}
            	
                
                System.out.print("-> ");
                command_choice = input.readLine();
                
                switch(command_choice){
    	            case "l":
    	                System.out.println("Insira os seguintes campos:");
    	                System.out.print("Username -> ");
    	                String loginUsername = input.readLine();
    	                System.out.print("Password -> ");
    	                String loginPassword = input.readLine();
    	
    	                // Authentication request
    	                baseUrl = "http://localhost:8080/utilizador/getUtilizadorByUsername";
    	                params.clear();
                    	params.put("username", loginUsername);
    	                response = Cliente.sendGetWithParams(baseUrl, params);
    	                String username = JsonPath.read(response, "$.username");
    	                
    	                if (response != null && !response.isEmpty()) {
    	                    // Parse the JSON and extract the username
    	                    username = JsonPath.read(response, "$.username");
    	                    if (username != null) {
    	                    	pw = JsonPath.read(response, "$.password");
    	                    } else { 	
    	                        System.out.println("Username is null. Register!");
    	                    }
    	                } else {
    	                    System.out.println("Username is null. Register!");
    	                }
    	                

    	                baseUrl = "http://localhost:8080/utilizador/getUtilizadorByPassword";
    	                params.clear();
                    	params.put("password", loginPassword);
    	                response1 = Cliente.sendGetWithParams(baseUrl, params);
    	                String password = JsonPath.read(response1, "$.password");
    	                
    	                if (response1 != null && !response1.isEmpty()) {
    	                    // Parse the JSON and extract the username
    	                    password = JsonPath.read(response1, "$.password");
    	                    uname = JsonPath.read(response1, "$.username");
    	                    //System.out.print(username + uname + password + pw);
    	                    if (username.equals(uname) && password.equals(pw)) {
    	                        usertype = JsonPath.read(response1, "$.type");
    	                        
    	                    } else { 	
    	                        System.out.println("Credenciais errradas. Tente novamente!");
    	                    }
    	                } else {
    	                    System.out.println("Password is null. Register!");
    	                }

    	                break;
    	                
    	            case "r":
                        System.out.println("Insira os seguintes campos:");
                        System.out.print("Username -> ");
                        username = input.readLine();
                        System.out.print("Password -> ");
                        password = input.readLine();
                        System.out.print("Email -> ");
                        String email = input.readLine();

                        // Check if the parameters already exist
                        baseUrl = "http://localhost:8080/utilizador/getUtilizadorByUsername?";
                        params.clear();
                    	params.put("username", username);
                        response = Cliente.sendGetWithParams(baseUrl, params);
                        
                        baseUrl = "http://localhost:8080/utilizador/getUtilizadorsByEmail?";
                        params.clear();
                    	params.put("email", email);
                        response1 = Cliente.sendGetWithParams(baseUrl, params);
                        
                        if (response != null && response1 != null) {
                        	baseUrl = "http://localhost:8080/utilizador/addUtilizador?";
                        	params.clear();
                        	params.put("username", username);
                        	params.put("password", password);
                        	params.put("email", email);
                        	params.put("type", "geral");
                        	
                        	str = sendPostWithParams(baseUrl, params);
                        	params.clear();
                        	
                        	System.out.println("Registo com sucesso");
                        }else {
                        
                        	System.out.println("Erro no registo");
                        }
                        break;
                    
    	            case "1":                       
                        if(usertype == "geral" || usertype == "admin") {
                        	System.out.println("Insira os seguintes campos:");
                            System.out.print("Nome do Artista -> ");
                            String nomeartista = input.readLine();
                            System.out.print("Tipo -> ");
                            String tipo = input.readLine();
                            System.out.print("Latitude -> ");
                            Integer latitude = Integer.parseInt(input.readLine());
                            System.out.print("Longitude -> ");
                            Integer longitude = Integer.parseInt(input.readLine());
                            System.out.print("Está a atuar? -> ");
                            String is_acting = input.readLine();

                            // Check if the artista already exists
                            baseUrl = "http://localhost:8080/artista/getArtistaByName?";
                            params.clear();
                            params.put("username", nomeartista);
                            response = Cliente.sendGetWithParams(baseUrl, params);
                            
                            if(response.equals(null)) {
                            	baseUrl = "http://localhost:8080/artista/addArtista?";
                            	params.clear();
                            	params.put("name", nomeartista);
                            	params.put("tipo", tipo);
                            	params.put("latitude", latitude);
                            	params.put("longitude", longitude);
                            	params.put("acting", is_acting);
                            	
                            	str = sendPostWithParams(baseUrl, params);
                            }else {
                            	baseUrl = "http://localhost:8080/artista/change_location?";
                            	params.clear();
                            	params.put("name", nomeartista);
                            	params.put("latitude", latitude);
                            	params.put("longitude", longitude);
                            	
                            	str = sendPutWithParams(baseUrl, params);
                            }
                        }
                        
                        break;
                        
    	            case "2":                       
                        ArrayList<String> lista_artistalocal = new ArrayList<>();

                        System.out.println("Insira os seguintes campos:");
                        System.out.print("Latitude -> ");
                        Integer latitude = Integer.parseInt(input.readLine());
                        System.out.print("Longitude -> ");
                        Integer longitude = Integer.parseInt(input.readLine());

                        baseUrl = "http://localhost:8080/artista/getArtistasByLatitude?"; 
                        params.clear();
                    	params.put("latitude", latitude);
                    	
                    	response = Cliente.sendGetWithParams(baseUrl, params);
                    	
                    	baseUrl = "http://localhost:8080/artista/getArtistasByLongitude?"; 
                        params.clear();
                    	params.put("longitude", longitude);
                    	
                        response1 = Cliente.sendGetWithParams(baseUrl, params);
                        
                        System.out.print(response);
                        System.out.print(response1);
                        
                        break;
    	            
    	            case "3":                       
    	            	ArrayList<String> lista_arte = new ArrayList<>();

                        System.out.println("Insira o seguinte campo:");
                        System.out.print("arte -> ");
                        String arte = input.readLine();

                        baseUrl = "http://localhost:8080/artista/getArtistasByTipoArte?"; 
                        params.clear();
                    	params.put("type", arte);
                    	
                    	response = Cliente.sendGetWithParams(baseUrl, params);
                                        
                        System.out.print(response);

                        
                        break;
                   
    	            case "4":                       
    	            	//ArrayList<String> lista_ativos = new ArrayList<>();

                        System.out.println("Insira o seguinte campo:");
                        System.out.print("ativo/inativo -> ");
                        String estado = input.readLine();

                        baseUrl = "http://localhost:8080/artista/getAllByApproval?"; 
                        params.clear();
                    	params.put("approval", estado);
                    	
                    	response = Cliente.sendGetWithParams(baseUrl, params);
                                        
                        System.out.print(response);

                        
                        break;
                        
    	            case "5":                        
                        System.out.println("Atuados");
                        break;
                        
    	            case "6":                        
                        System.out.println("Atuações");
                        break;
                    
    	            case "7":                        
    	            	System.out.println("Insira os seguintes campos:");
                        System.out.print("ID do artista -> ");
                        String artistaid = input.readLine();
                        System.out.print("Valor donativo -> ");
                        Integer valued = Integer.parseInt(input.readLine());
                        
                        LocalDate currentDate = LocalDate.now();
                        
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        String formattedDate = currentDate.format(formatter);


                        baseUrl = "http://localhost:8080/donation/addDonation"; 
                        params.clear();
                    	params.put("artistid", artistaid);
                    	params.put("username", uname);
                    	params.put("donationamount", valued);
                    	params.put("date", formattedDate);
                    	
                    	str = sendPostWithParams(baseUrl, params);

                        break;
                        
    	            case "8":                        
    	            	System.out.println("Insira os seguintes campos:");
                        System.out.print("ID do artista -> ");
                        str = input.readLine();


                        baseUrl = "http://localhost:8080/donation/getDonationsByArtistid"; 
                        params.clear();
                    	params.put("artistid", str);
                    	
                    	str = sendPostWithParams(baseUrl, params);
                    	
                    	System.out.println(str);

                        break;
                    
    	            case "A1":
                        if(usertype.equals("admin")){
                            System.out.print("Insira o campo: ");
                            System.out.print("Username -> ");
                            String username1 = input.readLine();
                                
                            baseUrl = "http://localhost:8080/utilizador/changetype"; 
                            params.clear();
                        	params.put("username", username1);
                        	
                        	str = sendPutWithParams(baseUrl, params);
                                
                        }else{
                           System.out.println("Sem Permissão"); 
                        }              
                        break;  
                    
    	            case "A2":                       
                        if(usertype.equals("admin")){
                            //ArrayList<String> lista_estado = new ArrayList<>();
                            System.out.print("Insira o campo: ");
                            System.out.print("Atuar (Sim/Não): ");
                            str= input.readLine();

                            baseUrl = "http://localhost:8080/artista/getAllByActing"; 
                            params.clear();
                        	params.put("username", str);

                        	str = sendPutWithParams(baseUrl, params);
                            
                            System.out.print(str);

                        }else{
                           System.out.println("Sem Permissão"); 
                        }

                        break;
                    
    	            case "A3":                       
                        if(usertype.equals("admin")){
                        	System.out.print("Insira o campo: ");
                            System.out.print("Artist ID-> ");
                            Integer aid = Integer.parseInt(input.readLine());
                            
                            baseUrl = "http://localhost:8080/artista/aprovestate"; 
                            params.clear();
                        	params.put("username", aid);
                        }else{
                           System.out.println("Sem Permissão"); 
                        }

                        break;
                        
    	            case "A4":                       
                        if(usertype.equals("admin")){
                        	
                        }else{
                           System.out.println("Sem Permissão"); 
                        }

                        break;
                    
    	            case "s":
                        System.out.println("Sessão encerrada!");
                        System.exit(0);
                    default:
                        System.out.println("Comando inválido, tente novamente outro comando");
                        System.out.println("");
                        
                        if (usertype == null || usertype.trim().isEmpty()) {
                            loginMenu();
                        } else if (usertype.equals("admin")) {
                            adminMenu();
                        } else {
                            ClienteMenu();
                        }
                }
            }
            
    	} catch (Exception e) {
            e.printStackTrace();
        }
    	
    }
}
