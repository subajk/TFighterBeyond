package com.zalgoproductions.strategies.script.potions.vials;

import com.zalgoproductions.util.Potions;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.game.api.methods.tab.Inventory;

public class VialCondition implements Condition {
	public boolean validate() {
		return Inventory.getCount(Potions.VIAL) > 0;
	}
}
