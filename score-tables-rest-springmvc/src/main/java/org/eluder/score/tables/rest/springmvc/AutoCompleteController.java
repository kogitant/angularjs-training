package org.eluder.score.tables.rest.springmvc;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.eluder.score.tables.api.AutoCompleteItem;
import org.eluder.score.tables.service.AutocompleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/autocomplete")
public class AutoCompleteController {

    @Autowired
    private AutocompleteService autocompleteService;
    
    @RequestMapping(value = "/players", method = GET)
    public @ResponseBody List<AutoCompleteItem> findPlayers(@RequestParam("token") final String token) {
        return autocompleteService.findPlayers(token);
    }
    
}
