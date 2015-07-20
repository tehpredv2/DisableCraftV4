package edu.rit.arc6373.DisableCraftV4;


import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.IOException;

import com.google.inject.Inject;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.event.state.PreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.event.inventory.CraftItemEvent;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.service.config.DefaultConfig;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.spec.CommandSpec;


@Plugin(id = "DisableCraftV4", name = "DisableCraftV4", version = "1.0")
public class DisableCraftV4{

	static Game game = null;
	static ConfigurationNode config = null;
	static ConfigurationLoader<CommentedConfigurationNode> configurationManager;
	
	@Inject
	private Logger logger;
	
	private void setLogger(Logger logger){
		this.logger = logger;
	}
	
	public Logger getLogger(){
		return logger;
	}

	
	@Inject
	@DefaultConfig(sharedRoot = true)
	private File dConfig;


	@Inject
	@DefaultConfig(sharedRoot = true)
	private ConfigurationLoader<CommentedConfigurationNode> confManager;
	
	@Subscribe
	public void onPreInit(PreInitializationEvent event){
		game = event.getGame();
		setLogger(logger);
		
		try {
			if (!dConfig.exists()) {
				dConfig.createNewFile();
				config = confManager.load();
				List<String> itemsList = Arrays.asList("iron_helmet", "iron_leggings", "iron_chestplate", "iron_boots");
				config.getNode("config", "items").setValue(itemsList);
				confManager.save(config); 
			}
			configurationManager = confManager;
			config = confManager.load();

		} catch (IOException exception) {
			getLogger().error("The default configuration could not be loaded or created!");
		}
		
		CommandSpec disableCommandSpec = CommandSpec.builder()
				.setDescription(Texts.of("Help Command"))
				.setPermission("disable.help")
				.setExecutor(new HelpExecutor())
				.build();
		
		game.getCommandDispatcher().register(this, disableCommandSpec, "disable help", "test", "derp");
		
		getLogger().info("DisableCraftV4 has been enabled!");
		getLogger().info("Created by tehpredv2! The best dev ever on EP");
	}
	
	
	@Subscribe
	public void craftItem(CraftItemEvent e){
		Text colored = Texts.builder("This is a test").color(TextColors.DARK_RED).build();
		Player p = (Player) e.getViewer();
		p.sendMessage(colored);
	}
	
	
	public static ConfigurationLoader<CommentedConfigurationNode> getConfigManager(){
		return configurationManager;
	}
	
}
