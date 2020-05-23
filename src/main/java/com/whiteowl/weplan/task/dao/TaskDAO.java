package com.whiteowl.weplan.task.dao;


import java.util.List;

import org.springframework.dao.DataAccessException;

public interface TaskDAO {

	public List selectAllInboxTaskList() throws DataAccessException;

}
