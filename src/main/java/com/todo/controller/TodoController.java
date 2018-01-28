package com.todo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.todo.dto.TodoDTO;
import com.todo.exception.TodoException;
import com.todo.service.TodoService;
@CrossOrigin
@RestController
@RequestMapping("/api/todo")
class TodoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);
	/*
	 * @Autowired private final TodoService service;
	 * 
	 * @Autowired TodoController(TodoService service) { this.service = service; }
	 */
	@Autowired
	private TodoService todoService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	TodoDTO create(@RequestBody @Valid TodoDTO todoEntry) {
		LOGGER.info("Creating a new todo entry with information: {}", todoEntry);

		TodoDTO created = todoService.create(todoEntry);
		LOGGER.info("Created a new todo entry with information: {}", created);

		return created;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	TodoDTO delete(@PathVariable("id") String id) {
		LOGGER.info("Deleting todo entry with id: {}", id);

		TodoDTO deleted = todoService.delete(id);
		LOGGER.info("Deleted todo entry with information: {}", deleted);

		return deleted;
	}

	@RequestMapping(method = RequestMethod.GET)
	List<TodoDTO> findAll() {
		LOGGER.info("Finding all todo entries");

		List<TodoDTO> todoEntries = todoService.findAll();
		LOGGER.info("Found {} todo entries", todoEntries.size());

		return todoEntries;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	TodoDTO findById(@PathVariable("id") String id) {
		LOGGER.info("Finding todo entry with id: {}", id);

		TodoDTO todoEntry = todoService.findById(id);
		LOGGER.info("Found todo entry with information: {}", todoEntry);

		return todoEntry;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	TodoDTO update(@RequestBody @Valid TodoDTO todoEntry) {
		LOGGER.info("Updating todo entry with information: {}", todoEntry);

		TodoDTO updated = todoService.update(todoEntry);
		LOGGER.info("Updated todo entry with information: {}", updated);

		return updated;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleTodoNotFound(TodoException ex) {
		LOGGER.error("Handling error with message: {}", ex.getMessage());
	}
}
