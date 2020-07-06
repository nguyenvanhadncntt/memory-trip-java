import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ITravelTrip, TravelTrip } from 'app/shared/model/travel-trip.model';
import { TravelTripService } from './travel-trip.service';
import { IFile } from 'app/shared/model/file.model';
import { FileService } from 'app/entities/file/file.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

type SelectableEntity = IFile | IUser;

@Component({
  selector: 'jhi-travel-trip-update',
  templateUrl: './travel-trip-update.component.html',
})
export class TravelTripUpdateComponent implements OnInit {
  isSaving = false;
  travelimages: IFile[] = [];
  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    title: [],
    description: [],
    travelImageId: [],
    userId: [],
  });

  constructor(
    protected travelTripService: TravelTripService,
    protected fileService: FileService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ travelTrip }) => {
      this.updateForm(travelTrip);

      this.fileService
        .query({ filter: 'traveltrip-is-null' })
        .pipe(
          map((res: HttpResponse<IFile[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IFile[]) => {
          if (!travelTrip.travelImageId) {
            this.travelimages = resBody;
          } else {
            this.fileService
              .find(travelTrip.travelImageId)
              .pipe(
                map((subRes: HttpResponse<IFile>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IFile[]) => (this.travelimages = concatRes));
          }
        });

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });
  }

  updateForm(travelTrip: ITravelTrip): void {
    this.editForm.patchValue({
      id: travelTrip.id,
      title: travelTrip.title,
      description: travelTrip.description,
      travelImageId: travelTrip.travelImageId,
      userId: travelTrip.userId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const travelTrip = this.createFromForm();
    if (travelTrip.id !== undefined) {
      this.subscribeToSaveResponse(this.travelTripService.update(travelTrip));
    } else {
      this.subscribeToSaveResponse(this.travelTripService.create(travelTrip));
    }
  }

  private createFromForm(): ITravelTrip {
    return {
      ...new TravelTrip(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      description: this.editForm.get(['description'])!.value,
      travelImageId: this.editForm.get(['travelImageId'])!.value,
      userId: this.editForm.get(['userId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITravelTrip>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
