package edu.sjsu.cmpe.ADayInTwitter.Service;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import edu.sjsu.cmpe.ADayInTwitter.Configuration.TwitterConfiguration;
import edu.sjsu.cmpe.ADayInTwitter.Display.TwitterResourceDataDisplay;
import edu.sjsu.cmpe.ADayInTwitter.Root.RootResource;

// TODO: Auto-generated Javadoc
/**
 * The Class TwitterService.
 *
 * @author Savani Bharat
 */
public class TwitterService extends Service<TwitterConfiguration>{

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {

		new TwitterService().run(args);

	}

	
	/* (non-Javadoc)
	 * @see com.yammer.dropwizard.Service#initialize(com.yammer.dropwizard.config.Bootstrap)
	 */
	public void initialize(Bootstrap<TwitterConfiguration> bootstrap) {
		bootstrap.setName("twitter-service");
	}

	/* (non-Javadoc)
	 * @see com.yammer.dropwizard.Service#run(com.yammer.dropwizard.config.Configuration, com.yammer.dropwizard.config.Environment)
	 */
	public void run(TwitterConfiguration configuration, Environment environment)
			throws Exception {

		environment.addResource(RootResource.class);
		environment.addResource(new TwitterResourceDataDisplay());
	}

}
