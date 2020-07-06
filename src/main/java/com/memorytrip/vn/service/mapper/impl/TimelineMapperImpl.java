package com.memorytrip.vn.service.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.memorytrip.vn.domain.Location;
import com.memorytrip.vn.domain.Timeline;
import com.memorytrip.vn.domain.TravelTrip;
import com.memorytrip.vn.service.dto.TimelineDTO;
import com.memorytrip.vn.service.mapper.LocationMapper;
import com.memorytrip.vn.service.mapper.TimelineMapper;
import com.memorytrip.vn.service.mapper.TravelTripMapper;

@Component
public class TimelineMapperImpl implements TimelineMapper {

    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private TravelTripMapper travelTripMapper;

    @Override
    public List<Timeline> toEntity(List<TimelineDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Timeline> list = new ArrayList<Timeline>( dtoList.size() );
        for ( TimelineDTO timelineDTO : dtoList ) {
            list.add( toEntity( timelineDTO ) );
        }

        return list;
    }

    @Override
    public List<TimelineDTO> toDto(List<Timeline> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TimelineDTO> list = new ArrayList<TimelineDTO>( entityList.size() );
        for ( Timeline timeline : entityList ) {
            list.add( toDto( timeline ) );
        }

        return list;
    }

    @Override
    public TimelineDTO toDto(Timeline timeline) {
        if ( timeline == null ) {
            return null;
        }

        TimelineDTO timelineDTO = new TimelineDTO();

        timelineDTO.setLocationId( timelineLocationId( timeline ) );
        timelineDTO.setTravelTripId( timelineTravelTripId( timeline ) );
        timelineDTO.setId( timeline.getId() );
        timelineDTO.setTitle( timeline.getTitle() );
        timelineDTO.setDescription( timeline.getDescription() );
        timelineDTO.setStartTime( timeline.getStartTime() );
        timelineDTO.setEndTime( timeline.getEndTime() );
        timelineDTO.setOrder( timeline.getOrder() );

        return timelineDTO;
    }

    @Override
    public Timeline toEntity(TimelineDTO timelineDTO) {
        if ( timelineDTO == null ) {
            return null;
        }

        Timeline timeline = new Timeline();

        timeline.setLocation( locationMapper.fromId( timelineDTO.getLocationId() ) );
        timeline.setTravelTrip( travelTripMapper.fromId( timelineDTO.getTravelTripId() ) );
        timeline.setId( timelineDTO.getId() );
        timeline.setTitle( timelineDTO.getTitle() );
        timeline.setDescription( timelineDTO.getDescription() );
        timeline.setStartTime( timelineDTO.getStartTime() );
        timeline.setEndTime( timelineDTO.getEndTime() );
        timeline.setOrder( timelineDTO.getOrder() );

        return timeline;
    }

    private Long timelineLocationId(Timeline timeline) {
        if ( timeline == null ) {
            return null;
        }
        Location location = timeline.getLocation();
        if ( location == null ) {
            return null;
        }
        Long id = location.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long timelineTravelTripId(Timeline timeline) {
        if ( timeline == null ) {
            return null;
        }
        TravelTrip travelTrip = timeline.getTravelTrip();
        if ( travelTrip == null ) {
            return null;
        }
        Long id = travelTrip.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
