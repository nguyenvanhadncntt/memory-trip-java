package com.memorytrip.vn.service.impl;

import com.memorytrip.vn.service.TimelineService;
import com.memorytrip.vn.domain.Timeline;
import com.memorytrip.vn.repository.TimelineRepository;
import com.memorytrip.vn.service.dto.TimelineDTO;
import com.memorytrip.vn.service.mapper.TimelineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Timeline}.
 */
@Service
@Transactional
public class TimelineServiceImpl implements TimelineService {

    private final Logger log = LoggerFactory.getLogger(TimelineServiceImpl.class);

    private final TimelineRepository timelineRepository;

    private final TimelineMapper timelineMapper;

    public TimelineServiceImpl(TimelineRepository timelineRepository, TimelineMapper timelineMapper) {
        this.timelineRepository = timelineRepository;
        this.timelineMapper = timelineMapper;
    }

    @Override
    public TimelineDTO save(TimelineDTO timelineDTO) {
        log.debug("Request to save Timeline : {}", timelineDTO);
        Timeline timeline = timelineMapper.toEntity(timelineDTO);
        timeline = timelineRepository.save(timeline);
        return timelineMapper.toDto(timeline);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TimelineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Timelines");
        return timelineRepository.findAll(pageable)
            .map(timelineMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TimelineDTO> findOne(Long id) {
        log.debug("Request to get Timeline : {}", id);
        return timelineRepository.findById(id)
            .map(timelineMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Timeline : {}", id);
        timelineRepository.deleteById(id);
    }
}
