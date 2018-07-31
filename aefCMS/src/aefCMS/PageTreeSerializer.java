package aefCMS;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PageTreeSerializer {
	
	// SAVE PAGETREE	
	public static void saveTreeToDisc(String savePageTreePath, PageTree currentTree) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Writer treeWriterUpdate = new FileWriter(savePageTreePath);

		gson.toJson(currentTree.getRoot(), treeWriterUpdate);
		treeWriterUpdate.close();
	}
	
	// LOAD PAGETREE	
	public static PageTree loadTreeFromDisc(String loadPageTreePath) throws FileNotFoundException {
		// load main json object			
		FileReader reader = new FileReader(loadPageTreePath);
		JsonParser parser = new JsonParser();
		// main	json object
		JsonObject loadedPageTree = (JsonObject) parser.parse(reader);
		// new empty root
		PageElement root = new PageElement(null, null);
		// reload root and get inner jsonobject
		root = reloadElementRecursive(root,loadedPageTree, true);

		return new PageTree(root); 
	}	
	
	// UTILITES
	private static PageElement reloadElementRecursive(PageElement currentElement, JsonObject currentJsonObject, Boolean iAmRoot) {
		
		// elements to load
		LibraryElement loadedType=null;
		Map<String, String> loadedParameters = new HashMap<String, String>();
		JsonArray currenChildren = new JsonArray();
		// loading elements one by one
		Gson gson = new Gson();
		// element type 
		loadedType = gson.fromJson(currentJsonObject.getAsJsonObject().getAsJsonObject("type"), LibraryElement.class);
		// current attribute map 
		loadedParameters = gson.fromJson(currentJsonObject.getAsJsonObject("parameters"), Map.class);
		// children list 
		currenChildren = currentJsonObject.getAsJsonArray("children");
	
		if (iAmRoot) {
			currentElement.setType(loadedType);
			currentElement.setParameters(loadedParameters);
			
			if (currenChildren.size()>0) {
				Iterator<JsonElement> localChildren = currenChildren.iterator();
				
				while (localChildren.hasNext()) {
					reloadElementRecursive(currentElement, localChildren.next().getAsJsonObject(), false);
				}
			}	
		}
		else {
				PageElement localNode = new PageElement(currentElement, loadedType, loadedParameters);		
				if (currenChildren.size()>0) {
					Iterator<JsonElement> localChildren = currenChildren.iterator();
					while (localChildren.hasNext()) {
						reloadElementRecursive(localNode,localChildren.next().getAsJsonObject() ,false);
					}				
				}	
		}
		return currentElement;
	}
	
}
