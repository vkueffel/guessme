package net.kueffel.guessme.persistence;

import net.kueffel.guessme.model.Game;

public interface GameDao {

	public abstract Game findById(String id);

	public abstract void save(Game game);

}
