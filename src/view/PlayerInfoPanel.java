
package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PlayerInfoPanel extends JPanel {
	private static final long serialVersionUID = 5849689848560582871L;
	private JLabel playerTitle = null;
	private JLabel playerName = null;
	private JLabel pointsTitle = null;
	private JLabel points = null;
	private JLabel betTitle = null;
	private JLabel betPoints = null;
	private JTextField addPointsTextField = null;
	private int betPointsInt = 0;
	
	public PlayerInfoPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(new Color(151, 42, 39));
		
		add(Box.createRigidArea(new Dimension(170, 20)));
		
		playerTitle = new JLabel("Player:");
		playerTitle.setForeground(new Color(255, 215, 0));
		playerTitle.setAlignmentX(CENTER_ALIGNMENT);
		add(playerTitle);
		
		add(Box.createRigidArea(new Dimension(170, 5)));
		
		playerName = new JLabel("No player yet");
		playerName.setForeground(Color.WHITE);
		playerName.setAlignmentX(CENTER_ALIGNMENT);
		add(playerName);
		
		pointsTitle = new JLabel("Points:");
		pointsTitle.setForeground(new Color(255, 215, 0));
		pointsTitle.setAlignmentX(CENTER_ALIGNMENT);
		pointsTitle.setVisible(false);
		add(pointsTitle);
		
		add(Box.createRigidArea(new Dimension(170, 5)));
		
		points = new JLabel("");
		points.setForeground(Color.WHITE);
		points.setVisible(false);
		points.setAlignmentX(CENTER_ALIGNMENT);
		add(points);
		
		add(Box.createRigidArea(new Dimension(170, 5)));
		
		betTitle = new JLabel("Current bet:");
		betTitle.setForeground(new Color(255, 215, 0));
		betTitle.setAlignmentX(CENTER_ALIGNMENT);
		add(betTitle);
		
		add(Box.createRigidArea(new Dimension(170, 5)));
		
		betPoints = new JLabel(Integer.toString(betPointsInt));
		betPoints.setForeground(Color.WHITE);
		betPoints.setAlignmentX(CENTER_ALIGNMENT);
		add(betPoints);
		
		add(Box.createRigidArea(new Dimension(170, 20)));
		
		add(Box.createRigidArea(new Dimension(170, 10)));
		
		addPointsTextField = new JTextField(4);
		addPointsTextField.setMaximumSize(new Dimension(90, 20));
		addPointsTextField.setEnabled(false);
		addPointsTextField.setVisible(false);
		add(addPointsTextField);
		
		add(Box.createVerticalGlue());
		
		add(Box.createRigidArea(new Dimension(170, 7)));

		setVisible(false);
	}
	
	public void setPlayerName(String name) {
		playerName.setText(name);
	}
	
	public String getPlayerName() {
		return playerName.getText();
	}
	
	public void showPoints() {
		pointsTitle.setVisible(true);
		points.setVisible(true);
	}
	
	public void hidePoints() {
		pointsTitle.setVisible(false);
		points.setVisible(false);
	}
	
	public void setPoints(String points) {
		this.points.setText(points);
	}
	
	public String getPoints() {
		return points.getText();
	}
	
	public void deductPoints(int points) {
		int currentPoints = Integer.parseInt(this.points.getText());
		int newPoints = currentPoints - points;
		this.points.setText(Integer.toString(newPoints));
	}
	
	public void addPoints(int points) {
		int currentPoints = Integer.parseInt(this.points.getText());
		int newPoints = currentPoints + points;
		this.points.setText(Integer.toString(newPoints));
	}
	
	public void notEnoughPoints() {
		JOptionPane.showMessageDialog(this, "Not enough points.");
	}
	
	public void setBetPoints(int points) {
		betPointsInt = points;
		betPoints.setText(Integer.toString(betPointsInt));
	}
	
	public JLabel getBetPoints() {
		return betPoints;
	}
	
	public void showBet() {
		betTitle.setVisible(true);
		betPoints.setVisible(true);
	}
	
	public void hideBet() {
		betTitle.setVisible(false);
		betPoints.setVisible(false);
	}
}