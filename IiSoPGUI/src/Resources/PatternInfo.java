
package Resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Joseph Ryan
 */
public class PatternInfo {
    
    private String name;
    private Scale scaleAdj = new Scale();
    private File file;
    private boolean[][] pattern = new boolean[20][20];
    private int bpm, nLength, instrument, octave;

    public PatternInfo(File f){
        file = f;
        this.name = file.getName().substring(0, file.getName().length()-4);
        try {
            Scanner s = new Scanner(f);
            bpm = s.nextInt();
            nLength = s.nextInt();
            instrument = s.nextInt();
            octave = s.nextInt();
            scaleAdj.changeScale(s.next());
            
            for(int c = 0; c < pattern[0].length; c++){
                for(int r = 0; r < pattern.length; r++){
                    pattern[r][c] = s.nextInt() == 1;
                }
            }
            s.close();
        } catch (FileNotFoundException ex) {}
    }
    
    public Scale getScale() {
        return scaleAdj;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public int getnLen() {
        return nLength;
    }

    public void setnLen(int nLen) {
        this.nLength = nLen;
    }

    public int getInstrument() {
        return instrument;
    }

    public void setInstrument(int instrument) {
        this.instrument = instrument;
    }

    public int getOctave() {
        return octave;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File f) {
        file = f;
        this.name = file.getName();
        try {
            Scanner s = new Scanner(f);
            bpm = s.nextInt();
            nLength = s.nextInt();
            instrument = s.nextInt();
            octave = s.nextInt();
            scaleAdj.changeScale(s.next());
            
            for(int c = 0; c < pattern[0].length; c++){
                for(int r = 0; r < pattern.length; r++){
                    pattern[r][c] = s.nextInt() == 1;
                }
            }
            s.close();
        } catch (FileNotFoundException ex) {}
    }

    public boolean[][] getPattern() {
        return pattern;
    }

    public void setPattern(boolean[][] pattern) {
        this.pattern = pattern;
    }
    
    public String toString(){
        String note = "T"+bpm+" I"+instrument+" ";
        String nlen = "";
            
            if(nLength == 1) nlen = "t";
            if(nLength == 2) nlen = "s";
            if(nLength == 4) nlen = "i";
            if(nLength == 8) nlen = "q";
            if(nLength == 16) nlen = "h";
            if(nLength == 32) nlen = "w";
            
            for(int r = 0; r < pattern.length; r++){
                for(int c = 0; c < pattern[0].length; c++){
                    if(pattern[r][c]){
                        note+=""+scaleAdj.getSelectedScale()[4-(c%5)]+((20-(c+1)+scaleAdj.backTrackAmount())/5+octave+1)+nlen+"+";
                    }
                }
                note = note.substring(0, note.length()-1);
                note += " ";
            }
            
            note = note.substring(0, note.length()-1); 
        
        return note;
    }
    
}
