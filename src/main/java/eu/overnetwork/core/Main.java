package eu.overnetwork.core;

import eu.overnetwork.database.DatabaseImpl;
import eu.overnetwork.events.guild.MemberJoin;
import eu.overnetwork.events.guild.MemberLeave;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static Dotenv dotenv = Dotenv.load();
    private static JDA jda;
    public static List<ListenerAdapter> listeneradapter = new ArrayList<>();


    public static void main(String[] args) throws LoginException {

        JDABuilder builder = JDABuilder.createDefault(dotenv.get("TOKEN"));

        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.enableCache(CacheFlag.VOICE_STATE);

        List<GatewayIntent> intents = new ArrayList<>(
                Arrays.asList(GatewayIntent.DIRECT_MESSAGE_REACTIONS,
                        GatewayIntent.DIRECT_MESSAGE_TYPING,
                        GatewayIntent.DIRECT_MESSAGES,
                        GatewayIntent.GUILD_BANS,
                        GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                        GatewayIntent.GUILD_INVITES,
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                        GatewayIntent.GUILD_MESSAGE_TYPING,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_PRESENCES,
                        GatewayIntent.GUILD_VOICE_STATES,
                        GatewayIntent.GUILD_WEBHOOKS,
                        GatewayIntent.MESSAGE_CONTENT)
        );

        builder.enableIntents(intents);
        builder.setActivity(Activity.watching("installation process"));
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);


        builder.addEventListeners(new MemberJoin(DatabaseImpl.getDatabase()));
        builder.addEventListeners(new MemberLeave(DatabaseImpl.getDatabase()));
        try {
            jda = builder.build();

            jda.awaitReady();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void onShutdown() {
        System.out.println("Shutting down...");
        jda.shutdown();
    }

    private static void Listener() {
        for (ListenerAdapter ad:Main.listeneradapter) {
            jda.addEventListener(ad);
            System.out.println("Listener added: " + ad.getClass().getName());
        }
    }
}
