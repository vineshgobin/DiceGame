package Main;

import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import view.MainFrame;
import model.GameEngineCallbackGUI;
import model.GameEngineImpl;

public class GUIMain {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				GameEngine gameEngine = new GameEngineImpl();

				MainFrame mainFrame = new MainFrame(gameEngine);

				gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(mainFrame));

			}
		});

	}
}