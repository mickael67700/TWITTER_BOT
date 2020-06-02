import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class bot_Twitter {
    static ConfigurationBuilder cb = new ConfigurationBuilder();

    //Update Status
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

    //Show Status of Users
    public static void showStatus(String user) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("") // To put yours on between ""
                .setOAuthConsumerSecret("") //To put yours on between ""
                .setOAuthAccessToken("") // To put yours on between ""
                .setOAuthAccessTokenSecret(""); //To put yours on between ""
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        int pageno = 1;

        List statuses = new ArrayList();
        while (true) {
            try {
                int size = statuses.size();
                Paging page = new Paging(pageno++, 100);
                twitter.getUserTimeline(user, page).addAll(statuses);
                System.out.println("***********************************************");
                System.out.println("Gathered " + twitter.getUserTimeline(user, page).size() + " tweets");
                ArrayList<String> list = new ArrayList<>();

                //get status dans user
                for (Status status : twitter.getUserTimeline(user, page)) {
                    //System.out.println("*********Place Tweets :**************\npalce country :"+status.getPlace().getCountry()+"\nplace full name :"+status.getPlace().getFullName()+"\nplace name :"+status.getPlace().getName()+"\nplace id :"+status.getPlace().getId()+"\nplace tipe :"+status.getPlace().getPlaceType()+"\nplace addres :"+status.getPlace().getStreetAddress());
                    System.out.println("[" + (pageno++) + ".] " + "Status id : " + status.getId());
                    System.out.println("id user : " + status.getUser().getId());
                    System.out.println("Length status :  " + status.getText().length());
                    System.out.println("@" + status.getUser().getScreenName() + " . " + status.getCreatedAt() + " : " + status.getUser().getName() + "--------" + status.getText());
                    System.out.println("url :" + status.getUser().getURL());
                    System.out.println("Lang :" + status.getLang());
                    System.out.println("Impressions : " + status.getRetweetedStatus());
                    System.out.println("Location : " + status.getGeoLocation());
                    System.out.println("Place : " + status.getPlace());
                    System.out.println("Contributors : " + Arrays.toString(status.getContributors()));
                    System.out.println(status.getCurrentUserRetweetId());

                    list.add(status.getText());
                }
                //Rec File
                try {
                    File ff = new File("results.txt"); // File definition
                    try {
                        ff.createNewFile();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    FileWriter ffw = null;
                    try {
                        ffw = new FileWriter(ff);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    try {
                        assert ffw != null;
                        ffw.write(String.valueOf(list));  //write a line in the results.txt file

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    try {
                        ffw.write("\n"); // Force the line
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    ffw.close(); // Close file
                } catch (Exception ignored) {
                }
                if (statuses.size() == size)
                    break;
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Total: " + statuses.size());
    }

}
