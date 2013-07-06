package hashimotonet;

/*
 * Copyright 2007 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//package twitter4j.examples.tweets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;

/**
 * Example application that uses OAuth method to acquire access to your account.<br>
 * This application illustrates how to use OAuth method with Twitter4J.<br>
 * 
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @author Osamu Hashimoto
 */
public final class UpdateStatus {
	/**
	 * Usage: java twitter4j.examples.tweets.UpdateStatus [text]
	 * 
	 * @param args
	 *            message
	 */
	public static void main(String[] args) {
/*
		try {
			new UpdateStatus().connectTwitter();
		} catch(Exception e) {
			e.printStackTrace();
		}
*/
		
		if (args.length < 1) {
			System.out
					.println("Usage: java twitter4j.examples.tweets.UpdateStatus [text]");
			System.exit(-1);
		}
		
		String message = "";
		for(int i = 0 ; i < args.length; i++) {
			message  += args[i] + " "; 
		}
		
		
		try {
			Twitter twitter = new TwitterFactory().getInstance();
			try {
				// get request token.
				// this will throw IllegalStateException if access token is
				// already available
				RequestToken requestToken = twitter.getOAuthRequestToken();

//				RequestToken requestToken = new RequestToken(Globals.KEY,Globals.SECRET);
				String url = requestToken.getAuthenticationURL();
				System.out.println("url = " + url );
				
				System.out.println("Got request token.");
				System.out.println("Request token: " + requestToken.getToken());
				System.out.println("Request token secret: "
						+ requestToken.getTokenSecret());
				AccessToken accessToken = null;

				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				while (null == accessToken) {
					System.out
							.println("Open the following URL and grant access to your account:");
					System.out.println(requestToken.getAuthorizationURL());
					System.out
							.print("Enter the PIN(if available) and hit enter after you granted access.[PIN]:");
					String pin = br.readLine();
					try {
						if (pin.length() > 0) {
							accessToken = twitter.getOAuthAccessToken(
									requestToken, pin);
						} else {
							accessToken = twitter
									.getOAuthAccessToken(requestToken);
						}
					} catch (TwitterException te) {
						if (401 == te.getStatusCode()) {
							System.out
									.println("Unable to get the access token.");
						} else {
							te.printStackTrace();
						}
					}
				}
				System.out.println("Got access token.");
				System.out.println("Access token: " + accessToken.getToken());
				System.out.println("Access token secret: "
						+ accessToken.getTokenSecret());
			} catch (IllegalStateException ie) {
				// access token is already available, or consumer key/secret is
				// not set.
				if (!twitter.getAuthorization().isEnabled()) {
					System.out.println("OAuth consumer key/secret is not set.");
					System.exit(-1);
				}
			}
			Status status = twitter.updateStatus(message);
			System.out.println("Successfully updated the status to ["
					+ status.getText() + "].");
			System.exit(0);
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
			System.exit(-1);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Failed to read the system input.");
			System.exit(-1);
		}
	}
	
	/**
	 * Twitterに接続する
	 * @throws TwitterException 
	 */
	private void connectTwitter() throws TwitterException {
	    // Twitetr4jの設定を読み込む
	    Configuration conf = ConfigurationContext.getInstance();
	    // Oauth認証オブジェクト作成
	    OAuthAuthorization Oauth = new OAuthAuthorization(conf);
	    // Oauth認証オブジェクトにconsumerKeyとconsumerSecretを設定
	    Oauth.setOAuthConsumer(Globals.KEY,
	            Globals.SECRET);
	 
//	    // リクエストトークンの作成
        RequestToken sRequestToken=null;
	    try {
	        sRequestToken= Oauth.getOAuthRequestToken();
	    } catch (TwitterException e) {
	        throw new TwitterException(e.toString());
	    }
	    String url = sRequestToken.getAuthorizationURL();
	    System.out.println("Url = " + url);
	 }	
}
