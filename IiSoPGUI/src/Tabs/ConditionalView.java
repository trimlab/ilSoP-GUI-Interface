
package Tabs;

import MusicThreads.MusicThread;
import Resources.Buttons.ConAddButton;
import Resources.Buttons.ConAddIfButton;
import Resources.Buttons.ConAddThenButton;
import Resources.Buttons.ConDeleteButton;
import Resources.Conditional;
import Resources.Buttons.ConDeleteIfButton;
import Resources.Buttons.ConDeleteThenButton;
import Resources.Buttons.ConUseButton;
import Resources.Item;
import Resources.PatternInfo;
import Resources.Section;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * The conditionals tab
 * @author Xazaviar
 */
public class ConditionalView extends Tab{
    
    private int selectedC = -1;
    private int hoverC = -1;
    private int selectedT = -1;
    private int hoverT = -1;
    private int selectedF = -1;
    private int hoverF = -1;
    private boolean newSelect = false;
    
    //Buttons
    private ConAddButton addC;
    private ConAddIfButton addF;
    private ConAddThenButton addT;
    private ConDeleteButton delC; 
    private ConDeleteIfButton delF;
    private ConDeleteThenButton delT;
    private ConUseButton useC;
    
    //Info
    private Item[] objects;
    private MusicThread[] threads;
    private ArrayList<Section> sections = new ArrayList<>();
    private ArrayList<PatternInfo> patterns = new ArrayList<>();
    private ArrayList<Conditional> conditionals = new ArrayList<>();
    
    
    /**
     * The default constructor (not in use)
     */
    public ConditionalView(){}   
    
    /**
     * Sets up the tab with a name and background color
     * @param n
     *          The name for the tab
     * @param c 
     *          The color of the background
     */
    public ConditionalView(String n, Color c, JPanel p){
        super.setName(n);
        super.setColor(c);
        super.setPanel(p);
    }
    
    /**
     * Does stuff
     */
    public void setupConditionals(){
        File[] files = super.finder("Resources/Conditionals");
        for(int t = 0; t < files.length; t++){
            conditionals.add(new Conditional(files[t],objects,sections,patterns,threads));
        }
    }
    
    /**
     * Return the conditionals list
     * @return 
     *          Returns the conditional list
     */
    public ArrayList<Conditional> getConditionals(){
        return this.conditionals;
    }
    
    /**
     * Gets all of the info needed to create new conditions
     * @param ob
     *          The list of objects being tracked
     * @param sec
     *          The current list of sections to use
     * @param pats 
     *          The current list of patterns to use
     * @param threads
     *          The list of active music threads
     */
    public void getInfo(Item[] ob, ArrayList<Section> sec, ArrayList<PatternInfo> pats,MusicThread[] threads){
        this.objects = ob;
        this.sections = sec;
        this.patterns = pats;
        this.threads = threads;
    }
    
    /**
     * The draw method that creates the tab
     * @param g
     *          The graphics
     */
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        //Draw Boxes
        g.setColor(new Color(210,210,210));
        g.fillRect(25, super.getHeight()+25, super.getPanel().getWidth()-50, 
                   super.getPanel().getHeight()-super.getHeight()-50);
        g.setColor(Color.white);
        g.fillRect(25+(super.getPanel().getWidth()-50)/3, super.getHeight()+25, 
                (super.getPanel().getWidth()-50)/3, super.getPanel().getHeight()-super.getHeight()-50);
        g.setColor(Color.black);
        g.drawRect(25, super.getHeight()+25, super.getPanel().getWidth()-50, 
                   super.getPanel().getHeight()-super.getHeight()-50);
        g.drawRect(25+(super.getPanel().getWidth()-50)/3, super.getHeight()+25, 
                (super.getPanel().getWidth()-50)/3, super.getPanel().getHeight()-super.getHeight()-50);
        
        //Draw Conditions
        g.setFont(new Font("ARIAL", Font.BOLD, 36));
        g2d.setStroke(new BasicStroke(3F));
        g.drawLine(30, super.getHeight()+62, 30+(super.getPanel().getWidth()-50)/3-25, super.getHeight()+62);
        g.drawLine(30+(super.getPanel().getWidth()-50)/3, super.getHeight()+62, 30+2*(super.getPanel().getWidth()-50)/3-25, super.getHeight()+62);
        g.drawLine(30+2*(super.getPanel().getWidth()-50)/3, super.getHeight()+62, super.getPanel().getWidth()-50, super.getHeight()+62);
        g2d.setStroke(new BasicStroke(1F));
        g.drawString("CONDITONALS", 30, super.getHeight()+60);
        g.drawString("CONDITIONS", 30+(super.getPanel().getWidth()-50)/3, super.getHeight()+60);
        g.drawString("ACTIONS", 30+2*(super.getPanel().getWidth()-50)/3, super.getHeight()+60);
        
        
        g.setFont(new Font("ARIAL", Font.BOLD, 20));
        for(int f = 0; f < conditionals.size(); f++){
            if(f == selectedC){
                g.setColor(new Color(112,112,255));
                g.fillRect(30, 100+25*f, 390, 25);
                if(newSelect)
                    drawConditionals(g,conditionals.get(f));
                delC.updateSelected(conditionals.get(f),conditionals);
                useC.updateSelected(conditionals.get(f));
                addF.updateCon(objects, conditionals.get(f), sections);
                addT.updateCon(threads, conditionals.get(f), patterns);
            }
            if(f == hoverC) g.setColor(Color.red);
            else if(conditionals.get(f).isEnabled()) g.setColor(Color.black);
            else g.setColor(Color.gray);
            g.drawString(conditionals.get(f).getName().substring(0, conditionals.get(f).getName().length()-4), 50, 120+25*f);
            g.fillOval(40, 110+25*f, 7, 7);
        }
        g.setFont(new Font("ARIAL", Font.PLAIN, 12));
        
        addC.updateConList(conditionals,objects,sections,patterns,threads);
        for(int u = 0; u < conditionals.size(); u++){
            for(int c= 0; c < conditionals.get(u).getConditions().size(); c++)
                conditionals.get(u).getConditions().get(c).updateCondition(objects, sections);
            for(int a = 0; a < conditionals.get(u).getActions().size(); a++)
                conditionals.get(u).getActions().get(a).updateAction(patterns, threads);
        }
        
    }
    
    private void drawConditionals(Graphics g, Conditional con){

        //Draw Conditions
        g.setFont(new Font("ARIAL", Font.BOLD, 20));
        for(int c = 0; c < con.getConditions().size(); c++){
            if(c == selectedF){
                g.setColor(new Color(112,112,255));
                g.fillRect(30+(super.getPanel().getWidth()-50)/3, 100+25*c, 390, 25);
                delF.update(con, con.getConditions().get(c).toString());
            }
            if(c == hoverF) g.setColor(Color.red);
            else if(con.isEnabled()){
                Item temp = con.getConditions().get(c).getObject();
                if(temp!=null)
                    g.setColor(temp.getColor());
                else
                    g.setColor(Color.DARK_GRAY);
            }
            else{ 
                Item temp = con.getConditions().get(c).getObject();
                if(temp!=null)
                    g.setColor(temp.getColorDimmed());
                else
                    g.setColor(Color.DARK_GRAY);
            }
            g.drawString(con.getConditions().get(c).toString(), 50+(super.getPanel().getWidth()-50)/3, 120+25*c);
            g.fillOval(40+(super.getPanel().getWidth()-50)/3, 110+25*c, 7, 7);
        }
        
        //Draw Actions
        g.setFont(new Font("ARIAL", Font.BOLD, 20));
        for(int c = 0; c < con.getActions().size(); c++){
            if(c == selectedT){
                g.setColor(new Color(112,112,255));
                g.fillRect(30+2*(super.getPanel().getWidth()-50)/3, 100+25*c, 390, 25);
                delT.update(con, con.getActions().get(c).toString());
            }
            if(c == hoverT) g.setColor(Color.red);
            else if(con.isEnabled()) g.setColor(Color.black);
            else g.setColor(Color.gray);
            g.drawString(con.getActions().get(c).toString(), 50+2*(super.getPanel().getWidth()-50)/3, 120+25*c);
            g.fillOval(40+2*(super.getPanel().getWidth()-50)/3, 110+25*c, 7, 7);
        }
        
    }
    
    /**
     * Sets up the new components for the new tab
     */
    public void setupComponents(){
        //Setup the Button
        addC = new ConAddButton();
        addC.setText("ADD");
        addC.setBounds(45, super.getPanel().getHeight()-45-super.getHeight(), 100, 30);
        addC.addActionListener(addC);
        addC.setFocusable(false);
        super.getPanel().add(addC);
        
        delC = new ConDeleteButton();
        delC.setText("DELETE");
        delC.setBounds((super.getPanel().getWidth()-50)/3-100, super.getPanel().getHeight()-45-super.getHeight(), 100, 30);
        delC.addActionListener(delC);
        delC.setFocusable(false);
        super.getPanel().add(delC);
        
        useC = new ConUseButton();
        useC.setText("ENABLE");
        useC.setBounds(180, super.getPanel().getHeight()-45-super.getHeight(), 100, 30);
        useC.addActionListener(useC);
        useC.setFocusable(false);
        super.getPanel().add(useC);
        
        addF = new ConAddIfButton();
        addF.setText("ADD");
        addF.setBounds(45+(super.getPanel().getWidth()-50)/3, super.getPanel().getHeight()-45-super.getHeight(), 100, 30);
        addF.addActionListener(addF);
        addF.setFocusable(false);
        super.getPanel().add(addF);
        
        delF = new ConDeleteIfButton();
        delF.setText("DELETE");
        delF.setBounds(2*(super.getPanel().getWidth()-50)/3-100, super.getPanel().getHeight()-45-super.getHeight(), 100, 30);
        delF.addActionListener(delF);
        delF.setFocusable(false);
        super.getPanel().add(delF);
        
        addT = new ConAddThenButton();
        addT.setText("ADD");
        addT.setBounds(45+2*(super.getPanel().getWidth()-50)/3, super.getPanel().getHeight()-45-super.getHeight(), 100, 30);
        addT.addActionListener(addT);
        addT.setFocusable(false);
        super.getPanel().add(addT);
        
        delT = new ConDeleteThenButton();
        delT.setText("DELETE");
        delT.setBounds(3*(super.getPanel().getWidth()-50)/3-100, super.getPanel().getHeight()-45-super.getHeight(), 100, 30);
        delT.addActionListener(delT);
        delT.setFocusable(false);
        super.getPanel().add(delT);
        
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
            if(!(selectedC!=-1 && (hoverF != -1 || hoverT != -1))){
                int temp = selectedC;
                selectedC = hoverC;
            
                if(selectedC != temp && selectedC != -1){
                    newSelect = true;
                }
                
            }
            selectedF = hoverF;
            selectedT = hoverT;
            
            
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
            boolean hC = false, hF = false, hT = false;
            
            for(int f = 0; f < conditionals.size(); f++){
                if(e.getX()<235 && e.getX()>25 && e.getY()<130+25*f && e.getY()>95+25*f){
                    hoverC = f;
                    hC = true;
                    break;
                }
            }
            try{
            if(selectedC != -1){
                for(int f = 0; f < conditionals.get(selectedC).getConditions().size(); f++){
                    if(e.getX()<645 && e.getX()>435 && e.getY()<130+25*f && e.getY()>95+25*f){
                        hoverF = f;
                        hF = true;
                        break;
                    }
                }
                for(int f = 0; f < conditionals.get(selectedC).getActions().size(); f++){
                    if(e.getX()<1055 && e.getX()>845 && e.getY()<130+25*f && e.getY()>95+25*f){
                        hoverT = f;
                        hT = true;
                        break;
                    }
                }
            }
            }catch(java.lang.IndexOutOfBoundsException ex){ selectedC = -1;}
            if(!hC) hoverC = -1;
            if(!hF) hoverF = -1;
            if(!hT) hoverT = -1;
        }
    }
}
