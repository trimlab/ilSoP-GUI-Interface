package Resources.Buttons;

import Resources.Conditional;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Joseph Ryan
 */
public class ConDeleteThenButton extends JButton implements ActionListener{

    private Conditional selected;
    private String action;
    
    public void actionPerformed(ActionEvent e) {
        if(selected!=null)
        selected.deleteAction(action);
    }
    
    public void update(Conditional s, String action){
        this.action = action;
        this.selected = s;
    }
    
}
