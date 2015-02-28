
package Resources.Buttons;

import Resources.Conditional;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Joseph Ryan
 */
public class ConUseButton extends JButton implements ActionListener{

    private Conditional selected;
    
    public void actionPerformed(ActionEvent e) {
        if(selected!=null)
        selected.toggleEnabled();
    }
    
    public void updateSelected(Conditional s){
        this.selected = s;
    }
}
