package cn.finance.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.finance.dao.MediaDao;
import cn.finance.model.Media;
import cn.springside.modules.orm.Page;

public class MediaService {

	private MediaDao mediaDao;

	@Transactional(readOnly = true)
	public List<Media> getAll(Page<Media> page, boolean hasTotal) {
		return mediaDao.getAll(page,hasTotal);
	}
	
	@Transactional(readOnly = true)
	public List<Media> getAll() {
		return mediaDao.getAll();
	}

	@Transactional(readOnly = true)
	public int getTotalCount(Page<Media>page) {
		return mediaDao.getTotalCount(page);
	}
	
	public Media getMediaById(Integer id) {
		return mediaDao.getMediaById(id);
	}
	
	@Transactional
	public void save(Media media){
		mediaDao.save(media);
	}
	
	public MediaDao getMediaDao() {
		return mediaDao;
	}

	public void setMediaDao(MediaDao mediaDao) {
		this.mediaDao = mediaDao;
	}


}
