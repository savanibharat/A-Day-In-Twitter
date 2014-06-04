package edu.sjsu.cmpe.ADayInTwitter;

import org.codehaus.jettison.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * Unit test for simple App.
 */
public class AppTest {

	public static void main(String[] args) throws Exception {

		MongoClient client = new MongoClient("localhost", 27017);
		DB db = client.getDB("anil");
		DBCollection collection = db.getCollection("anilkumar");
		
		BasicDBObject query = new BasicDBObject("place","2km WSW of Imperial, California");
		BasicDBObject fields=new BasicDBObject("_id", false).append("place", 1);
		
		DBCursor cursor = collection.find(query,fields);
		
		
		try {

			while (cursor.hasNext()) {

				DBObject data = cursor.next();
				System.out.println(data);
				String str=data.toString();
				String newStr=str.split("\" : \"")[1];//{ "place" : "2km WSW of Imperial, California"}
				newStr=newStr.substring(0,newStr.length()-2);
				System.out.println("proper address is "+newStr);
				
				JSONObject json=new JSONObject(data.toString());
				System.out.println("using jsonobject   "+json.get("place"));
				
			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			cursor.close();

		}
	}

}
