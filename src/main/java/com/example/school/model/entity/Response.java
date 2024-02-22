package com.example.school.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private int status;
    private String statusMessage;
    private Object data;
}
