package aefCMS;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {
	
	public static void main(String[] args) throws Exception {
			
			final String BASE_PATH = System.getProperty("user.home") + "/git/aefCMS/aefCMS";
			final String LIBRARY_PATH = BASE_PATH + "/cms_library";
			final String SAVED_PAGE_TREE_PATH =  BASE_PATH + "/saved_page_tree/pageTree.json";
			
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
			
			// SAVE PAGETREE
			saveTreeToDisc(SAVED_PAGE_TREE_PATH, page);
			
			// LOAD THE SAVED PAGE_ELEMENT
			try {
				PageElement reloadedPage = new PageElement(null, null, null);
				// LOAD MAIN JSON OBJECT			
				FileReader reader = new FileReader(SAVED_PAGE_TREE_PATH);
				JsonParser parser = new JsonParser();
				// MAIN	JSON OBJECT
				JsonObject loadedPageElement = (JsonObject) parser.parse(reader);
				reloadTree(reloadedPage, loadedPageElement, true);
				r = new HtmlRenderer(LIBRARY_PATH);
				System.out.println(r.render(reloadedPage));
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	////// UTILITIES
	
	// SAVE TREE	
	private static void saveTreeToDisc(String savedPageTreePath, PageElement currentElement) throws Exception {
		Gson gson = new Gson(); 
		Writer treeWriterUpdate = new FileWriter(savedPageTreePath);

		try {
			gson.toJson(currentElement, treeWriterUpdate);
			treeWriterUpdate.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	// RELOAD PAGE TREE
	private static void reloadTree(PageElement currentElement, JsonObject currentJsonObject, Boolean iAmRoot) throws Exception{
		// ELEMENTS TO LOAD
		LibraryElement loadedType=null;
		Map<String, String> loadedParameters = new HashMap<String, String>();
		JsonArray currenChildren = new JsonArray();
		// LOADING ELEMENTS ONE BY ONE
		Gson gson = new Gson();
		// ELEMENT TYPE 
		loadedType = gson.fromJson(currentJsonObject.getAsJsonObject().getAsJsonObject("type"), LibraryElement.class);
		// CURRENT ATTRIBUTE MAP 
		loadedParameters = gson.fromJson(currentJsonObject.getAsJsonObject("parameters"), Map.class);
		// CHILDREN LIST 
		currenChildren = currentJsonObject.getAsJsonArray("children");
	
		if (iAmRoot) {
			currentElement.setType(loadedType);
			currentElement.setParameters(loadedParameters);
			
			if (currenChildren.size()>0) {
				Iterator<JsonElement> localChildren = currenChildren.iterator();
				
				while (localChildren.hasNext()) {
					reloadTree(currentElement, localChildren.next().getAsJsonObject(), false);
				}
			}	
		}
		else {
				PageElement localNode = new PageElement(currentElement, loadedType, loadedParameters);		
				if (currenChildren.size()>0) {
					Iterator<JsonElement> localChildren = currenChildren.iterator();
					while (localChildren.hasNext()) {
						reloadTree(localNode,localChildren.next().getAsJsonObject() ,false);
					}				
				}	
		}	
	}
}
