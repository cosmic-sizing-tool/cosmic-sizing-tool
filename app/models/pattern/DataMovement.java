package pattern;

import java.util.*;

import com.avaje.ebean.Model; 
import play.data.format.*; 
import play.data.validation.*; 

public class DataMovement {
    private Long id;
    private int entry, exit, read, write;
    
    protected int getSizeCFP() {
        return entry + exit + read + write;
    }
}
