package com.kawa.cucumber.stepdefs;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import com.kawa.security.AuthoritiesConstants;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public abstract class StepDefs {

    @Autowired
    @Qualifier("stepDefsContext")
    protected StepDefsContext context;

    protected User user;

    protected void createAdminUser() {
        user = new User("admin", "admin", Collections.singletonList(new SimpleGrantedAuthority(AuthoritiesConstants.ADMIN)));
    }

    protected void createUser() {
        user = new User("user", "user", Collections.singletonList(new SimpleGrantedAuthority(AuthoritiesConstants.USER)));
    }
}
