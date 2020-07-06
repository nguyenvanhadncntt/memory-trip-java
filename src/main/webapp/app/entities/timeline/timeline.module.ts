import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MemorytripSharedModule } from 'app/shared/shared.module';
import { TimelineComponent } from './timeline.component';
import { TimelineDetailComponent } from './timeline-detail.component';
import { TimelineUpdateComponent } from './timeline-update.component';
import { TimelineDeleteDialogComponent } from './timeline-delete-dialog.component';
import { timelineRoute } from './timeline.route';

@NgModule({
  imports: [MemorytripSharedModule, RouterModule.forChild(timelineRoute)],
  declarations: [TimelineComponent, TimelineDetailComponent, TimelineUpdateComponent, TimelineDeleteDialogComponent],
  entryComponents: [TimelineDeleteDialogComponent],
})
export class MemorytripTimelineModule {}
