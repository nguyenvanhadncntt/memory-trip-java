import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITimeline, Timeline } from 'app/shared/model/timeline.model';
import { TimelineService } from './timeline.service';
import { TimelineComponent } from './timeline.component';
import { TimelineDetailComponent } from './timeline-detail.component';
import { TimelineUpdateComponent } from './timeline-update.component';

@Injectable({ providedIn: 'root' })
export class TimelineResolve implements Resolve<ITimeline> {
  constructor(private service: TimelineService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITimeline> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((timeline: HttpResponse<Timeline>) => {
          if (timeline.body) {
            return of(timeline.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Timeline());
  }
}

export const timelineRoute: Routes = [
  {
    path: '',
    component: TimelineComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'memorytripApp.timeline.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TimelineDetailComponent,
    resolve: {
      timeline: TimelineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'memorytripApp.timeline.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TimelineUpdateComponent,
    resolve: {
      timeline: TimelineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'memorytripApp.timeline.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TimelineUpdateComponent,
    resolve: {
      timeline: TimelineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'memorytripApp.timeline.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
