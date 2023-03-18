package com.kawa.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.kawa.service.EmailService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.*;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    private final FreeMarkerConfigurer freemarkerConfigurer;

    @Value("${email-service.username}")
    private String from;

    public EmailServiceImpl(JavaMailSender javaMailSender, FreeMarkerConfigurer freemarkerConfigurer) {
        this.javaMailSender = javaMailSender;
        this.freemarkerConfigurer = freemarkerConfigurer;
    }

    @Override
    public void sendEmail(
        String to,
        String subject,
        String content,
        boolean isMultipart,
        boolean isHtml,
        Map<String, File> inlineAttachments
    ) throws MessagingException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, isMultipart, "UTF-8");
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        if (inlineAttachments != null) {
            for (Map.Entry<String, File> entry : inlineAttachments.entrySet()) {
                helper.addInline(entry.getKey(), entry.getValue());
            }
        }
        helper.setText(content, isHtml);

        javaMailSender.send(mimeMessage);
    }

    @Override
    public File generateQRCode(String token) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(token, BarcodeFormat.QR_CODE, 350, 350);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();

        MatrixToImageConfig config = new MatrixToImageConfig(0xFF3E2F06, 0xFFC6B77A);
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, config);

        try (FileOutputStream fos = new FileOutputStream("QRCode.png")) {
            fos.write(pngOutputStream.toByteArray());
        }
        return new File("QRCode.png");
    }

    @Override
    public void sendEmailFromTemplate(
        String to,
        String subject,
        String templateName,
        Map<String, Object> templateModel,
        boolean isMultipart,
        boolean isHtml,
        Map<String, File> inlineAttachments
    ) throws MessagingException {
        try {
            Template content = freemarkerConfigurer.getConfiguration().getTemplate(templateName);
            String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(content, templateModel);
            sendEmail(to, subject, htmlBody, isMultipart, isHtml, inlineAttachments);
        } catch (IOException e) {
            throw new MessagingException("Could not load FreeMarker template", e);
        } catch (TemplateException e) {
            throw new MessagingException("Could not parse FreeMarker template", e);
        }
    }
}
