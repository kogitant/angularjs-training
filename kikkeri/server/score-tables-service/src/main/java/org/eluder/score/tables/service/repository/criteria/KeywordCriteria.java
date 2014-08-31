package org.eluder.score.tables.service.repository.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.eluder.score.tables.service.utils.KeywordTokenizer;
import org.springframework.data.mongodb.core.query.Criteria;

public class KeywordCriteria extends Criteria {

    public KeywordCriteria(final String key, final String term) {
        super(key);
        addParts(new KeywordTokenizer().apply(term).iterator());
    }
    
    private void addParts(final Iterator<String> parts) {
        regex(pattern(parts.next()));
        List<Criteria> criterias = new ArrayList<Criteria>();
        while (parts.hasNext()) {
            String part = parts.next();
            criterias.add(Criteria.where(getKey()).regex(pattern(part)));
        }
        if (criterias.size() > 0) {
            andOperator(criterias.toArray(new Criteria[criterias.size()]));
        }
    }

    private Pattern pattern(final String term) {
        return Pattern.compile("^" + Pattern.quote(term));
    }
}
