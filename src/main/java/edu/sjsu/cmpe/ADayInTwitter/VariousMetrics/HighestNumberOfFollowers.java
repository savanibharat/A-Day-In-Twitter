package edu.sjsu.cmpe.ADayInTwitter.VariousMetrics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*
 * Top 50 users with the highest number of followers
 *
 * */

public class HighestNumberOfFollowers {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new FileReader(new File(
				"D:\\Twitter Files\\MasterTweetsFile.txt")));
		
		BufferedWriter bw=new BufferedWriter(new FileWriter(new File("D:\\Twitter Files\\HighestFollowers.txt")));
		
		Map<String, Integer> followersCount=new HashMap<String,Integer>();
		
		//text+"|"+retweet_count+"|"+exactTime[3]+"|"+followers_count+"|"+friends_count+"|"+name+"|"+lang+"\n";
		//  0          1                2                    3                   4           5         6    
		String str=null;
		try{
		while((str=br.readLine())!=null){
			
			
			if(str==" ")
				continue;
			if(str.equals("||"))
				continue;
			String newStr[]=str.split("\\|");
			if(newStr.length>6 && newStr[3].length()<6 && newStr[3].length()>0 && newStr[3].matches("[0-9]+")){
				
			
				followersCount.put(newStr[5], Integer.parseInt(String.valueOf(newStr[3])));
			}
		}
		int count=0;
		Map<String,Integer> sortedFollowersCount=sortByValues(followersCount);
		for (Entry<String, Integer> entry : sortedFollowersCount.entrySet()) {
			
			if(count<50){
			bw.write(entry.getKey()+" "+entry.getValue()+"\n");
			count++;
			}
			else{
				break;
			}
			
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			bw.close();br.close();
		}
		
		
	}
	@SuppressWarnings("rawtypes")
	public static <K extends Comparable, V extends Comparable> Map<K, V> sortByValues(
			Map<K, V> map) {
		List<Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K, V>>(
				map.entrySet());

		Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {

			@SuppressWarnings("unchecked")
			@Override
			/*CAUTION ::
			 * return o2.getValue().compareTo(o1.getValue()); means descending order sorting
			 * return o1.getValue().compareTo(o1.getValue()); means ascending order sorting
			 * */
			public int compare(Entry<K, V> o1, Entry<K, V> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		// LinkedHashMap will keep the keys in the order they are inserted
		// which is currently sorted on natural ordering
		Map<K, V> sortedMap = new LinkedHashMap<K, V>();

		for (Map.Entry<K, V> entry : entries) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}
}
