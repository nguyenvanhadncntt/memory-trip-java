package com.memorytrip.vn.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TravelTripMapperTest {

    private TravelTripMapper travelTripMapper;

    @BeforeEach
    public void setUp() {
        travelTripMapper = new TravelTripMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(travelTripMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(travelTripMapper.fromId(null)).isNull();
    }
}
