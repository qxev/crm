package cn.finance.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.AccountDao;
import cn.finance.dao.ChannelDao;
import cn.finance.dao.ClientDao;
import cn.finance.dao.MediaDao;
import cn.finance.dao.PmDao;
import cn.finance.dao.ProjectDao;
import cn.finance.dao.SysInfoDao;
import cn.finance.model.Account;
import cn.finance.model.Channel;
import cn.finance.model.Client;
import cn.finance.model.Media;
import cn.finance.model.Project;
import cn.springside.modules.orm.Page;

@Transactional
public class AccountService {

	private AccountDao accountDao;

	private ClientDao clientDao;

	private ProjectDao projectDao;

	private ChannelDao channelDao;

	private MediaDao mediaDao;

	private PmDao pmDao;

	private SysInfoDao sysInfoDao;

	@Transactional(readOnly = true)
	public int getTotalCount(Page<Account> page) {
		return accountDao.getTotalCount(page);
	}

	public List<Account> getAllByMediaName(String name) {
		return accountDao.getAllByMediaName(name);
	}

	public List<Account> getAllByProjectId(Integer projectId) {
		return accountDao.getAllByProjectId(projectId);
	}

	public List<Account> getAllByProjectChannelId(Integer projectId, Integer channelId) {
		return accountDao.getAllByProjectChannelId(projectId, channelId);
	}

	public Account getAccountByNameMediaId(String name, Integer mediaId) {
		return accountDao.getAccountByNameMediaId(name, mediaId);
	}

	public Account getAccountBySv3Id(Integer sv3Id) {
		return accountDao.getAccountBySv3Id(sv3Id);
	}

	public Account getAccountById(Integer id) {
		return accountDao.getAccountById(id);
	}

	public Integer getAllStatusByChannelId(Integer channelId) {
		return accountDao.getAllStatusByChannelId(channelId);
	}

	public Integer getAllStatusByProjectId(Integer projectId) {
		return accountDao.getAllStatusByProjectId(projectId);
	}

	@Transactional(readOnly = true)
	public List<Account> getAll() {
		return accountDao.getAll();
	}

	@Transactional(readOnly = true)
	public int getAlertListCount(Page<Account> page) {
		Integer alertDays = new Integer(sysInfoDao.getSysInfoByName("alert_days").getValue());
		return accountDao.getAlertListCount(page, alertDays);
	}

	@Transactional(readOnly = true)
	public List<Account> getAlertList(Page<Account> page, boolean hasTotal) {
		Integer alertDays = new Integer(sysInfoDao.getSysInfoByName("alert_days").getValue());
		return accountDao.getAlertList(page, hasTotal, alertDays);
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Transactional(readOnly = true)
	public List<Account> getAll(Page<Account> page, boolean hasTotal) {
		return accountDao.getAll(page, hasTotal);
	}

	public BigDecimal getTotalBalance(Page<Account> page) {
		return accountDao.getTotalBalance(page);
	}

	public BigDecimal getTotalDailyBudget(Page<Account> page) {
		return accountDao.getTotalDailyBudget(page);
	}

	public List<Account> getAccountByExchangeId(Integer exchangeId) {
		return accountDao.getAccountByExchangeId(exchangeId);
	}

	public void save(Account account) {
		accountDao.save(account);
	}

	@Transactional
	public void saveTransactional(Account account) {
		accountDao.save(account);
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public PmDao getPmDao() {
		return pmDao;
	}

	public void setPmDao(PmDao pmDao) {
		this.pmDao = pmDao;
	}

	/**
	 * 修改每日拉报表的状态，控制job运行或不运行 status = 4 暂停 status = 2 修改
	 */
	public void updateJob(Account account) {
		Integer status = 4;
		if (account.getGetData() == 1) {
			status = 2;
		}
	}

	@Transactional
	public void updateAccount(Account account) {
		updateStatus(account);
		save(account);
		updateJob(account);
	}

	/**
	 * 根据帐号的状态，改变帐号父级的状态
	 */
	@Transactional
	public void updateStatus(Account account) {
		Channel channel = account.getChannel();
		channel.setStatus(getAllStatusByChannelId(channel.getId()));
		channelDao.save(channel);
		Project project = account.getProject();
		project.setStatus(getAllStatusByProjectId(project.getId()));
		projectDao.save(project);
		Client client = account.getProject().getClient();
		client.setStatus(projectDao.getAllStatusByClientId(client.getId()));
		clientDao.save(client);
		Media media = account.getChannel().getMedia();
		media.setStatus(channelDao.getAllStatusByMediaId(media.getId()));
		mediaDao.save(media);
	}

	public Account createAccount() {
		return accountDao.createAccount();
	}

	public ClientDao getClientDao() {
		return clientDao;
	}

	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	public ProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public ChannelDao getChannelDao() {
		return channelDao;
	}

	public void setChannelDao(ChannelDao channelDao) {
		this.channelDao = channelDao;
	}

	public MediaDao getMediaDao() {
		return mediaDao;
	}

	public void setMediaDao(MediaDao mediaDao) {
		this.mediaDao = mediaDao;
	}

	public SysInfoDao getSysInfoDao() {
		return sysInfoDao;
	}

	public void setSysInfoDao(SysInfoDao sysInfoDao) {
		this.sysInfoDao = sysInfoDao;
	}

}
