
package Resources;

import java.awt.Color;

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
    private double xAccAvg, yAccAvg, zAccAvg;
    private double cVel, cAcc, cVelAvg, cAccAvg;
    private int color = 0;
    private boolean enabled = true;
    
    private final Color[] colorWheel ={new Color(237,28,36), new Color(255,128,0),new Color(240,240,0),
                                       new Color(0,255,0), new Color(0,255,255),new Color(0,0,255),
                                       new Color(128,0,255), new Color(255,0,255)};
    
    private final Color[] colorWheelDim ={new Color(95,7,12), new Color(136,68,0),new Color(155,155,0),
                                          new Color(0,100,0), new Color(0,138,138),new Color(0,0,138),
                                          new Color(69,0,138), new Color(128,0,128)};

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
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public double getxVel() {
        return xVel;
    }

    public void setxVel(int xVel) {
        this.xVel = xVel;
    }

    public double getyVel() {
        return yVel;
    }

    public void setyVel(int yVel) {
        this.yVel = yVel;
    }

    public double getzVel() {
        return zVel;
    }

    public void setzVel(int zVel) {
        this.zVel = zVel;
    }

    public double getxAcc() {
        return xAcc;
    }

    public void setxAcc(int xAcc) {
        this.xAcc = xAcc;
    }

    public double getyAcc() {
        return yAcc;
    }

    public void setyAcc(int yAcc) {
        this.yAcc = yAcc;
    }

    public double getzAcc() {
        return zAcc;
    }

    public void setzAcc(int zAcc) {
        this.zAcc = zAcc;
    }

    public double getxVelAvg() {
        return xVelAvg;
    }

    public void setxVelAvg(int xVelAvg) {
        this.xVelAvg = xVelAvg;
    }

    public double getyVelAvg() {
        return yVelAvg;
    }

    public void setyVelAvg(int yVelAvg) {
        this.yVelAvg = yVelAvg;
    }

    public double getzVelAvg() {
        return zVelAvg;
    }

    public void setzVelAvg(int zVelAvg) {
        this.zVelAvg = zVelAvg;
    }

    public double getxAccAvg() {
        return xAccAvg;
    }

    public void setxAccAvg(int xAccAvg) {
        this.xAccAvg = xAccAvg;
    }

    public double getyAccAvg() {
        return yAccAvg;
    }

    public void setyAccAvg(int yAccAvg) {
        this.yAccAvg = yAccAvg;
    }

    public double getzAccAvg() {
        return zAccAvg;
    }

    public void setzAccAvg(int zAccAvg) {
        this.zAccAvg = zAccAvg;
    }

    public double getcVel() {
        return cVel;
    }

    public void setcVel(int cVel) {
        this.cVel = cVel;
    }

    public double getcAcc() {
        return cAcc;
    }

    public void setcAcc(int cAcc) {
        this.cAcc = cAcc;
    }

    public double getcVelAvg() {
        return cVelAvg;
    }

    public void setcVelAvg(int cVelAvg) {
        this.cVelAvg = cVelAvg;
    }

    public double getcAccAvg() {
        return cAccAvg;
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
}
