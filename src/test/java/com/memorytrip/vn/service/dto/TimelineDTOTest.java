package com.memorytrip.vn.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.memorytrip.vn.web.rest.TestUtil;

public class TimelineDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TimelineDTO.class);
        TimelineDTO timelineDTO1 = new TimelineDTO();
        timelineDTO1.setId(1L);
        TimelineDTO timelineDTO2 = new TimelineDTO();
        assertThat(timelineDTO1).isNotEqualTo(timelineDTO2);
        timelineDTO2.setId(timelineDTO1.getId());
        assertThat(timelineDTO1).isEqualTo(timelineDTO2);
        timelineDTO2.setId(2L);
        assertThat(timelineDTO1).isNotEqualTo(timelineDTO2);
        timelineDTO1.setId(null);
        assertThat(timelineDTO1).isNotEqualTo(timelineDTO2);
    }
}
