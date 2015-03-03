
package Resources;

import MusicThreads.MusicThread;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Joseph Ryan
 */
public class Conditional {
    
    private File file;
    private ArrayList<Condition> conditions = new ArrayList<>();
    private ArrayList<Action> actions = new ArrayList<>(); 
    private Item[] objects;
    private MusicThread[] threads;
    private ArrayList<Section> sections;
    private ArrayList<PatternInfo> patterns;
    private boolean enabled;
    
    public Conditional(File f, Item[] objects, ArrayList<Section> sections, ArrayList<PatternInfo> patterns, MusicThread[] threads){
        this.objects = objects;
        this.threads = threads;
        this.sections = sections;
        this.patterns = patterns;
        this.setFile(f);
        enabled = false;
    }

    public String getName(){
        return this.file.getName();
    }
    
    public void toggleEnabled(){
        this.enabled = !this.enabled;
    }
    
    public boolean isEnabled(){
        return this.enabled;
    }
    
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
        try {
            Scanner s = new Scanner(file);
            String a = "";
            while(s.hasNext()){
                a = s.nextLine();
                if(a.equals("if:")){
                }else if(!a.equals("then:"))
                    conditions.add(new Condition(a, objects, sections));
                else
                    break;

            }
            while(s.hasNext()){
                a = s.nextLine();
                actions.add(new Action(a, patterns, threads));
            }
            s.close();
        } catch (FileNotFoundException ex) {}
    }

    public ArrayList<Condition> getConditions() {
        return conditions;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }
    
    public void addConition(String condition){
        try { 
            conditions.add(new Condition(condition, objects, sections));
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(this.file)));
            this.file = new File(file.getAbsolutePath());
            out.println("if:");
            for(int c = 0; c < this.conditions.size(); c++){
                out.println(this.conditions.get(c).toString());
            }
            out.println("then:");
            for(int a = 0; a < this.actions.size(); a++){
                out.println(this.actions.get(a).toString());
            }
            out.close();
        } catch (IOException ex) {}
    }
    
    public void addAction(String action){
        try { 
            actions.add(new Action(action, patterns, threads));
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(this.file)));
            this.file = new File(file.getAbsolutePath());
            out.println("if:");
            for(int c = 0; c < this.conditions.size(); c++){
                out.println(this.conditions.get(c).toString());
            }
            out.println("then:");
            for(int a = 0; a < this.actions.size(); a++){
                out.println(this.actions.get(a).toString());
            }
            out.close();
        } catch (IOException ex) {}
    }
    
    public void deleteCondtion(String condition){
        try { 
            boolean deleted = false;
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(this.file)));
            this.file = new File(file.getAbsolutePath());
            out.println("if:");
            for(int c = 0; c < this.conditions.size(); c++){
                if(this.conditions.get(c).toString().equals(condition) && !deleted){
                    deleted = true;
                    this.conditions.remove(c);
                    c--;
                }else
                out.println(this.conditions.get(c).toString());
            }
            out.println("then:");
            for(int a = 0; a < this.actions.size(); a++){
                out.println(this.actions.get(a).toString());
            }
            out.close();
        } catch (IOException ex) {}
    }
    
    public void deleteAction(String action){
        try { 
            boolean deleted = false;
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(this.file)));
            this.file = new File(file.getAbsolutePath());
            out.println("if:");
            for(int c = 0; c < this.conditions.size(); c++){
                out.println(this.conditions.get(c).toString());
            }
            out.println("then:");
            for(int a = 0; a < this.actions.size(); a++){
                if(this.actions.get(a).toString().equals(action) && !deleted){
                    deleted = true;
                    this.actions.remove(a);
                    a--;
                }else
                out.println(this.actions.get(a).toString());
            }
            out.close();
        } catch (IOException ex) {}
    }
}
