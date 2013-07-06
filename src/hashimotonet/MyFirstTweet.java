package hashimotonet;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class MyFirstTweet {
	private void output() throws TwitterException  {
		// このファクトリインスタンスは再利用可能でスレッドセーフです
	    Twitter twitter = TwitterFactory.getSingleton();
	    Status status = twitter.updateStatus("laststatus");
	    System.out.println("Successfully updated the status to [" + status.getText() + "].");
	}
	
	private void getTimeline() throws Exception {
	    // このファクトリインスタンスは再利用可能でスレッドセーフです
	    Twitter twitter = TwitterFactory.getSingleton();
	    List<Status> statuses = twitter.getHomeTimeline();
	    System.out.println("Showing home timeline.");
	    for (Status status : statuses) {
	        System.out.println(status.getUser().getName() + ":" +
	                           status.getText());
	    }
	}
	
	public static void main(String args[]) {
		MyFirstTweet tweet = new MyFirstTweet();

		try {
			tweet.getTimeline();
			tweet.output();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
