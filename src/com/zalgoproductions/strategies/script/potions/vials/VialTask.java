package com.zalgoproductions.strategies.script.potions.vials;

import com.zalgoproductions.util.Potions;
import com.zalgoproductions.util.Sleeping;
import org.powerbot.concurrent.Task;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.Item;

public class VialTask implements Task {
	public void run() {
		for(Item i : Inventory.getItems()) {
			if(i.getId() == Potions.VIAL) {
				int count = Inventory.getCount();
				i.getWidgetChild().interact("Drop");
				Sleeping.waitForInventoryChange(count, 1000);
			}
		}
	}
}
