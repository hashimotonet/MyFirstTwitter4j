package hashimotonet;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class MyTimeline {
	public static void main(String args[]) throws TwitterException{
		 Twitter twitter = new TwitterFactory().getInstance();
		 try {
			 User sp_user = twitter.showUser("hashimotonet");
			 long id = sp_user.getId();
			 ResponseList<Status> tweetList = twitter.getUserTimeline(id, new Paging(1));
			 for (int i=0; i < tweetList.size(); i++) {
				 Status tweet = (Status) tweetList.get(i);
				 System.out.println("ツイート：" + tweet.getText());
				 System.out.println("------------------------------");
			 }
		 } catch (TwitterException e) {
			 e.printStackTrace();
			 System.out.println("特定失敗: " + e.getErrorMessage());
		 }
	}
}
