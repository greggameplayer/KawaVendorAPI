package com.kawa.service.dto.response;

import com.kawa.service.dto.request.VendorRequestDTO;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.kawa.domain.Vendor} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VendorResponseDTO extends VendorRequestDTO implements Serializable {

    private Long id;

    @NotNull
    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VendorResponseDTO)) {
            return false;
        }

        VendorResponseDTO vendorResponseDTO = (VendorResponseDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, vendorResponseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VendorResponseDTO{" +
            "id=" + getId() +
            ", token='" + getToken() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
