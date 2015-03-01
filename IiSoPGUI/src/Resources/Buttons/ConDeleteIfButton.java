package Resources.Buttons;

import Resources.Conditional;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author Joseph Ryan
 */
public class ConDeleteIfButton extends JButton implements ActionListener{

    private Conditional selected;
    private String condition;
    
    public void actionPerformed(ActionEvent e) {
        if(selected!=null)
        selected.deleteCondtion(condition);
    }
    
    public void update(Conditional s, String condition){
        this.condition = condition;
        this.selected = s;
    }
    
}
