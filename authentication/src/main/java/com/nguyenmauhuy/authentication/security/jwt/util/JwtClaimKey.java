package com.nguyenmauhuy.authentication.security.jwt.util;

public enum JwtClaimKey {
    ID("id"),
    USERNAME("userName"),
    ROLE("role");

    private String value;

    JwtClaimKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
