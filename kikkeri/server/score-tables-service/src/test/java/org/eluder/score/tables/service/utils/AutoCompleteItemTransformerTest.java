package org.eluder.score.tables.service.utils;

import org.eluder.score.tables.api.AutoCompleteItem;
import org.eluder.score.tables.api.NamedDocument;
import org.junit.Assert;
import org.junit.Test;

public class AutoCompleteItemTransformerTest {

    @Test
    public void testApplyOnFullMatch() {
        AutoCompleteItem item = apply(document("John Smith"), "smith john");
        Assert.assertEquals("[John Smith]", item.getHighlighted());
    }
    
    @Test
    public void testApplyOnPartialMatch() {
        AutoCompleteItem item = apply(document("John Smith"), "smi john");
        Assert.assertEquals("[John Smi]th", item.getHighlighted());
    }
    
    @Test
    public void testApplyOnNoMatch() {
        AutoCompleteItem item = apply(document("John Smith"), "arnold");
        Assert.assertEquals("John Smith", item.getHighlighted());
    }
    
    @Test
    public void testApplyOnMultipleWhitespaces() {
        AutoCompleteItem item = apply(document(" John   Smith"), "smi john");
        Assert.assertEquals(" [John   Smi]th", item.getHighlighted());
    }
    
    private AutoCompleteItem apply(final NamedDocument document, final String token) {
        return new AutoCompleteItemTransformer(token).apply(document);
    }
    
    private NamedDocument document(final String name) {
        return new NamedDocument() {
            @Override
            public String getId() {
                return "abcd";
            }
            @Override
            public String getName() {
                return name;
            }
            @Override
            public String getSlug() {
                return name;
            }
        };
    }
}
