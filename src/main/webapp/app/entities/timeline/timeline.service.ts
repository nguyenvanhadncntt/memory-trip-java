import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITimeline } from 'app/shared/model/timeline.model';

type EntityResponseType = HttpResponse<ITimeline>;
type EntityArrayResponseType = HttpResponse<ITimeline[]>;

@Injectable({ providedIn: 'root' })
export class TimelineService {
  public resourceUrl = SERVER_API_URL + 'api/timelines';

  constructor(protected http: HttpClient) {}

  create(timeline: ITimeline): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(timeline);
    return this.http
      .post<ITimeline>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(timeline: ITimeline): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(timeline);
    return this.http
      .put<ITimeline>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITimeline>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITimeline[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(timeline: ITimeline): ITimeline {
    const copy: ITimeline = Object.assign({}, timeline, {
      startTime: timeline.startTime && timeline.startTime.isValid() ? timeline.startTime.toJSON() : undefined,
      endTime: timeline.endTime && timeline.endTime.isValid() ? timeline.endTime.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.startTime = res.body.startTime ? moment(res.body.startTime) : undefined;
      res.body.endTime = res.body.endTime ? moment(res.body.endTime) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((timeline: ITimeline) => {
        timeline.startTime = timeline.startTime ? moment(timeline.startTime) : undefined;
        timeline.endTime = timeline.endTime ? moment(timeline.endTime) : undefined;
      });
    }
    return res;
  }
}
