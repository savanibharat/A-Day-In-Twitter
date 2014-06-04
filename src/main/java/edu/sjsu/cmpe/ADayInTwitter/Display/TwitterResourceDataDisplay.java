package edu.sjsu.cmpe.ADayInTwitter.Display;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.yammer.metrics.annotation.Timed;

// TODO: Auto-generated Javadoc
/**
 * The Class TwitterResourceDataDisplay.
 */
@Path("v1/documents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TwitterResourceDataDisplay {

	
	
	/** The config details. */
	String[] configDetails = new String[6];
	
	/** The db. */
	public static DB db = null;
	
	/** The collection. */
	public static DBCollection collection = null;
	
	/** The cursor. */
	public static DBCursor cursor = null;

	/**
	 * Instantiates a new twitter resource data display.
	 *
	 * @throws Exception the exception
	 */
	public TwitterResourceDataDisplay() throws Exception {

		try {
			

			/*
			 * dbName: twitter i=0 
			 * dbCollection: tweets i=1 
			 * databaseUsername :twitterdata i=2 
			 * databasePassword : twitterdata i=3
			 * databaseAddress : ds037997.mongolab.com i=4 
			 * databasePort : 37997
			 * i=5
			 */
			
			MongoClient client = new MongoClient(new ServerAddress(
					"ds037997.mongolab.com", 37997));

			db = client.getDB("twitter");
			collection = db.getCollection("tweets");

			String username = "twitterdata";
			String pwd = "twitterdata";
			char[] password = pwd.toCharArray();
			@SuppressWarnings("unused")
			boolean auth = db.authenticate(username, password);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	/**
	 * Gets the hash tags ordered.
	 *
	 * @return the hash tags ordered
	 * @throws Exception the exception
	 */
	@GET
	@Path("/hashtagsordered")
	@Timed(name = "hashtags-ordered")
	public static String getHashTagsOrdered() throws Exception {

		collection = db.getCollection("HashTagsOrdered");

		try {

			BasicDBObject entry = new BasicDBObject();
			BasicDBObject fields = new BasicDBObject("_id", false);

			cursor = collection.find(entry, fields);
			while (cursor.hasNext()) {
				DBObject doc = cursor.next();

				return doc.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return "{ ERROR: error in processing url }";
	}

	/**
	 * Gets the better most popular words.
	 *
	 * @return the better most popular words
	 * @throws Exception the exception
	 */
	@GET
	@Path("/popularwords")
	@Timed(name = "popular-words")
	public static String getBetterMostPopularWords() throws Exception {

		collection = db.getCollection("BetterMostPopularWords");

		try {
			BasicDBObject entry = new BasicDBObject();
			BasicDBObject fields = new BasicDBObject("_id", false);

			cursor = collection.find(entry, fields);
			while (cursor.hasNext()) {
				DBObject doc = cursor.next();

				return doc.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return "{ ERROR: error in processing url }";

	}

	/**
	 * Gets the higesh followers.
	 *
	 * @return the higesh followers
	 * @throws Exception the exception
	 */
	@GET
	@Path("/highestfollowers")
	@Timed(name = "highest-followers")
	public static String getHigeshFollowers() throws Exception {

		collection = db.getCollection("HighestFollowers");

		try {

			BasicDBObject entry = new BasicDBObject();
			BasicDBObject fields = new BasicDBObject("_id", false);

			cursor = collection.find(entry, fields);
			while (cursor.hasNext()) {
				DBObject doc = cursor.next();

				return doc.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return "{ ERROR: error in processing url }";

	}

	/**
	 * Gets the highest friends.
	 *
	 * @return the highest friends
	 * @throws Exception the exception
	 */
	@GET
	@Path("/highestfriends")
	@Timed(name = "highest-friends")
	public static String getHighestFriends() throws Exception {

		collection = db.getCollection("HighestFriends");

		try {

			BasicDBObject entry = new BasicDBObject();
			BasicDBObject fields = new BasicDBObject("_id", false);

			cursor = collection.find(entry, fields);

			while (cursor.hasNext()) {
				DBObject doc = cursor.next();
				return doc.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return "{ ERROR: error in processing url }";

	}

	/**
	 * Push highest tweets.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
	@GET
	@Path("/highesttweets")
	@Timed(name = "highest-tweets")
	public static String pushHighestTweets() throws Exception {

		collection = db.getCollection("HighestTweets");

		try {

			BasicDBObject entry = new BasicDBObject();
			BasicDBObject fields = new BasicDBObject("_id", false);
			cursor = collection.find(entry, fields);

			while (cursor.hasNext()) {
				DBObject doc = cursor.next();
				return doc.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return "{ ERROR: error in processing url }";

	}

	/**
	 * Push total tweets by hours.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
	@GET
	@Path("/tweetsbyhour")
	@Timed(name = "tweets-hour")
	public static String pushTotalTweetsByHours() throws Exception {
		try {
			collection = db.getCollection("TotalTweetsByHours");

			BasicDBObject entry = new BasicDBObject();

			BasicDBObject fields = new BasicDBObject("_id", false);
			cursor = collection.find(entry, fields);

			while (cursor.hasNext()) {
				DBObject doc = cursor.next();
				return doc.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return "{ ERROR: error in processing url }";

	}

	/**
	 * Push most bussiest hour in a day.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
	@GET
	@Path("/bussiesthour")
	@Timed(name = "bussiest-hour")
	public static String pushMostBussiestHourInADay() throws Exception {

		collection = db.getCollection("MostBussiestHourInADay");

		try {
			BasicDBObject entry = new BasicDBObject();
			BasicDBObject fields = new BasicDBObject("_id", false);
			cursor = collection.find(entry, fields);

			while (cursor.hasNext()) {
				DBObject doc = cursor.next();
				return doc.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return "{ ERROR: error in processing url }";

	}

}
