package net.kueffel.guessme.web;

import net.kueffel.guessme.RuleException;
import net.kueffel.guessme.model.Game;
import net.kueffel.guessme.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

	private static final String MODEL_ATTRIBUTE_GAME = "game";
	private static final String MODEL_ATTRIBUTE_ERROR = "error";

	@Autowired
	private GameService gameService;
	
	@Autowired
	@Qualifier("converter")
	private ConversionService converter;

	
	
	@RequestMapping(value = { "/", "/home", "/index" })
	public String home() {
		return "home";
	}

	
	
	@RequestMapping(value = { "/start", "/play" }, method = RequestMethod.GET)
	public String start(Model model) {
		Game g = gameService.startGame();
		addRuleAttributes(model);
		model.addAttribute(MODEL_ATTRIBUTE_GAME,
				converter.convert(g, GameState.class));
		return "play";
	}

	
	
	@RequestMapping(value = { "/play" }, method = RequestMethod.POST)
	public String play(@RequestParam("id") String gameId,
			@RequestParam("move") String input, Model model) {

		Game game = null;

		try {
			int move = Integer.parseInt(input);
			game = gameService.makeMove(gameId, move);
		} catch (NumberFormatException e) {
			model.addAttribute(MODEL_ATTRIBUTE_ERROR, "Please enter a number.");
			game = gameService.loadGameById(gameId);
		} catch (RuleException e) {
			model.addAttribute(MODEL_ATTRIBUTE_ERROR, e.getMessage());
			model.addAttribute(MODEL_ATTRIBUTE_GAME, e.getGame());
			game = e.getGame();
		} catch (Exception e) {
			model.addAttribute(MODEL_ATTRIBUTE_ERROR, e.getMessage());
		}

		if (game == null) {
			return "redirect:/start";
		}

		String view = "play";
		if (game.isFinished()) {
			model.addAttribute("won", game.isWon());
			model.addAttribute("correctNumber", game.getNumberToGuess());
			model.addAttribute("attempts", game.getAttempts());
			view = "done";
		} else {
			model.addAttribute(MODEL_ATTRIBUTE_GAME,
					converter.convert(game, GameState.class));
			addRuleAttributes(model);
		}
		return view;
	}

	
	
	private void addRuleAttributes(Model model) {
		model.addAttribute("minNumber", gameService.getMinNumber());
		model.addAttribute("maxNumber", gameService.getMaxNumber());
		model.addAttribute("maxAttempts", gameService.getMaxAttempts());
	}
}
