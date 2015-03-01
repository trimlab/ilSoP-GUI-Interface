
package Resources;

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
public class AddConGUI {
    //Basic setup for layout
    private final int Width = 400;
    private final int Height = 200;
    private JFrame frame;
    private myJPanel mainpanel;
    private Timer timer = new Timer();
    
    private ArrayList<Section> sections;
    private Item[] obj;
    private Conditional conditional;
    
    //Accessories
    private finishButton button;
    private ObjDropDownBox drop1;
    private SectionDropDownBox drop2;
    private ValueDropDownBox drop3;
    private OperatorDropDownBox drop4;
    private ConditionDropDownBox drop5;
    private myTextArea param2;
    
    private String object, section, value, oper;
    private int cond = 0;
    private boolean conSel = false, conFin = true;
    
    public AddConGUI(Item[] obj, ArrayList<Section> sections, Conditional conditional){
        this.obj = obj;
        this.conditional = conditional;
        this.sections = sections;
        
        frame = new JFrame("Create a new Condition");
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
        this.object = obj[0].getName();
        
        //Setup Buttons
        button = new finishButton();
        button.setText("FINISH");
        button.setBounds(150,165,100,30);
        button.addActionListener(button);
        button.setFocusable(false);
        button.setVisible(false);
        mainpanel.add(button);
        
        //Setup textArea
        param2 = new myTextArea();
        param2.setBounds(210,75,50,30);
        param2.addActionListener(param2);
        param2.addKeyListener(param2);
        param2.setFocusable(true);
        button.setVisible(false);
        param2.setText("0");
        mainpanel.add(param2);
        
        //Setup Dropdown boxes
        drop1 = new ObjDropDownBox();
        drop1.setBounds(80,5,180,30);
        drop1.setFocusable(false);
        drop1.addActionListener(drop1);
        mainpanel.add(drop1);
        
        drop5 = new ConditionDropDownBox();
        drop5.setBounds(80,40,180,30);
        drop5.setFocusable(false);
        drop5.addActionListener(drop5);
        mainpanel.add(drop5);
        
        drop2 = new SectionDropDownBox();
        drop2.setBounds(80,75,180,30);
        drop2.setFocusable(false);
        drop2.addActionListener(drop2);
        button.setVisible(false);
        mainpanel.add(drop2);
        
        drop3 = new ValueDropDownBox();
        drop3.setBounds(80,75,80,30);
        drop3.setFocusable(false);
        drop3.addActionListener(drop3);
        button.setVisible(false);
        mainpanel.add(drop3);
        
        drop4 = new OperatorDropDownBox();
        drop4.setBounds(165,75,40,30);
        drop4.setFocusable(false);
        drop4.addActionListener(drop4);
        button.setVisible(false);
        mainpanel.add(drop4);
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
                if(cond==0){
                    drop2.setVisible(true);
                    drop3.setVisible(false);
                    drop4.setVisible(false);
                    param2.setVisible(false);
                }else if(cond == 1){
                    drop3.setVisible(true);
                    drop4.setVisible(true);
                    param2.setVisible(true);
                    drop2.setVisible(false);
                }
            }else{
                drop2.setVisible(false);
                drop3.setVisible(false);
                drop4.setVisible(false);
                param2.setVisible(false);
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
            
            if(cond == 0){ //Section option
                if(object == null || section == null)
                    finish = false;
                output = object+" IN "+section;
            }else if(cond == 1){ //Value option
                double d=0;
                try{
                d = Double.parseDouble(param2.getText());
                }catch(NumberFormatException ee){
                    d = 0;
                }
                
                if(object == null || value == null || oper == null)
                    finish = false;
                output = object+" "+value+" "+oper+" "+d;
            }
            
            if(finish){
                conditional.addConition(output);
                frame.dispose();
            }
            
        }
        
    }
    
    private class ObjDropDownBox extends JComboBox implements ActionListener{
        
        public ObjDropDownBox(){
            for(int i = 0; i < obj.length; i++){
                this.addItem(obj[i].getName());
            }
        }
        
        public void actionPerformed(ActionEvent e) {
            object = (String)this.getSelectedItem();
            
        }
        
    }
    
    private class SectionDropDownBox extends JComboBox implements ActionListener{
        
        public SectionDropDownBox(){
            for(int i = 0; i < sections.size(); i++){
                this.addItem(sections.get(i).getName());
            }
        }
        
        public void actionPerformed(ActionEvent e) {
            section = (String)this.getSelectedItem();
            conFin = true;
        }
        
    }
    
    private class ValueDropDownBox extends JComboBox implements ActionListener{
        
        private String[] list = {"x","y","z",
                                 "xVel","yVel","zVel",
                                 "xAcc","yAcc","zAcc",
                                 "xVelAvg","yVelAvg","zVelAvg",
                                 "xAccAvg","yAccAvg","zAccAvg",
                                 "cVel","cAcc","cVelAvg","cAccAvg"};
        
        public ValueDropDownBox(){
            for(int i = 0; i < list.length; i++){
                this.addItem(list[i]);
            }
        }
        
        public void actionPerformed(ActionEvent e) {
            value = (String)this.getSelectedItem();
            conFin = true;
            
        }
        
    }
    
    private class OperatorDropDownBox extends JComboBox implements ActionListener{
        
        private String[] list = {">",">=","<","<=","="};
        
        public OperatorDropDownBox(){
            for(int i = 0; i < list.length; i++){
                this.addItem(list[i]);
            }
        }
        
        public void actionPerformed(ActionEvent e) {
            oper = (String)this.getSelectedItem();
            conFin = true;
            
        }
        
    }
    
    private class ConditionDropDownBox extends JComboBox implements ActionListener{
        
        private String[] list = {"In a section","Value"};
        
        public ConditionDropDownBox(){
            for(int i = 0; i < list.length; i++){
                this.addItem(list[i]);
            }
        }
        
        public void actionPerformed(ActionEvent e) {
            cond = this.getSelectedIndex();
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
        this.setText(this.getText().replaceAll("[^-.0-9]", ""));
        conFin = true;
    }
    
    public void keyPressed(KeyEvent e) {
        this.setText(this.getText().replaceAll("[^-.0-9]", ""));
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
