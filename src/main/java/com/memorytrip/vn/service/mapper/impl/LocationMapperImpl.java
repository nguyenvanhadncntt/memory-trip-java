package com.memorytrip.vn.service.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.memorytrip.vn.domain.Location;
import com.memorytrip.vn.service.dto.LocationDTO;
import com.memorytrip.vn.service.mapper.LocationMapper;

@Component
public class LocationMapperImpl implements LocationMapper {

    @Override
    public Location toEntity(LocationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Location location = new Location();

        location.setId( dto.getId() );
        location.setLocationAddress( dto.getLocationAddress() );
        location.setLat( dto.getLat() );
        location.setLng( dto.getLng() );

        return location;
    }

    @Override
    public LocationDTO toDto(Location entity) {
        if ( entity == null ) {
            return null;
        }

        LocationDTO locationDTO = new LocationDTO();

        locationDTO.setId( entity.getId() );
        locationDTO.setLocationAddress( entity.getLocationAddress() );
        locationDTO.setLat( entity.getLat() );
        locationDTO.setLng( entity.getLng() );

        return locationDTO;
    }

    @Override
    public List<Location> toEntity(List<LocationDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Location> list = new ArrayList<Location>( dtoList.size() );
        for ( LocationDTO locationDTO : dtoList ) {
            list.add( toEntity( locationDTO ) );
        }

        return list;
    }

    @Override
    public List<LocationDTO> toDto(List<Location> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<LocationDTO> list = new ArrayList<LocationDTO>( entityList.size() );
        for ( Location location : entityList ) {
            list.add( toDto( location ) );
        }

        return list;
    }
}
