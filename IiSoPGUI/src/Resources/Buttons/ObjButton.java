
package Resources.Buttons;

import Resources.Item;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Joseph Ryan
 */
public class ObjButton extends JButton implements ActionListener{
    private Item obj;
    private int c=0;

    public void setObj(Item obj) {
        this.obj = obj;
    }
    
    public void actionPerformed(ActionEvent e){
        c++;
        if(c >= obj.getColorWheel().length)
            c = 0;
        obj.setColor(c);
    }
}
