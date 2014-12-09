package net.kueffel.guessme.service;

import junit.framework.Assert;
import net.kueffel.guessme.model.Game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:appContext.xml" })
public class GameServiceImplTest {

	@Autowired
	private GameServiceImpl gameService;

	
	
	@Test
	public void testIfCorrectNumberIsDetected() {
		Game game = gameService.newGame();
		game.setNumberToGuess(10);
		
		Assert.assertTrue("Right number was not detected",gameService.checkMove(game, 10));
	}
	
	@Test
	public void testIfWrongNumberIsDetected() {
		Game game = gameService.newGame();
		game.setNumberToGuess(10);
		
		Assert.assertFalse("Wrong number was not detected",gameService.checkMove(game, 1));
	}
	
	
	@Test
	public void testIfGameEndsAfterMaxAttemptsWrongMoves() {
		Game game = gameService.newGame();
		game.setNumberToGuess(10);
		gameService.save(game);

		int i = 0;

		try {
			for (i = 0; i < gameService.getMaxAttempts() + 10; i++) {
				gameService.makeMove(game.getId(), 1);
			}
			Assert.assertTrue(
					"Game did not end after configured max attempts.", false);
		} catch (Exception e) {
			Assert.assertEquals(gameService.getMaxAttempts(), i);
		}
	}

	@Test
	public void testIfGameEndsAfterCorrectNumber() {
		Game game = gameService.newGame();
		game.setNumberToGuess(10);
		gameService.save(game);

		gameService.makeMove(game.getId(), 1);
		gameService.makeMove(game.getId(), 10);

		Assert.assertTrue(
				"Game did not finish after correct number was guessed",
				game.isFinished());
		Assert.assertTrue(
				"Game not set to 'won' after correct number was guessed",
				game.isWon());

	}
	


}
