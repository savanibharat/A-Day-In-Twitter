package edu.sjsu.cmpe.ADayInTwitter;

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
 * 
 * Present the top 5 hash tags for the complete period
 * 
*/
public class Top5HashTagsOfCompleteTime  {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception{
		
		long start=System.currentTimeMillis();
		
		BufferedReader br = new BufferedReader(new FileReader(new File(
				"D:\\Twitter Files\\MasterTweetsFile.txt")));
		
		BufferedWriter writeHashTags = new BufferedWriter(new FileWriter(new File(
				"D:\\Twitter Files\\HashTagsOrdered.txt")));
		
		BufferedWriter writeTweetWords=new BufferedWriter(new FileWriter(new File("D:\\Twitter Files\\TweetWords.txt")));
		
		String str = null;
		Map<String, Integer> hashTags = new HashMap<String, Integer>();
		Map<String, Integer> tweetWord= new HashMap<String, Integer>();
		
		int lines=0;
		while ((str = br.readLine()) != null) {
			lines++;
/*if you use split("|") then it will give u all characters example
			Weekly calender.split("|")
			output is
			W
			e
			e
			k
			l
			y
			
			c
			a
			l
			e
			n
			d
			e
			r
*/			
			if(str==" ")
				continue;
			if(str.equals("||"))
				continue;
			System.out.println(lines);
			String tweet = str.split("\\|")[0];
			if(tweet.length()!=0){
			//System.out.println(tweet);
			String[] tweetWords=tweet.split(" ");
			
			//putting all words of tweets in hashmap then we will sort then by values
			for (int i = 0; i < tweetWords.length; i++) {
				
				if(tweetWord.containsKey(tweetWords[i])){
					tweetWord.put(tweetWords[i], tweetWord.get(tweetWords[i])+1);
				}
				else{
					tweetWord.put(tweetWords[i], 1);
				}
				if(tweetWords[i].contains("#")){
					
					if(hashTags.containsKey(tweetWords[i])){
						hashTags.put( tweetWords[i] , hashTags.get(tweetWords[i]) + 1 );
					}
					else{
						hashTags.put( tweetWords[i] , 1 );
					}
					//System.out.println(hashTags);
				}
			}
			if(lines%100000==0){
				System.out.println("Total reconds processed are "+lines);
			}
		}
		}
			/*for (int i = 0; i < newStr.length; i++) {

				sortWords.put(newStr[i],
						Integer.parseInt(String.valueOf(newStr[1])));
			}
		}*/
		
		//Sort HashTags in Ordered Way
		Map<String, Integer> hashTag = sortByValues(hashTags);
		for (Map.Entry<String, Integer> entry : hashTag.entrySet()) {
			writeHashTags.write(entry.getKey()+" "+entry.getValue()+"\n");
		}
		
		//Sort words in Ordered Way
		Map<String, Integer> words = sortByValues(tweetWord);
		for (Map.Entry<String, Integer> entry : words.entrySet()) {
			writeTweetWords.write(entry.getKey()+" "+entry.getValue()+"\n");
		}
		long end=System.currentTimeMillis();
		System.out.println("Total time to run this program in milliseconds is "+(end-start));
		System.out.println("Total time to run this program in seconds is "+((end-start)/1000));
	}

	/**
	 * Sort by values.
	 *
	 * @param <K> the key type
	 * @param <V> the value type
	 * @param map the map
	 * @return the map
	 */
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
/*
Total time to run this program in milliseconds is 48433
Total time to run this program in seconds is 48
 */
