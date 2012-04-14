package com.zalgoproductions.strategies.script.incombat;

import com.zalgoproductions.util.Attacking;
import org.powerbot.concurrent.Task;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;

public class InCombatTask implements Task {
	public void run() {  
		if(Attacking.shouldSpecial()) {
			Attacking.setSpecialAttack(true);
		}
		Time.sleep(Random.nextInt(500, 1000));
	}
}
