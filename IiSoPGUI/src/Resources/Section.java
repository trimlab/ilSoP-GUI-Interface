
package Resources;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Joseph Ryan
 */
public class Section {
    private File file;
    public double x1, y1, z1;
    public double x2, y2, z2;
    
    public Section(File f){
        this.setFile(f);
    }
    
    public String getName(){
        return this.file.getName();
    }
    
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
        try{
            Scanner s = new Scanner(file);
            x1 = s.nextDouble();
            y1 = s.nextDouble();
            z1 = s.nextDouble();
            x2 = s.nextDouble();
            y2 = s.nextDouble();
            z2 = s.nextDouble();
            s.close();
        }catch(IOException e){}
    }
    
    public boolean inSection(double x, double y, double z){
        double xM=0, xm=0;
        double yM=0, ym=0;
        double zM=0, zm=0;
        
        if(x1 > x2){
            xM = x1;
            xm = x2;
        }else{
            xM = x2;
            xm = x1;
        }
        
        if(y1 > y2){
            yM = y1;
            ym = y2;
        }else{
            yM = y2;
            ym = y1;
        }
        
        if(z1 > z2){
            zM = z1;
            zm = z2;
        }else{
            zM = z2;
            zm = z1;
        }
        
        if(x < xm || x > xM)
            return false;
        if(y < ym || y > yM)
            return false;
        if(z < zm || z > zM)
            return false;
        
        return true;
    }
}
