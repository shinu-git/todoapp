package com.todo.exception;

public class TodoException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TodoException(String id) {
        super(String.format("No todo entry found with id: <%s>", id));
    }
}
