
package Tabs;

import Resources.Item;
import Resources.Buttons.ObjButton;
import Resources.Buttons.ObjEnabledButton;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 * The object tab
 * @author Xazaviar
 */
public class ObjectsView extends Tab{

    private Item[] objects;
    private ObjButton buttons;
    private ObjEnabledButton butts;
    
    private final int boxWid = 300;
    private int boxHei;
    private int selected = 0;
    private int hover = -1;
    
    int perCol = 31;
            
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
        //boxWid = (super.getPanel().getWidth()-100)/objects.length;
        boxHei = super.getPanel().getHeight()-super.getHeight()-100;
        
        Graphics2D g2d = (Graphics2D) g;
        
        //Draw List
        g.setFont(new Font("ARIAL", Font.BOLD, 16));
        
        for(int o = 0; o < objects.length; o++){
            
            if(o == selected){
                g.setColor(new Color(112,112,255));
                g.fillRect(20+(o/perCol)*150, 45+20*(o%perCol), 150, 20);
            }
            if(o == hover) g.setColor(Color.white);
            else g.setColor(objects[o].getColor());
            g.drawString(objects[o].getName(), 25+(o/perCol)*150, (o%perCol)*20+60);
            
        }  
        //Draw Data

        int x = (super.getPanel().getWidth()-(boxWid+50))+15;

        g.setColor(objects[selected].getColor());
        g.fillRect((super.getPanel().getWidth()-(boxWid+50)),super.getHeight()+50, boxWid, boxHei);
        g.setColor(Color.black);
        g2d.setStroke(new BasicStroke(2F));
        g.drawRect((super.getPanel().getWidth()-(boxWid+50)),super.getHeight()+50, boxWid, boxHei);
        g2d.setStroke(new BasicStroke(1F));
        
        g.setFont(new Font("ARIAL", Font.BOLD, 36));
        g.drawString(objects[selected].getName(),                   x, 120);
        g.setFont(new Font("ARIAL", Font.BOLD, 20));
        g.drawString("X: "+objects[selected].getX(),                x, 155);
        g.drawString("X Vel: "+objects[selected].getxVel(),         x, 175);
        g.drawString("X Acc: "+objects[selected].getxAcc(),         x, 195);
        g.drawString("X VelAvg: "+objects[selected].getxVelAvg(),   x, 215);
        g.drawString("X AccAvg: "+objects[selected].getxAccAvg(),   x, 235);
        g.drawString("Y: "+objects[selected].getY(),                x, 255);
        g.drawString("Y Vel: "+objects[selected].getyVel(),         x, 275);
        g.drawString("Y Acc: "+objects[selected].getyAcc(),         x, 295);
        g.drawString("Y VelAvg: "+objects[selected].getyVelAvg(),   x, 315);
        g.drawString("Y AccAvg: "+objects[selected].getyAccAvg(),   x, 335);
        g.drawString("Z: "+objects[selected].getZ(),                x, 355);
        g.drawString("Z Vel: "+objects[selected].getzVel(),         x, 375);
        g.drawString("Z Acc: "+objects[selected].getzAcc(),         x, 395);
        g.drawString("Z VelAvg: "+objects[selected].getzVelAvg(),   x, 415);
        g.drawString("Z AccAvg: "+objects[selected].getzAccAvg(),   x, 435);
        g.drawString("C Vel: "+objects[selected].getcVel(),         x, 455);
        g.drawString("C Acc: "+objects[selected].getcAcc(),         x, 475);
        g.drawString("C VelAvg: "+objects[selected].getcVelAvg(),   x, 495);
        g.drawString("C AccAvg: "+objects[selected].getcAccAvg(),   x, 515);

        g.setFont(new Font("ARIAL", Font.PLAIN, 12));
             
    }
    
    public void update(Item[] t){
        this.objects = t;
    }
    
    public void newSelect(){ 
        if(buttons!=null){
            super.getPanel().remove(buttons);
            super.getPanel().remove(butts);
        }
        
        buttons = new ObjButton();
        buttons.setText("COLOR");
        buttons.setObj(objects[selected]);
        buttons.setBounds((super.getPanel().getWidth()-(boxWid+50))+25, super.getPanel().getHeight()-100, 100, 30);
        buttons.addActionListener(buttons);
        buttons.setFocusable(false);
        super.getPanel().add(buttons);

        butts = new ObjEnabledButton();
        butts.setText((objects[selected].isEnabled()?"DISABLE":"ENABLE"));
        butts.setObj(objects[selected]);
        butts.setBounds((super.getPanel().getWidth()-(boxWid+50))+175, super.getPanel().getHeight()-100, 100, 30);
        butts.addActionListener(butts);
        butts.setFocusable(false);
        super.getPanel().add(butts);
    }
    
    /**
     * Sets up the new components for the new tab
     */
    public void setupComponents(){
        //Setup Buttons
        newSelect();
        
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
            if(hover!=-1){
                selected = hover;
                newSelect();
            }
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
            for(int f = 0; f < objects.length; f++){
                if(e.getX()>25+(f/perCol)*150 && e.getX()<175+(f/perCol)*150 && e.getY()<60+20*(f%perCol) && e.getY()>40+20*(f%perCol)){
                    hover = f;
                    h = true;
                    break;
                }
            }
            if(!h) hover = -1;
        }
        
    }
}
