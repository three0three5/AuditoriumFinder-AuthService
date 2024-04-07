package ru.orobtsovv.authservice.utils;

public class MQConstants {
    public static final String PROFILE_CREATE = "profile-create-queue";
    public static final String TELEGRAM_SAVE = "telegram-propagate-queue";
    public static final String TELEGRAM_REMOVE = "telegram-remove-queue";
    public static final String PROFILE_DELETE_AUTH = "profile-delete-auth-queue";
}
