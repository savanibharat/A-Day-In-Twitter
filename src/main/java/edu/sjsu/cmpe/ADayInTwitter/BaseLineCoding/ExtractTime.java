package edu.sjsu.cmpe.ADayInTwitter.BaseLineCoding;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

// TODO: Auto-generated Javadoc
/**
 * The Class ExtractTime.
 */
public class ExtractTime {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		
		MongoClient client = new MongoClient();
		BufferedWriter bw=new BufferedWriter(new FileWriter(new File("D:\\Twitter Files\\TweetsTime.txt")));
		DB db = client.getDB("twitter");
		DBCollection collection = db.getCollection("tweets");

		BasicDBObject query=new BasicDBObject();
		BasicDBObject fields=new BasicDBObject("created_at",1).append("_id", false);
		DBCursor cursor=collection.find(query,fields);
		int i=0;
		try {
			while (cursor.hasNext()) {
				DBObject cur = cursor.next();
				bw.write(cur+"\n");
				i++;
				if(i%100000==0)
					System.out.println("records done "+i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
			bw.close();
		}
		
	}
}