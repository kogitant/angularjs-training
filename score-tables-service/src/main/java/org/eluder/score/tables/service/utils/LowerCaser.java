package org.eluder.score.tables.service.utils;

import java.util.Locale;

import com.google.common.base.Function;
import com.google.common.base.Strings;

public class LowerCaser implements Function<String, String> {
    
    private static final Locale LOCALE = Locale.US;
    
    @Override
    public String apply(final String input) {
        if (Strings.isNullOrEmpty(input)) {
            return "";
        } else {
            return input.toLowerCase(LOCALE);
        }
    }

}
