package com.poc8.students;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
public StudentNotFoundException(String message) {
	super(message);
}
public StudentNotFoundException(String message,Throwable t) {
	super(message,t);
}
}