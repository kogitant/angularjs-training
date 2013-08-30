package org.eluder.score.tables.service;

import java.util.List;

import org.eluder.score.tables.api.Player;
import org.eluder.score.tables.api.query.BasicQuery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerServiceIT extends BaseIntegrationTest {

    @Autowired
    private PlayerService playerService;
    
    @Test
    public void index() {
        playerService.save(player("Test Player"));
        playerService.save(player("John Smith"));
        playerService.save(player("Joel Erwing Smitherton"));
        playerService.save(player("Mr Oyzo"));
    }
    
    @Test
    public void save() {
        playerService.save(player("Test Player"));
        
        List<Player> players = mongoOperations.findAll(Player.class);
        Assert.assertEquals(1, players.size());
    }
    
    @Test
    public void findByNameParts() {
        playerService.save(player("Test Player"));
        playerService.save(player("Oyzo"));
        playerService.save(player("Playah"));
        
        List<Player> players = null;
        
        players = playerService.findByNameKeywords(new BasicQuery("Test player"));
        Assert.assertEquals(1, players.size());
        
        players = playerService.findByNameKeywords(new BasicQuery("pla"));
        Assert.assertEquals(2, players.size());
    }
    
    private Player player(final String name) {
        Player player = new Player();
        player.setName(name);
        return player;
    }
}
