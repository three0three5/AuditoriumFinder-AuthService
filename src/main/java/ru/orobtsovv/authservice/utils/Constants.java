package ru.orobtsovv.authservice.utils;

public class Constants {
    public static final String ISSUER = "audfinder-authservice";
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String EMAIL_REGEX = "^[\\w-\\.]+@(hse\\.ru|edu.hse.ru)$";
    public static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyz-_ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String INVALID_EMAIL_MESSAGE = "Некорректный email";
    public static final String INVALID_PASSWORD_MESSAGE = "Пароль должен содержать не менее 8 символов, " +
            "содержать латинские символы в различных регистрах и хотя бы одну цифру";
    public static final String NICKNAME_MESSAGE = "Никнейм должен содержать не менее 2 символов";
    public static final String EMAIL_CODE_SENT = "Код отправлен";
    public static final String EMAIL_CODE_EXISTS_MESSAGE = "Код уже был отправлен";
    public static final String EMAIL_CODE_EXPIRED = "Код недействителен";
    public static final String EMAIL_CODE_NOT_VALID = "Код неверен";
    public static final String ACCOUNT_EXISTS_CONFLICT = "Аккаунт уже существует";
    public static final String ACCOUNT_NOT_FOUND = "Неправильный email или пароль";
    public static final String WRONG_PASSWORD = "Неправильный email или пароль";
    public static final String REFRESH_EXPIRED = "Session expired";
    public static final String INVALID_DATE_MESSAGE = "Invalid bannedUntil date";
    public static final String PASSWORD_UPDATED_RESPONSE = "Пароль обновлен";
    public static final String BANNED_MESSAGE = "Аккаунт заблокирован модератором";
}
