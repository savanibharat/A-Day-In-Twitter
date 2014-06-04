package edu.sjsu.cmpe.ADayInTwitter.BaseLineCoding;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class Tokenizer {

	public static void main(String[] args) throws Exception {

		HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
		BufferedReader br = new BufferedReader(new FileReader("D:\\tweets.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter(
				"D:\\HashTagCount.txt"));

		String str;
		int ii = 0;
		try {
			while ((str = br.readLine()) != null) {
				ii++;
				if (ii % 1000000 == 0)
					System.out.println("recored done " + ii);
				String[] split = str.split(": \"");
				if (split.length > 1) {

					String[] newSplit = split[1].split("\"}");
					String[] words = newSplit[0].split(" ");

					for (int i = 0; i < words.length; i++) {

						if (wordCount.containsKey(words[i])) {
							wordCount
									.put(words[i], wordCount.get(words[i]) + 1);
						} else {
							wordCount.put(words[i], 1);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		int hashTagCount = 0;
		for (String string : wordCount.keySet()) {
			if (string.contains("#")) {
				if (wordCount.get(string) > 500) {
					System.out.println(string + " " + wordCount.get(string));

					hashTagCount++;
				}
				bw.write(string + " " + wordCount.get(string) + "\n");
			}
		}
		bw.close();
		System.out.println(hashTagCount);
		wordCount = null;
	}
}