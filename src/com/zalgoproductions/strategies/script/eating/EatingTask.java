package com.zalgoproductions.strategies.script.eating;

import com.zalgoproductions.strategies.script.exit.ExitCondition;
import com.zalgoproductions.util.Eating;
import org.powerbot.concurrent.Task;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.Item;

public class EatingTask implements Task {
	public void run() {
		if(Inventory.getCount(Eating.foodFilter) > 0) {
			Item[] foods = Inventory.getItems();
			Item toEat = null;

			for(Item food : foods) {
				if(Eating.foodFilter.accept(food)) {
					toEat = food;
					break;
				}
			}

			if(toEat != null) {
				toEat.getWidgetChild().interact("Eat");
			}
		} else if (	Inventory.getCount(Eating.bonesFilter) > 0) {
			if(Eating.canB2B()) {
				Eating.castB2B();
			} else if (Eating.canB2P()) {
				Eating.castB2P();
			}
		} else {
			ExitCondition.stopScript();
		}		
	}
}
