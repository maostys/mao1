import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class SenyaBot extends HttpServlet{
	static Logger logger = Logger.getLogger("SenyaBot");

	static String consumerKey = "2o01rCpPZNu2JkkQFnUD0Lzgo";
	static String consumerSecret = "XVt9OrqeiPrWwro353OV8wtBhVA7oOmfThlBMw3ontlvTHm7FR";

	static String accessToken = "1197325210606653441-HEYuetvu0xVtnyUIUKa2hrKyCAU87R";
	static String accessTokenSecret = "DVSs1dHhXj2uYXVycLbopMIql4TkPjvOauctk4BAK9qZb";

	private String getTweet(){
		String tweets[] = {
				"家系といったら千屋っしょ",
				"通は固めの濃いめ",
				"やっぱり根岸の千家がいいよね。",
				"信頼の深夜営業",
				"水曜は定休日なんだよな",
				"とりあえず千家行こうぜ",
				"ユー、ライス付けちゃう？",
				"ミニカレーも意外と美味しいぜ",
				"ネギうますぎ",
				"あのネギ盛見るとテンションあがっちゃうよねー",
				"大将まじかっけぇ",
				"ここは食券制です"
		};
		int randint = (int)(Math.random()*tweets.length);
		return tweets[randint];
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//http://twitter4j.org/ja/configuration.html
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
			.setOAuthAccessToken(accessToken)
			.setOAuthAccessTokenSecret(accessTokenSecret)
			.setOAuthConsumerKey(consumerKey)
			.setOAuthConsumerSecret(consumerSecret);
		String message = getTweet();
		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		try {
			//Twitterに書き出し
			twitter.updateStatus(message);
		} catch (TwitterException e) {
			logger.log(Level.SEVERE, "Twitter error", e);
		}
	}
}