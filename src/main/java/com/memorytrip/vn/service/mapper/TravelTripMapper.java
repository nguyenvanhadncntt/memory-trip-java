package com.memorytrip.vn.service.mapper;


import com.memorytrip.vn.domain.TravelTrip;
import com.memorytrip.vn.service.dto.TravelTripDTO;

/**
 * Mapper for the entity {@link TravelTrip} and its DTO {@link TravelTripDTO}.
 */
public interface TravelTripMapper extends EntityMapper<TravelTripDTO, TravelTrip> {

    TravelTripDTO toDto(TravelTrip travelTrip);

    TravelTrip toEntity(TravelTripDTO travelTripDTO);

    default TravelTrip fromId(Long id) {
        if (id == null) {
            return null;
        }
        TravelTrip travelTrip = new TravelTrip();
        travelTrip.setId(id);
        return travelTrip;
    }
}
