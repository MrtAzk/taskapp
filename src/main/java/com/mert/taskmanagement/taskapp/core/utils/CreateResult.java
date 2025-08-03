package com.mert.taskmanagement.taskapp.core.utils;

import com.mert.taskmanagement.taskapp.core.result.Result;
import com.mert.taskmanagement.taskapp.core.result.ResultData;
import com.mert.taskmanagement.taskapp.dto.response.CursorResponse;
import org.springframework.data.domain.Page;

public class CreateResult {

    public static <T>ResultData<T> created(T data){
       return new ResultData<>(true,Msg.CREATED,"201",data);
    }
    public static <T>ResultData<T> validateError(T data) {
        return new ResultData<>(true,Msg.VALIDATE_ERROR,"400",data);
    }

    public static <T>ResultData<T> success(T data) {
        return new ResultData<>(true,Msg.SUCCESS,"209",data);
    }
    public static <T>ResultData<T> delete(T data) {
        return new ResultData<>(true,Msg.DELETE,"200",data);
    }
    public static Result notFound(String msg) {
        return new Result(true,msg,"404");
    }

    public static <T> ResultData<CursorResponse<T>> getAll(Page<T> pageData){

        CursorResponse<T> cursorResponse =new CursorResponse<>();
        cursorResponse.setItems(pageData.getContent());
        cursorResponse.setTotalElements(pageData.getTotalElements());
        cursorResponse.setPageNumber(pageData.getNumber());
        cursorResponse.setPageSize(pageData.getSize());
       return CreateResult.success(cursorResponse);
    }
}
