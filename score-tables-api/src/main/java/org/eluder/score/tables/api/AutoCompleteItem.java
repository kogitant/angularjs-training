package org.eluder.score.tables.api;

public class AutoCompleteItem implements Domain {

    public static final String HIGHLIGHT_PREFIX = "[";
    public static final String HIGHLIGHT_SUFFIX = "]";
    
    private String id;
    private String value;
    private String highlighted;
    
    public String getId() {
        return id;
    }
    
    public AutoCompleteItem setId(final String id) {
        this.id = id;
        return this;
    }
    
    public String getValue() {
        return value;
    }
    
    public AutoCompleteItem setValue(final String value) {
        this.value = value;
        return this;
    }
    
    public String getHighlighted() {
        return highlighted;
    }
    
    public AutoCompleteItem setHighlighted(final String highlighted) {
        this.highlighted = highlighted;
        return this;
    }
}
