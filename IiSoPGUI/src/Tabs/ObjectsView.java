
package Tabs;

import Resources.Item;
import Resources.Buttons.ObjButton;
import Resources.Buttons.ObjEnabledButton;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * The object tab
 * @author Xazaviar
 */
public class ObjectsView extends Tab{

    private Item[] objects;
    private ObjButton[] buttons;
    private ObjEnabledButton[] butts;
    
    private int boxWid, boxHei;
            
    /**
     * The default constructor (not in use)
     */
    public ObjectsView(){}   
    
    /**
     * Sets up the tab with a name and background color
     * @param n
     *          The name for the tab
     * @param c 
     *          The color of the background
     */
    public ObjectsView(String n, Color c, JPanel p, Item[] o){
        super.setName(n);
        super.setColor(c);
        super.setPanel(p);
        objects = o;
        buttons = new ObjButton[o.length];
        butts = new ObjEnabledButton[o.length];
        
        for(int cl = 0; cl < objects.length; cl++){
            objects[cl].setColor(0);
        }
        
        
    }
    
    /**
     * The draw method that creates the tab
     * @param g
     *          The graphics
     */
    public void draw(Graphics g){
        boxWid = (super.getPanel().getWidth()-100)/objects.length;
        boxHei = super.getPanel().getHeight()-super.getHeight()-100;
        
        Graphics2D g2d = (Graphics2D) g;
        
        for(int o = 0; o < objects.length; o++){
            //Draw Boxes
            g.setColor(objects[o].getColor());
            g.fillRect(o*boxWid+50,super.getHeight()+50, boxWid, boxHei);
            g.setColor(Color.black);
            g2d.setStroke(new BasicStroke(2F));
            g.drawRect(o*boxWid+50,super.getHeight()+50, boxWid, boxHei);
            g2d.setStroke(new BasicStroke(1F));
            
            //Draw Data
            g.setFont(new Font("ARIAL", Font.BOLD, 36));
            g.drawString(objects[o].getName(), o*boxWid+boxWid/2, 130);
            g.setFont(new Font("ARIAL", Font.BOLD, 20));
            
            g.drawString("X: "+objects[o].getX(), o*boxWid+boxWid/2-40, 155);
            g.drawString("X Vel: "+objects[o].getxVel(), o*boxWid+boxWid/2-40, 175);
            g.drawString("X Acc: "+objects[o].getxAcc(), o*boxWid+boxWid/2-40, 195);
            g.drawString("X VelAvg: "+objects[o].getxVelAvg(), o*boxWid+boxWid/2-40, 215);
            g.drawString("X AccAvg: "+objects[o].getxAccAvg(), o*boxWid+boxWid/2-40, 235);
            g.drawString("Y: "+objects[o].getY(), o*boxWid+boxWid/2-40, 255);
            g.drawString("Y Vel: "+objects[o].getyVel(), o*boxWid+boxWid/2-40, 275);
            g.drawString("Y Acc: "+objects[o].getyAcc(), o*boxWid+boxWid/2-40, 295);
            g.drawString("Y VelAvg: "+objects[o].getyVelAvg(), o*boxWid+boxWid/2-40, 315);
            g.drawString("Y AccAvg: "+objects[o].getyAccAvg(), o*boxWid+boxWid/2-40, 335);
            g.drawString("Z: "+objects[o].getZ(), o*boxWid+boxWid/2-40, 355);
            g.drawString("Z Vel: "+objects[o].getzVel(), o*boxWid+boxWid/2-40, 375);
            g.drawString("Z Acc: "+objects[o].getzAcc(), o*boxWid+boxWid/2-40, 395);
            g.drawString("Z VelAvg: "+objects[o].getzVelAvg(), o*boxWid+boxWid/2-40, 415);
            g.drawString("Z AccAvg: "+objects[o].getzAccAvg(), o*boxWid+boxWid/2-40, 435);
            g.drawString("C Vel: "+objects[o].getcVel(), o*boxWid+boxWid/2-40, 455);
            g.drawString("C Acc: "+objects[o].getcAcc(), o*boxWid+boxWid/2-40, 475);
            g.drawString("C VelAvg: "+objects[o].getcVelAvg(), o*boxWid+boxWid/2-40, 495);
            g.drawString("C AccAvg: "+objects[o].getcAccAvg(), o*boxWid+boxWid/2-40, 515);
            
            g.setFont(new Font("ARIAL", Font.PLAIN, 12));
            
        }
    }
    
    public void update(Item[] t){
        int len = objects.length;
        this.objects = t;
        
            //Check if remake needed
            if(len != objects.length){
                buttons = new ObjButton[objects.length];
                butts = new ObjEnabledButton[objects.length];

                for(int b = 0; b < buttons.length; b++){
                buttons[b] = new ObjButton();
                buttons[b].setText("COLOR");
                buttons[b].setObj(objects[b]);
                buttons[b].setBounds(b*(super.getPanel().getWidth()-100)/objects.length+80, super.getPanel().getHeight()-100, 100, 30);
                buttons[b].addActionListener(buttons[b]);
                buttons[b].setFocusable(false);
                super.getPanel().add(buttons[b]);

                butts[b] = new ObjEnabledButton();
                butts[b].setText("DISABLE");
                butts[b].setObj(objects[b]);
                butts[b].setBounds(b*(super.getPanel().getWidth()-100)/objects.length+(super.getPanel().getWidth()-100)/objects.length-80, super.getPanel().getHeight()-100, 100, 30);
                butts[b].addActionListener(butts[b]);
                butts[b].setFocusable(false);
                super.getPanel().add(butts[b]);
            }
        }
    }
    
    /**
     * Sets up the new components for the new tab
     */
    public void setupComponents(){
        //Setup Buttons
        for(int b = 0; b < buttons.length; b++){
            buttons[b] = new ObjButton();
            buttons[b].setText("COLOR");
            buttons[b].setObj(objects[b]);
            buttons[b].setBounds(b*(super.getPanel().getWidth()-100)/objects.length+80, super.getPanel().getHeight()-100, 100, 30);
            buttons[b].addActionListener(buttons[b]);
            buttons[b].setFocusable(false);
            super.getPanel().add(buttons[b]);
            
            butts[b] = new ObjEnabledButton();
            butts[b].setText("DISABLE");
            butts[b].setObj(objects[b]);
            butts[b].setBounds(b*(super.getPanel().getWidth()-100)/objects.length+(super.getPanel().getWidth()-100)/objects.length-80, super.getPanel().getHeight()-100, 100, 30);
            butts[b].addActionListener(butts[b]);
            butts[b].setFocusable(false);
            super.getPanel().add(butts[b]);
        }
    }
}
