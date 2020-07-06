package com.memorytrip.vn.service.mapper;


import com.memorytrip.vn.domain.Timeline;
import com.memorytrip.vn.service.dto.TimelineDTO;

/**
 * Mapper for the entity {@link Timeline} and its DTO {@link TimelineDTO}.
 */
public interface TimelineMapper extends EntityMapper<TimelineDTO, Timeline> {

    TimelineDTO toDto(Timeline timeline);

    Timeline toEntity(TimelineDTO timelineDTO);

    default Timeline fromId(Long id) {
        if (id == null) {
            return null;
        }
        Timeline timeline = new Timeline();
        timeline.setId(id);
        return timeline;
    }
}
