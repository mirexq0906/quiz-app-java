package com.example.exception;

public class FileProcessorException extends RuntimeException {
    public FileProcessorException(String message) {
        super(message);
    }
}
