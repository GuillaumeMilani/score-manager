import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScoresFormComponent } from './scores-form.component';

describe('ScoresFormComponent', () => {
  let component: ScoresFormComponent;
  let fixture: ComponentFixture<ScoresFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScoresFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScoresFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
