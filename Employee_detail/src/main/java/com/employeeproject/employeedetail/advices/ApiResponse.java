package com.employeeproject.employeedetail.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private T data;

    private ApiError error;

    @JsonFormat(pattern = "hh-mm-ss dd-mm-yyyy")
    private LocalDateTime timeStamp;

    public ApiResponse(){


        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(T data){

        this();
        this.data = data;
    }

    public ApiResponse(ApiError error){
        this();
        this.error = error;
    }
}
