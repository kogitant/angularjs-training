package org.eluder.score.tables.service.utils;

import java.util.List;
import java.util.regex.Pattern;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

public class KeywordTokenizer implements Function<String, List<String>> {
    
    private static final Pattern KEYWORD_SPLIT_PATTERN = Pattern.compile("\\s+");
    
    @Override
    public List<String> apply(final String input) {
        if (Strings.isNullOrEmpty(input)) {
            return ImmutableList.of();
        } else {
            String lowercased = new LowerCaser().apply(input);
            return ImmutableList.copyOf(Splitter.on(KEYWORD_SPLIT_PATTERN).omitEmptyStrings().split(lowercased));
        }
    }

}
