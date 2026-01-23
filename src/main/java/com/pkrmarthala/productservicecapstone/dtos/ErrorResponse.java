package com.pkrmarthala.productservicecapstone.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse
{
    private String status;              // "failure"
    private String message;             // error message
    private String path;                 // request path
    private LocalDateTime timestamp;    // when it happened

}
