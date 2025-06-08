package com.example.movieApp.cinemaNote.exception;


// com.example.movieApp.cinemaNote.exception 패키지에 생성
public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}