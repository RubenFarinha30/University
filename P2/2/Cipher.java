import java.text.Normalizer;
import java.util.Random;

public class Cipher {
    public Cipher() {
        
    }
    
    public static String normalized(String naturalText) { 
         naturalText = naturalText.toLowerCase();   // minusculas
    //   naturalText = naturalText.replaceAll("\\s","");  // remover espacos
         
         String normalized = Normalizer.normalize(naturalText, Normalizer.Form.NFD);
         
         naturalText = normalized.replaceAll("\\W", "");  //retirar e substituir não caracteres
        
        return naturalText;
        
    }

    public static String encode(String naturalText, int columns) {
        
        String random_values = naturalText;
        char[] ch = random_values.toCharArray();
        char[] natural_text_array = naturalText.toCharArray();

        String output = "";

        char[][] matriz = new char[columns][naturalText.length()/columns];
        int size_text = naturalText.length();
        
        int rest_division = size_text % columns;
        int int_division = size_text / columns;
        int contador = 0;

        for(int i = 0; i < columns; i++){
            for(int j = 0; j < int_division; j++){
                matriz[i][j] = natural_text_array[contador];
                contador++;
            }
        }

        if(rest_division == 0){
            for(int j = 0; j < int_division; j++){
                for(int i = 0; i < columns; i++){
                    output = output + matriz[i][j];
                    
                }
            }

        } else {

            for(int i = rest_division-1; i < columns; i++){
                Random r=new Random();        
                int randomNumber=r.nextInt(naturalText.length());

                matriz[i][int_division-1] = ch[randomNumber];
                contador++;
            }

            for(int j = 0; j < int_division; j++){
                for(int i = 0; i < columns; i++){
                    output = output + matriz[i][j];
                    
                }
            }
        }

        for(int i = 0; i < int_division; i++){
            for(int j = 0; j < columns; j++){
                System.out.print(matriz[i][j]);
            }
            System.out.print("\n");
        }

        return output;
    }
    

    
    public static void main(String[] args) {
        String text = Cipher.normalized("Bom dia, Alegria!");

        text = Cipher.encode(text, 8);
    }
}