package MusicThreads;

import org.jfugue.Pattern;

/**
 * This is a music thread, its intended use is to allow for multiple
 * instruments and instances of music to be active concurrently
 * @author Xazaviar
 */
public class MusicThread implements Runnable{

    private String name = "";
    private boolean canReceive = false;
    private Pattern pattern;
    
    public MusicThread(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void receivePattern(Pattern p){
        if(canReceive){
            pattern = p;
        }
    }
    
    public void run() {}
    
}
