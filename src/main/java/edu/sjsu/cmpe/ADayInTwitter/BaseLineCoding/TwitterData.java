package edu.sjsu.cmpe.ADayInTwitter.BaseLineCoding;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.UnknownHostException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * The Class FindTest.
 */
/**
 * @author Savani Bharat
 * 
 */
public class TwitterData {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws UnknownHostException
	 *             the unknown host exception
	 * @throws JSONException
	 * @throws Exception
	 */
	public static void main(String[] args) throws UnknownHostException,
			JSONException, Exception {

		MongoClient client = new MongoClient();
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				"D:\\Twitter Files\\MasterTweetsFile.txt")));
		DB db = client.getDB("twitter");
		DBCollection collection = db.getCollection("tweets");

		BasicDBObject query = new BasicDBObject();
		BasicDBObject fields = new BasicDBObject("text", 1)
				.append("_id", false).append("user.name", 1)
				.append("user.friends_count", 1).append("user.followers_count", 1)
				.append("retweet_count", 1).append("created_at", 1).append("user.lang",1);
		DBCursor cursor = collection.find(query, fields);
		int i = 0;
		try {
			while (cursor.hasNext()) {
				DBObject cur = cursor.next();
				//System.out.println(cur.toString());
				JSONObject json=new JSONObject(cur.toString());
				String text=json.getString("text");
				String retweet_count=json.getString("retweet_count");
				String createdAt=json.getString("created_at");
				
				String []exactTime=createdAt.split(" ");
				
				JSONObject subDoc=json.getJSONObject("user");
				String followers_count=subDoc.getString("followers_count");
				String friends_count=subDoc.getString("friends_count");
				String name=subDoc.getString("name");
				String lang=subDoc.getString("lang");
				i++;
				if(lang.equals("en")){
					
					String writeToFile=text+"|"+retweet_count+"|"+exactTime[3]+"|"+followers_count+"|"+friends_count+"|"+name+"|"+lang+"\n";
					bw.write(writeToFile);
				}
				if(i%100000==0)
					System.out.println(i+" done");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
			bw.close();
		}
	}
}