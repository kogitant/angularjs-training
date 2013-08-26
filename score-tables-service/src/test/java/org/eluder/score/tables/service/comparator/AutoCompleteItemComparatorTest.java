package org.eluder.score.tables.service.comparator;

import static org.junit.Assert.assertTrue;

import org.eluder.score.tables.api.AutoCompleteItem;
import org.junit.Test;

public class AutoCompleteItemComparatorTest {
    
    @Test
    public void testCompareFullMatch() {
        assertLhs(item("foo", "[foo]"), item("food", "[foo]d"));
    }
    
    @Test
    public void testCompareSame() {
        assertEqual(item("foo", "[foo]"), item("foo", "[foo]"));
    }
    
    @Test
    public void testComparePartialMatch() {
        assertRhs(item("parasite padawan", "[par]asite [par]mesan"), item("partial partco", "[par]tial [par]tco"));
    }
    
    @Test
    public void testComparePartialMixedMatch() {
        assertLhs(item("John Smith", "[Jo]hn [Smi]th"), item("Joel Erwing Smitherson", "[Jo]el Erwing [Smi]therson"));
    }
    
    private AutoCompleteItem item(final String value, final String highlighted) {
        return new AutoCompleteItem().setValue(value).setHighlighted(highlighted);
    }
    
    private static void assertLhs(final AutoCompleteItem lhs, final AutoCompleteItem rhs) {
        assertTrue(new AutoCompleteItemComparator().compare(lhs, rhs) < 0);
    }
    
    private static void assertRhs(final AutoCompleteItem lhs, final AutoCompleteItem rhs) {
        assertTrue(new AutoCompleteItemComparator().compare(lhs, rhs) > 0);
    }
    
    private static void assertEqual(final AutoCompleteItem lhs, final AutoCompleteItem rhs) {
        assertTrue(new AutoCompleteItemComparator().compare(lhs, rhs) == 0);
    }
}
