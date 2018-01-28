package com.todo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.todo.entity.Todo;
@Transactional
public interface TodoRepository extends MongoRepository<Todo, String> {

	void delete(Todo deleted);


	Optional<Todo> findById(String id);

	//Todo save(Todo saved);
	
	@Query("{title:'?0'}")
	Todo findTodoByTitle(String title);
}
