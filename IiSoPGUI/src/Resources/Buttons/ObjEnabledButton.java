
package Resources.Buttons;

import Resources.Item;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Xazaviar
 */
public class ObjEnabledButton extends JButton implements ActionListener{
    private Item obj;

    public void setObj(Item obj) {
        this.obj = obj;
    }
    
    public void actionPerformed(ActionEvent e){
        if(obj.isEnabled()){
            obj.setEnabled(false);
            this.setText("ENABLE");
        }else{
            obj.setEnabled(true);
            this.setText("DISABLE");
        }
    }
}
