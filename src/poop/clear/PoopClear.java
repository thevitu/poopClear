package poop.clear;

import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PoopClear {

	private Path path;

	private List<String> lines;
	
	private static final String FOREACH_REGEX = ".*<xsl:for-each select=\"(.*)\">.*";
	private static final String END_FOREACH_REGEX = ".*<\\/xsl:for-each>.*";

	public PoopClear(String file) {
		this.path = Paths.get(file);
	}

	private void print(String value) {
		System.out.println(value);
	}

	public void findPoop() {
		try {
			lines = Files.readAllLines(path);
			for (int i = 0; i < lines.size(); i++) {
				if (lines.get(i).matches(FOREACH_REGEX)) {
					int forEachOpen = 1;
					String poopTest = "(.*)" + lines.get(i).replaceFirst(FOREACH_REGEX, "$1/").replaceAll("/", "\\/") + "(.*)";
					print(lines.get(i).trim());
					for (int j = i; j < lines.size(); j++) {
						if (lines.get(j).matches(poopTest)) {
							print("    replace:" + lines.get(j).trim());
							lines.set(j, lines.get(j).replaceFirst(poopTest, "$1$2"));
							print("    to:" + lines.get(j).trim());
						}
						//logic to break sub loop
						if (lines.get(j).matches(FOREACH_REGEX)) {
							++forEachOpen;
						}
						if (lines.get(j).matches(END_FOREACH_REGEX)) {
							--forEachOpen;
						}
						if (forEachOpen == 0) {
							break;
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fixPoop() {
		findPoop();
		try {
			Files.write(this.path, this.lines, TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
