package com.backend.technlog.domain;

public enum UserRole {
    ADMIN("admin"),
    VISITOR("visitor"),
    REGULAR("regular");

    final String role;

    UserRole(String role) {
        this.role=role;
    }
}
