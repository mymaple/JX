package com.jx.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.jx.common.service.ComDictService;
import com.jx.common.util.SpringContextUtil;

public class ParameterSelectTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String cssClass;
	private String styleClass;
	private String type;
	private String multiple;
    private String onChange;
    
    private String value;
    private String nullStr;
    
    public int doEndTag() throws JspException{
    	
    	ComDictService comDictService = (ComDictService)SpringContextUtil.getBean("dictTagService");
    	
    	 StringBuffer sb = new StringBuffer();
         JspWriter out = pageContext.getOut();

         sb.append("<select name=\""+this.getName()+"\"");
         
         if(!StringUtils.isEmpty(this.getId())){
             sb.append("id=\"" + this.getId() + "\"");
         }
         if (!StringUtils.isEmpty(this.getCssClass())){
             sb.append("class=\"" + this.getCssClass() + "\"");
         }
         if(!StringUtils.isEmpty(this.getStyleClass())){
             sb.append("style=\"" + this.getStyleClass() + "\"");
         }
         if(!StringUtils.isEmpty(this.getType())){
             sb.append("style=\"" + this.getType() + "\"");
         }
         if(!StringUtils.isEmpty(this.getMultiple())){
             sb.append("multiple=\"" + this.getMultiple() + "\"");
         }
         if(!StringUtils.isEmpty(this.getOnChange())){
             sb.append("onchange=\"" + this.getOnChange() + "\"");
         }
         sb.append(">");

         if(!StringUtils.isEmpty(this.getNullStr())){
             sb.append("<option value=\"\">--"+this.getNullStr()+"--</option>");
         }

         for(BaseParameter dc:list){
             if (dc.getDictValue().equals(this.getValue())){
                 sb.append("<option value=\""+dc.getDictValue()+"\" selected>");
             }else {
                 sb.append("<option value=\""+dc.getDictValue()+"\">");
             }
             sb.append(dc.getDictName()+"</option>");
         }
         sb.append("</select>");
         try {
             out.write(sb.toString());
         } catch (IOException e) {
             // TODO Auto-generated catch block
             throw new JspException(e);
         }

         return TagSupport.EVAL_PAGE;    	
    	
    }
    
    
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getStyleClass() {
		return styleClass;
	}
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getNullStr() {
		return nullStr;
	}
	public void setNullStr(String nullStr) {
		this.nullStr = nullStr;
	}
	public String getMultiple() {
		return multiple;
	}
	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}
	public String getOnChange() {
		return onChange;
	}
	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}
	
	
}
