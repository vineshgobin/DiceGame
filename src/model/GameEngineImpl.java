package model;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Random;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class GameEngineImpl implements GameEngine {

	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Integer> playerTotals = new ArrayList<Integer>();
	private GameEngineCallback gameEngineCallback;
	int houseTotal;
	int playerTotal;

	@Override
	public boolean placeBet(Player player, int bet) {
		if (player.placeBet(bet)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement) {
		int num1;
		int num2;
		final int minNum = 1;
		Random random = new Random();
		DicePair dicePair = null;
		
		
		//Rolling the dice
		
		while (initialDelay < finalDelay) {
			num1 = random.nextInt(6) + minNum;
			num2 = random.nextInt(6) + minNum;
			dicePair = new DicePairImpl(num1, num2, 6);
			this.gameEngineCallback.intermediateResult(player, dicePair, this);
			initialDelay += delayIncrement;
			try {
				Thread.sleep(initialDelay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//Final dice values
		
		num1 = random.nextInt(6) + minNum;
		num2 = random.nextInt(6) + minNum;
		
		
		//Calculates total for this player and stores it in the playerTotals arraylist
		
		playerTotal = num1 + num2;
		
		for (Player players : this.getAllPlayers()) {
			playerTotals.add(playerTotal);
		}

		dicePair = new DicePairImpl(num1, num2, 6);
		player.setRollResult(dicePair);
		this.gameEngineCallback.result(player, dicePair, this);
	}

	@Override
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
		int num1;
		int num2;
		final int minNum = 1;
		Random random = new Random();
		DicePair dicePair = null;
		
		//Rolling the dice
		
		while (initialDelay < finalDelay) {
			num1 = random.nextInt(6) + minNum;
			num2 = random.nextInt(6) + minNum;
			dicePair = new DicePairImpl(num1, num2, 6);
			this.gameEngineCallback.intermediateHouseResult(dicePair, this);
			initialDelay += delayIncrement;
			try {
				Thread.sleep(initialDelay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Final dice values

		num1 = random.nextInt(6) + minNum;
		num2 = random.nextInt(6) + minNum;

		houseTotal = num1 + num2;

		// Calculates the points for each player if they win/lose/draw.

		for (Player player : this.getAllPlayers()) {
			if (playerTotals.get(Integer.valueOf(player.getPlayerId()) - 1) > houseTotal) {
				player.setPoints(player.getPoints() + player.getBet() * 2);
			} else if (playerTotals.get(Integer.valueOf(player.getPlayerId()) - 1) < houseTotal) {
				player.setPoints(player.getPoints());
			} else if (playerTotals.get(Integer.valueOf(player.getPlayerId()) - 1) == houseTotal) {
				player.setPoints(player.getPoints() + player.getBet());
			}
		}
	
		dicePair = new DicePairImpl(num1, num2, 6);
		this.gameEngineCallback.houseResult(dicePair, this);
	}

	@Override
	public void addPlayer(Player player) {
		players.add(player);
	}

	@Override
	public Player getPlayer(String id) {
		if (players.get(Integer.valueOf(id)) == null) {
			return null;
		} else {
			return players.get(Integer.valueOf(id));
		}
	}

	@Override
	public boolean removePlayer(Player player) {
		if (players.contains(player)) {
			players.remove(player);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.gameEngineCallback = gameEngineCallback;
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		if (this.gameEngineCallback == gameEngineCallback) {
			this.gameEngineCallback = null;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Collection<Player> getAllPlayers() {
		return this.players;
	}

}
