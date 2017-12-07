package Resources.Items.LeapMotion;

import Resources.Items.Item;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Vector;

/**
 *
 * @author Joseph Ryan
 */
public class LeapHand extends Item{
    
    //Leap Motion Data
    private double pitch, roll, yaw;
    private Vector palm;
    private final int id;
    private final Finger[] fingers = new Finger[5];
    
    
    public LeapHand(String name, String source, int id){
        super(name, source, "LEAPMOTION");
        this.id = id;
    }
    
    public double getPitch() {
        return Double.parseDouble(formatter.format(pitch));
    }
    public double getRoll() {
        return Double.parseDouble(formatter.format(roll));
    }
    public double getYaw() {
        return Double.parseDouble(formatter.format(yaw));
    }
    public Vector getPalm() {
        return palm;
    }
    public int getId() {
        return id;
    }
    public Finger[] getFingers() {
        return fingers;
    }
    
    public void updateValues(Hand hand, double time){
        for (int f = 0; f < hand.fingers().count(); f++) {
            this.fingers[f] = hand.finger(f);
        }
        
        this.pitch = Math.toDegrees(hand.direction().pitch());
        this.roll = Math.toDegrees(hand.direction().roll());
        this.yaw = Math.toDegrees(hand.direction().yaw());
        
        this.palm = hand.palmPosition();
        
        super.updateValues(hand.palmPosition().getX(), hand.palmPosition().getY(), hand.palmPosition().getZ(), time);
    }
}
