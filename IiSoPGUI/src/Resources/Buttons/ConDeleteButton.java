
package Resources.Buttons;

import Resources.Conditional;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author Xazaviar
 */
public class ConDeleteButton extends JButton implements ActionListener{

    private Conditional selected;
    private ArrayList<Conditional> ref;
    
    public void actionPerformed(ActionEvent e){ 
        File f = selected.getFile();
        ref.remove(selected);
        try{
            f.delete();
        }catch(Exception ee){
            System.out.println("ERRR");
        }
    }
    
    public void updateSelected(Conditional s, ArrayList<Conditional> ref){
        this.selected = s;
        this.ref = ref;
    }
}
