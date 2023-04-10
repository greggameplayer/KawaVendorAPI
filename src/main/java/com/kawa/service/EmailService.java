package com.kawa.service;

import com.google.zxing.WriterException;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.mail.MessagingException;

/**
 * Service Interface for sending emails.
 */
public interface EmailService {
    /**
     * Send an email with inline attachments.
     *
     * @param to the email address of the recipient
     * @param subject the subject of the email
     * @param content the content of the email
     * @param isMultipart whether the email is a multipart email
     * @param isHtml whether the email is HTML
     * @param inlineAttachments the inline attachments
     * @throws MessagingException if the message could not be sent
     * @throws IOException if the message could not be sent
     */
    void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml, Map<String, File> inlineAttachments)
        throws MessagingException, IOException;

    /**
     * Send an email with inline attachments using FreeMarker templates.
     *
     * @param to the email address of the recipient
     * @param subject the subject of the email
     * @param templateName the name of the FreeMarker template
     * @param templateModel the model to be used by the FreeMarker template
     * @param isMultipart whether the email is a multipart email
     * @param isHtml whether the email is HTML
     * @param inlineAttachments the inline attachments
     * @throws MessagingException if the message could not be sent
     */
    void sendEmailFromTemplate(
        String to,
        String subject,
        String templateName,
        Map<String, Object> templateModel,
        boolean isMultipart,
        boolean isHtml,
        Map<String, File> inlineAttachments
    ) throws MessagingException;

    /**
     * Generate QR code for the given authentication token.
     *
     * @param token the authentication token
     * @return the QR code
     * @throws WriterException if the QR code could not be generated
     * @throws IOException if the QR code could not be written
     */
    File generateQRCode(String token) throws WriterException, IOException;
}
