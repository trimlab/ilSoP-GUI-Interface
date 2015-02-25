package Resources;

/**
 * This is the easy access scale class for the Pattern Build view, its
 * intended to be used to set the scales quickly
 * @author Joseph Ryan
 */
public class Scale {

    private final String[] C_MAJOR_PENATONIC_SCALE = {"C","D","E","G","A"};
    private final String[] D_MAJOR_PENATONIC_SCALE = {"D","E","F#","A","B"};
    private final String[] E_MAJOR_PENATONIC_SCALE = {"E","F#","G#","B","C#"};
    private final String[] F_MAJOR_PENATONIC_SCALE = {"F","G","A","C","D"};
    private final String[] G_MAJOR_PENATONIC_SCALE = {"G","A","B","D","E"};
    private final String[] A_MAJOR_PENATONIC_SCALE = {"A","B","C#","E","F#"};
    private final String[] B_MAJOR_PENATONIC_SCALE = {"B","C#","D#","F#","G#"};
    
    private String[] selectedScale = C_MAJOR_PENATONIC_SCALE;
    
    /**
     * Default Constructor (Not in use)
     */
    public Scale(){}
    
    /**
     * Create the scale class with a selected scale
     * @param scale 
     *          The Scale to set the scale to
     */
    public Scale(Scales scale){
        this.changeScale(scale);
    }
    
    /**
     * Changes the selected scale to a
     * @param scale 
     *          The scale to change the scale to
     */
    public void changeScale(Scales scale){
        if(scale == Scales.C_MAJOR_PENATONIC) selectedScale = C_MAJOR_PENATONIC_SCALE;
        if(scale == Scales.D_MAJOR_PENATONIC) selectedScale = D_MAJOR_PENATONIC_SCALE;
        if(scale == Scales.E_MAJOR_PENATONIC) selectedScale = E_MAJOR_PENATONIC_SCALE;
        if(scale == Scales.F_MAJOR_PENATONIC) selectedScale = F_MAJOR_PENATONIC_SCALE;
        if(scale == Scales.G_MAJOR_PENATONIC) selectedScale = G_MAJOR_PENATONIC_SCALE;
        if(scale == Scales.A_MAJOR_PENATONIC) selectedScale = A_MAJOR_PENATONIC_SCALE;
        if(scale == Scales.B_MAJOR_PENATONIC) selectedScale = B_MAJOR_PENATONIC_SCALE;
    }
    
    /**
     * Changes the selected scale to a
     * @param scale 
     *          The scale to change the scale to
     */
    public void changeScale(String scale){
        if(scale.equals("C_MAJOR_PENATONIC")) selectedScale = C_MAJOR_PENATONIC_SCALE;
        if(scale.equals("D_MAJOR_PENATONIC")) selectedScale = D_MAJOR_PENATONIC_SCALE;
        if(scale.equals("E_MAJOR_PENATONIC")) selectedScale = E_MAJOR_PENATONIC_SCALE;
        if(scale.equals("F_MAJOR_PENATONIC")) selectedScale = F_MAJOR_PENATONIC_SCALE;
        if(scale.equals("G_MAJOR_PENATONIC")) selectedScale = G_MAJOR_PENATONIC_SCALE;
        if(scale.equals("A_MAJOR_PENATONIC")) selectedScale = A_MAJOR_PENATONIC_SCALE;
        if(scale.equals("B_MAJOR_PENATONIC")) selectedScale = B_MAJOR_PENATONIC_SCALE;
    }
    
    /**
     * Returns the selected scale
     * @return 
     *          String array of the notes within the scale
     */
    public String[] getSelectedScale(){
        return this.selectedScale;
    }
    
    /**
     * The amount to adjust the scale when writing the notes
     * @return 
     */
    public int backTrackAmount(){
        if(selectedScale.equals(C_MAJOR_PENATONIC_SCALE)) return 0;
        if(selectedScale.equals(D_MAJOR_PENATONIC_SCALE)) return 0;
        if(selectedScale.equals(E_MAJOR_PENATONIC_SCALE)) return 1;
        if(selectedScale.equals(F_MAJOR_PENATONIC_SCALE)) return 2;
        if(selectedScale.equals(G_MAJOR_PENATONIC_SCALE)) return 2;
        if(selectedScale.equals(A_MAJOR_PENATONIC_SCALE)) return 3;
        if(selectedScale.equals(B_MAJOR_PENATONIC_SCALE)) return 4;
        else return 0;
    }
    
    /**
     * Converts the selected Scale into a string
     * @return 
     */
    public String toString(){
        if(selectedScale.equals(C_MAJOR_PENATONIC_SCALE)) return "C_MAJOR_PENATONIC";
        if(selectedScale.equals(D_MAJOR_PENATONIC_SCALE)) return "D_MAJOR_PENATONIC";
        if(selectedScale.equals(E_MAJOR_PENATONIC_SCALE)) return "E_MAJOR_PENATONIC";
        if(selectedScale.equals(F_MAJOR_PENATONIC_SCALE)) return "F_MAJOR_PENATONIC";
        if(selectedScale.equals(G_MAJOR_PENATONIC_SCALE)) return "G_MAJOR_PENATONIC";
        if(selectedScale.equals(A_MAJOR_PENATONIC_SCALE)) return "A_MAJOR_PENATONIC";
        if(selectedScale.equals(B_MAJOR_PENATONIC_SCALE)) return "B_MAJOR_PENATONIC";
        else return "";
    }
    
    /**
     * Returns a String list of all of the current scales available
     * @return 
     */
    public String[] scaleList(){
        String[] list = {"C_MAJOR_PENATONIC","D_MAJOR_PENATONIC","E_MAJOR_PENATONIC",
                         "F_MAJOR_PENATONIC","G_MAJOR_PENATONIC","A_MAJOR_PENATONIC",
                         "B_MAJOR_PENATONIC",};
        return list;
    }
}
