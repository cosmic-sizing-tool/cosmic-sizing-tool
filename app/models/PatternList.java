package models;

import java.util.*;

public class PatternList {
    private Map<Long, Pattern> patternList = new HashMap<>();

    public Map<Long, Pattern> getPatternList() {
        return patternList;
    }

    public void setPatternList(Map<Long, Pattern> patternList) {
        this.patternList = patternList;
    }
    
    // ID form database are the key for HashMap Pattern
    public Pattern getPatternById(Long id) {
        return patternList.get(id);
    }
}
