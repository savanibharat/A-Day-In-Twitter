package edu.sjsu.cmpe.ADayInTwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author Savani Bharat
 * 
 */
public class OnlyTweets {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader(new File(
				"D:\\Twitter Files\\tweets.txt")));

		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				"Files\\OutputFile.txt")));

		String str = null;

		while ((str = br.readLine()) != null) {

			String newStr = str.replaceAll("\\W", " ").replaceAll(
					"   text     ", "");
			bw.write(newStr + "\n");
		}
	}
}