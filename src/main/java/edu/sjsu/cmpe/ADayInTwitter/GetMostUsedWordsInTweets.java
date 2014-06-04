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

// TODO: Auto-generated Javadoc
/**
 * The Class GetMostUsedWordsInTweets.
 *
 * @author Savani Bharat
 */
public class GetMostUsedWordsInTweets {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	@SuppressWarnings({ "resource" })
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader(new File(
				"D:\\Twitter Files\\TweetsHistogram.txt")));

		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				"D:\\Twitter Files\\TweetsHistogramOrdered.txt")));

		String str = null;
		Map<String, Integer> sortWords = new HashMap<String, Integer>();

		while ((str = br.readLine()) != null) {

			String[] newStr = str.split(" ");

			for (int i = 0; i < newStr.length; i++) {

				sortWords.put(newStr[i],
						Integer.parseInt(String.valueOf(newStr[1])));
			}
		}

		Map<String, Integer> words = sortByValues(sortWords);
		for (Map.Entry<String, Integer> string : words.entrySet()) {
			bw.write(string.getKey() + " " + string.getValue() + "\n");
		}
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
