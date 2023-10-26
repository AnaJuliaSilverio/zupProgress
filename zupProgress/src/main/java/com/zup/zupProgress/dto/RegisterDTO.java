package com.zup.zupProgress.dto;

import com.zup.zupProgress.model.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
