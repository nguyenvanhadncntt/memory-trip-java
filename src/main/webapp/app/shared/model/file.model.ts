import { FileType } from 'app/shared/model/enumerations/file-type.model';

export interface IFile {
  id?: number;
  name?: string;
  path?: string;
  originName?: string;
  type?: FileType;
  timelineId?: number;
}

export class File implements IFile {
  constructor(
    public id?: number,
    public name?: string,
    public path?: string,
    public originName?: string,
    public type?: FileType,
    public timelineId?: number
  ) {}
}
