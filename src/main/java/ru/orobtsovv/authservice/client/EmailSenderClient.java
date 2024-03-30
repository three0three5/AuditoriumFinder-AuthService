package ru.orobtsovv.authservice.client;

public interface EmailSenderClient {

    void send(String email, String code);
}
