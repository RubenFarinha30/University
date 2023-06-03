import java.text.Normalizer;
import java.util.Random;
import java.util.*;
import java.util.ArrayList;

public class Cipher {
    public Cipher() {
        
    }
    
    public static String normalized(String naturalTextt) { 
         naturalTextt = naturalTextt.toLowerCase();   // minusculas
    //   naturalTextt = naturalTextt.replaceAll("\\output","");  // remover espacos
         
         String normalized = Normalizer.normalize(naturalTextt, Normalizer.Form.NFD);
         
         naturalTextt = normalized.replaceAll("\\W", "");  //retirar e substituir não caracteres
        
        return naturalTextt;
        
    }

    public static String encode(String naturalText, int cols){
        naturalText = normalized(naturalText);
        String output = naturalText;
        int lines = naturalText.length()/cols;
        StringBuilder Cipher = new StringBuilder();

        if(naturalText.length()%cols!=0){
            lines+=1;
            int int_division = cols - naturalText.length() % cols;
            Random r = new Random();
            for(int i=0; i<=int_division; i++){
                output += naturalText.charAt(r.nextInt(naturalText.length()));
            }
            naturalText = output;
        }

        for(int i=0; i<lines; i++){
            lines = i;
            for(int j=0; j<cols; j++){
                Cipher.append(naturalText.charAt(lines));
                lines+=lines;
            }
        }
        output = Cipher.toString();
        return output;
    }

    public static List<Integer> findDividers(int x){
        List<Integer> l1 = new ArrayList<Integer>();
        for(int i = 1; i <= x; i++){
            if (x % i==0){
                l1.add(i);
            }
        }
        return l1;
    }

    public static List<String> breakCipher(String cipherText, List<String> words) {

        List<Integer> ld = new ArrayList<Integer>();
        ld = Cipher.findDividers(cipherText.length());
        String d;
        List<String> CipherRes = new ArrayList<String>();
        List<String> ScanRes = new ArrayList<String>();

        for(int i = 0; i < ld.size(); i++){
            d = encode(cipherText, ld.get(i));
            ScanRes = scan(d,words);  
            if(ScanRes.size() > CipherRes.size()){
                CipherRes = ScanRes;
            }
        }        
       return CipherRes;
    }

    public static List<String> scan(String cipherText, List<String> words){
        
        List<String> CipherT = new ArrayList<String>();
        List<String> CipherB = new ArrayList<String>();
        List<String> ScanRes = new ArrayList<String>();
        String y, suffix;
        
        for(String word : words) {
        	if(cipherText.startsWith(word)){
        		CipherT.add(word);
        	}
        }
        for(String t: CipherT){
        	suffix = cipherText.substring(t.length(),cipherText.length());
        	if(suffix.length() != 0) {
        		CipherB = scan(suffix, words);
        	}else {
        		return CipherT;
        	}
        	if(CipherB.isEmpty() && t == CipherT.get(CipherT.size()-1)) {
        		ScanRes.add(t);
        		return ScanRes;
        	}
        	for(String b : CipherB) {
        		y = String.format("%s %s", t, b);
        		ScanRes.add(y);
        	}
        }
        return ScanRes;
     
    }


 /*   public class MemoryProvider extends AbstractProvider {
        List<String> llista = new ArrayList<String>();
        public List<String> getWords(){
            return llista;
        }
        public void addWord(String word){

            Cipher.normalized(word);
            llista.add(word);
        }
} */

    
    public static void main(String[] args) {

        List<String> wordlist = new ArrayList<String>();
        wordlist.add("um");
        wordlist.add("uma");
        wordlist.add("dia");
        wordlist.add("noite");
        wordlist.add("flor");
        Cipher.breakCipher("umaflorumdia", wordlist);
       // MemoryProvider p = new MemoryProvider();
       // System.out.print(p.getWords());
    }
}
