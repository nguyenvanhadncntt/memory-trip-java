package com.memorytrip.vn.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.memorytrip.vn.web.rest.TestUtil;

public class TravelTripTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TravelTrip.class);
        TravelTrip travelTrip1 = new TravelTrip();
        travelTrip1.setId(1L);
        TravelTrip travelTrip2 = new TravelTrip();
        travelTrip2.setId(travelTrip1.getId());
        assertThat(travelTrip1).isEqualTo(travelTrip2);
        travelTrip2.setId(2L);
        assertThat(travelTrip1).isNotEqualTo(travelTrip2);
        travelTrip1.setId(null);
        assertThat(travelTrip1).isNotEqualTo(travelTrip2);
    }
}
