package com.todo.service;

import java.util.List;

import com.todo.dto.TodoDTO;

public interface ITodoService {
	
	TodoDTO create(TodoDTO todo);

	TodoDTO delete(String id);

	List<TodoDTO> findAll();

	TodoDTO findById(String id);

	TodoDTO update(TodoDTO todo);

}
