package cn.finance.web.action.json;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.finance.model.Channel;
import cn.finance.service.ChannelService;

import com.opensymphony.xwork2.Action;

public class GetChannelAction extends JdbcDaoSupport  {

	private static final long serialVersionUID = 1L;

	private Integer mediaId;
	
	private ChannelService channelService;
	
	private List<Channel> channels = new ArrayList<Channel>();

	/**
	 * 根据客户id查找所有的项目
	 * @return
	 */
	public String execute(){
		channels = channelService.getAllByMediaId(mediaId);
		return Action.SUCCESS;
	}

	public List<Channel> getChannels() {
		return channels;
	}

	public void setProjects(List<Channel> channels) {
		this.channels = channels;
	}

	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	public ChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(ChannelService channelService) {
		this.channelService = channelService;
	}
}
