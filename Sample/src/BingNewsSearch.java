import java.net.*;
import java.util.*;
import java.util.concurrent.SynchronousQueue;
import java.util.function.Consumer;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;

/*
 * Gson: https://github.com/google/gson
 * Maven info:
 *     groupId: com.google.code.gson
 *     artifactId: gson
 *     version: 2.8.1
 *
 * Once you have compiled or downloaded gson-2.8.1.jar, assuming you have placed it in the
 * same folder as this file (BingNewsSearch.java), you can compile and run this program at
 * the command line as follows.
 *
 * javac BingNewsSearch.java -classpath .;gson-2.8.1.jar -encoding UTF-8
 * java -cp .;gson-2.8.1.jar BingNewsSearch
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BingNewsSearch {

	// ***********************************************
	// *** Update or verify the following values. ***
	// **********************************************

	// Replace the subscriptionKey string value with your valid subscription key.
	static String subscriptionKey = "c29a56277e944a11854c3fea62c35ef6";

	// Verify the endpoint URI. At this writing, only one endpoint is used for Bing
	// search APIs. In the future, regional endpoints may be available. If you
	// encounter unexpected authorization errors, double-check this value against
	// the endpoint for your Bing Web search instance in your Azure dashboard.
	static String host = "https://api.cognitive.microsoft.com";
	static String path = "/bing/v7.0/news/search";

	static String searchTerm;

	public static SearchResults SearchNews(String searchQuery) throws Exception {
		// construct URL of search request (endpoint + query string)
		URL url = new URL(host + path + "?q=" + URLEncoder.encode(searchQuery, "UTF-8"));
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);

		// receive JSON body
		InputStream stream = connection.getInputStream();
		String response = new Scanner(stream).useDelimiter("\\A").next();

		// construct result object for return
		SearchResults results = new SearchResults(new HashMap<String, String>(), response);

		// extract Bing-related HTTP headers
		Map<String, List<String>> headers = connection.getHeaderFields();
		for (String header : headers.keySet()) {
			if (header == null)
				continue; // may have null key
			if (header.startsWith("BingAPIs-") || header.startsWith("X-MSEdge-")) {
				results.relevantHeaders.put(header, headers.get(header).get(0));
			}
		}

		stream.close();
		return results;
	}

	// pretty-printer for JSON; uses GSON parser to parse and re-serialize
	public static String prettify(String json_text) {
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse(json_text).getAsJsonObject();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(json);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter search term: ");
		searchTerm = in.nextLine();
		try {
			System.out.println("Searching the Web for: " + searchTerm);

			SearchResults result = SearchNews(searchTerm);

			System.out.println("\nRelevant HTTP Headers:\n");
			for (String header : result.relevantHeaders.keySet())
				System.out.println(header + ": " + result.relevantHeaders.get(header));

			System.out.println("\nJSON Response:\n");
			System.out.println(prettify(result.jsonResponse));
			ArrayList<Article> articles = parseJson(result.jsonResponse);
			System.out.println(articles);
			int bestIndex1 = 0, bestIndex2 = 0;
			int bestDiff = 0;
			for (int i = 0; i < articles.size(); i++) {
				for (int j = 0; j < articles.size(); j++) {
					ArticleComparisonIndexer c = new ArticleComparisonIndexer(articles.get(i), articles.get(j));
					if (c.getIndex() > bestDiff) {
						bestIndex1 = i;
						bestIndex2 = j;
						bestDiff = c.getIndex();
					}
				}
			}
			System.out.println();
			System.out.println("Articles with largest perspective difference: ");
			System.out.println(articles.get(bestIndex1) + "\n" + articles.get(bestIndex2));
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.exit(1);
		}

	}

	/**
	 * Parses JSON output from Microsoft News Search output
	 * @param jsonString
	 * @return - ArrayList of Article objects with attributes from output
	 */
	public static ArrayList<Article> parseJson(String jsonString) {
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse(jsonString).getAsJsonObject();
		ArrayList<Article> articles = new ArrayList<Article>();

		JsonArray value = json.getAsJsonArray("value");

		Consumer<JsonElement> consumer = new Consumer<JsonElement>() {

			@Override
			public void accept(JsonElement arg0) {
				JsonObject obj = arg0.getAsJsonObject();
				String name = obj.get("name").getAsString();
				String url = obj.get("url").getAsString();
				String provider = obj.get("provider").getAsJsonArray().get(0).getAsJsonObject().get("name")
						.getAsString();
				articles.add(new Article(provider, name, url));

			}
		};

		value.forEach(consumer);

		return articles;
	}
}

// Container class for search results encapsulates relevant headers and JSON
// data
class SearchResults {
	HashMap<String, String> relevantHeaders;
	String jsonResponse;

	SearchResults(HashMap<String, String> headers, String json) {
		relevantHeaders = headers;
		jsonResponse = json;
	}
}