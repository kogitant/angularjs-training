package org.eluder.score.tables.service;

import java.util.Collections;
import java.util.List;

import org.eluder.score.tables.api.AutoCompleteItem;
import org.eluder.score.tables.api.NamedDocument;
import org.eluder.score.tables.api.Player;
import org.eluder.score.tables.service.comparator.AutoCompleteItemComparator;
import org.eluder.score.tables.service.repository.PlayerRepository;
import org.eluder.score.tables.service.utils.AutoCompleteItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class AutocompleteService {

    @Autowired
    private PlayerRepository playerRepository;
    
    @Autowired
    private AutoCompleteItemComparator autoCompleteComparator;
    
    public List<AutoCompleteItem> findPlayers(final String token) {
        List<Player> players = playerRepository.findBySearchNameKeywords(token);
        return getProcessedResults(players, token);
    }
    
    private List<AutoCompleteItem> getProcessedResults(final List<? extends NamedDocument> results, final String token) {
        List<AutoCompleteItem> items = Lists.newArrayList(Lists.transform(results, new AutoCompleteItemTransformer(token)));
        Collections.sort(items, autoCompleteComparator);
        return items;
    }
}
