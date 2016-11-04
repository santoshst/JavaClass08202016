package stats.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import stats.domain.Season;
import stats.services.SeasonService;

@Controller
public class SeasonController {

	private SeasonService seasonService;
	
	@Autowired
    public void setSeasonService(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @RequestMapping(value = "/seasons", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("seasons", seasonService.listAllSeasons());
        System.out.println("Returning seasons:");
        return "seasons";
    }

    @RequestMapping("season/{season_number}")
    public String showSeason(@PathVariable Integer season_number, Model model){
        model.addAttribute("season", seasonService.getSeasonByNumber(season_number));
        return "seasonshow";
    }

    @RequestMapping("season/edit/{season_number}")
    public String edit(@PathVariable Integer season_number, Model model){
        model.addAttribute("season", seasonService.getSeasonByNumber(season_number));
        return "seasonform";
    }

    @RequestMapping("season/new")
    public String newSeason(Model model){
        model.addAttribute("season", new Season());
        return "seasonform";
    }

    @RequestMapping(value = "season", method = RequestMethod.POST)
    public String saveSeason(Season season){

        seasonService.saveSeason(season);

        return "redirect:/season/" + season.getSeason_number();
    }

    @RequestMapping("season/delete/{season_number}")
    public String deleteProduct(@PathVariable Integer season_number, Model model){
    	seasonService.deleteSeasonByNumber(season_number);
    	model.addAttribute("seasons", seasonService.listAllSeasons());
    	return "redirect:/seasons";
    }

}

