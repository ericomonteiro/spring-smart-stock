package com.github.ericomonteiro.smartstock.config.error.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ResourceNotFoundException extends BusinessException {
    private final String entity;
    private final String key;
}
