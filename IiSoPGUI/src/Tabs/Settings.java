/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Tabs;

import Resources.ObjButton;
import Resources.Slider;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * The settings tab
 * @author Xazaviar
 */
public class Settings extends Tab{

    private Slider master, treble, bass;
    
    /**
     * The default constructor (not in use)
     */
    public Settings(){}   
    
    /**
     * Sets up the tab with a name and background color
     * @param n
     *          The name for the tab
     * @param c 
     *          The color of the background
     */
    public Settings(String n, Color c, JPanel p){
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
        g.setColor(Color.gray);
        g.fillRect(super.getPanel().getWidth()/2-250, super.getPanel().getHeight()/2-180, 80, 340);
        g.fillRect(super.getPanel().getWidth()/2-90, super.getPanel().getHeight()/2-180, 80, 340);
        g.fillRect(super.getPanel().getWidth()/2+70, super.getPanel().getHeight()/2-180, 80, 340);
        g.setColor(Color.black);
        g.setFont(new Font("ARIAL", Font.BOLD, 20));
        g.drawString("MASTER", super.getPanel().getWidth()/2-250, super.getPanel().getHeight()/2-160);
        g.drawString("TREBLE", super.getPanel().getWidth()/2-90, super.getPanel().getHeight()/2-160);
        g.drawString("BASS", super.getPanel().getWidth()/2+83, super.getPanel().getHeight()/2-160);
        g.setFont(new Font("ARIAL", Font.PLAIN, 12));
    }
    
    /**
     * Sets up the new components for the new tab
     * @param p 
     *          The panel to set the components on
     */
    public void setupComponents(){
        master = new Slider();
        master.setOrientation(1);
        master.setMaximum(100);
        master.setMinimum(0);
        master.setMajorTickSpacing(10);
        master.setMinorTickSpacing(5);
        master.setPaintTicks(true);
        master.setPaintLabels(true);
        master.setBackground(Color.gray);
        master.setBounds(super.getPanel().getWidth()/2-240, super.getPanel().getHeight()/2-150, 50, 300);
        master.addChangeListener(master);
        master.setFocusable(false);
        master.setValue(100);
        super.getPanel().add(master);
        
        treble = new Slider();
        treble.setOrientation(1);
        treble.setMaximum(100);
        treble.setMinimum(0);
        treble.setMajorTickSpacing(10);
        treble.setMinorTickSpacing(5);
        treble.setPaintTicks(true);
        treble.setPaintLabels(true);
        treble.setBackground(Color.gray);
        treble.setBounds(super.getPanel().getWidth()/2-80, super.getPanel().getHeight()/2-150, 50, 300);
        treble.addChangeListener(treble);
        treble.setFocusable(false);
        treble.setValue(70);
        super.getPanel().add(treble);
        
        bass = new Slider();
        bass.setOrientation(1);
        bass.setMaximum(100);
        bass.setMinimum(0);
        bass.setMajorTickSpacing(10);
        bass.setMinorTickSpacing(5);
        bass.setPaintTicks(true);
        bass.setPaintLabels(true);
        bass.setBackground(Color.gray);
        bass.setBounds(super.getPanel().getWidth()/2+80, super.getPanel().getHeight()/2-150, 50, 300);
        bass.addChangeListener(bass);
        bass.setFocusable(false);
        bass.setValue(70);
        super.getPanel().add(bass);
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
            
        }
        
    }
    
    private class Mouse2 extends JPanel implements MouseMotionListener{
        public void mouseDragged(MouseEvent e) {
            
        }

        /**
         * What to do when the mouse is moving
         * @param e 
         *          The information about the MouseEvent
         */
        public void mouseMoved(MouseEvent e) {}
        
    }
}
