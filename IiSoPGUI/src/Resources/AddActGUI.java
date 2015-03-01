package Resources;

import MusicThreads.MusicThread;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Joseph Ryan
 */
public class AddActGUI {
    //Basic setup for layout
    private final int Width = 400;
    private final int Height = 200;
    private JFrame frame;
    private myJPanel mainpanel;
    private Timer timer = new Timer();
    
    private ArrayList<PatternInfo> patterns;
    private MusicThread[] threads;
    private Conditional conditional;
    
    //Accessories
    private finishButton button;
    private ActionDropDownBox drop1;
    private PatternDropDownBox drop2;
    private ThreadsDropDownBox drop3;
    private myTextArea note;
    
    private String pattern, thread;
    private int act = 0;
    private boolean conSel = false, conFin = false;
 
    public AddActGUI(MusicThread[] threads, ArrayList<PatternInfo> patterns, Conditional conditional){
        this.threads = threads;
        this.conditional = conditional;
        this.patterns = patterns;
        
        frame = new JFrame("Create a new Action");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-Width/2, 
                          Toolkit.getDefaultToolkit().getScreenSize().height/2-Height/2);
        
        mainpanel = new myJPanel();
        mainpanel.setPreferredSize(new Dimension(Width, Height));
        mainpanel.setLayout(null);
        mainpanel.setFocusable(true);
        mainpanel.requestFocus();
        
        this.setupComponents();
        
        frame.getContentPane().add(mainpanel);
        frame.pack();
        frame.setVisible(true);
        timer.schedule(new RemindTask(), 25);
    }
    
    public void setupComponents(){
        //Initial vars
        thread = threads[0].getName();
        
        //Setup Buttons
        button = new finishButton();
        button.setText("FINISH");
        button.setBounds(150,165,100,30);
        button.addActionListener(button);
        button.setFocusable(false);
        button.setVisible(false);
        mainpanel.add(button);
        
        //Setup textArea
        note = new myTextArea();
        note.setBounds(80,40,50,30);
        note.addActionListener(note);
        note.addKeyListener(note);
        note.setFocusable(true);
        button.setVisible(false);
        note.setText("G3");
        mainpanel.add(note);
        
        //Setup Dropdown boxes
        drop1 = new ActionDropDownBox();
        drop1.setBounds(80,5,180,30);
        drop1.setFocusable(false);
        drop1.addActionListener(drop1);
        mainpanel.add(drop1);
        
        drop2 = new PatternDropDownBox();
        drop2.setBounds(80,40,180,30);
        drop2.setFocusable(false);
        drop2.addActionListener(drop2);
        button.setVisible(false);
        mainpanel.add(drop2);
        
        drop3 = new ThreadsDropDownBox();
        drop3.setBounds(80,75,180,30);
        drop3.setFocusable(false);
        drop3.addActionListener(drop3);
        button.setVisible(false);
        mainpanel.add(drop3);
    }
    
    public void display(){
        mainpanel.repaint();
    }
    
    /**
     * This is where and what is actually drawn in
     * the frame
     */
    private class myJPanel extends JPanel{
        
        /**
         * Performs the drawing of the GUI
         * @param g 
         *          The Graphics variable
         */
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            setBackground(new Color(126,126,126));
            
            if(conSel){
                if(act==0){
                    drop2.setVisible(false);
                    drop3.setVisible(true);
                    note.setVisible(true);
                }else if(act == 1){
                    drop2.setVisible(true);
                    drop3.setVisible(true);
                    note.setVisible(false);
                }
            }else{
                drop2.setVisible(false);
                drop3.setVisible(false);
                note.setVisible(false);
            }
            if(conFin)
                button.setVisible(true);
            else
                button.setVisible(false);
        }
    }
    
    private class finishButton extends JButton implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            String output="";
            boolean finish = true;
            
            if(act == 0){ //Section option
                if(note.getText() == null || thread == null)
                    finish = false;
                output = "PLAY "+note.getText()+" IN "+thread;
            }else if(act == 1){ //Value option
                if(pattern == null || thread == null)
                    finish = false;
                output = "PLAY PATTERN "+pattern+" IN "+thread;
            }
            
            if(finish){
                conditional.addAction(output);
                frame.dispose();
            }
            
        }
        
    }
    
    private class PatternDropDownBox extends JComboBox implements ActionListener{
        
        public PatternDropDownBox(){
            for(int i = 0; i < patterns.size(); i++){
                this.addItem(patterns.get(i).getName());
            }
        }
        
        public void actionPerformed(ActionEvent e) {
            pattern = (String)this.getSelectedItem();
            conFin = true;
        }
        
    }
    
    private class ThreadsDropDownBox extends JComboBox implements ActionListener{
        
        public ThreadsDropDownBox(){
            for(int i = 0; i < threads.length; i++){
                this.addItem(threads[i].getName());
            }
        }
        
        public void actionPerformed(ActionEvent e) {
            thread = (String)this.getSelectedItem();
            conFin = true;
        }
        
    }
    
    private class ActionDropDownBox extends JComboBox implements ActionListener{
        
        private String[] list = {"Play note","Play Pattern"};
        
        public ActionDropDownBox(){
            for(int i = 0; i < list.length; i++){
                this.addItem(list[i]);
            }
        }
        
        public void actionPerformed(ActionEvent e) {
            act = this.getSelectedIndex();
            conSel = true;
        }
        
    }
    
    public class myTextArea extends JTextField implements ActionListener, KeyListener{
    
    private int max = 6;
    
    public void actionPerformed(ActionEvent e) {
        //System.out.println(this.getText());   
    }
    
    public void keyTyped(KeyEvent e) {
        if(this.getText().length() > max)
            this.setText(this.getText().substring(0, this.getText().length()-1));
        this.setText(this.getText().replaceAll("[^0-9A-G]", ""));
        conFin = true;
    }
    
    public void keyPressed(KeyEvent e) {
        this.setText(this.getText().replaceAll("[^1-7A-G]", ""));
        conFin = true;
    }
    public void keyReleased(KeyEvent e){}
    
    public void setMaxChars(int m){
        max = m;
    }
}
    
    public class RemindTask extends TimerTask{
        public void run(){  
            display(); 
            timer.schedule(new RemindTask(), 25);
        }
    }
}
