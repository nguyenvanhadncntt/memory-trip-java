import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MemorytripTestModule } from '../../../test.module';
import { TravelTripUpdateComponent } from 'app/entities/travel-trip/travel-trip-update.component';
import { TravelTripService } from 'app/entities/travel-trip/travel-trip.service';
import { TravelTrip } from 'app/shared/model/travel-trip.model';

describe('Component Tests', () => {
  describe('TravelTrip Management Update Component', () => {
    let comp: TravelTripUpdateComponent;
    let fixture: ComponentFixture<TravelTripUpdateComponent>;
    let service: TravelTripService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MemorytripTestModule],
        declarations: [TravelTripUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TravelTripUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TravelTripUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TravelTripService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TravelTrip(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TravelTrip();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
