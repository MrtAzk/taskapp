package com.mert.taskmanagement.taskapp.core.exception;

import com.mert.taskmanagement.taskapp.core.result.Result;
import com.mert.taskmanagement.taskapp.core.result.ResultData;
import com.mert.taskmanagement.taskapp.core.utils.CreateResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExpectionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundError(NotFoundException e){
        return new ResponseEntity<>(CreateResult.notFound(e.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<ResultData<List<String>>>handleValidationErrors(MethodArgumentNotValidException e){

        List<String> expectionList=e.getBindingResult().getFieldErrors().stream().map(error->error.getDefaultMessage()).toList();

        return new ResponseEntity<>(CreateResult.validateError(expectionList),HttpStatus.BAD_REQUEST);
    }
}
