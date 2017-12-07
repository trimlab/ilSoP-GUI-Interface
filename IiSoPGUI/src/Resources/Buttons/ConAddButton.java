
package Resources.Buttons;

import MusicThreads.MusicThread;
import Resources.Conditional;
import Resources.Items.Item;
import Resources.PatternInfo;
import Resources.Section;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Joseph Ryan
 */
public class ConAddButton extends JButton implements ActionListener{

    private ArrayList<Conditional> ref;
    private ArrayList<Section> ref2;
    private ArrayList<PatternInfo> ref3;
    private MusicThread[] ref4;
    private Item[] obj;
    
    public void actionPerformed(ActionEvent e){
        boolean rewrite = false; int i = 0;
        
        String m = "What is the name of this Conditional?";
        
        String filename = JOptionPane.showInputDialog(null, m, null);
        
        if(filename != null){
            for(i=0; i < ref.size(); i++){
                if(ref.get(i).getName().equals(filename)){
                    rewrite = true;
                    break;
                }
            }
            if(filename.length()>1) filename+=".txt";
            else filename = "NEW CONDITION.txt";

            File file = new File("Resources/Conditionals", filename);
            try{
                file.createNewFile();
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Resources\\Conditionals\\"+filename)));
                out.println("if:");
                out.println("then:");
                out.close();
            }catch(IOException ee){}
            
            if(rewrite)
                ref.get(i).setFile(file);
            else
                ref.add(new Conditional(file,obj,ref2,ref3,ref4));
        }
    }
    
    public void updateConList(ArrayList<Conditional> ref, Item[] obj, ArrayList<Section> ref2, ArrayList<PatternInfo> ref3, MusicThread[] ref4){
        this.ref = ref;
        this.obj = obj;
        this.ref2 = ref2;
        this.ref3 = ref3;
        this.ref4 = ref4;
    }
}
