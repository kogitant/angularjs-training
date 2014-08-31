package org.eluder.score.tables.service.events;

import org.eluder.score.tables.api.Player;
import org.eluder.score.tables.api.PlayerSearch;
import org.eluder.score.tables.service.utils.KeywordTokenizer;
import org.eluder.score.tables.service.utils.LowerCaser;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;

@Component
public class PlayerSearchUpdateEventListener extends AbstractMongoEventListener<Player> {
    
    @Override
    public void onBeforeConvert(final Player source) {
        PlayerSearch search = new PlayerSearch();
        search.setName(new LowerCaser().apply(source.getName()));
        search.getNameKeywords().addAll(new KeywordTokenizer().apply(source.getName()));
        source.setSearch(search);
    }
}
