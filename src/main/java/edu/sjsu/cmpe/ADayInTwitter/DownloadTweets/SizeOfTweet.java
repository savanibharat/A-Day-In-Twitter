package edu.sjsu.cmpe.ADayInTwitter.DownloadTweets;

public class SizeOfTweet {

	public static void main(String[] args) throws InterruptedException {
		
		String str= "{_id  : ObjectId( 534461ccb6fa4e92917719e6 ),username  :  Kitchen Appliances ,location  :  India ,content  :  The rising price of love in Iran: While the results are nowhere near as violent as in India, where some 6000 w... http://t.co/mb7Rs5Wp5h ,followers_count  : 1,friends_count  : 0,created_at  : ISODate( 2014-04-08T20:53:23Z ),retweet_count  : NumberLong(0),lang  :  en}";
		System.out.println(str.length());
		Thread.sleep(10000);
	}
}
