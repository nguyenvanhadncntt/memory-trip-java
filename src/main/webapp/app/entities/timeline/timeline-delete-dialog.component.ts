import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITimeline } from 'app/shared/model/timeline.model';
import { TimelineService } from './timeline.service';

@Component({
  templateUrl: './timeline-delete-dialog.component.html',
})
export class TimelineDeleteDialogComponent {
  timeline?: ITimeline;

  constructor(protected timelineService: TimelineService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.timelineService.delete(id).subscribe(() => {
      this.eventManager.broadcast('timelineListModification');
      this.activeModal.close();
    });
  }
}
