package Resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 *
 * @author Xazaviar
 */
public class StartDropDown extends JComboBox implements ActionListener{
    
    public void actionPerformed(ActionEvent e){
        System.out.println("am a box:"+e.paramString());
        
    }
}
