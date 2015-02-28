
package Resources.Buttons;

import Resources.Section;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author Joseph Ryan
 */
public class RoomDeleteButton extends JButton implements ActionListener{
    
    private Section selected;
    private ArrayList<Section> ref;
    
    public void actionPerformed(ActionEvent e){
        
        File f = selected.getFile(); 
        ref.remove(selected);
        try{
            f.delete();
        }catch(Exception ee){}
    }
    
    public void updateSelected(Section s, ArrayList<Section> ref){
        this.selected = s;
        this.ref = ref;
    }
}
