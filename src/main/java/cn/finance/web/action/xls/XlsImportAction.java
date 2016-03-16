package cn.finance.web.action.xls;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.Expense;
import cn.finance.model.bean.ServiceAdjustBean;
import cn.finance.service.ExpenseService;
import cn.finance.service.calc.CalcOldService;
import cn.finance.util.ExcelUtils;
import cn.finance.web.action.base.BaseAction;

public class XlsImportAction extends BaseAction {

	private Integer sv3Id;

	private String name;

	private Integer mediaId;

	private Integer accountId;

	private int type;

	private String key;

	private String upload;

	@Autowired
	private ExpenseService expenseService;

	@Autowired
	private CalcOldService calcOldService;

	private static final long serialVersionUID = 1L;

	public String importXls() throws FileNotFoundException, IOException {
		try {
			List<ServiceAdjustBean> list = new ArrayList<ServiceAdjustBean>();
			switch (type) {
			// 搜狗
			case 1:
				list = ExcelUtils.getSougoReport(upload);
				addExpense(list);
				break;
			// 网盟
			case 2:
				list = ExcelUtils.getNetAffReport(upload);
				updateExpense(list);
				break;
			// 搜搜
			case 3:
				list = ExcelUtils.getSoSoReport(upload);
				addExpense(list);
				break;
			// 雅虎
			case 4:
				list = ExcelUtils.getYahooReport(upload);
				addExpense(list);
				break;
			}
			calcOldService.reCalc(accountId);
			this.addActionMessage("导入成功!");
		} catch (IOException e) {
			this.addActionError("文件类型错误！");
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage() + "导入失败！");
		}
		return SUCCESS;
	}

	/**
	 * 更新数据
	 * 
	 * @param list
	 */
	private void updateExpense(List<ServiceAdjustBean> list) {
		for (ServiceAdjustBean bean : list) {
			Expense expense = expenseService.getExpenseByDateAndId(accountId, bean.getSumDate());
			if (expense != null) {
				expense.setOriginalNetAff(bean.getSum());
				expense.setNetAff(bean.getSum());
				expenseService.saveTransactional(expense);
			}
		}
	}

	/**
	 * 增加数据
	 * 
	 * @param list
	 */
	private void addExpense(List<ServiceAdjustBean> list) {
	}

	public String show() {
		// Account account =
		// accountService.getAccountByNameMediaId(name,mediaId);
		// if (account == null) {
		// account = accountService.getAccountBySv3Id(sv3Id);
		// }
		// if (account!=null){
		// accountId = account.getId();
		return SUCCESS;
		// } else {
		// return ERROR;
		// }
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getSv3Id() {
		return sv3Id;
	}

	public void setSv3Id(Integer sv3Id) {
		this.sv3Id = sv3Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMediaId() {
		return mediaId;
	}

	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

}