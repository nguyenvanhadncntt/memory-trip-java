package com.memorytrip.vn.repository;

import com.memorytrip.vn.domain.Timeline;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Timeline entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TimelineRepository extends JpaRepository<Timeline, Long> {
}
