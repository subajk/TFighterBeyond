package com.zalgoproductions.util;

import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.widget.Widget;

public class Eating {
	private static int eatPercent = 70;
	private static int[] eatIds = {};
	private static String[] eatNames = {};

	private static final int B2P_TAB_ID = 8015;
	private static final int B2B_TAB_ID = 8014;
	private static final int NATURE_RUNE = 561;
	private static final int EARTH_RUNE = 557;
	private static final int WATER_RUNE = 555;
	private static final int MUD_RUNE = 4698;
	
	private static final int[] BONES_ID = new int[]{526, 532, 530, 528, 3183, 2859};

	public static final Filter<Item> bonesFilter = new Filter<Item>() {
		public boolean accept(Item item) {
			for(int id : BONES_ID) {
				if(item.getId() == id)  {
					return true;
				}
			}
			return false;
		}
	};
	
	public static final Filter<Item> foodFilter = new Filter<Item>() {
		public boolean accept(Item item) {
			for(int id : eatIds) {
				if (item.getId() == id) {
					return true;
				}
			}
			for(String name : eatNames) {
				if(item.getName() != null && item.getName().toLowerCase().contains(name.toLowerCase())) {
					return true;
				}
			}
			return false;
		}
	};

	public static void setFoodIds(int[] ids) {
		eatIds = ids;
	}

	public static void setFoodNames(String[] names) {
		eatNames = names;
	}
	
	public static int getCurrentPercent() {
		float totalHp = Skills.getLevel(Skills.CONSTITUTION) * 10;
		float currHp = Integer.parseInt(Widgets.get(748, 8).getText());
		return Math.round(currHp / totalHp * 100);
	}
	
	public static void setEatPercent(final int percent) {
		eatPercent = percent;
	}
	
	public static int getEatPercent() {
		return eatPercent;
	}
	
	private static boolean haveB2BTab() {
		return Inventory.getCount(B2B_TAB_ID) > 0;
	}

	private static boolean haveB2PTab() {
		return Inventory.getCount(B2P_TAB_ID) > 0;
	}

	private static boolean haveB2BRunes() {
		return Inventory.getCount(true, NATURE_RUNE) > 2 && Inventory.getCount(true, MUD_RUNE) >= 1  ||
				(Inventory.getCount(true, EARTH_RUNE) >= 1 && Inventory.getCount(true, WATER_RUNE) >= 1);
	}

	private static boolean haveB2PRunes() {
		return Inventory.getCount(true, NATURE_RUNE) > 2 && Inventory.getCount(true, MUD_RUNE) >= 4  ||
				(Inventory.getCount(true, EARTH_RUNE) >= 4 && Inventory.getCount(true, WATER_RUNE) >= 4);
	}

	public static boolean canB2B() {
		return haveB2BTab() || haveB2BRunes();
	}

	public static boolean canB2P() {
		return haveB2PTab() || haveB2PRunes();
	}

	public static void castB2B() {
		if(haveB2BTab()) {
			for(Item item : Inventory.getItems()) {
				if(item.getId() == B2B_TAB_ID) {
					item.getWidgetChild().click(true);
					break;
				}
			}
		} else if (haveB2BRunes()) {
			Tabs.MAGIC.open();
			Widget modernBook = Widgets.get(192);
			if(!modernBook.validate())
				return;
			modernBook.getChild(65).interact("Cast");
			Time.sleep(Random.nextInt(150, 500));
		}
	}

	public static void castB2P() {
		if(haveB2PTab()) {
			for(Item item : Inventory.getItems()) {
				if(item.getId() == B2P_TAB_ID) {
					item.getWidgetChild().click(true);
					break;
				}
			}
		} else if (haveB2PRunes()) {
			Tabs.MAGIC.open();
			Widget modernBook = Widgets.get(192);
			if(!modernBook.validate())
				return;
			modernBook.getChild(33).interact("Cast");
			Time.sleep(Random.nextInt(150, 500));
		}                             
	}
}
