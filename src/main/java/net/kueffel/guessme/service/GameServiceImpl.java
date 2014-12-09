package net.kueffel.guessme.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import net.kueffel.guessme.RuleException;
import net.kueffel.guessme.model.Game;
import net.kueffel.guessme.persistence.GameDao;

public class GameServiceImpl implements GameService {

	@Autowired
	private GameDao gameDao;

	private int minNumber = 1;
	private int maxNumber = 20;
	private int maxAttempts = 5;

	public void setMinNumber(int minNumber) {
		this.minNumber = minNumber;
	}
	
	
	@Override
	public int getMinNumber() {
		return minNumber;
	}

	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}


	@Override
	public int getMaxNumber() {
		return maxNumber;
	}

	public void setMaxAttempts(int maxAttempts) {
		this.maxAttempts = maxAttempts;
	}
	
	@Override
	public int getMaxAttempts() {
		return maxAttempts;
	}

	@Override
	public Game startGame() {
		Game game = newGame();
		save(game);
		return game;
	}

	@Override
	public Game loadGameById(String id) {
		return gameDao.findById(id);
	}
	
	@Override
	public Game makeMove(String gameId,int move) {
		Game game=gameDao.findById(gameId);
		if (game==null) {
			throw new IllegalArgumentException("Don't know which game you're playing...");
		}
		
		boolean check=checkMove(game, move);
		game.setAttempts(game.getAttempts()+1);
		if (check || game.getAttempts()>=maxAttempts) {
			game.setFinished(true);
			game.setWon(check);
		}
		gameDao.save(game);
		return game;
	}
	
	
	boolean checkMove(Game game, int move) {
		if (game == null) {
			return false;
		}

		if (move < minNumber || move > maxNumber) {
			throw new RuleException(
					String.format("Value needs to be between %d and %d",
							minNumber, maxNumber)).withGame(game);
		}

		if (game.isFinished()) {
			throw new RuleException("Game is already over").withGame(game);
		}

		if (move == game.getNumberToGuess()) {
			return true;
		}

		return false;

	}

	void save(Game game) {
		gameDao.save(game);
	}

	Game newGame() {
		Game game = new Game().withId(UUID.randomUUID().toString());
		game.setNumberToGuess((int) ((maxNumber - minNumber + 1)
				* Math.random() + minNumber));
		return game;
	}

}
