package com.memorytrip.vn.service.mapper;


import com.memorytrip.vn.domain.Location;
import com.memorytrip.vn.service.dto.LocationDTO;

/**
 * Mapper for the entity {@link Location} and its DTO {@link LocationDTO}.
 */
public interface LocationMapper extends EntityMapper<LocationDTO, Location> {

    default Location fromId(Long id) {
        if (id == null) {
            return null;
        }
        Location location = new Location();
        location.setId(id);
        return location;
    }
}
