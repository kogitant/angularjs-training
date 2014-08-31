package org.eluder.score.tables.service.comparator;

import static java.util.regex.Pattern.quote;
import static org.eluder.score.tables.api.AutoCompleteItem.HIGHLIGHT_PREFIX;
import static org.eluder.score.tables.api.AutoCompleteItem.HIGHLIGHT_SUFFIX;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eluder.score.tables.api.AutoCompleteItem;
import org.springframework.stereotype.Component;

@Component
public class AutoCompleteItemComparator implements Comparator<AutoCompleteItem> {

    private static final Pattern HIGHLIGHT_PATTERN = Pattern.compile(
            quote(HIGHLIGHT_PREFIX) + "([^" + quote(HIGHLIGHT_SUFFIX) + "]+)" + quote(HIGHLIGHT_SUFFIX));
    
    @Override
    public int compare(final AutoCompleteItem lhs, final AutoCompleteItem rhs) {
        double lhsRelevance = getMatchingPercentage(lhs);
        double rhsRelevance = getMatchingPercentage(rhs);
        if (lhsRelevance > rhsRelevance) {
            return -1;
        }
        if (lhsRelevance < rhsRelevance) {
            return 1;
        }
        return 0;
    }
    
    private double getMatchingPercentage(final AutoCompleteItem item) {
        int valueLength = item.getValue().length();
        int matchLength = getMatchLength(item);
        return ((double) matchLength / (double) valueLength);
    }
    
    private int getMatchLength(final AutoCompleteItem item) {
        int matchLength = 0;
        Matcher matcher = HIGHLIGHT_PATTERN.matcher(item.getHighlighted());
        while (matcher.find()) {
            matchLength += matcher.group(1).length();
        }
        return matchLength;
    }
}
