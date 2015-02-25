
package Resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

/**
 *
 * @author Joseph Ryan
 */
public class StartTextArea extends JTextField implements ActionListener, KeyListener{
    
    private int max = 0;
    
    public void actionPerformed(ActionEvent e) {
        System.out.println(this.getText());   
    }
    
    public void keyTyped(KeyEvent e) {
        if(this.getText().length() > max)
            this.setText(this.getText().substring(0, this.getText().length()-1));
    }
    
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e){}
    
    public void setMaxChars(int m){
        max = m-1;
    }
}
