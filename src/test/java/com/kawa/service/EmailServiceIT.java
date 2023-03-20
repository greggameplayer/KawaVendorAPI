package com.kawa.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.zxing.WriterException;
import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.store.FolderException;
import com.icegreen.greenmail.util.ServerSetupTest;
import com.kawa.IntegrationTest;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * Integration tests for the {@link EmailService} service.
 */

@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
public class EmailServiceIT {

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA@gmail.com";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";

    private static final String DEFAULT_TOKEN =
        "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY3OTMxODkyOH0.AA0SQ9EaSFyidYohoWFoXnr80_OT4_dxs-VCc0k7KpfTYZuEiI1uTThRDml-uPoCUoJkQxaKNQ6GDBynhQ-8AA";

    private static final String DEFAULT_SUBJECT = "test";

    private static final String DEFAULT_CONTENT = "test";

    @Autowired
    private EmailService emailService;

    @Autowired
    private FreeMarkerConfigurer freemarkerConfigurer;

    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
        .withConfiguration(GreenMailConfiguration.aConfig().withUser("duke", "springboot"))
        .withPerMethodLifecycle(false);

    @BeforeAll
    public static void init() {
        greenMail.setUser(DEFAULT_EMAIL, DEFAULT_PASSWORD);
    }

    @BeforeEach
    public void setUp() throws FolderException {
        greenMail.purgeEmailFromAllMailboxes();
    }

    @Test
    void testGenerateQRCode() throws IOException, WriterException {
        File file = emailService.generateQRCode(DEFAULT_TOKEN);
        File existingImg = new File("src/test/resources/static/images/qrcode.png");
        assertThat(file).hasSameBinaryContentAs(existingImg);
        Files.delete(file.toPath());
    }

    @Test
    @Timeout(30)
    void testSendEmail() throws MessagingException, IOException {
        emailService.sendEmail(DEFAULT_EMAIL, DEFAULT_SUBJECT, DEFAULT_CONTENT, false, false, null);
        assertThat(greenMail.getReceivedMessages()).hasSize(1);
        assertThat(greenMail.getReceivedMessages()[0].getSubject()).isEqualTo(DEFAULT_SUBJECT);
    }

    @Test
    @Timeout(30)
    void testSendEmailWithInlineAttachments() throws Exception {
        File file = new File("src/test/resources/static/images/qrcode.png");
        Map<String, File> inlineAttachments = Map.of("qrcode", file);
        emailService.sendEmail(DEFAULT_EMAIL, DEFAULT_SUBJECT, DEFAULT_CONTENT, true, true, inlineAttachments);
        assertThat(greenMail.getReceivedMessages()).hasSize(1);
        assertThat(greenMail.getReceivedMessages()[0].getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(greenMail.getReceivedMessages()[0].getContentType()).contains("multipart/related");
        assertThat(greenMail.getReceivedMessages()[0].getContent()).isInstanceOf(MimeMultipart.class);
        MimeMultipart mimeMultipart = (MimeMultipart) greenMail.getReceivedMessages()[0].getContent();
        assertThat(mimeMultipart.getCount()).isEqualTo(2);
        assertThat(mimeMultipart.getBodyPart(0).getContent()).isInstanceOf(String.class);
        assertThat(mimeMultipart.getBodyPart(1).getFileName()).isEqualTo("qrcode.png");
        // convert the inline image to file and compare it with the original file
        File inlineImg = File.createTempFile("qrcode", ".png");
        Files.write(inlineImg.toPath(), mimeMultipart.getBodyPart(1).getInputStream().readAllBytes());
        assertThat(inlineImg).hasSameBinaryContentAs(file);
        Files.delete(inlineImg.toPath());
    }

    @Test
    @Timeout(30)
    void testSendEmailFromTemplate() throws MessagingException, IOException, TemplateException {
        Map<String, Object> model = Map.of("name", "test");

        emailService.sendEmailFromTemplate(DEFAULT_EMAIL, DEFAULT_SUBJECT, "test.ftlh", model, false, true, null);
        assertThat(greenMail.getReceivedMessages()).hasSize(1);
        assertThat(greenMail.getReceivedMessages()[0].getSubject()).isEqualTo(DEFAULT_SUBJECT);

        assertThat(greenMail.getReceivedMessages()[0].getContent()).isInstanceOf(String.class);

        Template template = freemarkerConfigurer.getConfiguration().getTemplate("test.ftlh");
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model).replaceAll("\r\n", "\n");
        String receivedContent = greenMail.getReceivedMessages()[0].getContent().toString().replaceAll("\r\n", "\n");

        assertThat(receivedContent).isEqualTo(content);
    }

    @Test
    @Timeout(30)
    void testSendEmailFromTemplateWithInlineAttachments() throws Exception {
        Map<String, Object> model = Map.of("name", "test");
        File file = new File("src/test/resources/static/images/qrcode.png");
        Map<String, File> inlineAttachments = Map.of("qrcode", file);

        emailService.sendEmailFromTemplate(DEFAULT_EMAIL, DEFAULT_SUBJECT, "test.ftlh", model, true, true, inlineAttachments);
        assertThat(greenMail.getReceivedMessages()).hasSize(1);
        assertThat(greenMail.getReceivedMessages()[0].getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(greenMail.getReceivedMessages()[0].getContentType()).contains("multipart/related");
        assertThat(greenMail.getReceivedMessages()[0].getContent()).isInstanceOf(MimeMultipart.class);
        MimeMultipart mimeMultipart = (MimeMultipart) greenMail.getReceivedMessages()[0].getContent();
        assertThat(mimeMultipart.getCount()).isEqualTo(2);
        assertThat(mimeMultipart.getBodyPart(0).getContent()).isInstanceOf(String.class);
        assertThat(mimeMultipart.getBodyPart(1).getFileName()).isEqualTo("qrcode.png");
        // convert the inline image to file and compare it with the original file
        File inlineImg = File.createTempFile("qrcode", ".png");
        Files.write(inlineImg.toPath(), mimeMultipart.getBodyPart(1).getInputStream().readAllBytes());
        assertThat(inlineImg).hasSameBinaryContentAs(file);
        Files.delete(inlineImg.toPath());

        Template template = freemarkerConfigurer.getConfiguration().getTemplate("test.ftlh");
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model).replaceAll("\r\n", "\n");

        // get bodypart content without ? instead of "
        String receivedContent = mimeMultipart.getBodyPart(0).getContent().toString().replaceAll("\r\n", "\n");

        assertThat(receivedContent).isEqualTo(content);
    }
}
