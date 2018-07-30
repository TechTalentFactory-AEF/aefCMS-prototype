package aefCMS;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

public class HtmlRenderer {
	
	private static String LOADER_TYPE = "file";
	private static String LOADER_CLASS = "org.apache.velocity.runtime.resource.loader.FileResourceLoader";
	
	private VelocityEngine ve;

	public HtmlRenderer(String velocityTemplatesPath) throws Exception {
		
		Properties p = new Properties();
	    p.setProperty("resource.loader", LOADER_TYPE);
	    p.setProperty("file.resource.loader.class", LOADER_CLASS);
	    p.setProperty("file.resource.loader.path", velocityTemplatesPath);
	    
	    ve = new VelocityEngine();
		ve.init(p);
	}
	
	public StringBuffer render(PageElement pe) throws ResourceNotFoundException, ParseErrorException, Exception {
		
		Template peTemplate = ve.getTemplate(pe.getType().getTemplate());
		VelocityContext peContext = new VelocityContext(pe.getParameters());
		
		if (! (pe.getChildren() == null || pe.getChildren().isEmpty())) {
			StringBuffer childrenOut = new StringBuffer();
			for (PageElement peChild : pe.getChildren()) {
				childrenOut.append(render(peChild));
			}
			peContext.put("children", childrenOut);
		}
		
		StringWriter peOutput = new StringWriter();
		peTemplate.merge(peContext, peOutput);
		
		return peOutput.getBuffer();
	}
	
}
