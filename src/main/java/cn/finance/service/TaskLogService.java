package cn.finance.service;

import cn.finance.dao.TaskLogDao;
import cn.finance.model.TaskLog;

public class TaskLogService {

	private TaskLogDao taskLogDao;

	public void save(TaskLog taskLog) {
		taskLogDao.save(taskLog);
	}

	public TaskLog createTaskLog(){
		return taskLogDao.createTaskLog();
	}

	public TaskLogDao getTaskLogDao() {
		return taskLogDao;
	}

	public void setTaskLogDao(TaskLogDao taskLogDao) {
		this.taskLogDao = taskLogDao;
	}
	
	public TaskLog getTaskLogById(Integer id){
		return taskLogDao.getTaskLogById(id);
	}
}
