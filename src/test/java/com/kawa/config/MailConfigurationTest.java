package com.kawa.config;

import static org.assertj.core.api.Assertions.assertThat;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.store.FolderException;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import javax.mail.MessagingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;

public class MailConfigurationTest {

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA@gmail.com";
    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";

    private static final String DEFAULT_SUBJECT = "Test Subject";

    private static final String DEFAULT_CONTENT = "Test Msg";

    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
        .withConfiguration(GreenMailConfiguration.aConfig().withUser("duke", "springboot"))
        .withPerMethodLifecycle(false);

    @BeforeAll
    public static void setup() {
        greenMail.setUser(DEFAULT_EMAIL, DEFAULT_PASSWORD);
    }

    @BeforeEach
    public void clean() throws FolderException {
        greenMail.purgeEmailFromAllMailboxes();
    }

    @Test
    @Timeout(30)
    void testSendEmail() throws MessagingException {
        GreenMailUtil.sendTextEmail(DEFAULT_EMAIL, "duke@localhost", DEFAULT_SUBJECT, DEFAULT_CONTENT, ServerSetupTest.SMTP);

        greenMail.waitForIncomingEmail(1);

        assertThat(greenMail.getReceivedMessages()).hasSize(1);
        assertThat(greenMail.getReceivedMessages()[0].getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(GreenMailUtil.getBody(greenMail.getReceivedMessages()[0])).isEqualTo(DEFAULT_CONTENT);
        assertThat(greenMail.getReceivedMessages()[0].getFrom()[0].toString()).hasToString("duke@localhost");
        assertThat(greenMail.getReceivedMessages()[0].getAllRecipients()[0].toString()).hasToString(DEFAULT_EMAIL);
    }
}
