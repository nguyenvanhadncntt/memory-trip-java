package com.memorytrip.vn.service;

import com.memorytrip.vn.service.dto.TimelineDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.memorytrip.vn.domain.Timeline}.
 */
public interface TimelineService {

    /**
     * Save a timeline.
     *
     * @param timelineDTO the entity to save.
     * @return the persisted entity.
     */
    TimelineDTO save(TimelineDTO timelineDTO);

    /**
     * Get all the timelines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TimelineDTO> findAll(Pageable pageable);


    /**
     * Get the "id" timeline.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TimelineDTO> findOne(Long id);

    /**
     * Delete the "id" timeline.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
