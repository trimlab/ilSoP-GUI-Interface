/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Tabs;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * The Pattern maker tab
 * @author Xazaviar
 */
public class PatternView extends Tab{

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
     */
    public PatternView(String n, Color c, JPanel p){
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
        
    }
    
    /**
     * Sets up the new components for the new tab
     * @param p 
     *          The panel to set the components on
     */
    public void setupComponents(){
    
    }
}
