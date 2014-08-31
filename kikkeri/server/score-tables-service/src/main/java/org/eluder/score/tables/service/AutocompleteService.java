package org.eluder.score.tables.service;

import java.util.Collections;
import java.util.List;

import org.eluder.score.tables.api.AutoCompleteItem;
import org.eluder.score.tables.api.NamedDocument;
import org.eluder.score.tables.api.Player;
import org.eluder.score.tables.api.query.BasicQuery;
import org.eluder.score.tables.service.comparator.AutoCompleteItemComparator;
import org.eluder.score.tables.service.repository.PlayerRepository;
import org.eluder.score.tables.service.utils.AutoCompleteItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Service
public class AutocompleteService {

    @Autowired
    private PlayerRepository playerRepository;
    
    @Autowired
    private AutoCompleteItemComparator autoCompleteComparator;
    
    public List<AutoCompleteItem> findPlayers(final BasicQuery query) {
        Iterable<Player> players = playerRepository.findBySearchNameKeywords(query);
        return getProcessedResults(players, query.getValue());
    }
    
    private List<AutoCompleteItem> getProcessedResults(final Iterable<? extends NamedDocument> results, final String token) {
        List<AutoCompleteItem> items = Lists.newArrayList(Iterables.transform(results, new AutoCompleteItemTransformer(token)));
        Collections.sort(items, autoCompleteComparator);
        return items;
    }
}
