import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITravelTrip } from 'app/shared/model/travel-trip.model';

@Component({
  selector: 'jhi-travel-trip-detail',
  templateUrl: './travel-trip-detail.component.html',
})
export class TravelTripDetailComponent implements OnInit {
  travelTrip: ITravelTrip | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ travelTrip }) => (this.travelTrip = travelTrip));
  }

  previousState(): void {
    window.history.back();
  }
}
