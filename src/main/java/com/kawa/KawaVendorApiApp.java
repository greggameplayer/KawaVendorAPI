package com.kawa;

import com.kawa.config.ApplicationProperties;
import com.kawa.config.CRLFLogConverter;
import com.kawa.config.Constants;
import com.kawa.security.AuthoritiesConstants;
import com.kawa.security.jwt.TokenProvider;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import tech.jhipster.config.DefaultProfileUtil;
import tech.jhipster.config.JHipsterConstants;

@org.springframework.nativex.hint.TypeHint(
    types = {
        org.HdrHistogram.Histogram.class,
        org.HdrHistogram.ConcurrentHistogram.class,
        java.util.HashSet.class,
        liquibase.configuration.LiquibaseConfiguration.class,
        com.zaxxer.hikari.HikariDataSource.class,
        liquibase.change.core.LoadDataColumnConfig.class,
        org.hibernate.type.TextType.class,
        tech.jhipster.domain.util.FixedPostgreSQL10Dialect.class,
    }
)
@SpringBootApplication
@EnableConfigurationProperties({ LiquibaseProperties.class, ApplicationProperties.class })
public class KawaVendorApiApp {

    private static final Logger log = LoggerFactory.getLogger(KawaVendorApiApp.class);

    private final Environment env;

    private final TokenProvider tokenProvider;

    private final ApplicationProperties applicationProperties;

    private String defaultUserToken;

    public KawaVendorApiApp(Environment env, TokenProvider tokenProvider, ApplicationProperties applicationProperties) {
        this.env = env;
        this.tokenProvider = tokenProvider;
        this.applicationProperties = applicationProperties;
    }

    /**
     * Initializes KawaVendorAPI.
     * <p>
     * Spring profiles can be configured with a program argument --spring.profiles.active=your-active-profile
     * <p>
     * You can find more information on how profiles work with JHipster on <a href="https://www.jhipster.tech/profiles/">https://www.jhipster.tech/profiles/</a>.
     */
    @PostConstruct
    public void initApplication() throws IOException {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (
            activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) &&
            activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_PRODUCTION)
        ) {
            log.error(
                "You have misconfigured your application! It should not run " + "with both the 'dev' and 'prod' profiles at the same time."
            );
        }
        if (
            activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) &&
            activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_CLOUD)
        ) {
            log.error(
                "You have misconfigured your application! It should not " + "run with both the 'dev' and 'cloud' profiles at the same time."
            );
        }

        createDefaultUser();
        log.info(
            CRLFLogConverter.CRLF_SAFE_MARKER,
            "\n{}\n\t" + "Default User JWT: \t{}\n{}",
            Constants.LOG_SEPARATOR,
            defaultUserToken,
            Constants.LOG_SEPARATOR
        );

        // If the "dev" profile is active and the OS is windows, display the default user JWT in the console
        if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) && System.getProperty("os.name").startsWith("Windows")) {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "echo " + defaultUserToken + " | clip");
            pb.inheritIO();
            pb.start();
        }
    }

    /**
     * Main method, used to run the application.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(KawaVendorApiApp.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        logApplicationStartup(env);
    }

    private static void logApplicationStartup(Environment env) {
        String protocol = Optional.ofNullable(env.getProperty("server.ssl.key-store")).map(key -> "https").orElse("http");
        String serverPort = env.getProperty("server.port");
        String contextPath = Optional
            .ofNullable(env.getProperty("server.servlet.context-path"))
            .filter(StringUtils::isNotBlank)
            .orElse("/");
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        log.info(
            CRLFLogConverter.CRLF_SAFE_MARKER,
            "\n----------------------------------------------------------\n\t" +
            "Application '{}' is running! Access URLs:\n\t" +
            "Local: \t\t{}://localhost:{}{}\n\t" +
            "External: \t{}://{}:{}{}\n\t" +
            "Profile(s): \t{}\n----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            protocol,
            serverPort,
            contextPath,
            protocol,
            hostAddress,
            serverPort,
            contextPath,
            env.getActiveProfiles().length == 0 ? env.getDefaultProfiles() : env.getActiveProfiles()
        );

        String configServerStatus = env.getProperty("configserver.status");
        if (configServerStatus == null) {
            configServerStatus = "Not found or not setup for this application";
        }
        log.info(
            CRLFLogConverter.CRLF_SAFE_MARKER,
            "\n{}\n\t" + "Config Server: \t{}\n{}",
            Constants.LOG_SEPARATOR,
            configServerStatus,
            Constants.LOG_SEPARATOR
        );
    }

    private void createDefaultUser() {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            applicationProperties.getDefaultUser().getUsername(),
            applicationProperties.getDefaultUser().getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority(AuthoritiesConstants.ADMIN))
        );

        defaultUserToken = tokenProvider.createToken(authentication, true);
    }
}
