import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class bot_Twitter {
    static ConfigurationBuilder cb = new ConfigurationBuilder();

    public static void updateStatus() throws TwitterException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("") // To put yours on between ""
                .setOAuthConsumerSecret("") //To put yours on between ""
                .setOAuthAccessToken("") // To put yours on between ""
                .setOAuthAccessTokenSecret(""); //To put yours on between ""
        try {

            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
            Status status = twitter.updateStatus("test de mise à jour de mon status");
            System.out.println("Successfully updated the status to [" + status.getText() + "].");

            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }
}