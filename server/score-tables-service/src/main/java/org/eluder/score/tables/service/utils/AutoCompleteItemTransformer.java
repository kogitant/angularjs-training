package org.eluder.score.tables.service.utils;

import static java.util.regex.Pattern.quote;
import static org.eluder.score.tables.api.AutoCompleteItem.HIGHLIGHT_PREFIX;
import static org.eluder.score.tables.api.AutoCompleteItem.HIGHLIGHT_SUFFIX;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eluder.score.tables.api.AutoCompleteItem;
import org.eluder.score.tables.api.NamedDocument;

import com.google.common.base.Function;

public class AutoCompleteItemTransformer implements Function<NamedDocument, AutoCompleteItem> {
    
    private static final Pattern EMPTY_HIGHLIGHT_PATTERN = Pattern.compile(quote(HIGHLIGHT_SUFFIX) + "(\\s+)" + quote(HIGHLIGHT_PREFIX));
    
    private final Pattern tokenPattern;
    
    public AutoCompleteItemTransformer(final String token) {
        StringBuilder pattern = new StringBuilder();
        for (String tokenKeyword : new KeywordTokenizer().apply(token)) {
            if (pattern.length() > 0) {
                pattern.append("|");
            }
            pattern.append(Pattern.quote(tokenKeyword));
        }
        this.tokenPattern = Pattern.compile("(^|\\s)(" + pattern + ")", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public AutoCompleteItem apply(final NamedDocument input) {
        AutoCompleteItem item = new AutoCompleteItem();
        item.setId(input.getId());
        item.setValue(input.getName());
        item.setHighlighted(getHighlighted(input.getName()));
        return item;
    }
    
    private String getHighlighted(final String value) {
        StringBuffer highlighted = new StringBuffer();
        Matcher tokenMatcher = tokenPattern.matcher(value);
        while (tokenMatcher.find()) {
            tokenMatcher.appendReplacement(highlighted, tokenMatcher.group(1) + "[" + tokenMatcher.group(2) + "]");
        }
        tokenMatcher.appendTail(highlighted);
        return cleanHighlighted(highlighted.toString());
    }
    
    private String cleanHighlighted(final String highlighted) {
        StringBuffer cleaned = new StringBuffer();
        Matcher matcher = EMPTY_HIGHLIGHT_PATTERN.matcher(highlighted);
        while (matcher.find()) {
            matcher.appendReplacement(cleaned, matcher.group(1));
        }
        matcher.appendTail(cleaned);
        return cleaned.toString();
    }
}
