package edu.sjsu.cmpe.ADayInTwitter.VariousMetrics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PresentTotalTweetsByHours {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader(new File(
				"D:\\Twitter Files\\TimeAndDate.txt")));

		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				"D:\\Twitter Files\\TotalTweetsByHours.txt")));

		Map<String, Integer> tweetsPerHour = new LinkedHashMap<String, Integer>();

		// text+"|"+retweet_count+"|"+exactTime[3]+"|"+followers_count+"|"+friends_count+"|"+name+"|"+lang+"\n";
		// 0 1 2 3 4 5 6
		String str = null;
		try {
			while ((str = br.readLine()) != null) {

				// Mon Sep 16 05:34:13
				// Sun Sep 15 20:04:03
				String[] date = str.split(" ");
				
				
				String hour = date[3].split(":")[0];

				if (tweetsPerHour.containsKey(date[0] + " " + date[2]
						+ " " + hour))
					tweetsPerHour.put(
							date[0] + " " + date[2] + " " + hour,
							tweetsPerHour.get(date[0] + " " + date[2]
									+ " " + hour) + 1);
				else
					tweetsPerHour.put(date[0] + " " + date[2] + " "
							+ hour, 1);

			}
			System.out.println(tweetsPerHour);
			Thread.sleep(100);

			
			for (Entry<String, Integer> entry : tweetsPerHour
					.entrySet()) {

				bw.write(entry.getKey() + " " + entry.getValue() + "\n");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bw.close();
			br.close();
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
			/*
			 * CAUTION :: return o2.getValue().compareTo(o1.getValue()); means
			 * descending order sorting return
			 * o1.getValue().compareTo(o1.getValue()); means ascending order
			 * sorting
			 */
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

