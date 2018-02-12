package Perspective;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import de.l3s.boilerpipe.extractors.DefaultExtractor;

public class Driver {

	public static void main(String[] args) {
		// System.out.println(sourceNames.toString());
		// System.out.println(bias.toString());
		// System.out.println(sourceNames.size());
		// System.out.println(bias.size());
		try {
			URL a = new URL("https://nyti.ms/2BnL6Ok");
			System.out.println(DefaultExtractor.INSTANCE.getText(a));
		} catch (MalformedURLException e) {
			e.getMessage();	
		} catch (BoilerpipeProcessingException e) {
			e.getMessage();
		}
	}
}
