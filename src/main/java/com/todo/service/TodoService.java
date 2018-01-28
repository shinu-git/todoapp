package com.todo.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.dto.TodoDTO;
import com.todo.entity.Todo;
import com.todo.exception.TodoException;
import com.todo.repository.TodoRepository;


@Service


public class TodoService implements ITodoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoService.class);


    @Autowired
    private TodoRepository todoRepository;

	/*
	 * @Autowired TodoService(TodoRepository todoRepository) { this.todoRepository =
	 * todoRepository; }
	 */

    @Override
    public TodoDTO create(TodoDTO todo) {
        LOGGER.info("Creating a new todo entry with information: {}", todo);

        Todo persisted = new Todo();
        persisted.setTitle(todo.getTitle());
        persisted.setDescription(todo.getDescription());
               

        todoRepository.save(persisted);
        LOGGER.info("Created a new todo entry with information: {}", persisted);

        return convertToDTO(persisted);
    }

    @Override
    public TodoDTO delete(String id) {
        LOGGER.info("Deleting a todo entry with id: {}", id);

        Todo deleted = findTodoById(id);
        ((TodoRepository) todoRepository).delete(deleted);

        LOGGER.info("Deleted todo entry with informtation: {}", deleted);

        return convertToDTO(deleted);
    }

    @Override
    public List<TodoDTO> findAll() {
        LOGGER.info("Finding all todo entries.");

        List<Todo> todos = todoRepository.findAll();

        LOGGER.info("Found {} todo entries", todos.size());

        return convertToDTOs(todos);
    }

    private List<TodoDTO> convertToDTOs(List<Todo> models) {
        return models.stream()
                .map(this::convertToDTO)
                .collect(toList());
    }

    @Override
    public TodoDTO findById(String id) {
        LOGGER.info("Finding todo entry with id: {}", id);

        Todo found = findTodoById(id);

        LOGGER.info("Found todo entry: {}", found);

        return convertToDTO(found);
    }

    @Override
    public TodoDTO update(TodoDTO todo) {
        LOGGER.info("Updating todo entry with information: {}", todo);

        Todo updated = findTodoById(todo.getId());
        updated.update(todo.getTitle(), todo.getDescription());
        updated = todoRepository.save(updated);

        LOGGER.info("Updated todo entry with information: {}", updated);

        return convertToDTO(updated);
    }

    private Todo findTodoById(String id) {
        Optional<Todo> result = todoRepository.findById(id);
        return result.orElseThrow(() -> new TodoException(id));

    }

    private TodoDTO convertToDTO(Todo model) {
        TodoDTO dto = new TodoDTO();

        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setDescription(model.getDescription());

        return dto;
    }
}
