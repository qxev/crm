package cn.finance.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.ExchangeDao;
import cn.finance.dao.TaskLogDao;
import cn.finance.model.Exchange;
import cn.finance.model.ExchangeHistory;
import cn.finance.model.TaskLog;
import cn.finance.model.bean.ResultBean;
import cn.springside.modules.orm.Page;

public class ExchangeService {

	private ExchangeDao exchangeDao;

	private TaskLogDao taskLogDao;

	@Transactional(readOnly = true)
	public List<Exchange> getAll() {
		return exchangeDao.getAll();
	}

	@Transactional(readOnly = true)
	public List<ExchangeHistory> getAll(Page<ExchangeHistory> page) {
		return exchangeDao.getAll(page);
	}

	@Transactional(readOnly = true)
	public List<ExchangeHistory> getCurrent() {
		return exchangeDao.getCurrent();
	}

	public Exchange getExchangeById(Integer id) {
		return exchangeDao.getExchangeById(id);
	}

	public ExchangeHistory getExchangeHistoryById(Integer id) {
		return exchangeDao.getExchangeHistoryById(id);
	}

	public List<ExchangeHistory> getExchangeHistoryByExchangeId(Integer id) {
		return exchangeDao.getExchangeHistoryByExchangeId(id);
	}

	public ExchangeDao getExchangeDao() {
		return exchangeDao;
	}

	public void setExchangeDao(ExchangeDao exchangeDao) {
		this.exchangeDao = exchangeDao;
	}

	public void save(Exchange exchange) {
		exchangeDao.save(exchange);
	}

	@Transactional
	public void saveHistory(ExchangeHistory exchangeHistory) {
		exchangeDao.saveHistory(exchangeHistory);
	}

	public TaskLogDao getTaskLogDao() {
		return taskLogDao;
	}

	public void setTaskLogDao(TaskLogDao taskLogDao) {
		this.taskLogDao = taskLogDao;
	}

	@Transactional(readOnly = true)
	public int getTotalCount(Page<ExchangeHistory> page) {
		return exchangeDao.getTotalCount(page);
	}

	public void doEveryMonthJob() {
		List<Exchange> exchanges = getAll();
		for (Exchange exchange : exchanges) {
			try {
				if (exchange.getId() != 1) {
					StringBuffer url = new StringBuffer("http://xurrency.com/api/");
					url.append(exchange.getShortName()).append("/cny/1");
					URL u = new URL(url.toString());
					URLConnection conn = u.openConnection();
					conn.connect();
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String content = br.readLine();
					JSONObject object = JSONObject.fromObject(content);
					ResultBean result = (ResultBean) JSONObject.toBean(object, ResultBean.class);
					ExchangeHistory history = new ExchangeHistory();
					history.setExchangeDate(new Date());
					history.setValue(result.getResult().getValue());
					history.setExchange(exchange);
					exchangeDao.saveHistory(history);
					TaskLog taskLog = taskLogDao.createTaskLog();
					taskLog.setAccountId(exchange.getId());
					taskLog.setType(10);
					taskLogDao.save(taskLog);
				}
			} catch (Exception e) {
				TaskLog taskLog = taskLogDao.createTaskLog();
				taskLog.setErrorLog(e.getMessage());
				taskLog.setAccountId(exchange.getId());
				taskLog.setType(11);
				taskLogDao.save(taskLog);
			}
		}
	}

	public void updateFuture(BigDecimal value, Date date, Integer exchangeId) {
		exchangeDao.updateFuture(value, date, exchangeId);
	}

	public void updateAccountRate(BigDecimal value, Date startDate, Date endDate, Integer exchangeId) {
		exchangeDao.updateAccountRate(value, startDate, endDate, exchangeId);
	}
}
