package Perspective;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Article class represents an article
 * 
 * @author jd
 *
 */
public class Article {

	private String source, title, URL, path;
	private int rating = 0;
	private ArrayList<String> sourceNames;
	private ArrayList<String> bias;

	public Article(String aSource, String aTitle, String aURL, String aPath ) {
		source = aSource;
		title = aTitle;
		URL = aURL;
		path = aPath;
		createSourceBiasArray();
	}

	public String toString() {
		return "\nSource: " + source + "\nTitle: " + title + "\nURL:" + URL + "\n";
	}

	/**
	 * 
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
	 * @return - article URL
	 */
	public String getURL() {
		return URL;
	}

	/**
	 * 
	 * @return - political bias rating from -2 to 2 -2: left -1: center left 0:
	 *         neutral 1: center right 2: right
	 */
	public int getIntRating() {
		int index = sourceNames.indexOf(source.toLowerCase());
		String sRating = "";
		if (index > 0) {
			sRating = bias.get(sourceNames.indexOf(source.toLowerCase()));
		}
		switch (sRating) {
		case "left":
			rating = -2;
			break;
		case "left-center":
			rating = -1;
			break;
		case "center":
			rating = 0;
			break;
		case "right-center":
			rating = 1;
			break;
		case "right":
			rating = 2;
			break;
		case "allsides":
			rating = 0;
			break;
		default:
			rating = 0;
		}
		return rating;
	}

	/**
	 * Creates two arraylists one for source name and one for bias
	 */
	public void createSourceBiasArray() {
		sourceNames = new ArrayList<String>();
		bias = new ArrayList<String>();
		try {
			int count = 1;
			File file = new File(path);
			Scanner in = new Scanner(file);
			String next = "";
			while (in.hasNextLine()) {
				next = in.nextLine().trim();
				if (!next.equals(",") && !next.isEmpty()) {
					if (next.contains(",")) {
						if (count % 2 == 1) {
							sourceNames.add(next.substring(0, next.indexOf(',')).toLowerCase());
							count++;
						} else {
							bias.add(next.substring(0, next.indexOf(',')).toLowerCase());
							count++;
						}
					} else {
						if (count % 2 == 1) {
							sourceNames.add(next.toLowerCase());
							count++;
						} else {
							bias.add(next.toLowerCase());
							count++;
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
