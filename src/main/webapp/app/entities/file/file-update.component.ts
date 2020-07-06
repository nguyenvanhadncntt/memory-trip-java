import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFile, File } from 'app/shared/model/file.model';
import { FileService } from './file.service';
import { ITimeline } from 'app/shared/model/timeline.model';
import { TimelineService } from 'app/entities/timeline/timeline.service';

@Component({
  selector: 'jhi-file-update',
  templateUrl: './file-update.component.html',
})
export class FileUpdateComponent implements OnInit {
  isSaving = false;
  timelines: ITimeline[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    path: [],
    originName: [],
    type: [],
    timelineId: [],
  });

  constructor(
    protected fileService: FileService,
    protected timelineService: TimelineService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ file }) => {
      this.updateForm(file);

      this.timelineService.query().subscribe((res: HttpResponse<ITimeline[]>) => (this.timelines = res.body || []));
    });
  }

  updateForm(file: IFile): void {
    this.editForm.patchValue({
      id: file.id,
      name: file.name,
      path: file.path,
      originName: file.originName,
      type: file.type,
      timelineId: file.timelineId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const file = this.createFromForm();
    if (file.id !== undefined) {
      this.subscribeToSaveResponse(this.fileService.update(file));
    } else {
      this.subscribeToSaveResponse(this.fileService.create(file));
    }
  }

  private createFromForm(): IFile {
    return {
      ...new File(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      path: this.editForm.get(['path'])!.value,
      originName: this.editForm.get(['originName'])!.value,
      type: this.editForm.get(['type'])!.value,
      timelineId: this.editForm.get(['timelineId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFile>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ITimeline): any {
    return item.id;
  }
}
