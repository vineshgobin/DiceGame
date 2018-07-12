package model;

import model.interfaces.DicePair;

import model.interfaces.Player;

public class SimplePlayer implements Player {

	private String id;
	private String playerName;
	private int points;
	private int bet;
	private DicePair rollResult;

	public SimplePlayer(String id, String playerName, int bet) {
		this.id = id;
		setPlayerName(playerName);
		setPoints(bet);
	}

	@Override
	public String getPlayerName() {
		return this.playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;		
	}

	@Override
	public int getPoints() {
		return this.points;
	}

	@Override
	public void setPoints(int points) {
		this.points = points;
		
	}

	@Override
	public String getPlayerId() {
		return this.id;
	}

	@Override
	public boolean placeBet(int bet) {
		if ((bet > this.points) || (bet < 0)) {
			return false;
		} else {
			this.bet = bet;
			this.points -= bet;
			return true;
		}
	}

	@Override
	public int getBet() {
		return this.bet;
	}

	@Override
	public DicePair getRollResult() {
		return this.rollResult;
	}

	@Override
	public void setRollResult(DicePair rollResult) {
		this.rollResult = rollResult;
	}
	
	public String toString() {
		return "Player id=" + this.getPlayerId() + ", name=" + this.getPlayerName() + ", points=" + this.getPoints();
	}

}
