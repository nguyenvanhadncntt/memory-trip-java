import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MemorytripSharedModule } from 'app/shared/shared.module';
import { TravelTripComponent } from './travel-trip.component';
import { TravelTripDetailComponent } from './travel-trip-detail.component';
import { TravelTripUpdateComponent } from './travel-trip-update.component';
import { TravelTripDeleteDialogComponent } from './travel-trip-delete-dialog.component';
import { travelTripRoute } from './travel-trip.route';
import { TravelTripInfoComponent } from './travel-trip-info.component/travel-trip-info.component';

@NgModule({
  imports: [MemorytripSharedModule, RouterModule.forChild(travelTripRoute)],
  declarations: [
    TravelTripComponent,
    TravelTripDetailComponent,
    TravelTripUpdateComponent,
    TravelTripInfoComponent,
    TravelTripDeleteDialogComponent,
  ],
  entryComponents: [TravelTripDeleteDialogComponent],
})
export class MemorytripTravelTripModule {}
