package cms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageElement {

	PageElement parent;
	LibraryElement type;
	Map<String, String> parameters;
	List<PageElement> children;
	
	public PageElement(PageElement parent, LibraryElement type, Map<String, String> parameters) {
		this.parent = parent;
		this.type = type;
		this.parameters = parameters;
		children = new ArrayList<>();
		if (parent != null) {
			parent.getChildren().add(this);
		}
	}

	private List<PageElement> getChildren() {
		return children;
	}

}
