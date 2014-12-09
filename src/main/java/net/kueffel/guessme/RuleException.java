package net.kueffel.guessme;

import net.kueffel.guessme.model.Game;

public class RuleException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Game game;

	public RuleException() {
		super();
	}

	public RuleException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RuleException(String message, Throwable cause) {
		super(message, cause);
	}

	public RuleException(String message) {
		super(message);
	}

	public RuleException(Throwable cause) {
		super(cause);
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public RuleException withGame(Game game) {
		setGame(game);
		return this;
	}

}
