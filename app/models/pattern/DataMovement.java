package pattern;

import java.util.*;

import com.avaje.ebean.Model; 
import play.data.format.*; 
import play.data.validation.*; 

public class DataMovement {
    private int entry, exit, read, write;
    
    protected DataMovement(int entry, int exit, int read, int write) {
        this.entry = entry;
        this.exit = exit;
        this.read = read;
        this.write = write;
    }
    
    protected int getCFPSize() {
        return entry + exit + read + write;
    }
}
