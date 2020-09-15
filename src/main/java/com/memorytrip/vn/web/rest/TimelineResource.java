package com.memorytrip.vn.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.memorytrip.vn.service.TimelineService;
import com.memorytrip.vn.service.dto.TimelineDTO;
import com.memorytrip.vn.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.memorytrip.vn.domain.Timeline}.
 */
@RestController
@RequestMapping("/api")
public class TimelineResource {

    private final Logger log = LoggerFactory.getLogger(TimelineResource.class);

    private static final String ENTITY_NAME = "timeline";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TimelineService timelineService;

    public TimelineResource(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    /**
     * {@code POST  /timelines} : Create a new timeline.
     *
     * @param timelineDTO the timelineDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new timelineDTO, or with status {@code 400 (Bad Request)} if the timeline has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/timelines")
    public ResponseEntity<TimelineDTO> createTimeline(@RequestBody TimelineDTO timelineDTO) throws URISyntaxException {
        log.debug("REST request to save Timeline : {}", timelineDTO);
        if (timelineDTO.getId() != null) {
            throw new BadRequestAlertException("A new timeline cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TimelineDTO result = timelineService.save(timelineDTO);
        return ResponseEntity.created(new URI("/api/timelines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /timelines} : Updates an existing timeline.
     *
     * @param timelineDTO the timelineDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated timelineDTO,
     * or with status {@code 400 (Bad Request)} if the timelineDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the timelineDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/timelines")
    public ResponseEntity<TimelineDTO> updateTimeline(@RequestBody TimelineDTO timelineDTO) throws URISyntaxException {
        log.debug("REST request to update Timeline : {}", timelineDTO);
        if (timelineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TimelineDTO result = timelineService.save(timelineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, timelineDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /timelines} : get all the timelines.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of timelines in body.
     */
    @GetMapping("/timelines")
    public ResponseEntity<List<TimelineDTO>> getAllTimelines(Pageable pageable) {
        log.debug("REST request to get a page of Timelines");
        Page<TimelineDTO> page = timelineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /timelines/:id} : get the "id" timeline.
     *
     * @param id the id of the timelineDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the timelineDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/timelines/{id}")
    public ResponseEntity<TimelineDTO> getTimeline(@PathVariable Long id) {
        log.debug("REST request to get Timeline : {}", id);
        Optional<TimelineDTO> timelineDTO = timelineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(timelineDTO);
    }

    /**
     * {@code DELETE  /timelines/:id} : delete the "id" timeline.
     *
     * @param id the id of the timelineDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/timelines/{id}")
    public ResponseEntity<Void> deleteTimeline(@PathVariable Long id) {
        log.debug("REST request to delete Timeline : {}", id);
        timelineService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
