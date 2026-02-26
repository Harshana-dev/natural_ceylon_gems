package com.harshana.ceylon_gems.dto.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) { super(message); }
}