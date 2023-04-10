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
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (o == null || getClass() != o.getClass()) return false;
        VendorRequestDTO that = (VendorRequestDTO) o;
        return (
            Objects.equals(name, that.name) && email.equals(that.email) && username.equals(that.username) && password.equals(that.password)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, username, password);
    }

    // prettier-ignore


    @Override
    public String toString() {
        return "VendorRequestDTO{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
