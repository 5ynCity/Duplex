package me.syn.duplex.compat;

import io.netty.channel.Channel;
import org.bukkit.entity.Player;

public interface Internals {
    Channel getChannel(Player player);
}
