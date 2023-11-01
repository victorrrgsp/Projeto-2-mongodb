package com.victorgsp.workshopmongo.services.exceptions;

public class ObjectNotFoundExcetion extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundExcetion(String msg){
        super(msg);
    }
}
