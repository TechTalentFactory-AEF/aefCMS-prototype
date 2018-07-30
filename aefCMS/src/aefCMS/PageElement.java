package aefCMS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageElement {

	private PageElement parent;
	private LibraryElement type;
	private Map<String, String> parameters;
	private List<PageElement> children;
	
	//CONSTRUCTOR
	
	public PageElement(PageElement parent, LibraryElement type, Map<String, String> parameters) {
		this.parent = parent;
		this.type = type;
		this.parameters = parameters;
		children = new ArrayList<>();
		if (parent != null) {
			parent.getChildren().add(this);
		}
	}

	//GETTERS SETTERS
	
	public PageElement getParent() {
		return parent;
	}

	public void setParent(PageElement parent) {
		this.parent = parent;
	}

	public LibraryElement getType() {
		return type;
	}

	public void setType(LibraryElement type) {
		this.type = type;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public List<PageElement> getChildren() {
		return children;
	}

	public void setChildren(List<PageElement> children) {
		this.children = children;
	}

}
