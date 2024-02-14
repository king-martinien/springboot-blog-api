package com.kingmartinien.springbootblogapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {

    private int errorCode;
    private List<String> message;
    private Instant datetime;
    private String details;

}
