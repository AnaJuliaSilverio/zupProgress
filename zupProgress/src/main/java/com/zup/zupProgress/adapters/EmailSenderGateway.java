package com.zup.zupProgress.adapters;

public interface EmailSenderGateway {
    void sendEmail(String toEmail, String subject, String body);
}
