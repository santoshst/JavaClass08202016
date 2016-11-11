package stats.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import stats.domain.Game;
import stats.domain.Season;
import stats.services.GameService;
import stats.services.SeasonService;

@Controller
public class GameController {

	@Autowired
	private GameService gameService;
	@Autowired
	private SeasonService seasonService;
	
//	public void setGameService(GameService gameService) {
//		this.gameService = gameService;
//	}
	
	public GameService getGameService() {
		return gameService;
	}
				  //value was "games"
	@RequestMapping(value = "season/{season_number}", method = RequestMethod.GET)
	public /*void/*/String list(@PathVariable Integer season_number, Model model) {		// Get a list of all games
		ArrayList<Game> seasonGames = gameService.listBySeason(season_number);
		Season season = seasonService.getSeasonByNumber(season_number);
		model.addAttribute("season", season);
		model.addAttribute("games", seasonGames);	// Add them to the page ?
		return "seasonshow";		// Reload the page?
	}			//"games"
	
	
	@RequestMapping("/season/{season_number}/game/{game_id}")
	public String showGame(@PathVariable Integer season_number, @PathVariable Integer game_id, Model model) {
		model.addAttribute("game", gameService.getGameById(game_id));
		return "gameshow";
	}
	
    @RequestMapping("season/{season_number}/game/{game_id}/edit")
    public String edit(@PathVariable Integer season_number, @PathVariable Integer game_id, Model model){
        model.addAttribute("game", gameService.getGameById(game_id));
        return "gameform";
    }

    @RequestMapping("season/{season_number}/game/new")
    public String newGame(@PathVariable Integer season_number, Model model){
    	Game newGame = new Game();
    	newGame.setSeason_number(season_number);
        model.addAttribute("game", newGame);	// Give a new (blank) game object to the form
        return "gameform";
    }

    @RequestMapping(value = "game", method = RequestMethod.POST)
    public String saveGame(Game game){
//    	Game temp = gameService.findGameBySeasonAndGame(game.getSeason_number(), game.getGame_number());
//    	    	System.out.println("");
//    	System.out.println("GameController... saving game_id = " + game.getGame_id());
//    	System.out.println("");
    	int gameId;
//    	if (temp == null) {
    		gameService.saveGame(game);
    		gameId = game.getGame_id();
//    	}
//    	else {
//    		temp.setSeason_number(game.getSeason_number());
//    		temp.setGame_number(game.getGame_number());
//    		gameService.saveGame(temp);
//    		gameId = temp.getGame_id();
//    	}
//    	System.out.println("");
//    	System.out.println("GameController... saving game_id = " + game.getGame_id());
//    	System.out.println("");
        return "redirect:/game/" + gameId;
    }
    
    @RequestMapping("season/{seaon_number}/game/delete/{game_id}")
    public String deleteGame(@PathVariable Integer game_id, Model model){
    	Game game = gameService.getGameById(game_id);
    	gameService.deleteGameById(game_id);
    	model.addAttribute("games", gameService.listBySeason(game.getSeason_number()));
    	return "redirect:/season/" + game.getSeason_number();// + game/games";
    }
    

}
