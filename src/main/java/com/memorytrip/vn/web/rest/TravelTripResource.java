package com.memorytrip.vn.web.rest;

import com.memorytrip.vn.service.TravelTripService;
import com.memorytrip.vn.web.rest.errors.BadRequestAlertException;
import com.memorytrip.vn.service.dto.TravelTripDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.memorytrip.vn.domain.TravelTrip}.
 */
@RestController
@RequestMapping("/api")
public class TravelTripResource {

    private final Logger log = LoggerFactory.getLogger(TravelTripResource.class);

    private static final String ENTITY_NAME = "travelTrip";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TravelTripService travelTripService;

    public TravelTripResource(TravelTripService travelTripService) {
        this.travelTripService = travelTripService;
    }

    /**
     * {@code POST  /travel-trips} : Create a new travelTrip.
     *
     * @param travelTripDTO the travelTripDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new travelTripDTO, or with status {@code 400 (Bad Request)} if the travelTrip has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/travel-trips")
    public ResponseEntity<TravelTripDTO> createTravelTrip(@RequestBody TravelTripDTO travelTripDTO) throws URISyntaxException {
        log.debug("REST request to save TravelTrip : {}", travelTripDTO);
        if (travelTripDTO.getId() != null) {
            throw new BadRequestAlertException("A new travelTrip cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TravelTripDTO result = travelTripService.save(travelTripDTO);
        return ResponseEntity.created(new URI("/api/travel-trips/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /travel-trips} : Updates an existing travelTrip.
     *
     * @param travelTripDTO the travelTripDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated travelTripDTO,
     * or with status {@code 400 (Bad Request)} if the travelTripDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the travelTripDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/travel-trips")
    public ResponseEntity<TravelTripDTO> updateTravelTrip(@RequestBody TravelTripDTO travelTripDTO) throws URISyntaxException {
        log.debug("REST request to update TravelTrip : {}", travelTripDTO);
        if (travelTripDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TravelTripDTO result = travelTripService.save(travelTripDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, travelTripDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /travel-trips} : get all the travelTrips.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of travelTrips in body.
     */
    @GetMapping("/travel-trips")
    public ResponseEntity<List<TravelTripDTO>> getAllTravelTrips(Pageable pageable) {
        log.debug("REST request to get a page of TravelTrips");
        Page<TravelTripDTO> page = travelTripService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /travel-trips/:id} : get the "id" travelTrip.
     *
     * @param id the id of the travelTripDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the travelTripDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/travel-trips/{id}")
    public ResponseEntity<TravelTripDTO> getTravelTrip(@PathVariable Long id) {
        log.debug("REST request to get TravelTrip : {}", id);
        Optional<TravelTripDTO> travelTripDTO = travelTripService.findOne(id);
        return ResponseUtil.wrapOrNotFound(travelTripDTO);
    }

    /**
     * {@code DELETE  /travel-trips/:id} : delete the "id" travelTrip.
     *
     * @param id the id of the travelTripDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/travel-trips/{id}")
    public ResponseEntity<Void> deleteTravelTrip(@PathVariable Long id) {
        log.debug("REST request to delete TravelTrip : {}", id);
        travelTripService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
