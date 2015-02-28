
package Resources;

import MusicThreads.MusicThread;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Joseph Ryan
 */
public class Action {
    
    private String actionText;
    private PatternInfo pattern;
    private String note, threadName;
    private MusicThread thread;
    private boolean patternAction = false;
    
    public Action(String action, ArrayList<PatternInfo> patterns, MusicThread[] threads){
        this.actionText = action;
        Scanner s = new Scanner(action);
        String scan = s.next();
        
        if(scan.equals("PLAY")){
            patternAction = true;
            scan = s.next();
            if(scan.equals("PATTERN")){
                patternAction = true;
                scan = "";
                while(!scan.endsWith("IN "))
                    scan += s.next() + " ";
                scan = scan.substring(0, scan.length()-1);
                for(int i = 0; i < patterns.size(); i++){
                    if(patterns.get(i).getName().equals(scan)){
                        pattern = patterns.get(i);
                        break;
                    }
                }
            }else{
                note = scan;
                scan = s.next(); // IN
            }
            threadName = s.next();
            for(int c = 0; c < threads.length; c++){
                if(threads[c].getName().equals(threadName)){
                    thread = threads[c];
                    break;
                }
            }
        }
        
        s.close();
    }
    
    public String toString(){
        return this.actionText;
    }
    
    /**
     * This checks to see if the thread being referenced 
     * is now a currently run thread and if the pattern 
     * being referenced is an available pattern
     * @param patterns
     *          The list of patterns
     * @param threads 
     *          The list of Music threads
     */
    public void updateAction(ArrayList<PatternInfo> patterns, MusicThread[] threads){
        Scanner s = new Scanner(actionText);
        String scan = s.next(); //PLAYS
        scan = s.next();
        if(scan.equals("PATTERN")){
            patternAction = true;
            scan = "";
            while(!scan.endsWith("IN "))
                scan += s.next() + " ";
            scan = scan.substring(0, scan.length()-1);
            for(int i = 0; i < patterns.size(); i++){
                if(patterns.get(i).getName().equals(scan)){
                    pattern = patterns.get(i);
                    break;
                }
            }
        }else{
            note = scan;
            scan = s.next(); // IN
        }
        threadName = s.next();
        for(int c = 0; c < threads.length; c++){
            if(threads[c].getName().equals(threadName)){
                thread = threads[c];
                break;
            }
        }
    }
    
    public void performAction(){
        if(this.thread != null){
            
        }
    }
}
