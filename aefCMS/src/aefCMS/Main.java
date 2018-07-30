package aefCMS;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
	
	public static void main(String[] args) throws Exception {
			
			final String BASE_PATH = System.getProperty("user.home") + "/git/aefCMS/aefCMS";
			final String LIBRARY_PATH = BASE_PATH + "/cms_library";
		
			//GENERATE LIBRARY
			
			Library lib = new Library(new File(LIBRARY_PATH));
			
			//CREATE BOGUS PageElements
			
			//page
					
			Map<String, String> pageAttributes = new HashMap<String, String>();
			pageAttributes.put("title", "My Page");
			PageElement page = new PageElement(null, lib.getElement("page"), pageAttributes);
			
			//title
			
			Map<String, String> titleAttributes = new HashMap<String, String>();
			titleAttributes.put("h-text", "HELLO WORLD TITLE");
			new PageElement(page, lib.getElement("title"), titleAttributes);
			
			//paragraph
			
			Map<String, String> paragraphAttributes = new HashMap<String, String>();
			paragraphAttributes.put("p-text", "Hello World Paragraph");
			paragraphAttributes.put("p-text-color", "red");
			
			new PageElement(page, lib.getElement("paragraph"), paragraphAttributes);
			
			//container
			
			Map<String, String> containerAttributes = new HashMap<String, String>();
			containerAttributes.put("border-style", "dotted");
			 
			PageElement c = new PageElement(page, lib.getElement("container"), containerAttributes);
			
			//paragraph inside container
			
			Map<String, String> paragraph2Attributes = new HashMap<String, String>();
			paragraph2Attributes.put("p-text", "Hello World Inside");
			paragraph2Attributes.put("p-text-color", "blue");
			
			new PageElement(c, lib.getElement("paragraph"), paragraph2Attributes);
			
			//GENERATE OUTPUT HTML
			
			HtmlRenderer r = new HtmlRenderer(LIBRARY_PATH);
			System.out.println(r.render(page));

	}

}
