package cn.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.ChannelDao;
import cn.finance.model.Channel;
import cn.springside.modules.orm.Page;

public class ChannelService {

	private ChannelDao channelDao;

	@Transactional(readOnly = true)
	public List<Channel> getAll(Page<Channel> page,boolean hasTotal) {
		return channelDao.getAll(page,hasTotal);
	}

	@Transactional(readOnly = true)
	public int getTotalCount(Page<Channel>page) {
		return channelDao.getTotalCount(page);
	}
	
	@Transactional(readOnly = true)
	public List<Channel> getAllNo(Page<Channel> page,boolean hasTotal) {
		return channelDao.getAllNo(page,hasTotal);
	}

	@Transactional(readOnly = true)
	public int getTotalCountNo(Page<Channel>page) {
		return channelDao.getTotalCountNo(page);
	}
	
	@Transactional
	public void save(Channel channel) {
		channelDao.save(channel);
	}

	@Transactional(readOnly = true)
	public List<Channel> getAllByMediaId(Integer mediaId) {
		return channelDao.getAllByMediaId(mediaId);
	}
	
	public Integer getAllStatusByMediaId(Integer projectId) {
		return channelDao.getAllStatusByMediaId(projectId);
	}
	
	@Transactional(readOnly = true)
	public Channel getById(Integer id) {
		return channelDao.getById(id);
	}
	
	public ChannelDao getChannelDao() {
		return channelDao;
	}

	public void setChannelDao(ChannelDao channelDao) {
		this.channelDao = channelDao;
	}
	
}
