package Resources.Buttons;

import Resources.AddConGUI;
import Resources.Conditional;
import Resources.Item;
import Resources.Section;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author Joseph Ryan
 */
public class ConAddIfButton extends JButton implements ActionListener{

    Conditional ref;
    private ArrayList<Section> ref2;
    private Item[] obj;
    
    public void actionPerformed(ActionEvent e){
        if(ref!=null){
            AddConGUI g = new AddConGUI(obj,ref2,ref);
        }
    }
    
    public void updateCon(Item[] obj, Conditional ref, ArrayList<Section> ref2){
        this.obj = obj;
        this.ref = ref;
        this.ref2 = ref2;
    }
}
