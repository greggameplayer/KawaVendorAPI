package com.kawa.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;
import com.kawa.IntegrationTest;
import com.kawa.domain.Vendor;
import com.kawa.repository.VendorRepository;
import com.kawa.security.AuthoritiesConstants;
import com.kawa.security.jwt.TokenProvider;
import com.kawa.service.dto.request.VendorRequestDTO;
import com.kawa.service.mapper.VendorRequestMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link VendorResource} REST controller.
 */

@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VendorResourceIT {

    private static final String DEFAULT_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_TOKEN = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA@gmail.com";

    private static final String UPDATED_EMAIL = "BBBBBBBBBB@gmail.com";

    private static final String ENTITY_API_URL = "/api/vendors";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static final Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2L * Integer.MAX_VALUE));

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private VendorRequestMapper vendorRequestMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVendorMockMvc;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Vendor vendor;

    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
        .withConfiguration(GreenMailConfiguration.aConfig().withUser("duke", "springboot"))
        .withPerMethodLifecycle(false);

    /**
     * Create an entity for this test.
     * <hr>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vendor createEntity(EntityManager em) {
        return new Vendor()
            .token(DEFAULT_TOKEN)
            .name(DEFAULT_NAME)
            .username(DEFAULT_USERNAME)
            .password(DEFAULT_PASSWORD)
            .email(DEFAULT_EMAIL);
    }

    /**
     * Create an updated entity for this test.
     * <hr>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vendor createUpdatedEntity(EntityManager em) {
        return new Vendor()
            .token(UPDATED_TOKEN)
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD);
    }

    @BeforeEach
    public void initTest() {
        vendor = createEntity(em);
    }

    @BeforeAll
    public static void init() {
        greenMail.setUser(DEFAULT_EMAIL, DEFAULT_PASSWORD);
    }

    @Test
    @Timeout(30)
    @WithMockUser(authorities = { AuthoritiesConstants.ADMIN })
    @Transactional
    void createVendor() throws Exception {
        int databaseSizeBeforeCreate = vendorRepository.findAll().size();
        // Create the Vendor
        VendorRequestDTO vendorRequestDTO = vendorRequestMapper.toDto(vendor);
        restVendorMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vendorRequestDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Vendor in the database
        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeCreate + 1);
        Vendor testVendor = vendorList.get(vendorList.size() - 1);
        assertTrue(tokenProvider.validateToken(testVendor.getToken()));
        assertThat(testVendor.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testVendor.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testVendor.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertTrue(passwordEncoder.matches(DEFAULT_PASSWORD, testVendor.getPassword()));

        MimeMessage receivedMessage = greenMail.getReceivedMessages()[0];
        assertThat(receivedMessage.getSubject()).isEqualTo("Welcome to Kawa");
    }

    @Test
    @WithMockUser(authorities = { AuthoritiesConstants.ADMIN })
    @Transactional
    void getAllVendors() throws Exception {
        // Initialize the database
        vendorRepository.saveAndFlush(vendor);

        // Get all the vendorList
        restVendorMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vendor.getId().intValue())))
            .andExpect(jsonPath("$.[*].token").value(hasItem(DEFAULT_TOKEN)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)));
    }

    @Test
    @WithMockUser(authorities = { AuthoritiesConstants.ADMIN })
    @Transactional
    void getVendor() throws Exception {
        // Initialize the database
        vendorRepository.saveAndFlush(vendor);

        // Get the vendor
        restVendorMockMvc
            .perform(get(ENTITY_API_URL_ID, vendor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vendor.getId().intValue()))
            .andExpect(jsonPath("$.token").value(DEFAULT_TOKEN))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD));
    }

    @Test
    @WithMockUser(authorities = { AuthoritiesConstants.ADMIN })
    @Transactional
    void getNonExistingVendor() throws Exception {
        // Get the vendor
        restVendorMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = { AuthoritiesConstants.ADMIN })
    @Transactional
    void putExistingVendor() throws Exception {
        // Initialize the database
        vendorRepository.saveAndFlush(vendor);

        int databaseSizeBeforeUpdate = vendorRepository.findAll().size();

        // Update the vendor
        Vendor updatedVendor = vendorRepository.findById(vendor.getId()).get();
        // Disconnect from session so that the updates on updatedVendor are not directly saved in db
        em.detach(updatedVendor);
        updatedVendor.name(UPDATED_NAME).username(UPDATED_USERNAME).password(UPDATED_PASSWORD).email(UPDATED_EMAIL);
        VendorRequestDTO vendorRequestDTO = vendorRequestMapper.toDto(updatedVendor);

        restVendorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedVendor.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vendorRequestDTO))
            )
            .andExpect(status().isOk());

        // Validate the Vendor in the database
        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeUpdate);
        Vendor testVendor = vendorList.get(vendorList.size() - 1);
        assertThat(testVendor.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testVendor.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testVendor.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertTrue(passwordEncoder.matches(UPDATED_PASSWORD, testVendor.getPassword()));
    }

    @Test
    @WithMockUser(authorities = { AuthoritiesConstants.ADMIN })
    @Transactional
    void putExistingVendorWithNoUsername() throws Exception {
        // Initialize the database
        vendorRepository.saveAndFlush(vendor);

        // Update the vendor
        Vendor updatedVendor = vendorRepository.findById(vendor.getId()).get();
        // Disconnect from session so that the updates on updatedVendor are not directly saved in db
        em.detach(updatedVendor);
        updatedVendor.name(UPDATED_NAME).username(null).password(UPDATED_PASSWORD).email(UPDATED_EMAIL);
        VendorRequestDTO vendorRequestDTO = vendorRequestMapper.toDto(updatedVendor);

        restVendorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedVendor.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vendorRequestDTO))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = { AuthoritiesConstants.ADMIN })
    @Transactional
    void putExistingVendorWithNoPassword() throws Exception {
        // Initialize the database
        vendorRepository.saveAndFlush(vendor);

        // Update the vendor
        Vendor updatedVendor = vendorRepository.findById(vendor.getId()).get();
        // Disconnect from session so that the updates on updatedVendor are not directly saved in db
        em.detach(updatedVendor);
        updatedVendor.name(UPDATED_NAME).username(UPDATED_USERNAME).password(null).email(UPDATED_EMAIL);
        VendorRequestDTO vendorRequestDTO = vendorRequestMapper.toDto(updatedVendor);

        restVendorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedVendor.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vendorRequestDTO))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = { AuthoritiesConstants.ADMIN })
    @Transactional
    void putExistingVendorWithBlankUsername() throws Exception {
        // Initialize the database
        vendorRepository.saveAndFlush(vendor);

        // Update the vendor
        Vendor updatedVendor = vendorRepository.findById(vendor.getId()).get();
        // Disconnect from session so that the updates on updatedVendor are not directly saved in db
        em.detach(updatedVendor);
        updatedVendor.name(UPDATED_NAME).username(UPDATED_USERNAME).password("").email(UPDATED_EMAIL);
        VendorRequestDTO vendorRequestDTO = vendorRequestMapper.toDto(updatedVendor);

        restVendorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedVendor.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vendorRequestDTO))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = { AuthoritiesConstants.ADMIN })
    @Transactional
    void putExistingVendorWithBlankPassword() throws Exception {
        // Initialize the database
        vendorRepository.saveAndFlush(vendor);

        // Update the vendor
        Vendor updatedVendor = vendorRepository.findById(vendor.getId()).get();
        // Disconnect from session so that the updates on updatedVendor are not directly saved in db
        em.detach(updatedVendor);
        updatedVendor.name(UPDATED_NAME).username(UPDATED_USERNAME).password("").email(UPDATED_EMAIL);
        VendorRequestDTO vendorRequestDTO = vendorRequestMapper.toDto(updatedVendor);

        restVendorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedVendor.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vendorRequestDTO))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = { AuthoritiesConstants.ADMIN })
    @Transactional
    void deleteVendor() throws Exception {
        // Initialize the database
        vendorRepository.saveAndFlush(vendor);

        int databaseSizeBeforeDelete = vendorRepository.findAll().size();

        // Delete the vendor
        restVendorMockMvc
            .perform(delete(ENTITY_API_URL_ID, vendor.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
