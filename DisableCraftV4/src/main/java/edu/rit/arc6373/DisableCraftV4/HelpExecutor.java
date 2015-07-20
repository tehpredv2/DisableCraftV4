package edu.rit.arc6373.DisableCraftV4;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;
import org.spongepowered.api.util.command.spec.CommandExecutor;

public class HelpExecutor implements CommandExecutor{

	@Override
	public CommandResult execute(CommandSource src, CommandContext ctx) throws CommandException {
		// TODO Auto-generated method stub
		if(src instanceof Player){
			Text text = Texts.builder("DisableCraftV4 Help Menu").color(TextColors.GOLD).build();
			src.sendMessage(text);
		}
		return CommandResult.success();
	}

}
