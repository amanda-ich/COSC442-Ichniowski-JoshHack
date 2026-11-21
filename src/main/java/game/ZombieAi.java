package game;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class ZombieAi extends CreatureAi {
	private Creature player;
	
	@SuppressFBWarnings(
		value = "EI_EXPOSE_REP2",
		justification = "Zombie decisions must track the player's live position and state.")
	public ZombieAi(Creature creature, Creature player) {
		super(creature);
		this.player = player;
	}

	public void onUpdate(){
		if (Math.random() < 0.2)
			return;
		
		if (creature.canSee(player.x, player.y, player.z))
			hunt(player);
		else
			wander();
	}
}
