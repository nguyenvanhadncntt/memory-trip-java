package com.memorytrip.vn.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TimelineMapperTest {

    private TimelineMapper timelineMapper;

    @BeforeEach
    public void setUp() {
        timelineMapper = new TimelineMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(timelineMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(timelineMapper.fromId(null)).isNull();
    }
}
