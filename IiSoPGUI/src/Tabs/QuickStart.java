/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Tabs;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import Resources.*;
import java.awt.Font;
import java.io.File;

/**
 * The quick start tab
 * @author Xazaviar
 */
public class QuickStart extends Tab{

    private StartButton button1;
    private StartTextArea textField1;
    private StartDropDown dropDown1;
    private StartCheckBox checkBox1;
    
    /**
     * The default constructor (not in use)
     */
    public QuickStart(){}  
    
    /**
     * Sets up the tab with a name and background color
     * @param n
     *          The name for the tab
     * @param c 
     *          The color of the background
     */
    public QuickStart(String n, Color c, JPanel p){
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
        g.setColor(Color.BLACK);
        g.setFont(new Font("ARIAL", Font.BOLD, 20));
        g.drawString("Enable Editing:", super.getPanel().getWidth()/2-140, super.getPanel().getHeight()/2-20);
        g.setFont(new Font("ARIAL", Font.PLAIN, 12));
    }
    
    /**
     * Sets up the new components for the new tab
     * @param p 
     *          The panel to set the components on
     */
    public void setupComponents(){
        //Setup the Button
        button1 = new StartButton();
        button1.setText("START");
        button1.setBounds(super.getPanel().getWidth()/2-50, super.getPanel().getHeight()/2, 100, 30);
        button1.addActionListener(button1);
        button1.setFocusable(false);
        super.getPanel().add(button1);
        
        //Load Sample Module options
        //and set up drop down box
        dropDown1 = new StartDropDown();
        File[] files = super.finder("Resources/Sample Modules");
        for(int l = 0; l < files.length; l++){
            dropDown1.addItem(files[l]);
        }
        dropDown1.setBounds(super.getPanel().getWidth()/2-175, super.getPanel().getHeight()/2-80, 350, 30);
        dropDown1.setFocusable(false);
        dropDown1.addActionListener(dropDown1);
        super.getPanel().add(dropDown1);
        
        //Set up TextArea
        textField1 = new StartTextArea();
        textField1.setBounds(super.getPanel().getWidth()/2-175, super.getPanel().getHeight()/2-120, 350, 30);
        textField1.addActionListener(textField1);
        textField1.addKeyListener(textField1);
        textField1.setMaxChars(50);
        textField1.setFocusable(true);
        super.getPanel().add(textField1);
        
        //Set up check box
        checkBox1 = new StartCheckBox();
        checkBox1.setContentAreaFilled(false);
        checkBox1.setBounds(super.getPanel().getWidth()/2+10, super.getPanel().getHeight()/2-37, 20, 20);
        checkBox1.addActionListener(checkBox1);
        checkBox1.setFocusable(false);
        super.getPanel().add(checkBox1);
    }
}
