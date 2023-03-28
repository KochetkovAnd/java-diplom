package ru.scratch.domain.enums;

public enum Permission {
    CREATE_LEVELS("create_levels");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
