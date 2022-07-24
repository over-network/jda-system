package eu.overnetwork.events.guild.template;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.io.File;

public class VerifyEmbed extends ListenerAdapter {


    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equalsIgnoreCase("create-verify-embed")) {

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Over-Network System", "https://raw.githubusercontent.com/mrtuxa/bot-images/main/unknown.png");
            embed.setDescription("**Verifizierung**");
            embed.addField("Nutze bitte den unstenstehenden Befehl um den Rang zu erhalten", ":white_check_mark: Member `/verify`", false);
            embed.setColor(Color.WHITE);
            embed.setFooter("Over-Network", "https://raw.githubusercontent.com/mrtuxa/bot-images/main/unknown.png");

            event.getChannel().sendMessageEmbeds(embed.build()).addFile(new File("../resources/verify.gif")).queue();
        }
    }
}
