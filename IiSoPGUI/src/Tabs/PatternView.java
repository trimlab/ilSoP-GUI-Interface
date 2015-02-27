/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Tabs;

import Resources.Buttons.PatDeleteButton;
import Resources.Buttons.PatSaveButton;
import Resources.PatTextArea;
import Resources.Scale;
import Resources.Scales;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import org.jfugue.Pattern;
import org.jfugue.Player;
import org.jfugue.StreamingPlayer;

/**
 * The Pattern maker tab
 * @author Xazaviar
 */
public class PatternView extends Tab{
    private int numFiles = 0;
    private int selected = -1;
    private int hover = -1;
    private boolean newSelect = false;
    
    //Music Vars
    private int instrument = 0;
    private int bpm = 150;
    private int beat = 0;
    private int octave = 0;
    private int nLength = 8; // 1 = 32nd, 2 = 16th, 4 = eigth, 8 = quarter, 16 = half, 32 = whole
    private Scale scale = new Scale(Scales.C_MAJOR_PENATONIC);
    private boolean[][] synth = new boolean[20][20];
    StreamingPlayer player = new StreamingPlayer();
//    Player player = new Player();
    
    //Components
    private PatDeleteButton delete;
    private PatSaveButton save;
    private PatNewButton newB;
    private PatInDropDownBox instr;
    private PatScDropDownBox sca;
    private PatOcDropDownBox oct;
    private PatTextArea bpms;
    private PatNlDropDownBox nLen;
    
    private Timer timer;
    
    /**
     * The default constructor (not in use)
     */
    public PatternView(){}   
    
    /**
     * Sets up the tab with a name and background color
     * @param n
     *          The name for the tab
     * @param c 
     *          The color of the background
     * @param p 
     *          The panel to draw on
     */
    public PatternView(String n, Color c, JPanel p){
        super.setName(n);
        super.setColor(c);
        super.setPanel(p);
        
        timer = new Timer();
        timer.schedule(new RemindTask(), 60000/bpm);
    }
    
    /**
     * The draw method that creates the tab
     * @param g
     *          The graphics
     */
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        //Draw Side Bar
        g.setColor(Color.WHITE);
        g.fillRect(0, super.getHeight(), 230, super.getPanel().getHeight()-super.getHeight());
        g.setColor(Color.BLACK);
        g.setFont(new Font("ARIAL", Font.BOLD, 20));
        g.drawString("PATTERNS", 10, 55);
        g.drawLine(10,57,220,57);
        
        File[] files = super.finder("Resources/Patterns");
        numFiles = files.length;
        g.setFont(new Font("ARIAL", Font.BOLD, 16));
        for(int f = 0; f < files.length; f++){
            if(f == selected){
                g.setColor(new Color(112,112,255));
                g.fillRect(2, 65+25*f, 226, 20);
                delete.updateSelected(files[f]);
                if(newSelect)
                    newPatternReadin(files[f]);
            }
            if(f == hover) g.setColor(Color.red);
            else g.setColor(Color.black);
            g.drawString(files[f].getName().substring(0, files[f].getName().length()-4), 20, 80+25*f);
            g.fillOval(10, 70+25*f, 7, 7);
        }
        g.setFont(new Font("ARIAL", Font.PLAIN, 12));
        
        //Text Labels
        g.setColor(Color.black);
        g.setFont(new Font("ARIAL", Font.BOLD, 15));
        g.drawString("Scale:", 235, super.getHeight()+55);
        g.drawString("Instrument:", 235, super.getHeight()+90);
        g.drawString("Octaves:", 235, super.getHeight()+125);
        g.drawString("Note Len:", 235, super.getHeight()+160);
        g.drawString("BPM:", 235, super.getHeight()+195);
        g.setFont(new Font("ARIAL", Font.PLAIN, 12));
        
        //Pattern Builder
        g.setColor(Color.BLACK);
        g.fillRect(500, super.getHeight()+20, 640, 640);
        for(int c = 0; c < synth[0].length; c++){
            for(int r = 0; r < synth.length; r++){
                if(synth[r][c]&& beat == r) g.setColor(Color.green);
                else if(synth[r][c])        g.setColor(Color.white);
                else                        g.setColor(Color.darkGray);
                g.fillRect(503+(32*(r%20)), super.getHeight()+23+(32*(c%20)), 26, 26);
            }
        }
        
        //Beat tracker
        for(int b = 0; b < synth.length; b++){
            g.setColor(Color.black);
            g.fillOval(509+(32*(b%20)), super.getHeight()+663, 14, 14);
            if(b == beat)   g.setColor(Color.red);
            else            g.setColor(Color.black);
            g.fillOval(512+(32*(b%20)), super.getHeight()+666, 8, 8);
        }
        
        //Update button info
        save.updateCurrent(synth, bpm, nLength, scale.toString(), instrument, octave);
        try{
            bpm = Integer.parseInt(bpms.getText());
        }catch(java.lang.NumberFormatException e){bpm=120;}
    }
    
    private void newPatternReadin(File f){
        try{
            Scanner s = new Scanner(f);
            
            bpm = s.nextInt();
            bpms.setText(""+bpm);
            nLength = s.nextInt();
            nLen.setSelection();
            instrument = s.nextInt();
            instr.setSelectedIndex(instrument);
            octave = s.nextInt();
            oct.setSelection();
            scale.changeScale(s.next());
            sca.setSelection();
            
            for(int c = 0; c < synth[0].length; c++){
                for(int r = 0; r < synth.length; r++){
                    synth[r][c] = s.nextInt() == 1;
                }
            }
            s.close();
            
        }catch (IOException e){
            
        }
    }
    
    /**
     * Sets up the new components for the new tab
     */
    public void setupComponents(){
        //Set up Board
        for(int c = 0; c < synth[0].length; c++){
            for(int r = 0; r < synth.length; r++){
                synth[r][c] = false;
            }
        }
        
        //Start Beat Timer
        beat = 0;
        instrument = 0;
        octave = 0;
        scale.changeScale(Scales.C_MAJOR_PENATONIC);
        
        //Setup buttons
        delete = new PatDeleteButton();
        delete.setText("DELETE");
        delete.setBounds(120, super.getPanel().getHeight()-50, 100, 30);
        delete.addActionListener(delete);
        delete.setFocusable(false);
        super.getPanel().add(delete);
        
        save = new PatSaveButton();
        save.setText("SAVE");
        save.setBounds(super.getPanel().getWidth()-110, super.getPanel().getHeight()-50, 100, 30);
        save.addActionListener(save);
        save.setFocusable(false);
        super.getPanel().add(save);
        
        newB = new PatNewButton();
        newB.setText("NEW");
        newB.setBounds(10, super.getPanel().getHeight()-50, 100, 30);
        newB.addActionListener(newB);
        newB.setFocusable(false);
        super.getPanel().add(newB);
        
        //Setup Dropdown boxes
        sca = new PatScDropDownBox();
        sca.setBounds(315, super.getHeight()+35, 180, 30);
        sca.setFocusable(false);
        sca.addActionListener(sca);
        super.getPanel().add(sca);
        
        instr = new PatInDropDownBox();
        instr.setBounds(315, super.getHeight()+70, 180, 30);
        instr.setFocusable(false);
        instr.addActionListener(instr);
        super.getPanel().add(instr);
        
        oct = new PatOcDropDownBox();
        oct.setBounds(315, super.getHeight()+105, 180, 30);
        oct.setFocusable(false);
        oct.addActionListener(oct);
        super.getPanel().add(oct);
        
        nLen = new PatNlDropDownBox();
        nLen.setBounds(315, super.getHeight()+140, 180, 30);
        nLen.setFocusable(false);
        nLen.addActionListener(nLen);
        nLen.setSelectedIndex(0);
        super.getPanel().add(nLen);
        
        //Setup Text Area
        bpms = new PatTextArea();
        bpms.setBounds(315, super.getHeight()+175, 50, 30);
        bpms.addActionListener(bpms);
        bpms.addKeyListener(bpms);
        bpms.setMaxChars(3);
        bpms.setFocusable(true);
        bpms.setText(""+bpm);
        super.getPanel().add(bpms);
        
        super.getPanel().addMouseListener(new Mouse());
        super.getPanel().addMouseMotionListener(new Mouse2());
    }
    
    private class Mouse extends JPanel implements MouseListener{
        
        //Unused
        public void mouseClicked(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        
        /**
         * What to do when the mouse is pressed
         * @param e 
         *          The information about the MouseEvent
         */
        public void mousePressed(MouseEvent e) {
            int temp = selected;
            selected = hover;
            
            if(selected != temp && selected != -1){
                newSelect = true;
            }
            
            //If Clicked on grid
            if(e.getX()>=500 && e.getX()<=1140 && e.getY()<=690 && e.getY()>=55)
                synth[(e.getX()-500)/32][(e.getY()-55)/32] = !synth[(e.getX()-500)/32][(e.getY()-55)/32];
        }
        
    }
    
    private class Mouse2 extends JPanel implements MouseMotionListener{
        public void mouseDragged(MouseEvent e) {}

        /**
         * What to do when the mouse is moving
         * @param e 
         *          The information about the MouseEvent
         */
        public void mouseMoved(MouseEvent e) {
            boolean h = false;
            for(int f = 0; f < numFiles; f++){
                if(e.getX()<230 && e.getY()<90+25*f && e.getY()>65+25*f){
                    hover = f;
                    h = true;
                    break;
                }
            }
            if(!h) hover = -1;
        }
        
    }
    
    /**
     * This class runs the beat timer, when the beat is hit
     * it will play all notes that are in that specific section
     */
    public class RemindTask extends TimerTask{
        public void run(){  
            beat++;
            if(beat == synth.length) beat = 0;
            
            //Play the notes
            String note = "T"+bpm+" I"+instrument+" ";
            String nlen = "";
            
            if(nLength == 1) nlen = "t";
            if(nLength == 2) nlen = "s";
            if(nLength == 4) nlen = "i";
            if(nLength == 8) nlen = "q";
            if(nLength == 16) nlen = "h";
            if(nLength == 32) nlen = "w";
            
            for(int c = 0; c < synth.length; c++){
                if(synth[beat][c]){
                    note+=""+scale.getSelectedScale()[4-(c%5)]+((20-(c+1)+scale.backTrackAmount())/5+octave+1)+nlen+"+";
                }
            }
            
            note = note.substring(0, note.length()-1);    
//            System.out.println(note);
            
            player.stream(new Pattern(note));
//            player.play(new Pattern(note));
            
            if(bpm < 80) bpm = 80;
            if(bpm > 240) bpm = 240;
            
            timer.schedule(new RemindTask(), (60000/bpm)*(nLength/8));
        }
     }
    
    /**
     * The New button on this tab
     */
    public class PatNewButton extends JButton implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            //Set up Board
            for(int c = 0; c < synth[0].length; c++){
                for(int r = 0; r < synth.length; r++){
                    synth[r][c] = false;
                }
            }
        }

    }
    
    /**
     * The drop down box for the instruments
     */
    public class PatInDropDownBox extends JComboBox implements ActionListener{
    
        private String[] instrumentList = {"PIANO","BRIGHT_ACOUSTIC","ELECTRIC_GRAND",
                                           "HONKEY_TONK","ELECTRIC_PIANO1","ELECTRIC_PIANO2",
                                           "HARPISCHORD","CLAVINET","CELESTA",
                                           "GLOCKENSPIEL","MUSIC_BOX","VIBRAPHONE",
                                           "MARIMBA","XYLOPHONE","TUBULAR_BELLS",
                                           "DULCIMER","DRAWBAR_ORGAN","PERCUSSIVE_ORGAN",
                                           "ROCK_ORGAN","CHURCH_ORGAN","REED_ORGAN",
                                           "ACCORIDAN","HARMONICA","TANGO_ACCORDIAN",
                                           "NYLON_STRING_GUITAR","STEEL_STRING_GUITAR","ELECTRIC_JAZZ_GUITAR",
                                           "ELECTRIC_CLEAN_GUITAR","ELECTRIC_MUTED_GUITAR","OVERDRIVEN_GUITAR",
                                           "DISTORTION_GUITAR","GUITAR_HARMONICS","ACOUSTIC_BASS",
                                           "ELECTRIC_BASS_FINGER","ELECTRIC_BASS_PICK","FRETLESS_BASS",
                                           "SLAP_BASS_1","SLAP_BASS_2","SYNTH_BASS_1",
                                           "SYNTH_BASS_2","VIOLIN","VIOLA",
                                           "CELLO","CONTRABASS","TREMOLO_STRINGS",
                                           "PIZZICATO_STRINGS","ORCHESTRAL_STRINGS","TIMPANI",
                                           "STRING_ENSEMBLE_1","STRING_ENSEMBLE_2","SYNTH_STRINGS_1",
                                           "SYNTH_STRINGS_2","CHOIR_AAHS","VOICE_OOHS",
                                           "SYNTH_VOICE","ORCHESTRA_HIT","TRUMPET",
                                           "TROMBONE","TUBA","MUTED_TRUMPET",
                                           "FRENCH_HORN","BRASS_SECTION","SYNTHBRASS_1",
                                           "SYNTHBRASS_2","SOPRANO_SAX","ALTO_SAX",
                                           "TENOR_SAX","BARITONE_SAX","OBOE",
                                           "ENGLISH_HORN","BASSOON","CLARINET",
                                           "PICCOLO","FLUTE","RECORDER",
                                           "PAN_FLUTE","BLOWN_BOTTLE","SKAKUHACHI",
                                           "WHISTLE","OCARINA","LEAD_SQUARE",
                                           "LEAD_SAWTOOTH","LEAD_CALLIOPE","LEAD_CHIFF",
                                           "LEAD_CHARANG","LEAD_VOICE","LEAD_FIFTHS",
                                           "LEAD_BASSLEAD","PAD_NEW_AGE","PAD_WARM",
                                           "PAD_POLYSYNTH","PAD_CHOIR","PAD_BOWED",
                                           "PAD_METALLIC","PAD_HALO","PAD_SWEEP",
                                           "FX_RAIN","FX_SOUNDTRACK","FX_CRYSTAL",
                                           "FX_ATMOSPHERE","FX_BRIGHTNESS","FX_GOBLINS",
                                           "FX_ECHOES","FX_SCI-FI","SITAR",
                                           "BANJO","SHAMISEN","KOTO",
                                           "KALIMBA","BAGPIPE","FIDDLE","SHANAI"};
        
        public PatInDropDownBox(){
            for(int i = 0; i < instrumentList.length; i++){
                this.addItem(""+i+" "+instrumentList[i]);
            }
        }
        
        public void actionPerformed(ActionEvent e){
            Scanner s = new Scanner((String)this.getSelectedItem());
            instrument = s.nextInt();
        }
    }
    
    /**
     * The drop down box for the scales
     */
    public class PatScDropDownBox extends JComboBox implements ActionListener{
        
        public PatScDropDownBox(){
            for(int s = 0; s < scale.scaleList().length; s++){
                this.addItem(scale.scaleList()[s]);
            }
        }
        public void actionPerformed(ActionEvent e){
            scale.changeScale((String)this.getSelectedItem());
            
        }
        public void setSelection(){
            for(int s = 0; s < scale.scaleList().length; s++){
                if(scale.toString().equals(scale.scaleList()[s]))
                    this.setSelectedIndex(s);
            }
        }
    }
    
    /**
     * The drop down box for the octaves
     */
    public class PatOcDropDownBox extends JComboBox implements ActionListener{
        
        public PatOcDropDownBox(){
            String[] oct = {"1 - 4","2 - 5", "3 - 6","4 - 7"};
            for(int s = 0; s < oct.length; s++){
                this.addItem(oct[s]);
            }
        }
        public void actionPerformed(ActionEvent e){
            Scanner s = new Scanner((String)this.getSelectedItem());
            octave = s.nextInt()-1;
        }
        public void setSelection(){
            if(octave == 0)
                this.setSelectedIndex(0);
            else if(octave == 1)
                this.setSelectedIndex(1);
            else if(octave == 2)
                this.setSelectedIndex(2);
            else 
                this.setSelectedIndex(3);
        }
    }
    
    /**
     * The drop down box for the octaves
     */
    public class PatNlDropDownBox extends JComboBox implements ActionListener{
        
        public PatNlDropDownBox(){
            String[] oct = {"8 Quarter","16 Half","32 Whole"};
            for(int s = 0; s < oct.length; s++){
                this.addItem(oct[s]);
            }
        }
        public void actionPerformed(ActionEvent e){
            Scanner s = new Scanner((String)this.getSelectedItem());
            nLength = s.nextInt();
            
        }
        public void setSelection(){
            if(nLength == 8)
                this.setSelectedIndex(0);
            else if(nLength == 16)
                this.setSelectedIndex(1);
            else 
                this.setSelectedIndex(2);
        }
    }
}
