package cms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class LibraryElement {
	
	private String name;
	private String template;
	private List<String> attributes;
	
	public LibraryElement(String name, String template) {
		this.name = name;
		this.template = template;
		attributes = new ArrayList<String>();
		File file = new File(template);
		String string;
		try {
			string = FileUtils.readFileToString(file, "UTF-8");
			Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");
			Matcher matcher = pattern.matcher(string);
			while(matcher.find()) {
			    attributes.add(matcher.group(1));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}
	
}
