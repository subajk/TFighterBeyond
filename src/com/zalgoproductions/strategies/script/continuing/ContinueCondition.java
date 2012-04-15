package com.zalgoproductions.strategies.script.continuing;

import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;

public class ContinueCondition implements Condition {

	public boolean validate() {
		Tabs.INVENTORY.open();
		return Widgets.canContinue();
	}
}
