package aefCMS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlRendererTest {

	public static void main(String[] args) throws Exception {
		
		HtmlRenderer hr = new HtmlRenderer("../aefCMS/velocity_templates/");

		//bogus LibElements
		
		LibElement mainHtml = new LibElement();
		mainHtml.setTemplate("mainPage.vm");
		mainHtml.setName("mainHtml");
		
		LibElement par = new LibElement();
		par.setTemplate("par.vm");
		par.setName("par");
		ArrayList<String> parAttributes = new ArrayList<String>();
		parAttributes.add("text-color");
		par.setAttributes(parAttributes);
		
		LibElement div = new LibElement();
		div.setTemplate("div.vm");
		div.setName("div");
		ArrayList<String> divAttributes = new ArrayList<String>();
		divAttributes.add("border-style");
		div.setAttributes(divAttributes);
		
		//bogus PageElements
		
		PageElement index = new PageElement();
		index.setFather(null);
		index.setType(mainHtml);
		List<PageElement> indexChildren = new ArrayList<PageElement>();
		
		PageElement p = new PageElement();
		p.setFather(index);
		p.setType(par);
		Map<String, String> pParameters = new HashMap<String,String>();
		pParameters.put("text-color", "red");
		pParameters.put("text", "Hello World");
		p.setParameters(pParameters);
		indexChildren.add(p);
		
		PageElement d = new PageElement();
		d.setFather(index);
		d.setType(div);
		Map<String, String> dParameters = new HashMap<String,String>();
		dParameters.put("border-style", "dotted");
		d.setParameters(dParameters);
		List<PageElement> dChildren = new ArrayList<PageElement>();
		indexChildren.add(d);
		
		PageElement pd = new PageElement();
		pd.setFather(d);
		pd.setType(par);
		Map<String, String> pdParameters = new HashMap<String,String>();
		pdParameters.put("text-color", "blue");
		pdParameters.put("text", "Hello World Inside");
		pd.setParameters(pdParameters);
		dChildren.add(pd);
		
		d.setChildren(dChildren);
		index.setChildren(indexChildren);
		
		//RENDERING
		
		System.out.println(hr.render(index));
		
	}

}
