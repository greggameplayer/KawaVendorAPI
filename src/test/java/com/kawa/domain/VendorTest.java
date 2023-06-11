package com.kawa.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.kawa.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VendorTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vendor.class);
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        Vendor vendor2 = new Vendor();
        vendor2.setId(vendor1.getId());
        assertThat(vendor1).isEqualTo(vendor2);
        vendor2.setId(2L);
        assertThat(vendor1).isNotEqualTo(vendor2);
        vendor1.setId(null);
        assertThat(vendor1).isNotEqualTo(vendor2);
    }

    @Test
    void testHashCode() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setToken("token");
        vendor.setName("name");
        vendor.setEmail("email");
        vendor.setUsername("username");
        vendor.setPassword("password");
        // test that the hashcode is the same as the class hashcode
        assertThat(vendor.hashCode()).hasSameHashCodeAs(vendor.getClass());
    }

    @Test
    void testToString() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setToken("token");
        vendor.setName("name");
        vendor.setEmail("email");
        vendor.setUsername("username");
        vendor.setPassword("password");
        assertThat(vendor.toString())
            .hasToString("Vendor{id=1, token='token', name='name', email='email', username='username', password='password'}");
    }

    @Test
    void testEqualsSameObject() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setToken("token");
        Vendor vendor2 = vendor;
        assertThat(vendor).isEqualTo(vendor2);
    }
}
