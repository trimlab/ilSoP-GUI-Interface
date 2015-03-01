package Resources.Buttons;

import Resources.PatternInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Xazaviar
 */
public class PatSaveButton extends JButton implements ActionListener{

    private boolean[][] grid;
    private int tempo, nLen, instrument, oct;
    private String scale;
    private ArrayList<PatternInfo> ref;
    
    public void actionPerformed(ActionEvent e) {
        
        
        String m = "What is the name of this pattern?";
        
        String filename = JOptionPane.showInputDialog(null, m, null);
        boolean rewrite = false; int i = 0;
        
        if(filename != null){
            for(int t = 0; t < ref.size(); t++){
                if(filename.equals(ref.get(t).getName())){
                    rewrite = true;
                    i = t;
                    break;
                }
            }
            if(filename.length()>1) filename+=".txt";
            else filename = "NEW PATTERN.txt";

            File file = new File("Resources/Patterns", filename);
            try{
                file.createNewFile();
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Resources\\Patterns\\"+filename)));
                out.println(""+tempo+" "+nLen+" "+instrument+" "+oct+" "+scale);
                for(int c = 0; c < grid[0].length; c++){
                    for(int r = 0; r < grid.length; r++){
                        out.print(grid[r][c] ? "1 " : "0 ");
                    }
                    out.println();
                }
                out.close();
            }catch(IOException ee){}
            
            if(rewrite)
                ref.get(i).setFile(file);
            else
                ref.add(new PatternInfo(file));
        }
    }
    
    public void updateCurrent(boolean[][] grid, int tempo, int nLength, String scale, int instr, int oct, ArrayList<PatternInfo> ref){
        this.grid = grid;
        this.tempo = tempo;
        this.nLen = nLength;
        this.scale = scale;
        this.instrument = instr;
        this.oct = oct;
        this.ref = ref;
    }
    
}
