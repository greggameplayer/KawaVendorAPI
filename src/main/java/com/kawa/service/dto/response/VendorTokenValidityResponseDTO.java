package com.kawa.service.dto.response;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.NotBlank;

/**
 * A DTO for token validity response.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VendorTokenValidityResponseDTO implements Serializable {

    public VendorTokenValidityResponseDTO(boolean isValid, boolean isExpired, Date expiryDate) {
        this.isValid = isValid;
        this.isExpired = isExpired;
        this.expiryDate = expiryDate;
    }

    @NotBlank
    private boolean isValid;

    @NotBlank
    private boolean isExpired;

    private Date expiryDate;

    public boolean getValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public boolean getExpired() {
        return isExpired;
    }

    public void setExpired(Boolean expired) {
        isExpired = expired;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorTokenValidityResponseDTO that = (VendorTokenValidityResponseDTO) o;
        return isValid == that.isValid && isExpired == that.isExpired && Objects.equals(expiryDate, that.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isValid, isExpired, expiryDate);
    }

    @Override
    public String toString() {
        return "VendorTokenValidityResponseDTO{" + "isValid=" + isValid + ", isExpired=" + isExpired + ", expiryDate=" + expiryDate + '}';
    }
}
