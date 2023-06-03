import java.text.Normalizer;

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

    
    public static void main(String[] args) {
        System.out.print(Cipher.normalized("É o 1º troço, João!"));
    }
}