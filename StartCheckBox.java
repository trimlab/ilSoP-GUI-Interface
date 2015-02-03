package Resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;

/**
 *
 * @author Joseph Ryan
 */
public class StartCheckBox extends JCheckBox implements ActionListener{
 
    private boolean checked = false;
    
    public void actionPerformed(ActionEvent e) {
        
        if(checked) checked = false;
        else checked = true;
        
        System.out.println(checked);
    }
    
    public boolean isChecked() {
        return checked;
    }
}
