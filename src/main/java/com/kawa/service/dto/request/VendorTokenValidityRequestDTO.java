package com.kawa.service.dto.request;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotBlank;

/**
 * A DTO for token validity request.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VendorTokenValidityRequestDTO implements Serializable {

    @NotBlank
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorTokenValidityRequestDTO that = (VendorTokenValidityRequestDTO) o;
        return token.equals(that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

    @Override
    public String toString() {
        return "VendorTokenValidityRequestDTO{" + "token='" + token + '\'' + '}';
    }
}
