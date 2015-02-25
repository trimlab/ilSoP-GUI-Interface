/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Tabs;

import Resources.RoomAddButton;
import Resources.RoomDeleteButton;
import Resources.StartTextArea;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JPanel;

/**
 * The room tab
 * @author Xazaviar
 */
public class RoomView extends Tab{

    private StartTextArea x1, x2, y1, y2, z1, z2, name;
    private RoomAddButton add;
    private RoomDeleteButton delete;
    private int numFiles = 0;
    private int selected = -1;
    private int hover = -1;
    
    /**
     * The default constructor (not in use)
     */
    public RoomView(){}   
    
    /**
     * Sets up the tab with a name and background color
     * @param n
     *          The name for the tab
     * @param c 
     *          The color of the background
     */
    public RoomView(String n, Color c, JPanel p){
        super.setName(n);
        super.setColor(c);
        super.setPanel(p);
    }
    
    /**
     * The draw method that creates the tab
     * @param g
     *          The graphics
     * @param p 
     *          The panel to draw on
     */
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        //Draw room
        g.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3F));
        g.drawRect(25, super.getHeight()+25, 700, 350);
        
        g.drawLine(125,super.getPanel().getHeight()-25,875,super.getPanel().getHeight()-25);
        g.drawLine(26, super.getHeight()+376, 125,super.getPanel().getHeight()-25);
        g.drawLine(726, super.getHeight()+374, 875,super.getPanel().getHeight()-25);
        g2d.setStroke(new BasicStroke(1F));
        
        //Draw TVs
        for(int d = 0; d < 6; d++){
            g.drawRect(25+d*117, 55, 117, 87);
            g.drawRect(25+d*117, 142, 117, 87);
            g.drawRect(25+d*117, 229, 117, 87);
            g.drawRect(25+d*117, 316, 117, 87);
        }
        
        //Draw Side bar
        g.setColor(Color.WHITE);
        g.fillRect(super.getPanel().getWidth()-280, super.getHeight(), 280, super.getPanel().getHeight()-super.getHeight());
        g.setColor(Color.BLACK);
        
        g.setFont(new Font("ARIAL", Font.BOLD, 20));
        g.drawString("SECTIONS", super.getPanel().getWidth()-270, 155);
        
        g.drawLine(super.getPanel().getWidth()-270, 157, super.getPanel().getWidth()-10, 157);
        
        //Full bars
        //Y axis
        g.setColor(Color.green);
        g.drawLine(376, super.getHeight()+377, 500, super.getPanel().getHeight()-27);
        //X axis
        g.setColor(Color.red);
        g.drawLine(70, 550, 800, 550);
        //Z axis
        g.setColor(Color.blue);
        g.drawLine(438, 550, 438, 200);
        
        
        File[] files = super.finder("Resources/Sections");
        numFiles = files.length;
        g.setFont(new Font("ARIAL", Font.BOLD, 16));
        for(int f = 0; f < files.length; f++){
            if(f == selected){
                g.setColor(new Color(112,112,255));
                g.fillRect(super.getPanel().getWidth()-278, 165+25*f, 276, 20);
                drawSection(g2d,files[f]);
            }
            if(f == hover) g.setColor(Color.red);
            else g.setColor(Color.black);
            g.drawString(files[f].getName().substring(0, files[f].getName().length()-4), super.getPanel().getWidth()-260, 180+25*f);
            g.fillOval(super.getPanel().getWidth()-270, 170+25*f, 7, 7);
        }
        g.setFont(new Font("ARIAL", Font.PLAIN, 12));
        
        //Draw add box
        g.setColor(Color.GRAY);
        g.fillRect(super.getPanel().getWidth()-280, super.getHeight(), 280, 130-super.getHeight());
        g.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3F));
        g.drawLine(super.getPanel().getWidth()-279, 130, super.getPanel().getWidth(), 130);
        g2d.setStroke(new BasicStroke(1F));
        
        g.setFont(new Font("ARIAL", Font.BOLD, 15));
        g.drawString("Make New Section", super.getPanel().getWidth()-270, super.getHeight()+18);
        g.setFont(new Font("ARIAL", Font.PLAIN, 15));
        g.drawString("x1", super.getPanel().getWidth()-270, super.getHeight()+48);
        g.drawString("x2", super.getPanel().getWidth()-270, super.getHeight()+83);
        g.drawString("y1", super.getPanel().getWidth()-220, super.getHeight()+48);
        g.drawString("y2", super.getPanel().getWidth()-220, super.getHeight()+83);
        g.drawString("z1", super.getPanel().getWidth()-170, super.getHeight()+48);
        g.drawString("z2", super.getPanel().getWidth()-170, super.getHeight()+83);
        g.setFont(new Font("ARIAL", Font.PLAIN, 12));
        
        

//        //Y axis
//        g.setColor(Color.green);
//        g.drawLine(376, 530, 423, super.getPanel().getHeight()-27);
//        //X axis
//        g.setColor(Color.red);
//        g.drawLine(376, 530, 600, 530);
//        //Z axis
//        g.setColor(Color.blue);
//        g.drawLine(376, 529, 376, 300);
        
        //Update add button
        String[] test={x1.getText(),y1.getText(),z1.getText(),x2.getText(),y2.getText(),z2.getText()};
        for(int t = 0; t < test.length; t++){
            if(test[t].equals("") || test[t].equals("-")) test[t] = "-10";
            if(!(test[t].contains("0") || test[t].contains("1") || test[t].contains("2") ||
                 test[t].contains("3") || test[t].contains("4") || test[t].contains("5") ||
                 test[t].contains("6") || test[t].contains("7") || test[t].contains("8") ||
                 test[t].contains("9"))) test[t] = "-10";
        }
            
        add.updateData(name.getText(), Double.parseDouble(test[0]), 
                       Double.parseDouble(test[1]), Double.parseDouble(test[2]), 
                       Double.parseDouble(test[3]), Double.parseDouble(test[4]), 
                       Double.parseDouble(test[5]));
        if(selected != -1)
        delete.updateSelected(files[selected]);

    }
    
    /**
     * Draws the selected section
     * @param g 
     *          The graphics
     */
    private void drawSection(Graphics2D g, File f){
        Polygon fill = new Polygon();
        double x1=0,y1=0,z1=0,x2=0,y2=0,z2=0;
        try{
        Scanner s = new Scanner(f);
        x1 = s.nextDouble();
        y1 = s.nextDouble();
        z1 = s.nextDouble();
        x2 = s.nextDouble();
        y2 = s.nextDouble();
        z2 = s.nextDouble();
        s.close();
        }catch(IOException e){}
        
        //Build the polygon
        fill.addPoint(point(x1,y1,z1).x,point(x1,y1,z1).y); //1
        fill.addPoint(point(x1,y2,z1).x,point(x1,y2,z1).y); //2
        fill.addPoint(point(x2,y2,z1).x,point(x2,y2,z1).y); //3
        fill.addPoint(point(x2,y2,z2).x,point(x2,y2,z2).y); //4
        fill.addPoint(point(x2,y1,z2).x,point(x2,y1,z2).y); //5
        fill.addPoint(point(x1,y1,z2).x,point(x1,y1,z2).y); //6
        fill.addPoint(point(x1,y2,z2).x,point(x1,y2,z2).y); //7
        
        fill.addPoint(point(x2,y2,z2).x,point(x2,y2,z2).y); //4
        fill.addPoint(point(x1,y2,z2).x,point(x1,y2,z2).y); //7
        fill.addPoint(point(x1,y2,z1).x,point(x1,y2,z1).y); //2
        fill.addPoint(point(x1,y2,z2).x,point(x1,y2,z2).y); //7
        fill.addPoint(point(x1,y1,z2).x,point(x1,y1,z2).y); //6
        fill.addPoint(point(x1,y1,z1).x,point(x1,y1,z1).y); //1
        
        g.setStroke(new BasicStroke(2F));
        Composite originalComposite = g.getComposite();
        float alpha = .3F;
                int type = AlphaComposite.SRC_OVER;
                g.setComposite(AlphaComposite.getInstance(type, alpha));
        g.setColor(Color.magenta);
        g.fillPolygon(fill);
        g.setComposite(originalComposite);
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(4F));
        g.drawPolygon(fill);
        g.setStroke(new BasicStroke(1F));
        
        //Find the two points
        /*  Center of room: 442, 530
         *  Right Screen Edge: (725,25) - (725,405)
         *  Left Screen Edge: (25,25) - (25,405)
         * 
         *  Top Floor Edge: (25, 405) - (725, 405)
         *  Right Floor Edge: (725, 405) - (875, 695)
         *  Left Floor Edge: (25, 405) - (125, 695)
         *  Bottom Floor Edge: (125, 695) - (875, 695)
         */
        //Point 1
        
//        x1A = (int)(x1*(716/6)+438+(y1-1)*-(124/6));
//        y1A = (int)((z1+2)*(-350/7)+super.getHeight()+520+(y1-1)*-(289/6));
//
//        
//        g.fillRect(x1A-2, y1A-2, 5, 5);
//        
//        //Point 2
//        x2A = (int)(x2*(716/6))+438; //x
//        y2A = (int)((z2+2)*-350/7)+super.getHeight()+520; //z
//        x2A += (y2-1)*-(124/6);
//        y2A += (y2-1)*-(289/6);
//        
//        g.setColor(Color.magenta);
//        g.fillRect(x2A-2, y2A-2, 5, 5);
//        
//        System.out.println("("+x1+", "+y1+", "+z1+"):"+x1A+", "+y1A+"\n"+
//                           "("+x2+", "+y2+", "+z2+"):"+x2A+", "+y2A+"\n");

    }
    
    private Point point(double x, double y, double z){
        return new Point((int)(x*(716/6)+438+(y-1)*-(125/6)), 
                      (int)((z+2)*(-350/7)+super.getHeight()+520+(y-1)*-(290/6)));
    }
    
    /**
     * Sets up the new components for the new tab
     */
    public void setupComponents(){
        //Setup Text Areas
        x1 = new StartTextArea();
        x1.setBounds(super.getPanel().getWidth()-250, super.getHeight()+30, 25, 25);
        x1.setMaxChars(5);
        x1.addActionListener(x1);
        x1.addKeyListener(x1);
        x1.setFocusable(true);
        super.getPanel().add(x1);
        
        x2 = new StartTextArea();
        x2.setBounds(super.getPanel().getWidth()-250, super.getHeight()+65, 25, 25);
        x2.setMaxChars(5);
        x2.addActionListener(x2);
        x2.addKeyListener(x2);
        x2.setFocusable(true);
        super.getPanel().add(x2);
        
        y1 = new StartTextArea();
        y1.setBounds(super.getPanel().getWidth()-200, super.getHeight()+30, 25, 25);
        y1.setMaxChars(5);
        y1.addActionListener(y1);
        y1.addKeyListener(y1);
        y1.setFocusable(true);
        super.getPanel().add(y1);
        
        y2 = new StartTextArea();
        y2.setBounds(super.getPanel().getWidth()-200, super.getHeight()+65, 25, 25);
        y2.setMaxChars(5);
        y2.addActionListener(y2);
        y2.addKeyListener(y2);
        y2.setFocusable(true);
        super.getPanel().add(y2);
        
        z1 = new StartTextArea();
        z1.setBounds(super.getPanel().getWidth()-150, super.getHeight()+30, 25, 25);
        z1.setMaxChars(5);
        z1.addActionListener(z1);
        z1.addKeyListener(z1);
        z1.setFocusable(true);
        super.getPanel().add(z1);
        
        z2 = new StartTextArea();
        z2.setBounds(super.getPanel().getWidth()-150, super.getHeight()+65, 25, 25);
        z2.setMaxChars(5);
        z2.addActionListener(z2);
        z2.addKeyListener(z2);
        z2.setFocusable(true);
        super.getPanel().add(z2);
        
        name = new StartTextArea();
        name.setBounds(super.getPanel().getWidth()-115, super.getHeight()+25, 100, 25);
        name.setMaxChars(25);
        name.addActionListener(name);
        name.addKeyListener(name);
        name.setFocusable(true);
        super.getPanel().add(name);
        
        //Setup the Button
        add = new RoomAddButton();
        add.setText("ADD");
        add.setBounds(super.getPanel().getWidth()-115, super.getHeight()+63, 100, 30);
        add.addActionListener(add);
        add.setFocusable(false);
        super.getPanel().add(add);
        
        delete = new RoomDeleteButton();
        delete.setText("DELETE");
        delete.setBounds(super.getPanel().getWidth()-115, super.getPanel().getHeight()-50, 100, 30);
        delete.addActionListener(delete);
        delete.setFocusable(false);
        super.getPanel().add(delete);
        
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
            //System.out.println("HELLO");
            selected = hover;
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
                if(e.getX()>1000 && e.getY()<190+25*f && e.getY()>165+25*f){
                    hover = f;
                    h = true;
                    break;
                }
            }
            if(!h) hover = -1;
        }
        
    }
    
    
            

}
