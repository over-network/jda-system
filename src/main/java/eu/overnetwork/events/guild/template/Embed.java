package eu.overnetwork.events.guild.template;

import net.dv8tion.jda.api.EmbedBuilder;

public interface Embed {

    default void setTitle(EmbedBuilder embed) {
        embed.setTitle("Over-Network System", "https://raw.githubusercontent.com/mrtuxa/bot-images/main/unknown.png");
    }

    default void setFooter(EmbedBuilder embed) {
        embed.setFooter("Over-Network", "https://raw.githubusercontent.com/mrtuxa/bot-images/main/unknown.png");
    }
}
