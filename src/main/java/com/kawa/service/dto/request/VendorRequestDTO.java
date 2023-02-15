package com.kawa.service.dto.request;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.kawa.domain.Vendor} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VendorRequestDTO implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorRequestDTO that = (VendorRequestDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VendorRequestDTO{" +
            ", name='" + getName() + "'" +
            "}";
    }
}
