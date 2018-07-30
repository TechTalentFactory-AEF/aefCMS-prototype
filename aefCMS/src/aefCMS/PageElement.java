package aefCMS;

import java.util.List;
import java.util.Map;

public class PageElement {
	
	private PageElement father;
	private LibElement type;
	private Map<String, String> paramethers;
	private List<PageElement> children;
	
	//GETTERS SETTERS
	
	public PageElement getFather() {
		return father;
	}
	public void setFather(PageElement father) {
		this.father = father;
	}
	
	public LibElement getType() {
		return type;
	}
	public void setType(LibElement type) {
		this.type = type;
	}
	
	public Map<String, String> getParamethers() {
		return paramethers;
	}
	public void setParamethers(Map<String, String> paramethers) {
		this.paramethers = paramethers;
	}
	
	public List<PageElement> getChildren() {
		return children;
	}
	public void setChildren(List<PageElement> children) {
		this.children = children;
	}
	
}
