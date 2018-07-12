package view;

import java.awt.*;

import javax.swing.*;

import controller.Controller;
import model.GameEngineCallbackGUI;
import model.interfaces.GameEngine;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -7523238222452192023L;
	private GameEngine gameEngine;
	private StartGamePanel startGamePanel;
	private Menu menu;
	private TitlePanel heading;
	private PlayerInfoPanel playerPanel;
	private TableAndToolbarPanel tableAndToolbarContainerPanel;
	
	public MainFrame(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
		gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(this));
		
		startGamePanel = new StartGamePanel();
		menu = new Menu();
		heading = new TitlePanel();
		playerPanel = new PlayerInfoPanel();
		tableAndToolbarContainerPanel = new TableAndToolbarPanel();
		
		setJMenuBar(menu);
		add(heading, BorderLayout.NORTH);
		add(playerPanel, BorderLayout.WEST);
		add(tableAndToolbarContainerPanel, BorderLayout.CENTER);
		add(startGamePanel, BorderLayout.CENTER);
		
		// set details of main frame
		setTitle("Dice Game");
		setSize(600, 420);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		setResizable(false);
		new Controller(this.gameEngine, this);
		setVisible(true);
	}
	
	public StartGamePanel getStartGamePanel() {
		return startGamePanel;
	}
	
	public PlayerInfoPanel getPlayerPanel() {
		return playerPanel;
	}
	
	public TableAndToolbarPanel getTableAndToolbarContainerPanel() {
		return tableAndToolbarContainerPanel;
	}
	
	public Menu getMenu() {
		return menu;
	}
}
