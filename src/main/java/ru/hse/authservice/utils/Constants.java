package ru.hse.authservice.utils;

public class Constants {
    public static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyz-_ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String INVALID_EMAIL_MESSAGE = "Некорректный email";
    public static final String INVALID_PASSWORD_MESSAGE = "Пароль должен содержать не менее 8 символов, " +
            "содержать латинские символы в различных регистрах и хотя бы одну цифру";
    public static final String TELEGRAM_PROVIDED = "Telegram handler should be provided";
    public static final String PROOF_KEY_PROVIDED = "Proof key should be provided";
    public static final String NICKNAME_MESSAGE = "Никнейм должен содержать не менее 2 символов";
    public static final int PROOF_KEY_LENGTH = 128;
}
