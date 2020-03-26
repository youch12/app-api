package com.octo.ecom.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Builder
@Data
public class CustomMessage {

    private String message;
    private HttpStatus status;

}
