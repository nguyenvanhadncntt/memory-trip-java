export interface ILocation {
  id?: number;
  locationAddress?: string;
  lat?: number;
  lng?: number;
}

export class Location implements ILocation {
  constructor(public id?: number, public locationAddress?: string, public lat?: number, public lng?: number) {}
}
