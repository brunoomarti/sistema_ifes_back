package com.sistemaifes.sistemaifes.util;

public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    TEACHER("teacher"),
    COORDINATOR("coordinator"),
    STUDENT("student");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
