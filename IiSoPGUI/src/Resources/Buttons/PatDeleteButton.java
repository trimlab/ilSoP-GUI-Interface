
package Resources.Buttons;

import Resources.PatternInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author Xazaviar
 */
public class PatDeleteButton extends JButton implements ActionListener{
    
    private PatternInfo selected;
    private ArrayList<PatternInfo> ref;
    
    public void actionPerformed(ActionEvent e){
        
        File f = selected.getFile();
        ref.remove(selected);
        try{
            f.delete();
        }catch(Exception ee){}
    }
    
    public void updateSelected(PatternInfo s, ArrayList<PatternInfo> ref){
        this.selected = s;
        this.ref = ref;
    }
    
}
