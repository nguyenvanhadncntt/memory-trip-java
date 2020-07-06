import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITimeline } from 'app/shared/model/timeline.model';

@Component({
  selector: 'jhi-timeline-detail',
  templateUrl: './timeline-detail.component.html',
})
export class TimelineDetailComponent implements OnInit {
  timeline: ITimeline | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ timeline }) => (this.timeline = timeline));
  }

  previousState(): void {
    window.history.back();
  }
}
