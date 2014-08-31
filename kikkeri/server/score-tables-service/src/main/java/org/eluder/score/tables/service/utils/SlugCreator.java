package org.eluder.score.tables.service.utils;

import java.text.Normalizer;
import java.text.Normalizer.Form;

import com.google.common.base.Function;
import com.google.common.base.Strings;

public class SlugCreator implements Function<String, String> {

    @Override
    public String apply(final String input) {
        if (Strings.isNullOrEmpty(input)) {
            return input;
        }
        String slug  = Normalizer.normalize(new LowerCaser().apply(input), Form.NFD);
        slug = slug.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        slug = slug.replaceAll("[^\\p{L}\\p{N}\\s\\-]", "");
        slug = slug.replaceAll("[\\s\\-]+", "-");
        return slug;
    }

}
