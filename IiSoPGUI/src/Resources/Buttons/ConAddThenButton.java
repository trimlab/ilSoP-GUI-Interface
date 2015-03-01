package Resources.Buttons;

import MusicThreads.MusicThread;
import Resources.AddActGUI;
import Resources.Conditional;
import Resources.PatternInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author Joseph Ryan
 */
public class ConAddThenButton extends JButton implements ActionListener{

    Conditional ref;
    private ArrayList<PatternInfo> ref2;
    private MusicThread[] threads;
    
    public void actionPerformed(ActionEvent e){
        if(ref!=null){
            AddActGUI g = new AddActGUI(threads,ref2,ref);
        }
    }
    
    public void updateCon(MusicThread[] threads, Conditional ref, ArrayList<PatternInfo> ref2){
        this.threads = threads;
        this.ref = ref;
        this.ref2 = ref2;
    }
}
