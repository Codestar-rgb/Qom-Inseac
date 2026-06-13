/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentTranslation
 */
package com.dhanantry.scapeandrunparasites.network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandSRPHelp
extends CommandBase {
    private static final Map<String, List<Entry>> TOPICS = new LinkedHashMap<String, List<Entry>>();

    public String func_71517_b() {
        return "srphelp";
    }

    public String func_71518_a(ICommandSender sender) {
        return "/srphelp [topic]";
    }

    public int func_82362_a() {
        return 2;
    }

    public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            sender.func_145747_a(CommandSRPHelp.tr("srphelp.header.warning", new Object[0]));
            sender.func_145747_a(CommandSRPHelp.tr("srphelp.header.topics", new Object[0]));
            for (String t : TOPICS.keySet()) {
                sender.func_145747_a(CommandSRPHelp.tr("srphelp.topic.item", t));
            }
            sender.func_145747_a(CommandSRPHelp.tr("srphelp.footer.hint", new Object[0]));
            return;
        }
        String topic = args[0].toLowerCase(Locale.ROOT);
        if (!TOPICS.containsKey(topic)) {
            sender.func_145747_a(CommandSRPHelp.tr("srphelp.error.unknown", topic));
            sender.func_145747_a(CommandSRPHelp.tr("srphelp.header.topics_inline", String.join((CharSequence)", ", TOPICS.keySet())));
            return;
        }
        sender.func_145747_a(CommandSRPHelp.tr("srphelp.header.warning", new Object[0]));
        sender.func_145747_a(CommandSRPHelp.tr("srphelp.topic.header", topic));
        for (Entry e : TOPICS.get(topic)) {
            sender.func_145747_a(CommandSRPHelp.tr(e.usageKey, new Object[0]));
            sender.func_145747_a(CommandSRPHelp.tr(e.key, new Object[0]));
        }
    }

    private static ITextComponent tr(String key, Object ... args) {
        return new TextComponentTranslation(key, args);
    }

    public List<String> func_184883_a(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1) {
            return CommandSRPHelp.func_175762_a((String[])args, new ArrayList<String>(TOPICS.keySet()));
        }
        return Collections.emptyList();
    }

    static {
        TOPICS.put("srparasites", Arrays.asList(new Entry("srphelp.srparasites.setgeneration.desc", "srphelp.srparasites.setgeneration.usage"), new Entry("srphelp.srparasites.getgeneration.desc", "srphelp.srparasites.getgeneration.usage"), new Entry("srphelp.srparasites.readconfigurationfile.desc", "srphelp.srparasites.readconfigurationfile.usage"), new Entry("srphelp.srparasites.toggle_dotiledrops.desc", "srphelp.srparasites.toggle_dotiledrops.usage"), new Entry("srphelp.srparasites.toggle_domobevolution.desc", "srphelp.srparasites.toggle_domobevolution.usage"), new Entry("srphelp.srparasites.resetdatafile.desc", "srphelp.srparasites.resetdatafile.usage"), new Entry("srphelp.srparasites.parasites.desc", "srphelp.srparasites.parasites.usage")));
        TOPICS.put("srpgeneration", Arrays.asList(new Entry("srphelp.srpgeneration.setgeneration.desc", "srphelp.srpgeneration.setgeneration.usage"), new Entry("srphelp.srpgeneration.getgeneration.desc", "srphelp.srpgeneration.getgeneration.usage")));
        TOPICS.put("srpevolution", Arrays.asList(new Entry("srphelp.srpevolution.getphase.desc", "srphelp.srpevolution.getphase.usage"), new Entry("srphelp.srpevolution.addpoints.desc", "srphelp.srpevolution.addpoints.usage"), new Entry("srphelp.srpevolution.setcooldown.desc", "srphelp.srpevolution.setcooldown.usage"), new Entry("srphelp.srpevolution.addcooldown.desc", "srphelp.srpevolution.addcooldown.usage"), new Entry("srphelp.srpevolution.setphase.desc", "srphelp.srpevolution.setphase.usage"), new Entry("srphelp.srpevolution.set_evolutiongaining.desc", "srphelp.srpevolution.set_evolutiongaining.usage"), new Entry("srphelp.srpevolution.set_evolutionloss.desc", "srphelp.srpevolution.set_evolutionloss.usage"), new Entry("srphelp.srpevolution.evolutionlock_getlist.desc", "srphelp.srpevolution.evolutionlock_getlist.usage"), new Entry("srphelp.srpevolution.evolutionlock_reset.desc", "srphelp.srpevolution.evolutionlock_reset.usage"), new Entry("srphelp.srpevolution.evolutionlock_unlockall.desc", "srphelp.srpevolution.evolutionlock_unlockall.usage")));
        TOPICS.put("srpudevelopment", Arrays.asList(new Entry("srphelp.srpudevelopment.getlevel.desc", "srphelp.srpudevelopment.getlevel.usage"), new Entry("srphelp.srpudevelopment.setlevel.desc", "srphelp.srpudevelopment.setlevel.usage"), new Entry("srphelp.srpudevelopment.viewalldims.desc", "srphelp.srpudevelopment.viewalldims.usage")));
        TOPICS.put("srpnodes", Arrays.asList(new Entry("srphelp.srpnodes.viewall.desc", "srphelp.srpnodes.viewall.usage"), new Entry("srphelp.srpnodes.setnode.desc", "srphelp.srpnodes.setnode.usage"), new Entry("srphelp.srpnodes.removenode.desc", "srphelp.srpnodes.removenode.usage"), new Entry("srphelp.srpnodes.clearworld.desc", "srphelp.srpnodes.clearworld.usage")));
        TOPICS.put("srpcolonies", Arrays.asList(new Entry("srphelp.srpcolonies.viewall.desc", "srphelp.srpcolonies.viewall.usage"), new Entry("srphelp.srpcolonies.setcolony.desc", "srphelp.srpcolonies.setcolony.usage"), new Entry("srphelp.srpcolonies.removecolony.desc", "srphelp.srpcolonies.removecolony.usage"), new Entry("srphelp.srpcolonies.clearworld.desc", "srphelp.srpcolonies.clearworld.usage"), new Entry("srphelp.srpcolonies.resetglobaladaptation.desc", "srphelp.srpcolonies.resetglobaladaptation.usage"), new Entry("srphelp.srpcolonies.viewallglobaladaptation.desc", "srphelp.srpcolonies.viewallglobaladaptation.usage")));
        TOPICS.put("srpvectors", Arrays.asList(new Entry("srphelp.srpvectors.viewall.desc", "srphelp.srpvectors.viewall.usage"), new Entry("srphelp.srpvectors.setvector.desc", "srphelp.srpvectors.setvector.usage"), new Entry("srphelp.srpvectors.removevector.desc", "srphelp.srpvectors.removevector.usage"), new Entry("srphelp.srpvectors.clearworld.desc", "srphelp.srpvectors.clearworld.usage")));
        TOPICS.put("srpdislodgment", Arrays.asList(new Entry("srphelp.srpdislodgment.random_code.desc", "srphelp.srpdislodgment.random_code.usage"), new Entry("srphelp.srpdislodgment.set_code.desc", "srphelp.srpdislodgment.set_code.usage"), new Entry("srphelp.srpdislodgment.codes_reset.desc", "srphelp.srpdislodgment.codes_reset.usage")));
        TOPICS.put("srpguide", Arrays.asList(new Entry("srphelp.srpguide.unlockall.desc", "srphelp.srpguide.unlockall.usage"), new Entry("srphelp.srpguide.restore.desc", "srphelp.srpguide.restore.usage"), new Entry("srphelp.srpguide.clearall.desc", "srphelp.srpguide.clearall.usage")));
        TOPICS.put("srpguideclear", Arrays.asList(new Entry("srphelp.srpguideclear.self.desc", "srphelp.srpguideclear.self.usage")));
        TOPICS.put("harlequin_here", Arrays.asList(new Entry("srphelp.harlequin_here.convert.desc", "srphelp.harlequin_here.convert.usage")));
        TOPICS.put("harlequin_convert", Arrays.asList(new Entry("srphelp.harlequin_convert.blotch.desc", "srphelp.harlequin_convert.blotch.usage")));
        TOPICS.put("harlequin_scatter", Arrays.asList(new Entry("srphelp.harlequin_scatter.ruins.desc", "srphelp.harlequin_scatter.ruins.usage")));
        TOPICS.put("conjure", Arrays.asList(new Entry("srphelp.conjure.desc", "srphelp.conjure.usage")));
        TOPICS.put("srpguidistortion", Arrays.asList(new Entry("srphelp.srpguidistortion.desc", "srphelp.srpguidistortion.usage")));
        TOPICS.put("srpbestiarystats", Arrays.asList(new Entry("srphelp.srpbestiarystats.clear.desc", "srphelp.srpbestiarystats.clear.usage")));
        TOPICS.put("srp_breathe", Arrays.asList(new Entry("srphelp.srp_breathe.desc", "srphelp.srp_breathe.usage")));
        TOPICS.put("srp_celestial", Arrays.asList(new Entry("srphelp.srp_celestial.desc", "srphelp.srp_celestial.usage")));
    }

    private static class Entry {
        final String key;
        final String usageKey;

        Entry(String key, String usageKey) {
            this.key = key;
            this.usageKey = usageKey;
        }
    }
}

