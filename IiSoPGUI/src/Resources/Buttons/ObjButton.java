
package Resources.Buttons;

import Resources.Item;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Xazaviar
 */
public class ObjButton extends JButton implements ActionListener{
    private Item obj;

    public void setObj(Item obj) {
        this.obj = obj;
    }
    
    public void actionPerformed(ActionEvent e){
        obj.setColor(obj.getColorWheel()[(int)(Math.random()*100)%obj.getColorWheel().length]);
    }
}
