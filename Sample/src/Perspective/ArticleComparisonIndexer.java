package Perspective;
/**
 * Class for comparing the perspectives of two articles based on source bias and
 * article text
 * 
 * @author jd
 *
 */
public class ArticleComparisonIndexer {
	private Article article1, article2;
	private int index = 0;

	/**
	 * 
	 * @param a1
	 *            - first article
	 * @param a2
	 *            - second article
	 */
	public ArticleComparisonIndexer(Article a1, Article a2) {
		article1 = a1;
		article2 = a2;
	}

	/**
	 * 
	 * @return - index representing how likely two articles are to differ in
	 *         perspectives
	 */
	public int getIndex() {
		int sourceIndex = getSourceIndex();
		double textIndex = getTextIndex();
		index = sourceIndex;
		// enter formula here modifying index variable
		return index;
	}

	/**
	 * 
	 * @return - calculates difference index of two articles based on source bias
	 *         database from allsides.com
	 */
	public int getSourceIndex() {
		return Math.abs(article1.getIntRating() - article2.getIntRating());
	}

	/**
	 * 
	 * @return - calculates difference index from analyzing phrases in text of two
	 *         articles
	 */
	public int getTextIndex() {
		// some natural language processing here
		return 0;
	}
}
