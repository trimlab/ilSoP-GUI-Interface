
package Resources.Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;

/**
 *
 * @author Xazaviar
 */
public class ConDeleteButton extends JButton implements ActionListener{

    private File selected;
    
    public void actionPerformed(ActionEvent e){ 
        File f = selected;
        try{
            f.delete();
        }catch(Exception ee){
            System.out.println("ERRR");
        }
    }
    
    public void updateSelected(File s){
        this.selected = s;
    }
}
