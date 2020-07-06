import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'travel-trip',
        loadChildren: () => import('./travel-trip/travel-trip.module').then(m => m.MemorytripTravelTripModule),
      },
      {
        path: 'timeline',
        loadChildren: () => import('./timeline/timeline.module').then(m => m.MemorytripTimelineModule),
      },
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.MemorytripLocationModule),
      },
      {
        path: 'file',
        loadChildren: () => import('./file/file.module').then(m => m.MemorytripFileModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class MemorytripEntityModule {}
