package hashimotonet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.Status;
import twitter4j.TwitterException;

public class SimpleTweet {
    public static void main(String[] args) throws IOException, TwitterException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tweet = "";
        while(tweet.isEmpty()) {
            System.out.print("¡‰½‚µ‚Ä‚é ? : ");
            tweet = br.readLine();
            if(tweet.length() > 140) {
                System.out.println("š”§ŒÀ‚ğ’´‚¦‚Ä‚¢‚Ü‚·B");
                tweet = "";
                continue;
            }
        }

        Twitter twitter = new TwitterFactory().getInstance();
        Status status = twitter.updateStatus(tweet);
        System.out.println(status.getUser().getScreenName() + " ‚Æ‚µ‚Ä“Še‚µ‚Ü‚µ‚½ : " + status.getText());

        br.close();
    }
}
