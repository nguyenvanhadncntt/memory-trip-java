import { ITimeline } from 'app/shared/model/timeline.model';

export interface ITravelTrip {
  id?: number;
  title?: string;
  description?: string;
  travelImageId?: number;
  userId?: number;
  timelines?: ITimeline[];
}

export class TravelTrip implements ITravelTrip {
  constructor(
    public id?: number,
    public title?: string,
    public description?: string,
    public travelImageId?: number,
    public userId?: number,
    public timelines?: ITimeline[]
  ) {}
}
