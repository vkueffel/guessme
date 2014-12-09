package net.kueffel.guessme.util;

import net.kueffel.guessme.model.Game;
import net.kueffel.guessme.web.GameState;

import org.springframework.core.convert.converter.Converter;

public class GameToGameStateConverter implements Converter<Game, GameState> {

	@Override
	public GameState convert(Game game) {
		GameState state=new GameState();
		if (game==null) {
			return state;
		}
		
		state.setId(game.getId());
		state.setFinished(game.isFinished());
		state.setWon(game.isWon());
		state.setAttempt(game.getAttempts()+1);
		return state;
	}

}
