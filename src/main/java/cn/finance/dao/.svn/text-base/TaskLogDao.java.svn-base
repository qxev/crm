package cn.finance.dao;

import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.finance.model.TaskLog;

public class TaskLogDao extends HibernateDaoSupport {

	public void save(TaskLog taskLog) {
		this.getHibernateTemplate().saveOrUpdate(taskLog);
	}
	
	public TaskLog createTaskLog() {
		TaskLog taskLog = new TaskLog();
		taskLog.setDeleted("0");
		taskLog.setCreateAt(new Date());
		return taskLog;
	}

	@SuppressWarnings("unchecked")
	public TaskLog getTaskLogById(Integer id) {
		List<TaskLog> list = (List<TaskLog>)this.getHibernateTemplate().find(
				" from TaskLog where id = ? ", id);
		if (list != null && list.size() > 0) {
			return (TaskLog) list.get(0);
		}
		return null;
	}
}
