// Mario Belso

package keyboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListOfKeyboards {

    List<Keyboard> keyboards;
    
    public ListOfKeyboards() {
        listOfKeyboards = new List<Keyboard>();
    }
    
    public Keyboard getKeyboard(int index) {
        return keyboards.get(index);
    }
    
    public void setKeyboard(int index, Keyboard keyboard) {
        keyboards.set(index, keyboard);
    }
    
    public Keyboard[] getKeyboards() {
        return (Keyboard[]) keyboards.toArray();
    }
    
    public void setKeyboards(Keyboard[] keyboards) {
        this.keyboards =Arrays.asList(keyboards);
    }
   
}
