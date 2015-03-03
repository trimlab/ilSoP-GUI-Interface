
package Resources.Buttons;

import Resources.Conditional;
import Tabs.Tab;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;

/**
 *
 * @author Joseph Ryan
 */
public class StartButton extends JButton implements ActionListener{
    
    private ArrayList<Conditional> conditionals;
    private int selection = 0;
    
    public void actionPerformed(ActionEvent e){
        if(selection >-1){
            File[] files = Tab.finder("Resources/Modules");
            try {
                Scanner s = new Scanner(files[selection]);
                while(s.hasNext()){
                    String scan = s.nextLine();
                    for(int l = 0; l < conditionals.size(); l++){
                        if(scan.equals(conditionals.get(l).getName().substring(0, conditionals.get(l).getName().length()-4))){
                            conditionals.get(l).toggleEnabled();
                            break;
                        }
                    }
                }
                s.close();
            } catch (FileNotFoundException ex) {}
        }
    }
    
    
    public void update(ArrayList<Conditional> conditionals, int selection){
        this.conditionals = conditionals;
        this.selection = selection;
    }
}
