import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MemorytripTestModule } from '../../../test.module';
import { TravelTripDetailComponent } from 'app/entities/travel-trip/travel-trip-detail.component';
import { TravelTrip } from 'app/shared/model/travel-trip.model';

describe('Component Tests', () => {
  describe('TravelTrip Management Detail Component', () => {
    let comp: TravelTripDetailComponent;
    let fixture: ComponentFixture<TravelTripDetailComponent>;
    const route = ({ data: of({ travelTrip: new TravelTrip(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MemorytripTestModule],
        declarations: [TravelTripDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TravelTripDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TravelTripDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load travelTrip on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.travelTrip).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
