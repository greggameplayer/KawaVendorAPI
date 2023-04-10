package com.kawa.service.mapper;

import com.kawa.domain.Vendor;
import com.kawa.service.dto.response.VendorResponseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Vendor} and its DTO {@link VendorResponseDTO}.
 */
@Mapper(componentModel = "spring")
public interface VendorResponseMapper extends EntityMapper<VendorResponseDTO, Vendor> {}
