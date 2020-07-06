import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITravelTrip, TravelTrip } from 'app/shared/model/travel-trip.model';
import { TravelTripService } from './travel-trip.service';
import { TravelTripComponent } from './travel-trip.component';
import { TravelTripDetailComponent } from './travel-trip-detail.component';
import { TravelTripUpdateComponent } from './travel-trip-update.component';

@Injectable({ providedIn: 'root' })
export class TravelTripResolve implements Resolve<ITravelTrip> {
  constructor(private service: TravelTripService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITravelTrip> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((travelTrip: HttpResponse<TravelTrip>) => {
          if (travelTrip.body) {
            return of(travelTrip.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TravelTrip());
  }
}

export const travelTripRoute: Routes = [
  {
    path: '',
    component: TravelTripComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'memorytripApp.travelTrip.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TravelTripDetailComponent,
    resolve: {
      travelTrip: TravelTripResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'memorytripApp.travelTrip.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TravelTripUpdateComponent,
    resolve: {
      travelTrip: TravelTripResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'memorytripApp.travelTrip.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TravelTripUpdateComponent,
    resolve: {
      travelTrip: TravelTripResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'memorytripApp.travelTrip.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
