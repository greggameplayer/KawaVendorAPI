package com.kawa.cucumber;

import com.kawa.IntegrationTest;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@IntegrationTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class CucumberTestContextConfiguration {}
