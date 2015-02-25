
package Resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;

/**
 *
 * @author Xazaviar
 */
public class RoomAddButton extends JButton implements ActionListener{

    private double x1=0,x2=0,y1=0,y2=0,z1=0,z2=0;
    private String filename="";
    
    public void actionPerformed(ActionEvent e){
        if(filename.length()>1) filename+=".txt";
        else filename = "NEW SECTION.txt";
        
        if(x1 > 3 || x1 < -3){
            x1 = 0;
        }
        if(y1 > 4 || y1 < -2){
            y1 = 1;
        }
        if(z1 > 5 || z1 < -2){
            z1 = -2;
        }
        if(x2 > 3 || x2 < -3){
            x2 = 0;
        }
        if(y2 > 4 || y2 < -2){
            y2 = 1;
        }
        if(z2 > 5 || z2 < -2){
            z2 = -2;
        }
        
        File file = new File("Resources/Sections", filename);
        try{
        file.createNewFile();
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Resources\\Sections\\"+filename)));
        out.print(x1+" "+y1+" "+z1+" "+x2+" "+y2+" "+z2);
        out.close();
        }catch(IOException ee){}
    }
    
    public void updateData(String name, double x1, double y1, double z1, double x2, double y2, double z2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.z1 = z1;
        this.z2 = z2;
        this.filename = name;
        
    }
}
