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

// TODO: Auto-generated Javadoc
/**
 * The Class GetDatesAndTime.
 */
public class GetDatesAndTime {


	/**
	 * The main method.
	 *
	 * @param args            the arguments
	 * @throws UnknownHostException             the unknown host exception
	 * @throws JSONException the JSON exception
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws UnknownHostException,
			JSONException, Exception {

		BufferedWriter bw=new BufferedWriter(new FileWriter(new File("D:\\Twitter Files\\TimeAndDate.txt")));
		
		MongoClient client = new MongoClient();
		
		DB db = client.getDB("twitter");
		DBCollection collection = db.getCollection("tweets");

		BasicDBObject query = new BasicDBObject();
		BasicDBObject fields = new BasicDBObject("created_at", 1)
				.append("_id", false);
		DBCursor cursor = collection.find(query, fields);
		int i = 0;
		try {
			while (cursor.hasNext()) {
				DBObject cur = cursor.next();

				JSONObject json=new JSONObject(cur.toString());
				String dateAndTime=json.getString("created_at");
				//System.out.println(dateAndTime);
				
				String[] data=dateAndTime.split(" ");
				//System.out.println(data[0]+data[1]+data[2]+data[3]);
				bw.write(data[0]+" "+data[1]+" "+data[2]+" "+data[3]+"\n");
				
				i++;
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