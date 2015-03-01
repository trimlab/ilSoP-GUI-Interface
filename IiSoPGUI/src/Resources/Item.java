
package Resources;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * The item information and data sent by
 * vicon is sorted and stored here
 * @author Xazaviar
 */
public class Item {

    private String Name;
    private double x, y, z;
    private double xVel, yVel, zVel;
    private double xAcc, yAcc, zAcc;
    private double xVelAvg, yVelAvg, zVelAvg;
    private double[] xVelBuff, yVelBuff, zVelBuff;
    private double xAccAvg, yAccAvg, zAccAvg;
    private double[] xAccBuff, yAccBuff, zAccBuff;
    private double cVel, cAcc;
    private double cVelAvg, cAccAvg;
    private double[] cVelBuff, cAccBuff;
    private int color = 0;
    private boolean enabled = true;
    
    //Buffer variables for getting averages
    private final int bufferSize = 250;
    private int bufferIndex = 0;
    
    private final Color[] colorWheel ={new Color(237,28,36), new Color(255,128,0),new Color(240,240,0),
                                       new Color(0,255,0), new Color(0,255,255),new Color(0,0,255),
                                       new Color(128,0,255), new Color(255,0,255)};
    
    private final Color[] colorWheelDim ={new Color(95,7,12), new Color(136,68,0),new Color(155,155,0),
                                          new Color(0,100,0), new Color(0,138,138),new Color(0,0,138),
                                          new Color(69,0,138), new Color(128,0,128)};

    NumberFormat formatter = new DecimalFormat("#0.00");
    
    public Item(String name){
        this.Name = name;
        //Build buffers
        xVelBuff = new double[bufferSize];
        yVelBuff = new double[bufferSize];
        zVelBuff = new double[bufferSize];
        cVelBuff = new double[bufferSize];
        xAccBuff = new double[bufferSize];
        yAccBuff = new double[bufferSize];
        zAccBuff = new double[bufferSize];
        cAccBuff = new double[bufferSize];
        for(int b = 0; b < bufferSize; b++){
            xVelBuff[b] = 0.0;
            yVelBuff[b] = 0.0;
            zVelBuff[b] = 0.0;
            cVelBuff[b] = 0.0;
            xAccBuff[b] = 0.0;
            yAccBuff[b] = 0.0;
            zAccBuff[b] = 0.0;
            cAccBuff[b] = 0.0;
        }
    }
    
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public Color[] getColorWheel() {
        return colorWheel;
    }   
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public double getX() {
        return Double.parseDouble(formatter.format(x));
    }
    public void setX(int x) {
        this.x = x;
    }
    public double getY() {
        return Double.parseDouble(formatter.format(y));
    }
    public void setY(int y) {
        this.y = y;
    }
    public double getZ() {
        return Double.parseDouble(formatter.format(z));
    }
    public void setZ(int z) {
        this.z = z;
    }
    public double getxVel() {
        return Double.parseDouble(formatter.format(xVel));
    }
    public void setxVel(int xVel) {
        this.xVel = xVel;
    }
    public double getyVel() {
        return Double.parseDouble(formatter.format(yVel));
    }
    public void setyVel(int yVel) {
        this.yVel = yVel;
    }
    public double getzVel() {
        return Double.parseDouble(formatter.format(zVel));
    }
    public void setzVel(int zVel) {
        this.zVel = zVel;
    }
    public double getxAcc() {
        return Double.parseDouble(formatter.format(xAcc));
    }
    public void setxAcc(int xAcc) {
        this.xAcc = xAcc;
    }
    public double getyAcc() {
        return Double.parseDouble(formatter.format(yAcc));
    }
    public void setyAcc(int yAcc) {
        this.yAcc = yAcc;
    }
    public double getzAcc() {
        return Double.parseDouble(formatter.format(zAcc));
    }
    public void setzAcc(int zAcc) {
        this.zAcc = zAcc;
    }
    public double getxVelAvg() {
        return Double.parseDouble(formatter.format(xVelAvg));
    }
    public void setxVelAvg(int xVelAvg) {
        this.xVelAvg = xVelAvg;
    }
    public double getyVelAvg() {
        return Double.parseDouble(formatter.format(yVelAvg));
    }
    public void setyVelAvg(int yVelAvg) {
        this.yVelAvg = yVelAvg;
    }
    public double getzVelAvg() {
        return Double.parseDouble(formatter.format(zVelAvg));
    }
    public void setzVelAvg(int zVelAvg) {
        this.zVelAvg = zVelAvg;
    }
    public double getxAccAvg() {
        return Double.parseDouble(formatter.format(xAccAvg));
    }
    public void setxAccAvg(int xAccAvg) {
        this.xAccAvg = xAccAvg;
    }
    public double getyAccAvg() {
        return Double.parseDouble(formatter.format(yAccAvg));
    }
    public void setyAccAvg(int yAccAvg) {
        this.yAccAvg = yAccAvg;
    }
    public double getzAccAvg() {
        return Double.parseDouble(formatter.format(zAccAvg));
    }
    public void setzAccAvg(int zAccAvg) {
        this.zAccAvg = zAccAvg;
    }
    public double getcVel() {
        return Double.parseDouble(formatter.format(cVel));
    }
    public void setcVel(int cVel) {
        this.cVel = cVel;
    }
    public double getcAcc() {
        return Double.parseDouble(formatter.format(cAcc));
    }
    public void setcAcc(int cAcc) {
        this.cAcc = cAcc;
    }
    public double getcVelAvg() {
        return Double.parseDouble(formatter.format(cVelAvg));
    }
    public void setcVelAvg(int cVelAvg) {
        this.cVelAvg = cVelAvg;
    }
    public double getcAccAvg() {
        return Double.parseDouble(formatter.format(cAccAvg));
    }
    public void setcAccAvg(int cAccAvg) {
        this.cAccAvg = cAccAvg;
    }    
    public Color getColor() {
        if(enabled){
            return this.colorWheel[color];
        }else{
            return Color.DARK_GRAY;
        }
    }   
    public Color getColorDimmed() {
        if(enabled){
            return this.colorWheelDim[color];
        }else{
            return Color.DARK_GRAY;
        }
    }
    public void setColor(int i) {
        if(enabled)
            this.color = i;
    } 
    public void updateValues(double x, double y, double z, double time){
        int previousIndex = bufferIndex -1;
        if(bufferIndex == 0)
            previousIndex = bufferSize - 1;
        
        //X stats
        double oldx = this.x;
        this.x = x;
        this.xVel = Math.abs((this.x-oldx)/time);
        this.xVelBuff[bufferIndex] = xVel;
        this.xVelAvg = this.xVelAvg - this.xVelBuff[previousIndex]/bufferSize + this.xVelBuff[bufferIndex]/bufferSize;
        this.xAcc = this.xVel/time;
        this.xAccBuff[bufferIndex] = xAcc;
        this.xAccAvg = this.xAccAvg - this.xAccBuff[previousIndex]/bufferSize + this.xAccBuff[bufferIndex]/bufferSize;
        
        //Y stats
        double oldy = this.y;
        this.y = y;
        this.yVel = Math.abs((this.y-oldy)/time);
        this.yVelBuff[bufferIndex] = yVel;
        this.yVelAvg = this.yVelAvg - this.yVelBuff[previousIndex]/bufferSize + this.yVelBuff[bufferIndex]/bufferSize;
        this.yAcc = this.yVel/time;
        this.yAccBuff[bufferIndex] = yAcc;
        this.yAccAvg = this.yAccAvg - this.yAccBuff[previousIndex]/bufferSize + this.yAccBuff[bufferIndex]/bufferSize;
        
        
        //Z stats
        double oldz = this.z;
        this.z = z;
        this.zVel = Math.abs((this.z-oldz)/time);
        this.zVelBuff[bufferIndex] = zVel;
        this.zVelAvg = this.zVelAvg - this.zVelBuff[previousIndex]/bufferSize + this.zVelBuff[bufferIndex]/bufferSize;
        this.zAcc = this.zVel/time;
        this.zAccBuff[bufferIndex] = zAcc;
        this.zAccAvg = this.zAccAvg - this.zAccBuff[previousIndex]/bufferSize + this.zAccBuff[bufferIndex]/bufferSize;
        
        
        //Cumulative stats
        this.cVel = (this.xVel+this.yVel+this.zVel)/3.0;
        this.cVelBuff[bufferIndex] = cVel;
        this.cVelAvg = this.cVelAvg - this.cVelBuff[previousIndex]/bufferSize + this.cVelBuff[bufferIndex]/bufferSize;
        this.cAcc = (this.xAcc+this.yAcc+this.zAcc)/3.0;
        this.cAccBuff[bufferIndex] = cAcc;
        this.cAccAvg = this.cAccAvg - this.cAccBuff[previousIndex]/bufferSize + this.cAccBuff[bufferIndex]/bufferSize;
        
        //Increment buffer index
        bufferIndex++;
        if(bufferIndex==bufferSize) bufferIndex = 0;
    }
}
