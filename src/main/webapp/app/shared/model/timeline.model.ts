import { Moment } from 'moment';
import { IFile } from 'app/shared/model/file.model';

export interface ITimeline {
  id?: number;
  title?: string;
  description?: string;
  startTime?: Moment;
  endTime?: Moment;
  order?: number;
  locationId?: number;
  files?: IFile[];
  travelTripId?: number;
}

export class Timeline implements ITimeline {
  constructor(
    public id?: number,
    public title?: string,
    public description?: string,
    public startTime?: Moment,
    public endTime?: Moment,
    public order?: number,
    public locationId?: number,
    public files?: IFile[],
    public travelTripId?: number
  ) {}
}
