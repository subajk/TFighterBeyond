package com.zalgoproductions.strategies.script.eating;

import com.zalgoproductions.util.Eating;
import org.powerbot.concurrent.strategy.Condition;

public class EatingCondition implements Condition {
	public boolean validate() {
		return Eating.getCurrentPercent() <= Eating.getEatPercent();
	}
}
