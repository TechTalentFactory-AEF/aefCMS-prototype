package aefCMS;

import java.util.List;

public class LibElement {
	
	private String template;
	private String name;
	private List<String> attributes;
	
	//GETTERS SETTERS
	
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}
	
}
