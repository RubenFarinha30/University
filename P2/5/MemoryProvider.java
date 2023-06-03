import java.util.*;
import java.util.stream.*;

public class MemoryProvider extends AbstractProvider {
    List<String> llista = new ArrayList<String>();
    
    public List<String> getWords(){
        
        List<String> sortedList = llista.stream().sorted().collect(Collectors.toList());

        
        return sortedList;
    }
    public void addWord(String word){
        
        word = Cipher.normalized(word);
        
        if(!llista.contains(word)) {
            llista.add(word);
        }
    }
}