package com.memorytrip.vn.service.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.memorytrip.vn.domain.File;
import com.memorytrip.vn.domain.TravelTrip;
import com.memorytrip.vn.domain.User;
import com.memorytrip.vn.service.dto.TravelTripDTO;
import com.memorytrip.vn.service.mapper.FileMapper;
import com.memorytrip.vn.service.mapper.TravelTripMapper;
import com.memorytrip.vn.service.mapper.UserMapper;

@Component
public class TravelTripMapperImpl implements TravelTripMapper {

    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<TravelTrip> toEntity(List<TravelTripDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<TravelTrip> list = new ArrayList<TravelTrip>( dtoList.size() );
        for ( TravelTripDTO travelTripDTO : dtoList ) {
            list.add( toEntity( travelTripDTO ) );
        }

        return list;
    }

    @Override
    public List<TravelTripDTO> toDto(List<TravelTrip> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TravelTripDTO> list = new ArrayList<TravelTripDTO>( entityList.size() );
        for ( TravelTrip travelTrip : entityList ) {
            list.add( toDto( travelTrip ) );
        }

        return list;
    }

    @Override
    public TravelTripDTO toDto(TravelTrip travelTrip) {
        if ( travelTrip == null ) {
            return null;
        }

        TravelTripDTO travelTripDTO = new TravelTripDTO();

        travelTripDTO.setTravelImageId( travelTripTravelImageId( travelTrip ) );
        travelTripDTO.setUserId( travelTripUserId( travelTrip ) );
        travelTripDTO.setId( travelTrip.getId() );
        travelTripDTO.setTitle( travelTrip.getTitle() );
        travelTripDTO.setDescription( travelTrip.getDescription() );

        return travelTripDTO;
    }

    @Override
    public TravelTrip toEntity(TravelTripDTO travelTripDTO) {
        if ( travelTripDTO == null ) {
            return null;
        }

        TravelTrip travelTrip = new TravelTrip();

        travelTrip.setUser( userMapper.userFromId( travelTripDTO.getUserId() ) );
        travelTrip.setTravelImage( fileMapper.fromId( travelTripDTO.getTravelImageId() ) );
        travelTrip.setId( travelTripDTO.getId() );
        travelTrip.setTitle( travelTripDTO.getTitle() );
        travelTrip.setDescription( travelTripDTO.getDescription() );

        return travelTrip;
    }

    private Long travelTripTravelImageId(TravelTrip travelTrip) {
        if ( travelTrip == null ) {
            return null;
        }
        File travelImage = travelTrip.getTravelImage();
        if ( travelImage == null ) {
            return null;
        }
        Long id = travelImage.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long travelTripUserId(TravelTrip travelTrip) {
        if ( travelTrip == null ) {
            return null;
        }
        User user = travelTrip.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
