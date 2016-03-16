package cn.finance.web.taglib;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.struts2.components.Component;

import cn.springside.modules.orm.Page;

import com.opensymphony.xwork2.util.ValueStack;

public class TableTitle extends Component {

	private String action;

	private String sqlName;

	private String title;
	
	private String ctx;
	
	private String businessTypeId;
	
	private Page<List> page = new Page<List>(30);

	public TableTitle(ValueStack stack) {
		super(stack);
	}

	@Override
	public boolean start(Writer writer) {
		if (action != null) {
			try {
				StringBuffer sb = new StringBuffer();
				sb.append("<a href=\"");
				sb.append(action);
				sb.append("?page.pageNo=");
				sb.append(page.getPageNo());
				sb.append("&page.filter=");
				sb.append(page.getFilter());
				sb.append("&pageSize=");
				sb.append(page.getPageSize());
				sb.append("&page.order=");
				if ("asc".equals(page.getOrder())&&sqlName.equals(page.getOrderBy())) {
					sb.append("desc");
				} else {
					sb.append("asc");
				}
				sb.append("&page.orderBy=");
				sb.append(sqlName);
				if (businessTypeId!=null){
					sb.append("&businessTypeId=");
					sb.append(businessTypeId);
				}
				sb.append("\">");
				sb.append(title);
				if ("asc".equals(page.getOrder())&&sqlName.equals(page.getOrderBy())) {
					sb.append("<img border=0 style='cursor:pointer' src='");
					sb.append(ctx);
					sb.append("/images/up.gif'>");
				}
				if ("desc".equals(page.getOrder())&&sqlName.equals(page.getOrderBy())) {
					sb.append("<img border=0 style='cursor:pointer' src='");
					sb.append(ctx);
					sb.append("/images/down.gif'>");
				}
				sb.append("</a>");
				writer.write(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return super.start(writer);
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getCtx() {
		return ctx;
	}

	public void setCtx(String ctx) {
		this.ctx = ctx;
	}

	public Page<List> getPage() {
		return page;
	}

	public void setPage(Page<List> page) {
		this.page = page;
	}

	public String getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(String businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

}
