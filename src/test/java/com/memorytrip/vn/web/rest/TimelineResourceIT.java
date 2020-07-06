package com.memorytrip.vn.web.rest;

import com.memorytrip.vn.MemorytripApp;
import com.memorytrip.vn.domain.Timeline;
import com.memorytrip.vn.repository.TimelineRepository;
import com.memorytrip.vn.service.TimelineService;
import com.memorytrip.vn.service.dto.TimelineDTO;
import com.memorytrip.vn.service.mapper.TimelineMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TimelineResource} REST controller.
 */
@SpringBootTest(classes = MemorytripApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TimelineResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Instant DEFAULT_START_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_ORDER = 1;
    private static final Integer UPDATED_ORDER = 2;

    @Autowired
    private TimelineRepository timelineRepository;

    @Autowired
    private TimelineMapper timelineMapper;

    @Autowired
    private TimelineService timelineService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTimelineMockMvc;

    private Timeline timeline;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Timeline createEntity(EntityManager em) {
        Timeline timeline = new Timeline()
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .startTime(DEFAULT_START_TIME)
            .endTime(DEFAULT_END_TIME)
            .order(DEFAULT_ORDER);
        return timeline;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Timeline createUpdatedEntity(EntityManager em) {
        Timeline timeline = new Timeline()
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME)
            .order(UPDATED_ORDER);
        return timeline;
    }

    @BeforeEach
    public void initTest() {
        timeline = createEntity(em);
    }

    @Test
    @Transactional
    public void createTimeline() throws Exception {
        int databaseSizeBeforeCreate = timelineRepository.findAll().size();
        // Create the Timeline
        TimelineDTO timelineDTO = timelineMapper.toDto(timeline);
        restTimelineMockMvc.perform(post("/api/timelines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(timelineDTO)))
            .andExpect(status().isCreated());

        // Validate the Timeline in the database
        List<Timeline> timelineList = timelineRepository.findAll();
        assertThat(timelineList).hasSize(databaseSizeBeforeCreate + 1);
        Timeline testTimeline = timelineList.get(timelineList.size() - 1);
        assertThat(testTimeline.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testTimeline.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTimeline.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testTimeline.getEndTime()).isEqualTo(DEFAULT_END_TIME);
        assertThat(testTimeline.getOrder()).isEqualTo(DEFAULT_ORDER);
    }

    @Test
    @Transactional
    public void createTimelineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = timelineRepository.findAll().size();

        // Create the Timeline with an existing ID
        timeline.setId(1L);
        TimelineDTO timelineDTO = timelineMapper.toDto(timeline);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTimelineMockMvc.perform(post("/api/timelines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(timelineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Timeline in the database
        List<Timeline> timelineList = timelineRepository.findAll();
        assertThat(timelineList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTimelines() throws Exception {
        // Initialize the database
        timelineRepository.saveAndFlush(timeline);

        // Get all the timelineList
        restTimelineMockMvc.perform(get("/api/timelines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(timeline.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME.toString())))
            .andExpect(jsonPath("$.[*].order").value(hasItem(DEFAULT_ORDER)));
    }
    
    @Test
    @Transactional
    public void getTimeline() throws Exception {
        // Initialize the database
        timelineRepository.saveAndFlush(timeline);

        // Get the timeline
        restTimelineMockMvc.perform(get("/api/timelines/{id}", timeline.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(timeline.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.startTime").value(DEFAULT_START_TIME.toString()))
            .andExpect(jsonPath("$.endTime").value(DEFAULT_END_TIME.toString()))
            .andExpect(jsonPath("$.order").value(DEFAULT_ORDER));
    }
    @Test
    @Transactional
    public void getNonExistingTimeline() throws Exception {
        // Get the timeline
        restTimelineMockMvc.perform(get("/api/timelines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTimeline() throws Exception {
        // Initialize the database
        timelineRepository.saveAndFlush(timeline);

        int databaseSizeBeforeUpdate = timelineRepository.findAll().size();

        // Update the timeline
        Timeline updatedTimeline = timelineRepository.findById(timeline.getId()).get();
        // Disconnect from session so that the updates on updatedTimeline are not directly saved in db
        em.detach(updatedTimeline);
        updatedTimeline
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME)
            .order(UPDATED_ORDER);
        TimelineDTO timelineDTO = timelineMapper.toDto(updatedTimeline);

        restTimelineMockMvc.perform(put("/api/timelines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(timelineDTO)))
            .andExpect(status().isOk());

        // Validate the Timeline in the database
        List<Timeline> timelineList = timelineRepository.findAll();
        assertThat(timelineList).hasSize(databaseSizeBeforeUpdate);
        Timeline testTimeline = timelineList.get(timelineList.size() - 1);
        assertThat(testTimeline.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testTimeline.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTimeline.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testTimeline.getEndTime()).isEqualTo(UPDATED_END_TIME);
        assertThat(testTimeline.getOrder()).isEqualTo(UPDATED_ORDER);
    }

    @Test
    @Transactional
    public void updateNonExistingTimeline() throws Exception {
        int databaseSizeBeforeUpdate = timelineRepository.findAll().size();

        // Create the Timeline
        TimelineDTO timelineDTO = timelineMapper.toDto(timeline);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTimelineMockMvc.perform(put("/api/timelines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(timelineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Timeline in the database
        List<Timeline> timelineList = timelineRepository.findAll();
        assertThat(timelineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTimeline() throws Exception {
        // Initialize the database
        timelineRepository.saveAndFlush(timeline);

        int databaseSizeBeforeDelete = timelineRepository.findAll().size();

        // Delete the timeline
        restTimelineMockMvc.perform(delete("/api/timelines/{id}", timeline.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Timeline> timelineList = timelineRepository.findAll();
        assertThat(timelineList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
