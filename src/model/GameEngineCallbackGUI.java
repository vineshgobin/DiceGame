package model;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;

import view.MainFrame;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class GameEngineCallbackGUI implements GameEngineCallback {
	private MainFrame mainFrame;
	private int houseTotal;
	private int total;
	
	public GameEngineCallbackGUI(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void intermediateResult(final Player player, DicePair dicePair,
			GameEngine engine) {
		final int num1 = dicePair.getDice1();
		final int num2 = dicePair.getDice2();
		final int total = num1 + num2;
		final String statusText = player.getPlayerName() + " is rolling...";
		
		// update GUI view
		try {
			SwingUtilities.invokeAndWait(new Runnable()
			{
				@Override
				public void run()
				{
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameStatusLabel().setText(statusText);
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice1().setVisible(true);
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice2().setVisible(true);
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice1().setText(Integer.toString(num1));
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice2().setText(Integer.toString(num2));
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getRollHouseButton().setEnabled(false);
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void result(final Player player, DicePair dicePair, final GameEngine gameEngine) {
		final int num1 = dicePair.getDice1();
		final int num2 = dicePair.getDice2();
	 total = num1 + num2;

		// update GUI view
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run()
				{
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice1().setText(Integer.toString(num1));
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice2().setText(Integer.toString(num2));
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getPlayerResultLabel().setText(player.getPlayerName() + "'s result: " + total);
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getPlayerResultLabel().setVisible(true);
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getRollHouseButton().setEnabled(true);
					// enable quit and exit
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getQuitButton().setEnabled(true);
					mainFrame.getMenu().getQuitMenuItem().setEnabled(true);
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getExitButton().setEnabled(true);
					mainFrame.getMenu().getExitMenuItem().setEnabled(true);
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine engine) {
		final int num1 = dicePair.getDice1();
		final int num2 = dicePair.getDice2();

		// update GUI view
		try {
			SwingUtilities.invokeAndWait(new Runnable()
			{
				@Override
				public void run()
				{
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().disableBet();
					mainFrame.getMenu().getplaceBetMenuItem().setEnabled(false);
					// disable quit and exit
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getQuitButton().setEnabled(false);
					mainFrame.getMenu().getQuitMenuItem().setEnabled(false);
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getExitButton().setEnabled(false);
					mainFrame.getMenu().getExitMenuItem().setEnabled(false);
					// label stuff
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getHouseResultLabel().setVisible(false);
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameStatusLabel().setText("House is rolling...");
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice1().setVisible(true);
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice2().setVisible(true);
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice1().setText(Integer.toString(num1));
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice2().setText(Integer.toString(num2));
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void houseResult(DicePair dicePair, GameEngine engine) {
		final int num1 = dicePair.getDice1();
		final int num2 = dicePair.getDice2();
		 houseTotal = num1 + num2;

		// update GUI view
		try {
			SwingUtilities.invokeAndWait(new Runnable()
			{
				@Override
				public void run()
				{
					// update house result
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice1().setText(Integer.toString(num1));
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getDicePanel().getDice2().setText(Integer.toString(num2));
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getHouseResultLabel().setText("House result: " + houseTotal);
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getHouseResultLabel().setVisible(true);
					updateResult();
					// enable quit and exit
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getQuitButton().setEnabled(true);
					mainFrame.getMenu().getQuitMenuItem().setEnabled(true);
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getExitButton().setEnabled(true);
					mainFrame.getMenu().getExitMenuItem().setEnabled(true);
					
					// enable add points
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void updateResult() {
		
			final int winpoint = Integer.parseInt(mainFrame.getPlayerPanel().getPoints()) + Integer.parseInt(mainFrame.getPlayerPanel().getBetPoints().getText())*2;
			final int lostpoint = Integer.parseInt(mainFrame.getPlayerPanel().getPoints());
			final int drawpoint = Integer.parseInt(mainFrame.getPlayerPanel().getPoints()) + Integer.parseInt(mainFrame.getPlayerPanel().getBetPoints().getText());
			
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					if (total > houseTotal) {
						// player won
						mainFrame.getPlayerPanel().setPoints(Integer.toString(winpoint));
						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setText(mainFrame.getPlayerPanel().getPlayerName() + " won! :)");
						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setVisible(true);
						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setForeground(new Color(255, 215, 0));
					} else if (total == houseTotal) {
						// draw
						mainFrame.getPlayerPanel().setPoints(Integer.toString(drawpoint));

						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setText("Draw");
						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setVisible(true);
						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setForeground(Color.WHITE);
					} else if (total < houseTotal) {
						// house won
						mainFrame.getPlayerPanel().setPoints(Integer.toString(lostpoint));

						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setText("The house won :(");
						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setVisible(true);
						mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameResultLabel().setForeground(Color.DARK_GRAY);
					}
					
					// update GUI points
					mainFrame.getPlayerPanel().getBetPoints().setText("0");
					// update GUI item status
					mainFrame.getTableAndToolbarContainerPanel().getGameTablePanel().getGameStatusPanel().getGameStatusLabel().setText("Place another bet to play again");
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().enableBet();
					mainFrame.getMenu().getplaceBetMenuItem().setEnabled(true);
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().focusActiveBetText();
					mainFrame.getTableAndToolbarContainerPanel().getToolBar().getRollHouseButton().setEnabled(false);
				}
			});
			
	}
}
	
