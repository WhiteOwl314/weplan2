package com.whiteowl.weplan.task.service;


import java.util.List;

import org.springframework.dao.DataAccessException;

public interface TaskService {

	public List listInboxTasks() throws DataAccessException;

}
