
package Main;

import MusicThreads.MusicThread;
import Resources.Conditional;
import Resources.Item;
import Resources.PatternInfo;
import Resources.Section;
import java.awt.Graphics;
import javax.swing.JPanel;
import Tabs.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * This is the main interface that the user
 * sees and uses
 * 
 * @author Xazaviar
 */
public class MainGUI {

    //Basic setup for layout
    private final int Width = 1280;
    private final int Height = 720;
    private JFrame frame;
    private myJPanel mainpanel;
    private Timer timer = new Timer();
    private final int tickRate = 25;
    
    //Other vars
    private int tab = 0;
    private Tab[] tabs = new Tab[6];
    private Item[] obj;
    private MusicThread[] threads;
    private ArrayList<Section> sections = new ArrayList<>();
    private ArrayList<PatternInfo> patterns = new ArrayList<>();
    private ArrayList<Conditional> conditionals = new ArrayList<>();
    private ReceiveThread dataIn;
    
    
    /**
     * The default constructor. Sets up the GUI
     */
    public MainGUI(){
        frame = new JFrame("IiSoP GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-Width/2, 
                          Toolkit.getDefaultToolkit().getScreenSize().height/2-Height/2);
        
        mainpanel = new myJPanel();
        mainpanel.setPreferredSize(new Dimension(Width, Height));
        mainpanel.setLayout(null);
        mainpanel.addMouseListener(new Mouse());
        mainpanel.setFocusable(true);
        mainpanel.requestFocus();
        
        tempMake(); ///Temporary
        
        //Set up server Connection
        dataIn = new ReceiveThread();
        new Thread(dataIn).start();
        
        String[] names = {"Melody1","Melody2","Melody3","Bass1","Bass2","Bass3"};
        threads = new MusicThread[names.length];
        for(int t = 0; t < threads.length; t++){
            threads[t] = new MusicThread(names[t]);
            new Thread(threads[t]).start();
        }
        
        tabs[0] = new QuickStart("Quick Start",new Color(136,0,21), mainpanel); 
        tabs[1] = new ObjectsView("Objects",new Color(112,146,190), mainpanel,obj); 
        tabs[2] = new RoomView("Room View",new Color(255,127,39), mainpanel); 
        tabs[3] = new PatternView("Pattern Builder",new Color(127,127,127), mainpanel);
        sections = ((RoomView) tabs[2]).getSections();
        patterns = ((PatternView) tabs[3]).getPatterns();
        tabs[4] = new ConditionalView("Conditions",new Color(133,146,64), mainpanel); 
        ((ConditionalView) tabs[4]).getInfo(obj,sections, patterns,threads);
        ((ConditionalView) tabs[4]).setupConditionals();
        tabs[5] = new Settings("Settings",new Color(163,73,164), mainpanel);
        
        frame.getContentPane().add(mainpanel);
        frame.pack();
        frame.setVisible(true);
        timer.schedule(new RemindTask(), tickRate);
        tabs[0].setupComponents();
    }  
    
    private void tempMake(){
        obj = new Item[1];
//        System.out.println(obj.length);
        String[] names = {"FootL","HandL","FootR","HandR"};
        for(int i = 0; i < obj.length; i++){
            obj[i] = new Item(names[i]);    
            obj[i].updateValues(0, 0, 0, 1.0*tickRate/1000);
        }
    }
    
//*********************************************************   
//  Other Classes
//*********************************************************  
    
    /*
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
            setBackground(new Color(0,0,0));
            
            if(dataIn.connected)
                g.setColor(new Color(255,255,255));
            else
                g.setColor(new Color(255,0,0));
            
            g.fillRect(mainpanel.getWidth()-25, 24, 4, 4);
            g.fillRect(mainpanel.getWidth()-20, 20, 4, 8);
            g.fillRect(mainpanel.getWidth()-15, 16, 4, 12);
            g.fillRect(mainpanel.getWidth()-10, 12, 4, 16);
            g.fillRect(mainpanel.getWidth()-05, 8, 4, 20);
            
            drawTabs(g);
        }
        
        /**
         * Draws the tabs colors, backgrounds, and names
         * @param g 
         *          The Graphics variable
         */
        private void drawTabs(Graphics g){
            for(int t = 0; t < tabs.length; t++){
                g.setColor(tabs[t].getColor());
                //Check to see if the tab is the one currently selected
                
                g.fillRect(t*tabs[t].getWidth(), 0, tabs[t].getWidth(), tabs[t].getHeight());
                if(t == tab){
                    g.fillRect(0, tabs[t].getHeight(), Width, Height-tabs[t].getHeight());
                    tabs[t].draw(g);
                }
                
                
                g.setFont(new Font("ARIAL", Font.PLAIN, 12));
                g.setColor(Color.white);
                g.drawString(tabs[t].getName(),t*100+10,20);
                
            }
        }
    }
    
    private class Mouse extends myJPanel implements MouseListener{
        
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
            mainpanel.requestFocus();
            
            //Checks for a change in tabs
            for(int c = 0; c < 6; c++){
                if(e.getY() < tabs[c].getHeight() && e.getX() > c*tabs[c].getWidth() && e.getX() < c*tabs[c].getWidth()+tabs[c].getWidth()){
                    tab = c;
                    mainpanel.removeAll();
                    for(int r = 0; r < mainpanel.getMouseListeners().length;){
                        mainpanel.removeMouseListener(mainpanel.getMouseListeners()[r]);
                    }
                    for(int r = 0; r < mainpanel.getMouseMotionListeners().length;){
                        mainpanel.removeMouseMotionListener(mainpanel.getMouseMotionListeners()[r]);
                    }
                    mainpanel.addMouseListener(this);
                    tabs[c].setupComponents();
                    mainpanel.repaint();
                    break;
                }
            }
        }
        
    }
    
    public class RemindTask extends TimerTask{
        public void run(){  
            
            sections = ((RoomView) tabs[2]).getSections();
            patterns = ((PatternView) tabs[3]).getPatterns();
            conditionals = ((ConditionalView) tabs[4]).getConditionals();
            ((ConditionalView) tabs[4]).getInfo(obj,sections, patterns,threads);
            
            //Update coordinates
            for(int i = 0; i < obj.length; i++){   
                obj[i].updateValues(0, 0, 0, 1.0*tickRate/1000);
            }
            
            runConditionals();
            
            mainpanel.repaint(); 
            timer.schedule(new RemindTask(), tickRate);
        }
        
        private void runConditionals(){
            for(int c = 0; c < conditionals.size(); c++){
                if(conditionals.get(c).isEnabled()){
                    boolean doActions = true;
                    for(int con = 0; con < conditionals.get(c).getConditions().size(); con++){
                        if(!conditionals.get(c).getConditions().get(con).testCondition()){
                            doActions = false;
                            break;
                        }
                    }
                    
                    if(doActions){
                        for(int a = 0; a < conditionals.get(c).getActions().size(); a++){
                            conditionals.get(c).getActions().get(a).performAction();
                        }
                    }
                }
            }
            
        }
     }
    
    public class ReceiveThread implements Runnable{

        private final int serverPort = 9875;
        private DatagramSocket serverSocket;
//        private String ipAddress = "";
        
        public boolean connected = false;
        
        public void run() {
            while(true){
                try {
                    serverSocket = new DatagramSocket(serverPort);
                    connected = true;
                    break;
                } catch (SocketException ex) {

                }
            }
            
            while(true){
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                try{
                    connected = false;
                    serverSocket.receive(receivePacket);
                    connected = true;
                }catch (IOException e) {
                    serverSocket.close();
                    while(!reconnect()){
                        connected = false;
                    }
                }
                this.decodeAndUpdate(new String(receivePacket.getData()));
                sleep(25);
            }
        }
        
        /**
        * Tells the thread to sleep for a set amount of time
        * @param dur 
        *          The duration the thread sleeps
        */
       public void sleep(int dur){
           try{
           Thread.sleep(dur);
           }catch(InterruptedException e){
               System.out.println("Room has been interrupted");
           }
       }
        
        public void decodeAndUpdate(String input){
            String in = input.replaceAll("[~%-.0-9]", "");
            Scanner s = new Scanner(in);
            int count=-1;
            while(s.hasNext()){
                s.next();
                count++;
            }
            
            //Update the objects based on the data received
            if(count>obj.length){
                s = new Scanner(in);
                obj = new Item[count];
                for(int i = 0; i < count; i++){
                    obj[i] = new Item(s.next());
                    obj[i].updateValues(0, 0, 0, 1.0*tickRate/1000);
                }
            }
            
            in = input.replaceAll("[~%A-Za-z]", " "); 
            s = new Scanner(in);
            
            //Update coordinates
            for(int i = 0; i < obj.length; i++){
                if(obj[i].isEnabled())
                    obj[i].updateValues(s.nextDouble(), s.nextDouble(), s.nextDouble(), 1.0*tickRate/1000);
            }
            s.close();
        }
        
        public boolean reconnect(){
            try {
                serverSocket = new DatagramSocket(serverPort);
                connected = true;
                return true;
            } catch (SocketException ex) {}
            return false;
        }
        
    }
}
