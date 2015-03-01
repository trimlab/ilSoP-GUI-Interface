
package Resources;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Joseph Ryan
 */
public class Condition {
    
    private String conditionText;
    private Item object;
    private Section section;
    private boolean sectionCondition = false;
    private String paramType, operator;
    private double param2;
    
    
    public Condition(String condition, Item[] list, ArrayList<Section> sections){
        this.conditionText = condition;
        
        //Build the condition
        Scanner s = new Scanner(condition);
        String scan = s.next();
        for(int i = 0; i < list.length; i++){
            if(list[i].getName().equals(scan)){
                object = list[i];
                break;
            }
        }
        scan = s.next();
        if(scan.equals("IN")){
            sectionCondition = true;
            scan = "";
            while(s.hasNext())
                scan += s.next()+" ";
            scan = scan.substring(0, scan.length()-1);
            for(int i = 0; i < sections.size(); i++){
                if(sections.get(i).getName().equals(scan)){
                    section = sections.get(i);
                    break;
                }
            }
        }else{
            paramType = scan;
            operator = s.next();
            param2 = s.nextDouble();
        }
        s.close();
    }
    
    public String toString(){
        return this.conditionText;
    }
    
    /**
     * Checks the list of objects to see if the object this 
     * condition is trying to track is now in the list of 
     * tracked objects and if the section is now available
     * @param list 
     *          The list of currently track objects
     * @param sections
     *          The list of currently available sections
     */
    public void updateCondition(Item[] list, ArrayList<Section> sections){
        Scanner s = new Scanner(conditionText);
        String scan = s.next();
        for(int i = 0; i < list.length; i++){
            if(list[i].getName().equals(scan)){
                object = list[i];
            }
        }
        scan = s.next();
        if(scan.equals("IN")){
            scan = "";
            while(s.hasNext())
                scan += s.next()+" ";
            scan = scan.substring(0, scan.length()-1);
            for(int i = 0; i < sections.size(); i++){
                if(sections.get(i).getName().equals(scan)){
                    section = sections.get(i);
                    break;
                }
            }
        }
        s.close();
    }
    
    private double param1(){
        if(paramType.equals("x")){
            return object.getX();
        }else if(paramType.equals("y")){
            return object.getY();
        }else if(paramType.equals("z")){
            return object.getZ();
        }else if(paramType.equals("xVel")){
            return object.getxVel();
        }else if(paramType.equals("yVel")){
            return object.getyVel();
        }else if(paramType.equals("zVel")){
            return object.getzVel();
        }else if(paramType.equals("xAcc")){
            return object.getxAcc();
        }else if(paramType.equals("yAcc")){
            return object.getyAcc();
        }else if(paramType.equals("zAcc")){
            return object.getzAcc();
        }else if(paramType.equals("xVelAvg")){
            return object.getxVelAvg();
        }else if(paramType.equals("yVelAvg")){
            return object.getyVelAvg();
        }else if(paramType.equals("zVelAvg")){
            return object.getzVelAvg();
        }else if(paramType.equals("xAccAvg")){
            return object.getxAccAvg();
        }else if(paramType.equals("yAccAvg")){
            return object.getyAccAvg();
        }else if(paramType.equals("zAccAvg")){
            return object.getzAccAvg();
        }else if(paramType.equals("cVel")){
            return object.getcVel();
        }else if(paramType.equals("cAcc")){
            return object.getcAcc();
        }else if(paramType.equals("cVelAvg")){
            return object.getcVelAvg();
        }else if(paramType.equals("cAccAvg")){
            return object.getcAccAvg();
        }
        return 0;
    }
    
    public boolean testCondition(){
        if(object == null)
            return false;
        if(!object.isEnabled())
            return false;
        if(sectionCondition && section == null)
            return false;
        else if(sectionCondition){
            return section.inSection(object.getX(), object.getY(), object.getZ());
        }
        
        if(operator.equals(">")){
            return param1() > param2;
        }else if(operator.equals(">=")){
            return param1() >= param2;
        }else if(operator.equals("<")){
            return param1() < param2;
        }else if(operator.equals("<=")){
            return param1() <= param2;
        }else if(operator.equals("=")){
            return param1() == param2;
        }
        
        return false;
    }
    
    public Item getObject(){
        return this.object;
    }
}
