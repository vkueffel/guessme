package net.kueffel.guessme.service;

import net.kueffel.guessme.model.Game;

public interface GameService {

	public abstract Game makeMove(String gameId, int move);

	public abstract Game startGame();

	public abstract int getMaxAttempts();

	public abstract int getMaxNumber();

	public abstract int getMinNumber();

	public abstract Game loadGameById(String id);

}
