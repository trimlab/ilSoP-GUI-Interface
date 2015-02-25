
package Resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Xazaviar
 */
public class ConAddButton extends JButton implements ActionListener{

    public void actionPerformed(ActionEvent e){
        String m = "What is the name of this Conditional?";
        
        String filename = JOptionPane.showInputDialog(null, m, null);
        
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
    }
}
