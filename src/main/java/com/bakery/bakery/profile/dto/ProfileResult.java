package com.bakery.bakery.profile.dto;

import lombok.Data;

@Data
public class ProfileResult {
    private String message;
    private Long process;
    private String bakerUsername;
    private String bakerPassword;
}
