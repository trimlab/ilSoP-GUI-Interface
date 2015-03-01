package MusicThreads;

import org.jfugue.Pattern;
import org.jfugue.Player;

/**
 * This is a music thread, its intended use is to allow for multiple
 * instruments and instances of music to be active concurrently
 * @author Xazaviar
 */
public class MusicThread implements Runnable{

    private String name = "";
    private Pattern pattern;
//    StreamingPlayer player = new StreamingPlayer();
    Player player = new Player();
    
    public MusicThread(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void receivePattern(Pattern p){
        pattern = p;
    }
    
    public void run() {
        while(true){
            
            if(pattern != null){
                player.play(pattern);
//                player.stream(pattern);
            }
            pattern = null;
            
            sleep(1);
        }
    }
    
    /**
     * Tells the thread to sleep for a set amount of time
     * @param dur 
     *          The duration the thread sleeps
     */
    public void sleep(int dur){
        try{
            Thread.sleep(dur);
        }catch(InterruptedException e){
            System.out.println("Room has been interrupted");
        }
    }
}
