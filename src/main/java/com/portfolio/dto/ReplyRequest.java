package com.portfolio.dto;

import lombok.Data;

@Data
public class ReplyRequest {
    private String email;
    private String subject;
    private String message;
}