import java.io.FileReader;

/**
 * Article class represents an article
 * @author jd
 *
 */
public class Article {
	
	private String source, title, author, URL;
	private int rating  = 0;
	
	public Article(String aSource, String aTitle, String aAuthor, String aURL, int aRating) {
		source =  aSource;
		title = aTitle;
		author = aAuthor;
		URL = aURL;
	}
	/**
	 * 
	 * @return - source name ex: New York Times
	 */
	public String getSource() {
		return source;
	}
	/**
	 * 
	 * @return - article title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 
	 * @return - article author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * 
	 * @return - article URL
	 */
	public String getURL() {
		return URL;
	}
	/**
	 * 
	 * @return -  political bias rating from -2 to 2
	 * -2: left
	 * -1: center left
	 *  0: neutral
	 *  1: center right
	 *  2: right 
	 */
	public int getRating(){
		String sRating = "";
		switch (sRating) {
			case "left" : rating = -2; break;
			case "center-left" :  rating = -1; break;
			case "center" : rating = 0;  break;
			case "center-right" :  rating = -1; break;
			case "right" : rating = -2; break;
		}
		return rating;
	}
	public void createSourceBiasArray() {
		try{
			FileReader filereader =  new FileReader("biasData.txt");
		}catch (Exception e) {
			System.out.println("you fucked up");
		}
	}
}
