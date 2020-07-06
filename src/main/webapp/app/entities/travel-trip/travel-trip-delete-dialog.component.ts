import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITravelTrip } from 'app/shared/model/travel-trip.model';
import { TravelTripService } from './travel-trip.service';

@Component({
  templateUrl: './travel-trip-delete-dialog.component.html',
})
export class TravelTripDeleteDialogComponent {
  travelTrip?: ITravelTrip;

  constructor(
    protected travelTripService: TravelTripService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.travelTripService.delete(id).subscribe(() => {
      this.eventManager.broadcast('travelTripListModification');
      this.activeModal.close();
    });
  }
}
