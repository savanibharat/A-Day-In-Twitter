package edu.sjsu.cmpe.ADayInTwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Savani Bharat
 *
 */
public class TweetsHistogram {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader(new File(
				"Files\\OutputFile.txt")));

		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				"Files\\TweetsHistogram.txt")));

		String str = null;

		HashMap<String, Integer> word = new HashMap<String, Integer>();

		while ((str = br.readLine()) != null) {

			String[] newStr = str.split(" ");
			for (int i = 0; i < newStr.length; i++) {

				if (word.containsKey(newStr[i]))
					word.put(newStr[i], word.get(newStr[i]) + 1);
				else
					word.put(newStr[i], 1);
			}
		}
		for (Map.Entry<String, Integer> string : word.entrySet()) {
			bw.write(string.getKey() + " " + string.getValue() + "\n");
		}
	}
}
