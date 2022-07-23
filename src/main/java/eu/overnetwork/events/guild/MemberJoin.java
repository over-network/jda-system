package eu.overnetwork.events.guild;

import eu.overnetwork.database.Database;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class MemberJoin extends ListenerAdapter {

    private Database database;
    

    

    public MemberJoin(Database database){
        this.database = database;
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {

        database.createUser(event.getGuild().getId(), event.getUser().getId());

        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Loading user data...");
        embed.setColor(Color.CYAN);
        embed.setDescription("Please wait while we load your data...");


        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Over-Network", "https://raw.githubusercontent.com/mrtuxa/bot-images/main/unknown.png");
        eb.setDescription("Solltest du noch keinen Account habe kannst du dich unter https://panel.over-network.eu/register registrieren.");
        eb.addField("Herzlich Willkommen " + event.getUser().getName(), "Du bist nun in der Over-Network-Guilde beigetreten, bitte verifiziere dich in <#989851509686693908>", false);
        eb.setThumbnail(event.getUser().getAvatarUrl());
        eb.setColor(Color.WHITE);
        eb.setFooter("Over-Network", "https://raw.githubusercontent.com/mrtuxa/bot-images/main/unknown.png");

        TextChannel channel = event.getGuild().getTextChannelById("987700321155448872");

        channel.sendMessageEmbeds(embed.build()).queue(MessageToBeEdited -> {
            MessageToBeEdited.editMessageEmbeds(eb.build()).queueAfter(5, TimeUnit.SECONDS);
        });


        
    }
}
