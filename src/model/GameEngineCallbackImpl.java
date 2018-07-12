package model;

import java.util.logging.ConsoleHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * 
 * Skeleton example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see model.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
	private Logger logger = Logger.getLogger("assignment1");
	private ConsoleHandler handler = new ConsoleHandler();
	

	public GameEngineCallbackImpl()
	{
		// FINE shows rolling output, INFO only shows result
		logger.setLevel(Level.FINER);
		logger.setUseParentHandlers(false);
		handler.setLevel(Level.FINER);
		logger.addHandler(handler);
	}
	
	@Override
	public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine)
	{
		// intermediate results logged at Level.FINE
		logger.log(Level.FINE, String.format("%s%s%s%d%s%d%s%d\n", "Player: ", player.getPlayerName(),
				", ROLLING Dice 1: ", dicePair.getDice1(),
				", Dice 2: ", dicePair.getDice2(), " .. Total: ", dicePair.getDice1() + dicePair.getDice2()));
	}

	@Override
	public void result(Player player, DicePair result, GameEngine gameEngine)
	{
		// final results logged at Level.INFO
		logger.log(Level.INFO, String.format("%s%s%s%d%s%d%s%d\n", "Player: ", player.getPlayerName(),
				", *RESULT* Dice 1: ", result.getDice1(),
				", Dice 2: ", result.getDice2(), " .. Total: ", result.getDice1() + result.getDice2()));
	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine) {
		logger.log(Level.FINE, String.format("%s%s%d%s%d%s%d\n", "House: ",
				", ROLLING Dice 1: ", dicePair.getDice1(),
				", Dice 2: ", dicePair.getDice2(), " .. Total: ", dicePair.getDice1() + dicePair.getDice2()));
	}

	@Override
	public void houseResult(DicePair result, GameEngine gameEngine) {
		logger.log(Level.INFO, String.format("%s%s%d%s%d%s%d\n", "House: ",
				", *RESULT* Dice 1: ", result.getDice1(),
				", Dice 2: ", result.getDice2(), " .. Total: ", result.getDice1() + result.getDice2()));
		
		//Logs the final outcome of the game
		
		for (Player player : gameEngine.getAllPlayers()) {
			logger.log(Level.INFO, player.toString());
		}
	}
}
