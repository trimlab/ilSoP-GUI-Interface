
package Resources.Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;

/**
 *
 * @author Xazaviar
 */
public class PatDeleteButton extends JButton implements ActionListener{
    
    private File selected;
    
    public void actionPerformed(ActionEvent e){
        
        File f = selected;        
        try{
            f.delete();
        }catch(Exception ee){}
    }
    
    public void updateSelected(File s){
        this.selected = s;
    }
    
}
