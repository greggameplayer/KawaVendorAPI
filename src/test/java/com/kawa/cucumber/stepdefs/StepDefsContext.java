package com.kawa.cucumber.stepdefs;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.*;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class StepDefsContext implements ResultActions {

    protected ResultActions currentResultAction;

    @Autowired
    private MockMvc mockMvc;

    public ResultActions getCurrentResultAction() {
        return currentResultAction;
    }

    public void setCurrentResultAction(ResultActions currentResultAction) {
        this.currentResultAction = currentResultAction;
    }

    @Override
    public @NotNull ResultActions andExpect(@NotNull ResultMatcher matcher) throws Exception {
        return currentResultAction.andExpect(matcher);
    }

    @Override
    public @NotNull ResultActions andDo(@NotNull ResultHandler handler) throws Exception {
        return currentResultAction.andDo(handler);
    }

    @Override
    public @NotNull MvcResult andReturn() {
        return currentResultAction.andReturn();
    }

    public ResultActions perform(RequestBuilder requestBuilder) throws Exception {
        setCurrentResultAction(mockMvc.perform(requestBuilder));
        return this;
    }

    public ResultActions requestGet(String s, User user) throws Exception {
        return mockMvc.perform(get(s).contentType(MediaType.APPLICATION_JSON).with(user(user)));
    }
}
