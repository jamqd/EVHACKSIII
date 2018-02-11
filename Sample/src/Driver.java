import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		// System.out.println(sourceNames.toString());
		// System.out.println(bias.toString());
		// System.out.println(sourceNames.size());
		// System.out.println(bias.size());
		Article test = new Article("New York Times", "TestTitle", "TestURL");
		System.out.println(test.getIntRating());
	}
}
