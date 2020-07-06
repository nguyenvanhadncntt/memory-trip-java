import { Component, OnInit, Input } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { ActivatedRoute } from '@angular/router';

import { ITravelTrip } from '../../../shared/model/travel-trip.model';
import { TravelTripService } from '../travel-trip.service';
import { FileService } from '../../../entities/file/file.service';
import { UserService } from '../../../core/user/user.service';

@Component({
  selector: 'jhi-travel-trip-info',
  templateUrl: './travel-trip-info.component.html',
  styleUrls: ['./travel-trip-info.component.scss'],
})
export class TravelTripInfoComponent implements OnInit {
  isSaving = false;
  @Input() travelTrip?: ITravelTrip;

  constructor(
    protected travelTripService: TravelTripService,
    protected fileService: FileService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {}
}
