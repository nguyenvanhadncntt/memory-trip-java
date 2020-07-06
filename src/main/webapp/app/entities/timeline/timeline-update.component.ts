import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITimeline, Timeline } from 'app/shared/model/timeline.model';
import { TimelineService } from './timeline.service';
import { ILocation } from 'app/shared/model/location.model';
import { LocationService } from 'app/entities/location/location.service';
import { ITravelTrip } from 'app/shared/model/travel-trip.model';
import { TravelTripService } from 'app/entities/travel-trip/travel-trip.service';

type SelectableEntity = ILocation | ITravelTrip;

@Component({
  selector: 'jhi-timeline-update',
  templateUrl: './timeline-update.component.html',
})
export class TimelineUpdateComponent implements OnInit {
  isSaving = false;
  locations: ILocation[] = [];
  traveltrips: ITravelTrip[] = [];

  editForm = this.fb.group({
    id: [],
    title: [],
    description: [],
    startTime: [],
    endTime: [],
    order: [],
    locationId: [],
    travelTripId: [],
  });

  constructor(
    protected timelineService: TimelineService,
    protected locationService: LocationService,
    protected travelTripService: TravelTripService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ timeline }) => {
      if (!timeline.id) {
        const today = moment().startOf('day');
        timeline.startTime = today;
        timeline.endTime = today;
      }

      this.updateForm(timeline);

      this.locationService
        .query({ filter: 'timeline-is-null' })
        .pipe(
          map((res: HttpResponse<ILocation[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ILocation[]) => {
          if (!timeline.locationId) {
            this.locations = resBody;
          } else {
            this.locationService
              .find(timeline.locationId)
              .pipe(
                map((subRes: HttpResponse<ILocation>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ILocation[]) => (this.locations = concatRes));
          }
        });

      this.travelTripService.query().subscribe((res: HttpResponse<ITravelTrip[]>) => (this.traveltrips = res.body || []));
    });
  }

  updateForm(timeline: ITimeline): void {
    this.editForm.patchValue({
      id: timeline.id,
      title: timeline.title,
      description: timeline.description,
      startTime: timeline.startTime ? timeline.startTime.format(DATE_TIME_FORMAT) : null,
      endTime: timeline.endTime ? timeline.endTime.format(DATE_TIME_FORMAT) : null,
      order: timeline.order,
      locationId: timeline.locationId,
      travelTripId: timeline.travelTripId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const timeline = this.createFromForm();
    if (timeline.id !== undefined) {
      this.subscribeToSaveResponse(this.timelineService.update(timeline));
    } else {
      this.subscribeToSaveResponse(this.timelineService.create(timeline));
    }
  }

  private createFromForm(): ITimeline {
    return {
      ...new Timeline(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      description: this.editForm.get(['description'])!.value,
      startTime: this.editForm.get(['startTime'])!.value ? moment(this.editForm.get(['startTime'])!.value, DATE_TIME_FORMAT) : undefined,
      endTime: this.editForm.get(['endTime'])!.value ? moment(this.editForm.get(['endTime'])!.value, DATE_TIME_FORMAT) : undefined,
      order: this.editForm.get(['order'])!.value,
      locationId: this.editForm.get(['locationId'])!.value,
      travelTripId: this.editForm.get(['travelTripId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITimeline>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
