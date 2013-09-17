package org.eluder.score.tables.api;

import org.springframework.data.mongodb.core.index.Indexed;

public abstract class SlugDocument extends MutableDocument implements NamedDocument {

    @Indexed(unique = true)
    private String slug;
    
    @Override
    public String getSlug() {
        return slug;
    }
    
    public SlugDocument setSlug(final String slug) {
        this.slug = slug;
        return this;
    }
    
}
