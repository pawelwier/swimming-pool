package pl.akademiakodu.swimmingpool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static pl.akademiakodu.swimmingpool.service.SearchService.searchByName;

@Controller
public class SearchController {

    @GetMapping("/search")
    public String showMain() {

        return "search";
    }

    @GetMapping("/results")
    public String showMain(@RequestParam(required = false) String q,
                            ModelMap modelMap) {

        if (q != null && !q.equals("")) {modelMap.put("results", searchByName(q));
        return "results";}
        else {modelMap.put("noanswer","");
        return "search";}
    }

}
