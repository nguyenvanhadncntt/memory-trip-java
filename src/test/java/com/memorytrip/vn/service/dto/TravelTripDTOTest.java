package com.memorytrip.vn.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.memorytrip.vn.web.rest.TestUtil;

public class TravelTripDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TravelTripDTO.class);
        TravelTripDTO travelTripDTO1 = new TravelTripDTO();
        travelTripDTO1.setId(1L);
        TravelTripDTO travelTripDTO2 = new TravelTripDTO();
        assertThat(travelTripDTO1).isNotEqualTo(travelTripDTO2);
        travelTripDTO2.setId(travelTripDTO1.getId());
        assertThat(travelTripDTO1).isEqualTo(travelTripDTO2);
        travelTripDTO2.setId(2L);
        assertThat(travelTripDTO1).isNotEqualTo(travelTripDTO2);
        travelTripDTO1.setId(null);
        assertThat(travelTripDTO1).isNotEqualTo(travelTripDTO2);
    }
}
