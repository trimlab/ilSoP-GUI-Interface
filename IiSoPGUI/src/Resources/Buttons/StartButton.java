
package Resources.Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Xazaviar
 */
public class StartButton extends JButton implements ActionListener{

    public void actionPerformed(ActionEvent e){
        System.out.println("YOU HAVE FOUND ME");
    }
}
