package summer.base.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import summer.Summer;
import summer.base.manager.Command;
import summer.base.manager.config.Cheats;
import summer.base.utilities.ChatUtils;

public class HideCommand implements Command {

	@Override
	public boolean run(String[] args) {
		if (args.length == 2) {
			try {
				Cheats m = Summer.INSTANCE.cheatManager.getModuleByName(args[1]);
				if (args[1].equalsIgnoreCase(m.getName()))
					m.setDisplayName("");
			} catch (Exception e) {
				ChatUtils.sendMessage("Module not found.");
			}
			return true;
		}
		return false;
	}

	@Override
	public String usage() {
		return ChatFormatting.WHITE + "h | Hides a module from the arraylist";
	}

}
