package edu.sjsu.cmpe.ADayInTwitter.MostPopularWords;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;

/*
 * 
 * Most popular words
 *	  Present the most popular English words over the complete period of time (exclude prepositions and pronouns)
 *(I will be taking top 50 most popular words)
 */
/**
 * @author Savani Bharat
 *
 */
public class PopularWords {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		// TweetWords
		BufferedReader br = new BufferedReader(new FileReader(new File(
				"D:\\Twitter Files\\TweetWords.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				"D:\\Twitter Files\\MostPopularWords.txt")));

		BufferedReader readPrepositions = new BufferedReader(new FileReader(
				new File("Files\\Prepositions.txt")));
		Set<String> prepositionsPronounsConjunctions = new HashSet<String>();

		BufferedReader readPronouns = new BufferedReader(new FileReader(
				new File("Files\\Pronouns.txt")));
		BufferedReader readConjunctions = new BufferedReader(new FileReader(
				new File("Files\\Conjunctions.txt")));
		String read = null;
		while ((read = readPrepositions.readLine()) != null) {

			prepositionsPronounsConjunctions.add(read);

		}
		System.out.println(prepositionsPronounsConjunctions);
		read = null;
		while ((read = readPronouns.readLine()) != null) {

			prepositionsPronounsConjunctions.add(read);
		}
		System.out.println(prepositionsPronounsConjunctions);

		read = null;
		while ((read = readConjunctions.readLine()) != null) {

			prepositionsPronounsConjunctions.add(read);

		}
		System.out.println(prepositionsPronounsConjunctions);

		read = null;
		int word = 0;
		try {
			while ((read = br.readLine()) != null && word < 50) {

				String[] str = read.split(" ");
				if (str.length == 2) {

					if (!prepositionsPronounsConjunctions.contains(str[0])) {
						
						bw.write(str[0] + " " + str[1] + "\n");
						word++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bw.close();
		}
	}
}