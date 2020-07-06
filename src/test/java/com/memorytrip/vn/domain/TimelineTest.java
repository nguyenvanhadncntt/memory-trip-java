package com.memorytrip.vn.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.memorytrip.vn.web.rest.TestUtil;

public class TimelineTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Timeline.class);
        Timeline timeline1 = new Timeline();
        timeline1.setId(1L);
        Timeline timeline2 = new Timeline();
        timeline2.setId(timeline1.getId());
        assertThat(timeline1).isEqualTo(timeline2);
        timeline2.setId(2L);
        assertThat(timeline1).isNotEqualTo(timeline2);
        timeline1.setId(null);
        assertThat(timeline1).isNotEqualTo(timeline2);
    }
}
