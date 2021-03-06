package com.zalgoproductions.script;

import com.zalgoproductions.resources.Fonts;
import com.zalgoproductions.strategies.script.alchemy.AlchCondition;
import com.zalgoproductions.strategies.script.alchemy.AlchTask;
import com.zalgoproductions.strategies.script.ammo.AmmoCondition;
import com.zalgoproductions.strategies.script.ammo.AmmoTask;
import com.zalgoproductions.strategies.script.antiban.AntibanCondition;
import com.zalgoproductions.strategies.script.antiban.AntibanTask;
import com.zalgoproductions.strategies.script.attacking.AttackCondition;
import com.zalgoproductions.strategies.script.attacking.AttackTask;
import com.zalgoproductions.strategies.script.bones.BonesCondition;
import com.zalgoproductions.strategies.script.bones.BonesTask;
import com.zalgoproductions.strategies.script.continuing.ContinueCondition;
import com.zalgoproductions.strategies.script.continuing.ContinueTask;
import com.zalgoproductions.strategies.script.eating.EatingCondition;
import com.zalgoproductions.strategies.script.eating.EatingTask;
import com.zalgoproductions.strategies.script.exit.ExitCondition;
import com.zalgoproductions.strategies.script.exit.ExitTask;
import com.zalgoproductions.strategies.script.explorersring.RingCondition;
import com.zalgoproductions.strategies.script.explorersring.RingTask;
import com.zalgoproductions.strategies.script.incombat.InCombatCondition;
import com.zalgoproductions.strategies.script.incombat.InCombatTask;
import com.zalgoproductions.strategies.script.looting.LootingCondition;
import com.zalgoproductions.strategies.script.looting.LootingTask;
import com.zalgoproductions.strategies.script.potions.PotionTask;
import com.zalgoproductions.strategies.script.potions.vials.VialCondition;
import com.zalgoproductions.strategies.script.potions.vials.VialTask;
import com.zalgoproductions.strategies.script.prayer.PrayerCondition;
import com.zalgoproductions.strategies.script.prayer.PrayerTask;
import com.zalgoproductions.strategies.script.running.RunningCondition;
import com.zalgoproductions.strategies.script.running.RunningTask;
import com.zalgoproductions.strategies.script.safespot.SafespotCondition;
import com.zalgoproductions.strategies.script.safespot.SafespotTask;
import com.zalgoproductions.strategies.setup.SetupCondition;
import com.zalgoproductions.strategies.setup.SetupTask;
import com.zalgoproductions.strategies.setup.SleepCondition;
import com.zalgoproductions.strategies.setup.SleepTask;
import com.zalgoproductions.util.Attacking;
import com.zalgoproductions.util.Looting;
import com.zalgoproductions.util.paint.Display;
import com.zalgoproductions.util.paint.Paint;
import com.zalgoproductions.util.paint.displays.TFighterDisplay;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.bot.event.listener.PaintListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * V0.51 Bug Fixes
 * V0.5 SDN RELEASE
 * V0.2 Bug Fixes and B2B, B2P
 * V0.1 Public Beta Start
 */
@Manifest(name = "TFighter Beyond", description = "Universal Fighter", version = 0.51d, authors = {"Zalgo2462"}, premium = false)
public class TFighterBeyond extends ActiveScript implements PaintListener, MouseListener {
	public static boolean needsSetup = true;
	public static long startTime;
	public static final Manifest mani = TFighterBeyond.class.getAnnotation(Manifest.class);
	
	@Override
	protected void setup() {
		log.info("Welcome the TFighter Beyond. Going beyond what reality allows.");
		if(!Game.isLoggedIn()) {
			log.info("Start the script logged in!");
			stop();
		}
		//TODO: CREATE AND SUBMIT TASKS
		Strategy antibanStrategy = new Strategy(new AntibanCondition(), new AntibanTask());
		antibanStrategy.setLock(false);
		antibanStrategy.setReset(false);
		provide(antibanStrategy);

		Strategy stopStrategy = new Strategy(new ExitCondition(), new ExitTask(this));
		stopStrategy.setLock(true);
		stopStrategy.setReset(true);

		Strategy setupStrategy = new Strategy(new SetupCondition(), new SetupTask());
		setupStrategy.setLock(true);
		setupStrategy.setReset(true);
		provide(setupStrategy);
		
		Strategy sleepWhileSetupStrategy = new Strategy(new SleepCondition(), new SleepTask());
		sleepWhileSetupStrategy.setLock(true);
		sleepWhileSetupStrategy.setReset(true);
		provide(sleepWhileSetupStrategy);

		Strategy continueStrategy = new Strategy(new ContinueCondition(), new ContinueTask());
		continueStrategy.setLock(true);
		continueStrategy.setReset(true);
		provide(continueStrategy);
		
		Strategy eatingStrategy = new Strategy(new EatingCondition(), new EatingTask());
		eatingStrategy.setLock(true);
		eatingStrategy.setReset(true);
		provide(eatingStrategy);
		
		Strategy ammoStrategy = new Strategy(new AmmoCondition(), new AmmoTask());
		ammoStrategy.setLock(true);
		ammoStrategy.setReset(true);
		provide(ammoStrategy);
		
		Strategy prayerStrategy = new Strategy(new PrayerCondition(), new PrayerTask());
		prayerStrategy.setLock(true);
		prayerStrategy.setReset(true);
		provide(prayerStrategy);
		
		Strategy ringStrategy = new Strategy(new RingCondition(), new RingTask());
		ringStrategy.setLock(true);
		ringStrategy.setReset(true);
		provide(ringStrategy);

		Strategy runningStrategy = new Strategy(new RunningCondition(), new RunningTask());
		runningStrategy.setLock(true);
		runningStrategy.setReset(true);
		provide(runningStrategy);

		Strategy safespotStrategy = new Strategy(new SafespotCondition(), new SafespotTask());
		safespotStrategy.setLock(true);
		safespotStrategy.setReset(true);
		provide(safespotStrategy);

		PotionTask potionTask = new PotionTask();
		Strategy potionManagerStrategy = new Strategy(potionTask, potionTask);
		potionManagerStrategy.setLock(true);
		potionManagerStrategy.setReset(true);
		provide(potionManagerStrategy);

		Strategy inCombatStrategy = new Strategy(new InCombatCondition(), new InCombatTask());
		inCombatStrategy.setLock(true);
		inCombatStrategy.setReset(true);
		provide(inCombatStrategy);
		
		Strategy vialStrategy = new Strategy(new VialCondition(), new VialTask());
		vialStrategy.setLock(true);
		vialStrategy.setReset(true);
		provide(vialStrategy);

		Strategy lootingStrategy = new Strategy(new LootingCondition(), new LootingTask());
		lootingStrategy.setLock(true);
		lootingStrategy.setReset(true);
		provide(lootingStrategy);

		Strategy buryingStrategy = new Strategy(new BonesCondition(), new BonesTask());
		buryingStrategy.setLock(true);
		buryingStrategy.setReset(true);
		provide(buryingStrategy);

		Strategy alchemyStrategy = new Strategy(new AlchCondition(), new AlchTask());
		alchemyStrategy.setLock(true);
		buryingStrategy.setReset(true);
		provide(alchemyStrategy);
		
		Strategy attackStrategy = new Strategy(new AttackCondition(), new AttackTask());
		attackStrategy.setLock(true);
		attackStrategy.setReset(true);
		provide(attackStrategy);
	}

	public void onRepaint(final Graphics g) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.WHITE);
		g.setFont(Fonts.BATMAN_FONT.deriveFont(Font.PLAIN, 16));

		String[] debug = new String[] {};
		int y = 10;
		int x = 10;
		for(String str : debug) {
			g.drawString(str, x, y += g.getFontMetrics().getMaxAscent());
		}
		if(Paint.shouldPaint()) {
			g.setColor(Color.GREEN);
			for(NPC npc : NPCs.getLoaded(Attacking.NPC_FILTER)) {
				Polygon[] polygons = npc.getLocation().getBounds();
				for(Polygon p : polygons) {
					g.drawPolygon(p);
				}
			}
			g.setColor(Color.WHITE);
			for(GroundItem groundItem : GroundItems.getLoaded(Looting.LOOT_FILTER)) {
				Polygon[] polygons = groundItem.getLocation().getBounds();
				for(Polygon p : polygons) {
					g.drawPolygon(p);
				}
			}
			Paint.paintRug(g);
			Paint.paintTab(g);
			Paint.paintDisplay(g, TFighterDisplay.class.asSubclass(Display.class));
		}
		Paint.paintMouse(g);
	}
	
	public static long getRunTime() {
		return System.currentTimeMillis() - startTime;
	}

	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		if(Paint.settings.getBounds().contains(p)) {
			needsSetup = true;
		}
		if(Paint.chat.contains(p)) {
			Paint.setPaint(!Paint.shouldPaint());
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}
