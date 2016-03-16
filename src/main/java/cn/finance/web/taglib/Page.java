package cn.finance.web.taglib;

import java.io.Writer;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class Page extends Component {

	private String action;
	
	private String excel;
	
	private String businessTypeId;

	private cn.springside.modules.orm.Page page;

	public cn.springside.modules.orm.Page getPage() {
		return page;
	}

	public void setPage(cn.springside.modules.orm.Page page) {
		this.page = page;
	}

	public Page(ValueStack stack) {
		super(stack);
	}

	/**
	 * 画 上一页的标签
	 * 
	 * @param sb
	 * @return StringBuffer
	 */
	private StringBuffer getPrePage(StringBuffer sb) {
		sb.append("<a class='page-prev' href=");
		sb.append(this.action);
		sb.append("?page.pageNo=");
		sb.append(getPage().getPrePage());
		sb.append("&page.filter=");
		sb.append(getPage().getFilter());
		sb.append("&pageSize=");
		sb.append(getPage().getPageSize());
		sb.append("&page.order=");
		sb.append(getPage().getOrder());
		sb.append("&page.orderBy=");
		sb.append(getPage().getOrderBy());
		if (businessTypeId!=null){
			sb.append("&businessTypeId=");
			sb.append(businessTypeId);
		}
		sb.append(">上一页</a>");
		return sb;
	}

	/**
	 * 画下一页
	 * 
	 * @param sb
	 * @return
	 */
	private StringBuffer getNextPage(StringBuffer sb) {
		sb.append("<a class='page-next' href=");
		sb.append(this.action);
		sb.append("?page.pageNo=");
		sb.append(getPage().getNextPage());
		sb.append("&page.filter=");
		sb.append(getPage().getFilter());
		sb.append("&pageSize=");
		sb.append(getPage().getPageSize());
		sb.append("&page.order=");
		sb.append(getPage().getOrder());
		sb.append("&page.orderBy=");
		sb.append(getPage().getOrderBy());
		if (businessTypeId!=null){
			sb.append("&businessTypeId=");
			sb.append(businessTypeId);
		}
		sb.append(">下一页</a>");
		return sb;
	}
	
	/**
	 * 画首页的标签
	 * 
	 * @param sb
	 * @return StringBuffer
	 */
	private StringBuffer getFirstPage(StringBuffer sb) {
		sb.append("<a class='page-first' href=");
		sb.append(this.action);
		sb.append("?page.pageNo=");
		sb.append(1);
		sb.append("&page.filter=");
		sb.append(getPage().getFilter());
		sb.append("&pageSize=");
		sb.append(getPage().getPageSize());
		sb.append("&page.order=");
		sb.append(getPage().getOrder());
		sb.append("&page.orderBy=");
		sb.append(getPage().getOrderBy());
		if (businessTypeId!=null){
			sb.append("&businessTypeId=");
			sb.append(businessTypeId);
		}
		sb.append(">首页</a>");
		return sb;
	}

	/**
	 * 画末页
	 * 
	 * @param sb
	 * @return
	 */
	private StringBuffer getLastPage(StringBuffer sb) {
		sb.append("<a class='page-last' href=");
		sb.append(this.action);
		sb.append("?page.pageNo=");
		sb.append(getPage().getTotalPages());
		sb.append("&page.filter=");
		sb.append(getPage().getFilter());
		sb.append("&pageSize=");
		sb.append(getPage().getPageSize());
		sb.append("&page.order=");
		sb.append(getPage().getOrder());
		sb.append("&page.orderBy=");
		sb.append(getPage().getOrderBy());
		if (businessTypeId!=null){
			sb.append("&businessTypeId=");
			sb.append(businessTypeId);
		}
		sb.append(">末页</a>");
		return sb;
	}

	private String writePageNo(long pageNo) {
		StringBuffer sb = new StringBuffer();
		sb.append("&nbsp;<a href=");
		sb.append(this.action);
		sb.append("?page.pageNo=");
		sb.append(pageNo);
		sb.append("&page.filter=");
		sb.append(getPage().getFilter());
		sb.append("&pageSize=");
		sb.append(getPage().getPageSize());
		sb.append("&page.order=");
		sb.append(getPage().getOrder());
		sb.append("&page.orderBy=");
		sb.append(getPage().getOrderBy());
		if (businessTypeId!=null){
			sb.append("&businessTypeId=");
			sb.append(businessTypeId);
		}
		sb.append(">");
		sb.append(pageNo);
		sb.append("</a>");
		return sb.toString();
	}

	@Override
	public boolean start(Writer writer) {
		long prePage = 0;
		long frePage = 0;
		
		int leftNum = 4;            //分页向左的条数
		int rightNum = 5;           //分页向右的条数

		boolean result = super.start(writer);
		try {
			StringBuffer sb = new StringBuffer();

			sb.append("<div class='list-nav'>");
			sb.append("<div class='pagination'>");
			
			if(this.getPage().getTotalCount() > 0) {
				sb.append("<span class='page-info'>共");
				sb.append(this.getPage().getTotalCount());
				sb.append("条记录</span>   ");
			}
			
			if(this.getPage().getTotalPages() > 0) {
				sb.append("<span class='page-info'>第");
				sb.append(this.getPage().getPageNo());
				sb.append("页/共");
				sb.append(getPage().getTotalPages());
				sb.append("页</span>");
			}
			
			if (getPage().getPageNo() != 1) {
				getFirstPage(sb);
			}
			
			if (this.getPage().isHasPre()) {
				getPrePage(sb);
			}

			if (getPage().getTotalPages() > 6) {
				

				prePage = getPage().getPageNo() - leftNum;
				frePage = getPage().getPageNo() + rightNum;
				if (prePage<2)
					prePage = 1;
				if (frePage>=getPage().getTotalPages())
					frePage = getPage().getTotalPages();

				for (long i = prePage; i <= frePage; i++) {
					if (i == getPage().getPageNo()) {
						sb.append("&nbsp;<span class='cur_page'>" + i + "</span>");
					} else {
						sb.append(writePageNo(i));
					}
				}
			} else {
				for (int i = 1; i <= getPage().getTotalPages(); i++) {
					if (i == getPage().getPageNo()) {
						sb.append("&nbsp;<span class='cur_page'>" + i + "</span>");
					} else {
						sb.append(writePageNo(i));
					}
				}
			}

			if (this.getPage().isHasNext()) {
				getNextPage(sb);
			}
			
			if ((getPage().getPageNo() != getPage().getTotalPages()) && (getPage().getTotalPages() > 1)) {
				getLastPage(sb);
			}
			
			if(this.getPage().getTotalPages() > 0) {
				sb.append("到第<input type='text' size='2' name='go' id='go' />页");
				if (businessTypeId!=null){
					sb.append("<input type='button' value='go' class='GoButton' onclick='gotoB(\"");
				} else {
					sb.append("<input type='button' value='go' class='GoButton' onclick='goto(\"");
				}
				sb.append(action);
				sb.append("\")'/>");
				sb.append("");
			}
			if (!"".equals(excel)){
				sb.append("<input onClick=\"excelOutput('");
				sb.append(excel);
				sb.append("')\" type='button' class='ExcelButton' value='导出到Excel' />");
			}
			sb.append("</div>");
			sb.append("</div>");
			writer.write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getExcel() {
		return excel;
	}

	public void setExcel(String excel) {
		this.excel = excel;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(String businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
	
}
