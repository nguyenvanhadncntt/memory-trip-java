package com.memorytrip.vn.web.rest;

import com.memorytrip.vn.MemorytripApp;
import com.memorytrip.vn.domain.TravelTrip;
import com.memorytrip.vn.repository.TravelTripRepository;
import com.memorytrip.vn.service.TravelTripService;
import com.memorytrip.vn.service.dto.TravelTripDTO;
import com.memorytrip.vn.service.mapper.TravelTripMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TravelTripResource} REST controller.
 */
@SpringBootTest(classes = MemorytripApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TravelTripResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private TravelTripRepository travelTripRepository;

    @Autowired
    private TravelTripMapper travelTripMapper;

    @Autowired
    private TravelTripService travelTripService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTravelTripMockMvc;

    private TravelTrip travelTrip;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TravelTrip createEntity(EntityManager em) {
        TravelTrip travelTrip = new TravelTrip()
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION);
        return travelTrip;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TravelTrip createUpdatedEntity(EntityManager em) {
        TravelTrip travelTrip = new TravelTrip()
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION);
        return travelTrip;
    }

    @BeforeEach
    public void initTest() {
        travelTrip = createEntity(em);
    }

    @Test
    @Transactional
    public void createTravelTrip() throws Exception {
        int databaseSizeBeforeCreate = travelTripRepository.findAll().size();
        // Create the TravelTrip
        TravelTripDTO travelTripDTO = travelTripMapper.toDto(travelTrip);
        restTravelTripMockMvc.perform(post("/api/travel-trips")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(travelTripDTO)))
            .andExpect(status().isCreated());

        // Validate the TravelTrip in the database
        List<TravelTrip> travelTripList = travelTripRepository.findAll();
        assertThat(travelTripList).hasSize(databaseSizeBeforeCreate + 1);
        TravelTrip testTravelTrip = travelTripList.get(travelTripList.size() - 1);
        assertThat(testTravelTrip.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testTravelTrip.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createTravelTripWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = travelTripRepository.findAll().size();

        // Create the TravelTrip with an existing ID
        travelTrip.setId(1L);
        TravelTripDTO travelTripDTO = travelTripMapper.toDto(travelTrip);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTravelTripMockMvc.perform(post("/api/travel-trips")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(travelTripDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TravelTrip in the database
        List<TravelTrip> travelTripList = travelTripRepository.findAll();
        assertThat(travelTripList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTravelTrips() throws Exception {
        // Initialize the database
        travelTripRepository.saveAndFlush(travelTrip);

        // Get all the travelTripList
        restTravelTripMockMvc.perform(get("/api/travel-trips?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(travelTrip.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getTravelTrip() throws Exception {
        // Initialize the database
        travelTripRepository.saveAndFlush(travelTrip);

        // Get the travelTrip
        restTravelTripMockMvc.perform(get("/api/travel-trips/{id}", travelTrip.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(travelTrip.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingTravelTrip() throws Exception {
        // Get the travelTrip
        restTravelTripMockMvc.perform(get("/api/travel-trips/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTravelTrip() throws Exception {
        // Initialize the database
        travelTripRepository.saveAndFlush(travelTrip);

        int databaseSizeBeforeUpdate = travelTripRepository.findAll().size();

        // Update the travelTrip
        TravelTrip updatedTravelTrip = travelTripRepository.findById(travelTrip.getId()).get();
        // Disconnect from session so that the updates on updatedTravelTrip are not directly saved in db
        em.detach(updatedTravelTrip);
        updatedTravelTrip
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION);
        TravelTripDTO travelTripDTO = travelTripMapper.toDto(updatedTravelTrip);

        restTravelTripMockMvc.perform(put("/api/travel-trips")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(travelTripDTO)))
            .andExpect(status().isOk());

        // Validate the TravelTrip in the database
        List<TravelTrip> travelTripList = travelTripRepository.findAll();
        assertThat(travelTripList).hasSize(databaseSizeBeforeUpdate);
        TravelTrip testTravelTrip = travelTripList.get(travelTripList.size() - 1);
        assertThat(testTravelTrip.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testTravelTrip.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingTravelTrip() throws Exception {
        int databaseSizeBeforeUpdate = travelTripRepository.findAll().size();

        // Create the TravelTrip
        TravelTripDTO travelTripDTO = travelTripMapper.toDto(travelTrip);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTravelTripMockMvc.perform(put("/api/travel-trips")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(travelTripDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TravelTrip in the database
        List<TravelTrip> travelTripList = travelTripRepository.findAll();
        assertThat(travelTripList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTravelTrip() throws Exception {
        // Initialize the database
        travelTripRepository.saveAndFlush(travelTrip);

        int databaseSizeBeforeDelete = travelTripRepository.findAll().size();

        // Delete the travelTrip
        restTravelTripMockMvc.perform(delete("/api/travel-trips/{id}", travelTrip.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TravelTrip> travelTripList = travelTripRepository.findAll();
        assertThat(travelTripList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
