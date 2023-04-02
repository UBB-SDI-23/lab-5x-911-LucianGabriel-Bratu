import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatisticsoverviewComponent } from './statisticsoverview.component';

describe('StatisticsoverviewComponent', () => {
  let component: StatisticsoverviewComponent;
  let fixture: ComponentFixture<StatisticsoverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StatisticsoverviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StatisticsoverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
