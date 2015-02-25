
package Resources;

import java.awt.Color;
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
            obj.setColor(Color.DARK_GRAY);
            obj.setEnabled(false);
            this.setText("ENABLE");
        }else{
            obj.setEnabled(true);
            obj.setColor(obj.getColorWheel()[(int)(Math.random()*100)%obj.getColorWheel().length]);
            this.setText("DISABLE");
        }
    }
}
