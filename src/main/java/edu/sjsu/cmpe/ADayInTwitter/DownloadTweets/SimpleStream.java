package edu.sjsu.cmpe.ADayInTwitter.DownloadTweets;

import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class SimpleStream {

	public static MongoClient client = null;
	public static DB db = null;
	public static DBCollection collection = null;
	public static BasicDBObject entry = null;

	public SimpleStream() throws Exception {

		client = new MongoClient("localhost", 27017);

		db = client.getDB("twitter");
		
		collection = db.getCollection("tweet_stream");
		

	}

	public static void main(String[] args) throws Exception {

		SimpleStream s = new SimpleStream();

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey("C4aImwuTdLyKv5Cc9EDLg");
		cb.setOAuthConsumerSecret("YNvSq21V38v1M0vggTGG39qg4lLPOqBFwa3sOSYS4");
		cb.setOAuthAccessToken("526143868-k8wL5wHqLIyqFkUTMZWJJgm1PRD18aUtQkca5xkH");
		cb.setOAuthAccessTokenSecret("7AfabemgNxCD07D3LXcfydgumC5Avl6OZL5v1azh7Cfxg");

		TwitterStream twitterStream = new TwitterStreamFactory(cb.build())
				.getInstance();

		StatusListener listener = new StatusListener() {

			@Override
			public void onException(Exception arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStatus(Status status) {

				/*
				 * text retweet_count user.followers_count user.friends_count
				 * created_at
				 */

				// gets Username

				/*
				 * String username = status.getUser().getScreenName();
				 * System.out.println(username);
				 * System.out.println("-----------------------------"
				 * +status.getUser().getName()); String profileLocation =
				 * user.getLocation(); System.out.println(profileLocation); long
				 * tweetId = status.getId();
				 * 
				 * System.out.println(tweetId); String content =
				 * status.getText(); System.out.println(content +"\n");
				 */

				User user = status.getUser();
				/*String username = status.getUser().getName();// name
				String location = user.getLocation();// location
				String content = status.getText();// tweet
				int followers_count = status.getUser().getFollowersCount();
				int friends_count = status.getUser().getFriendsCount();
				Date created_at = status.getCreatedAt();
				long retweet_count = status.getRetweetCount();*/
				
				entry = new BasicDBObject();
					entry.append("username", status.getUser().getName());
					entry.append("location", user.getLocation());
					entry.append("content", status.getText());
					entry.append("followers_count", status.getUser().getFollowersCount());
					entry.append("friends_count", status.getUser().getFriendsCount());
					entry.append("created_at", status.getCreatedAt());
					entry.append("retweet_count", status.getRetweetCount());
					entry.append("lang", status.getUser().getLang());
					collection.insert(entry);
					//System.out.println("name");
					entry=null;
					
			}

			@Override
			public void onTrackLimitationNotice(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub

			}

		};
		FilterQuery fq = new FilterQuery();

		String keywords[] = { "Ireland,india,USA,USSR,U.K,Brazil,Japan,Indonesia,Mexico,Canada,Spain,Philippines" };

		fq.track(keywords);

		twitterStream.addListener(listener);
		twitterStream.filter(fq);

	}
}