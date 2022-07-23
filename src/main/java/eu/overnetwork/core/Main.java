package eu.overnetwork.core;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class Main {

    public static final Dotenv dotenv = Dotenv.load();
    private final JDA jda;

    public Main() throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(dotenv.get("TOKEN"));
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.setActivity(Activity.watching("installation process"));
        jda = builder.build();

        Guild guild = jda.getGuildById("863530982460489739");

        if (guild != null) {
            guild.updateCommands().addCommands(
            Commands.slash("create-verify-embed", "Creates the verify embed")
        ).queue();
        }
    }


    public static void main(String[] args) throws LoginException {
        try {
            Main api = new Main();
        } catch (LoginException e) {
            e.printStackTrace();
            throw new LoginException();
        }
    }
}
