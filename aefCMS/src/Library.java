import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.IOException;

public class Library {
	
	static List<Path> templateList = new ArrayList<Path>();
	static List<String> templateListFolder = new ArrayList<String>();
	static List<String> templateName = new ArrayList<String>();
	private static Stream<Path> paths;
 
	public static void main(String[] args) throws IOException {
		loadTemplates();
		System.out.println(templateName);
		System.out.println(templateListFolder);
		loadTemplateVariable(templateListFolder.get(0), templateName.get(0));
		
	}
	
	public static void loadTemplates() throws IOException {
		
		try {
			paths = Files.walk(Paths.get("/home/piccioni/git/aefCMS/aefCMS/PageElementFolder"));
			templateList = paths.filter(Files::isRegularFile).collect(Collectors.toList());
			
			Iterator<Path> iterator=templateList.iterator();			
			while (iterator.hasNext()) {
				Path localPath= iterator.next();
				String localName= localPath.getFileName().toString();
				String localFolder = localPath.getParent().toString();
				templateName.add(localName);
				templateListFolder.add(localFolder);
			}
			paths.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadTemplateVariable(String templatePath, String templateName) throws IOException {
		
		File myFileReader = new File(templatePath+"/"+templateName);
		String myString = FileUtils.readFileToString(myFileReader,"UTF-8");
		Pattern p = Pattern.compile("\\$\\{.*?\\}");
	    Matcher m = p.matcher(myString);
	    List<String> variables = new ArrayList<String>();
	    while (m.find()) {
	        variables.add(m.group());
	    }
	    System.out.println(variables);
	}

}

