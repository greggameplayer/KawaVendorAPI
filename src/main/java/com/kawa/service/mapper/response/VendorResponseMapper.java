package com.kawa.service.mapper.response;

import com.kawa.domain.Vendor;
import com.kawa.service.dto.response.VendorResponseDTO;
import com.kawa.service.mapper.EntityMapper;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Vendor} and its DTO {@link VendorResponseDTO}.
 */
@Mapper(componentModel = "spring")
public interface VendorResponseMapper extends EntityMapper<VendorResponseDTO, Vendor> {}
