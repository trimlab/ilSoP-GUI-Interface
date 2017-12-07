package Resources.Items.LeapMotion;

import Resources.Items.LeapMotion.*;
import com.leapmotion.leap.*;

/**
 *
 * @author Joseph Ryan
 */
public class LeapMotionListener extends Listener{
    
    private LeapHand[] hands = new LeapHand[2];
    
//    public LeapMotionListener(){
//        super();
//    }
    
    
    public void onConnect(Controller controller) {
        System.out.println("Leap Motion has been Connected");
    }

    public void onFrame(Controller controller) {
        Frame frame = controller.frame();
        
        for(Hand hand : frame.hands()){
            String handType = hand.isLeft() ? "Left hand" : "Right hand";
            
            if(hand.isLeft()){
                if(hands[0]==null){
                    hands[0] = new LeapHand(handType, "Leap Motion", hand.id());
                }
                hands[0].updateValues(hand, 1.0*100/1000);
            }
            else{
                if(hands[1]==null){
                    hands[1] = new LeapHand(handType, "Leap Motion", hand.id());
                }
                hands[1].updateValues(hand, 1.0*10/1000);
            }
        }

//        outputData(frame);
    } 
    
    public LeapHand[] getHands(){
        return hands;
    }
    
    public void outputData(Frame frame){
        System.out.println("Frame id: " + frame.id()
                       + ", hands: " + frame.hands().count()
                       + ", fingers: " + frame.fingers().count());
        
        //Get hands
        for(Hand hand : frame.hands()) {
            String handType = hand.isLeft() ? "Left hand" : "Right hand";
            System.out.println("  " + handType + ", id: " + hand.id()
                             + ", palm position: " + hand.palmPosition());

            // Get the hand's normal vector and direction
            Vector normal = hand.palmNormal();
            Vector direction = hand.direction();

            // Calculate the hand's pitch, roll, and yaw angles
            System.out.println("  pitch: " + Math.toDegrees(direction.pitch()) + " degrees, "
                             + "roll: " + Math.toDegrees(normal.roll()) + " degrees, "
                             + "yaw: " + Math.toDegrees(direction.yaw()) + " degrees");

            // Get arm bone
            Arm arm = hand.arm();
            System.out.println("  Arm direction: " + arm.direction()
                             + ", wrist position: " + arm.wristPosition()
                             + ", elbow position: " + arm.elbowPosition());

            // Get fingers
            for (Finger finger : hand.fingers()) {
                System.out.println("    " + finger.type() + ", id: " + finger.id()
                                 + ", length: " + finger.length()
                                 + "mm, width: " + finger.width() + "mm");

                //Get Bones
                for(Bone.Type boneType : Bone.Type.values()) {
                    Bone bone = finger.bone(boneType);
                    System.out.println("      " + bone.type()
                                     + " bone, start: " + bone.prevJoint()
                                     + ", end: " + bone.nextJoint()
                                     + ", direction: " + bone.direction());
                }
            }
        }
    }
}
