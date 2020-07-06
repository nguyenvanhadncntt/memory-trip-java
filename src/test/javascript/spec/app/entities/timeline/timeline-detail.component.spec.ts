import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MemorytripTestModule } from '../../../test.module';
import { TimelineDetailComponent } from 'app/entities/timeline/timeline-detail.component';
import { Timeline } from 'app/shared/model/timeline.model';

describe('Component Tests', () => {
  describe('Timeline Management Detail Component', () => {
    let comp: TimelineDetailComponent;
    let fixture: ComponentFixture<TimelineDetailComponent>;
    const route = ({ data: of({ timeline: new Timeline(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MemorytripTestModule],
        declarations: [TimelineDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TimelineDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TimelineDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load timeline on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.timeline).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
