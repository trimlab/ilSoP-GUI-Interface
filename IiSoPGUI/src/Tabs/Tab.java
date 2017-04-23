package Tabs;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.JPanel;

/**
 * This is the generalize tab
 * @author Xazaviar
 */
public abstract class Tab {

    private Color back;
    private String name;
    private JPanel p;
    
    private final int height = 30;
    private final int width = 100;

    /**
     * The default constructor (not in use)
     */
    public Tab(){}   
    
    /**
     * Returns the background color for the tab
     * @return 
     *          The background color
     */
    public Color getColor() {
        return back;
    }

    /**
     * Sets the background color for the tab
     * @param back 
     *          The color to set the background tab too
     */
    public void setColor(Color back) {
        this.back = back;
    }

    /**
     * The name of the tab
     * @return 
     *          The name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the tab
     * @param name 
     *          The new name for the tab
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the panel that is being drawn on
     * @return 
     *          The panel
     */
    public JPanel getPanel() {
        return p;
    }

    /**
     * Sets the panel that will be drawn on
     * @param p 
     *          The new panel
     */
    public void setPanel(JPanel p) {
        this.p = p;
    }
    
    /**
     * Returns the height of the tab
     * @return 
     *          The height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the width of tab
     * @return 
     *          The width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * The draw method that creates the tab
     * @param g
     *          The graphics
     */
    public void draw(Graphics g){}
    
    /**
     * Sets up the new components for the new tab
     */
    public void setupComponents(){}
    
    /**
     * Finds all of the .txt files in a given directory
     * and returns them
     * @param dirName
     *          The directory to search
     * @return 
     *          An array of the .txt files in the given directory
     */
    public static File[] finder(String dirName){
    	File dir = new File(dirName);
    	return dir.listFiles(new FilenameFilter() { 
    	         public boolean accept(File dir, String filename)
    	              { return filename.endsWith(".txt"); }
    	} );

    }
    
}
