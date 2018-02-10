import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Article class represents an article
 * @author jd
 *
 */
public class Article {
	
	private String source, title, author, URL;
	private int rating  = 0;
	private ArrayList <String> sourceNames;
	private ArrayList <String> bias;
	
	public Article(String aSource, String aTitle, String aAuthor, String aURL) {
		source =  aSource;
		title = aTitle;
		author = aAuthor;
		URL = aURL;
		createSourceBiasArray();
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
	public int getIntRating(){
		String sRating = bias.get(sourceNames.indexOf(source));
		switch (sRating) {
			case "left" : rating = -2; break;
			case "left-center" :  rating = -1; break;
			case "center" : rating = 0;  break;
			case "right-center" :  rating = -1; break;
			case "right" : rating = -2; break;
			case "allsides" : rating = 0; break;
		}
		return rating;
	}
	/**
	 * Creates two arraylists one for source name and one for bias
	 */
	public void createSourceBiasArray() {
		sourceNames = new ArrayList<String>();
		bias = new ArrayList<String>();
		try{
			int count = 1;
			File file = new File("biasData.txt");
			Scanner in = new Scanner(file);
			String next = "";
			while(in.hasNextLine()) {
				next = in.nextLine().trim();
				if(!next.equals(",") && !next.isEmpty()) {
					if(next.contains(",")) {
						if(count%2 == 1) {
							sourceNames.add(next.substring(0,next.indexOf(',')));
							count++;
						}else {
							bias.add(next.substring(0,next.indexOf(',')));
							count++;
						}
					}else {
						if(count%2 == 1) {
							sourceNames.add(next);
							count++;
						}else {
							bias.add(next);
							count++;
						}
					}
				}
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
