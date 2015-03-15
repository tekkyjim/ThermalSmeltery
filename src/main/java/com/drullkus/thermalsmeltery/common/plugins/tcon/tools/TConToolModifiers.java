package com.drullkus.thermalsmeltery.common.plugins.tcon.tools;

import mantle.pulsar.pulse.Handler;
import mantle.pulsar.pulse.Pulse;
import net.minecraft.item.ItemStack;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.crafting.ModifyBuilder;
import tconstruct.library.tools.HarvestTool;
import tconstruct.library.tools.ToolCore;

import com.drullkus.thermalsmeltery.common.items.TSItems;
import com.drullkus.thermalsmeltery.common.lib.LibMisc;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(LibMisc.MOD_ID)
@Pulse(id = "TSmelt TCon Tools", description = "Tinker's Construct Tools Integration", modsRequired = "TConstruct")
public class TConToolModifiers
{

	@Handler
	public void postInit(FMLPostInitializationEvent event)
	{
		ItemStack voidTouch = new ItemStack(TSItems.itemBase, 1, 2);
		int effect = 20;
		ModifyBuilder.registerModifier(new ModVoidTouch(new ItemStack[] { voidTouch }, effect, "Voiding", "\u00a7b", "Voiding"));

		for (ToolCore tool : TConstructRegistry.getToolMapping())
		{
			if (tool instanceof HarvestTool)
			{
				String icon = "thermalsmeltery" + ":";
				icon += "tools/" + tool.getDefaultFolder() + "/";
				icon += "VoidMiner" + tool.getEffectSuffix();
				tool.registerEffectPath(effect, icon);
			}
		}
		TConstructRegistry.registerActiveToolMod(new TConActiveToolMod());
	}
}
