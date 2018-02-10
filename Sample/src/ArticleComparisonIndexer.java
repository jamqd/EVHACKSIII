/**
 * Class for comparing the perspectives of two articles based on source bias and article text
 * @author jd
 *
 */
public class ArticleComparisonIndexer {
	private Article article1, article2;
	private double index = 0;
	
	public ArticleComparisonIndexer(Article a1, Article a2) {
		article1 = a1;
		article2 = a2;
	}
	
	public double getIndex() {
		int sourceIndex =  getSourceIndex();
		double textIndex = getTextIndex();
		index = sourceIndex;
		// enter formula here modifying index variable
		return index;
	}
	
	public int getSourceIndex() {
		return Math.abs(article1.getIntRating() -  article2.getIntRating());
	}
	
	public double getTextIndex() {
		//some natural language processing here
		return 0;
	}
}
