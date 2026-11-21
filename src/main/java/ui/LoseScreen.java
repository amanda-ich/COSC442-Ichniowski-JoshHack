package ui;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import game.Creature;

public class LoseScreen implements Screen {
	private Creature player;
	
	@SuppressFBWarnings(
		value = "EI_EXPOSE_REP2",
		justification = "The screen displays the cause of death from the same player instance.")
	public LoseScreen(Creature player){
		this.player = player;
	}
	
	@Override
	public void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter("R.I.P.", 3);
		terminal.writeCenter(player.causeOfDeath(), 5);
		terminal.writeCenter("-- press [enter] to restart --", 22);
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
	}
}
