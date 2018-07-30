package cms;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MyCms {

	public static void main(String[] args) {
		File root = new File("/home/federico/root");
		Library library = new Library(root);
		Map<String, String> pageAttributes = new HashMap<String, String>();
		pageAttributes.put("title", "My Page");
		PageElement page = new PageElement(null, library.getElement("page"), pageAttributes);
		Map<String, String> titleAttributes = new HashMap<String, String>();
		titleAttributes.put("content", "Lorem ipsum");
		new PageElement(page, library.getElement("title"), titleAttributes);
		Map<String, String> paragraphAttributes = new HashMap<String, String>();
		paragraphAttributes.put("content", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum dapibus ornare ligula nec dignissim. Nullam eu risus eu ligula luctus malesuada. Cras pretium pharetra est, non suscipit erat eleifend nec. Donec in bibendum nisi. Sed quis elit eu quam placerat molestie eget non mi. Nam tincidunt porttitor euismod. Sed volutpat mi tortor, vel venenatis massa consectetur ut. Nam id porta risus.");
		new PageElement(page, library.getElement("paragraph"), paragraphAttributes);
	}

}
