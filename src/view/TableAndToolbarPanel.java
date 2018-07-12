package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
public class TableAndToolbarPanel extends JPanel {
	private static final long serialVersionUID = -9048676903836093197L;
	private PlayingPanel gameTablePanel;
	private ToolBarPanel toolBarPanel;
	
	public TableAndToolbarPanel() {
		setLayout(new BorderLayout());
		gameTablePanel = new PlayingPanel();
		add(gameTablePanel, BorderLayout.CENTER);
		toolBarPanel = new ToolBarPanel();
		add(toolBarPanel, BorderLayout.SOUTH);
	}
	public ToolBarPanel getToolBar() {
		return toolBarPanel;
	}
	
	public PlayingPanel getGameTablePanel() {
		return gameTablePanel;
	}
}