package edu.sjsu.cmpe.ADayInTwitter.MongoConnection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

/**
 * @author Savani Bharat
 * 
 */
public class MongoAndRest {

	public static BufferedReader br = null;
	String[] configDetails = new String[6];
	public static DB db = null;
	public static DBCollection collection = null;
	public static DBCursor cursor = null;

	public MongoAndRest() throws Exception {

		try {
			br = new BufferedReader(new FileReader(new File(
					"Files\\DBConfigurations")));
			String str = null;

			/*
			 * dbName: twitter i=0 dbCollection: tweets i=1 databaseUsername :
			 * twitterdata i=2 databasePassword : twitterdata i=3
			 * databaseAddress : ds037997.mongolab.com i=4 databasePort : 37997
			 * i=5
			 */
			int i = 0;
			while ((str = br.readLine()) != null) {

				String data = str.split(" : ")[1];
				configDetails[i] = data;
				System.out.println(configDetails[i]);
				i++;

			}
			int port = Integer.parseInt(String.valueOf(configDetails[5]));
			MongoClient client = new MongoClient(new ServerAddress(
					configDetails[4], port));

			db = client.getDB(configDetails[0]);
			collection = db.getCollection("tweets");

			String username = configDetails[2];
			String pwd = configDetails[3];
			char[] password = pwd.toCharArray();
			@SuppressWarnings("unused")
			boolean auth = db.authenticate(username, password);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			br.close();
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {

		MongoAndRest m = new MongoAndRest();

		// pushHashTagsOrdered();
		// pushBetterMostPopularWords();
		//pushHigeshFollowers();
		//pushHighestFriends();
		//pushHighestTweets();
		//pushTotalTweetsByHours();
		//pushMostBussiestHourInADay();
	}

	public static void pushHashTagsOrdered() throws Exception {

		collection = db.getCollection("HashTagsOrdered");

		try {
			br = new BufferedReader(new FileReader(new File(
					"D:\\Twitter Files\\HashTagsOrdered.txt")));

			String str = null;
			int count = 0;
			BasicDBObject entry = new BasicDBObject();

			while ((str = br.readLine()) != null && count <= 10) {
				count++;
				String newStr[] = str.split(" ");
				entry.put(newStr[0], newStr[1]);
				count++;
			}

			//collection.drop();
			collection.insert(entry);
			cursor = collection.find();

			while (cursor.hasNext()) {
				DBObject doc = cursor.next();
				System.out.println(doc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
			br.close();
		}
	}

	public static void pushBetterMostPopularWords() throws Exception {

		collection = db.getCollection("BetterMostPopularWords");

		try {
			br = new BufferedReader(new FileReader(new File(
					"D:\\Twitter Files\\BetterMostPopularWords.txt")));

			String str = null;
			int count = 0;
			BasicDBObject entry = new BasicDBObject();

			while ((str = br.readLine()) != null && count <= 10) {
				count++;
				String newStr[] = str.split(" ");
				entry.put(newStr[0], newStr[1]);
				count++;
			}

			collection.insert(entry);
			cursor = collection.find();

			while (cursor.hasNext()) {
				DBObject doc = cursor.next();
				System.out.println(doc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
			br.close();
		}

	}

	public static void pushHigeshFollowers() throws Exception {

		collection = db.getCollection("HighestFollowers");

		try {
			br = new BufferedReader(new FileReader(new File(
					"D:\\Twitter Files\\HighestFollowers.txt")));

			String str = null;
			int count = 0;
			BasicDBObject entry = new BasicDBObject();

			while ((str = br.readLine()) != null && count <= 10) {

				String newStr[] = str.split(" ");
				entry.put(newStr[0], newStr[1]);
				count++;
			}

			collection.insert(entry);
			cursor = collection.find();

			while (cursor.hasNext()) {
				DBObject doc = cursor.next();
				System.out.println(doc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
			br.close();
		}

	}
	
	public static void pushHighestFriends() throws Exception{
		
		collection = db.getCollection("HighestFriends");

		try {
			br = new BufferedReader(new FileReader(new File(
					"D:\\Twitter Files\\HighestFriends.txt")));

			String str = null;
			int count = 0;
			BasicDBObject entry = new BasicDBObject();

			while ((str = br.readLine()) != null && count <= 10) {

				String newStr[] = str.split(" ");
				entry.put(newStr[0], newStr[1]);
				count++;
			}

			collection.insert(entry);
			cursor = collection.find();

			while (cursor.hasNext()) {
				DBObject doc = cursor.next();
				System.out.println(doc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
			br.close();
		}
		
	}
	public static void pushHighestTweets() throws Exception{
		
		collection = db.getCollection("HighestTweets");

		try {
			br = new BufferedReader(new FileReader(new File(
					"D:\\Twitter Files\\HighestTweets.txt")));

			String str = null;
			int count = 0;
			BasicDBObject entry = new BasicDBObject();

			while ((str = br.readLine()) != null && count <= 10) {

				String newStr[] = str.split(" ");
				entry.put(newStr[0], newStr[1]);
				count++;
			}

			collection.insert(entry);
			cursor = collection.find();

			while (cursor.hasNext()) {
				DBObject doc = cursor.next();
				System.out.println(doc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
			br.close();
		}
		
	}
	
	public static void pushTotalTweetsByHours() throws Exception{
		
		collection = db.getCollection("TotalTweetsByHours");

		try {
			br = new BufferedReader(new FileReader(new File(
					"D:\\Twitter Files\\TotalTweetsByHours.txt")));

			String str = null;
			BasicDBObject entry = new BasicDBObject();

			while ((str = br.readLine()) != null) {

				String newStr[] = str.split(" ");
				entry.put(newStr[0]+" "+newStr[1]+" "+newStr[2], newStr[3]);
			}

			collection.insert(entry);
			cursor = collection.find();

			while (cursor.hasNext()) {
				DBObject doc = cursor.next();
				System.out.println(doc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
			br.close();
		}
		
	}
	
	public static void pushMostBussiestHourInADay() throws Exception{
		
		collection = db.getCollection("MostBussiestHourInADay");

		try {
			br = new BufferedReader(new FileReader(new File(
					"D:\\Twitter Files\\MostBussiestHourInADay.txt")));

			String str = null;
			
			BasicDBObject entry = new BasicDBObject();

			while ((str = br.readLine()) != null) {

				String newStr[] = str.split(" ");
				entry.put(newStr[0]+" "+newStr[1]+" "+newStr[2], newStr[3]);
			}

			collection.insert(entry);
			cursor = collection.find();

			while (cursor.hasNext()) {
				DBObject doc = cursor.next();
				System.out.println(doc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
			br.close();
		}
	}
}
