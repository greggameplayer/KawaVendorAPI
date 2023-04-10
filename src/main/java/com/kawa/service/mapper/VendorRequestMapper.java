package com.kawa.service.mapper;

import com.kawa.domain.Vendor;
import com.kawa.service.dto.request.VendorRequestDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Vendor} and its DTO {@link VendorRequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface VendorRequestMapper extends EntityMapper<VendorRequestDTO, Vendor> {
    Vendor toEntity(VendorRequestDTO dto, Long id, String token);
}
