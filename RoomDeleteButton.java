package Resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;

/**
 *
 * @author Xazaviar
 */
public class RoomDeleteButton extends JButton implements ActionListener{
    
    private File selected;
    
    public void actionPerformed(ActionEvent e){
        
        File f = selected;
//        System.out.println(f.toString());
        
        try{
            if(f.delete()){
//                System.out.println("Deleted");
            }
        }catch(Exception ee){}
//        System.out.println("Make a new .txt");
    }
    
    public void updateSelected(File s){
        this.selected = s;
    }
}
