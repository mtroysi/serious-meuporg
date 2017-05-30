package com.example.controller;

import com.example.dto.ResultDto;
import com.example.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Morgane TROYSI on 30/05/17.
 */

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    /**
     * Retourne la liste des utilisateurs  et des tâches dont le nom contiennent la chaîne en paramètre.
     * @param query chaîne à chercher
     * @return liste des utilisateurs & tâches correspondants
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<ResultDto> searchUsersAndTasks(@RequestParam("query") String query) {
        return searchService.searchUsersAndTasks(query);
    }
}
