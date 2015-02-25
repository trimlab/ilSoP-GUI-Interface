
package Resources;

import java.awt.Color;

/**
 * The item information and data sent by
 * vicon is sorted and stored here
 * @author Xazaviar
 */
public class Item {

    private String Name;
    private int x, y, z;
    private int xVel, yVel, zVel;
    private int xAcc, yAcc, zAcc;
    private int xVelAvg, yVelAvg, zVelAvg;
    private int xAccAvg, yAccAvg, zAccAvg;
    private int cVel, cAcc, cVelAvg, cAccAvg;
    private Color c;
    private boolean enabled = true;
    
    private final Color[] colorWheel ={Color.RED, Color.BLUE, Color.CYAN, 
                                 Color.GREEN, Color.MAGENTA, Color.ORANGE, 
                                 Color.PINK, Color.YELLOW, Color.WHITE};

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getxVel() {
        return xVel;
    }

    public void setxVel(int xVel) {
        this.xVel = xVel;
    }

    public int getyVel() {
        return yVel;
    }

    public void setyVel(int yVel) {
        this.yVel = yVel;
    }

    public int getzVel() {
        return zVel;
    }

    public void setzVel(int zVel) {
        this.zVel = zVel;
    }

    public int getxAcc() {
        return xAcc;
    }

    public void setxAcc(int xAcc) {
        this.xAcc = xAcc;
    }

    public int getyAcc() {
        return yAcc;
    }

    public void setyAcc(int yAcc) {
        this.yAcc = yAcc;
    }

    public int getzAcc() {
        return zAcc;
    }

    public void setzAcc(int zAcc) {
        this.zAcc = zAcc;
    }

    public int getxVelAvg() {
        return xVelAvg;
    }

    public void setxVelAvg(int xVelAvg) {
        this.xVelAvg = xVelAvg;
    }

    public int getyVelAvg() {
        return yVelAvg;
    }

    public void setyVelAvg(int yVelAvg) {
        this.yVelAvg = yVelAvg;
    }

    public int getzVelAvg() {
        return zVelAvg;
    }

    public void setzVelAvg(int zVelAvg) {
        this.zVelAvg = zVelAvg;
    }

    public int getxAccAvg() {
        return xAccAvg;
    }

    public void setxAccAvg(int xAccAvg) {
        this.xAccAvg = xAccAvg;
    }

    public int getyAccAvg() {
        return yAccAvg;
    }

    public void setyAccAvg(int yAccAvg) {
        this.yAccAvg = yAccAvg;
    }

    public int getzAccAvg() {
        return zAccAvg;
    }

    public void setzAccAvg(int zAccAvg) {
        this.zAccAvg = zAccAvg;
    }

    public int getcVel() {
        return cVel;
    }

    public void setcVel(int cVel) {
        this.cVel = cVel;
    }

    public int getcAcc() {
        return cAcc;
    }

    public void setcAcc(int cAcc) {
        this.cAcc = cAcc;
    }

    public int getcVelAvg() {
        return cVelAvg;
    }

    public void setcVelAvg(int cVelAvg) {
        this.cVelAvg = cVelAvg;
    }

    public int getcAccAvg() {
        return cAccAvg;
    }

    public void setcAccAvg(int cAccAvg) {
        this.cAccAvg = cAccAvg;
    }
    
    public Color getColor() {
        return c;
    }

    public void setColor(Color c) {
        if(enabled)
        this.c = c;
    }
}
