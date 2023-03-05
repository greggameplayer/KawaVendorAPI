package com.kawa.service.dto.request;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotBlank;

/**
 * A DTO for the {@link com.kawa.domain.Vendor} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VendorRequestDTO implements Serializable {

    private String name;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendorRequestDTO)) return false;
        VendorRequestDTO that = (VendorRequestDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, username, password);
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "VendorRequestDTO{" +
            "name='" + name + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
