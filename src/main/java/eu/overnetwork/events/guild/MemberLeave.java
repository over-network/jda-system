package eu.overnetwork.events.guild;

import eu.overnetwork.core.Main;
import eu.overnetwork.database.Database;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MemberLeave extends ListenerAdapter {

    private Database database;

    public MemberLeave() {
        Main.listeneradapter.add(this);
    }

    public MemberLeave(Database database) {
        this.database = database;
    }


    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {
        database.deleteUser(event.getGuild().getId(), event.getUser().getId());
        TextChannel channel = event.getGuild().getTextChannelById("987683172118593556");

        channel.sendMessage("Der User " + event.getUser().getName() + " hat den Server verlassen.").queue();
    }
}
