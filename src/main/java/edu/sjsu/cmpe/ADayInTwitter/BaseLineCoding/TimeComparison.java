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
 * The Class TimeComparison.
 */
public class TimeComparison {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		
		//Sun Sep 15 05:27:28
		
		/*SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		Date date=sdf.parse("05:27:28");
		System.out.println(sdf.format(date));
		*/
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
				//cur will have this thing in it { "created_at" : "Sun Sep 15 05:27:18 +0000 2013"}
				//our interest is Sun Sep 15 05:27:18 2013
				String properDate[]=cur.toString().split(" ");
				String day=properDate[3].replaceAll("\\W", "");
				System.out.println(day);
				System.out.println(properDate[6]);
				System.out.println(properDate[8].replaceAll("\\W", ""));
				
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
			bw.close();
		}
		
	}
}
