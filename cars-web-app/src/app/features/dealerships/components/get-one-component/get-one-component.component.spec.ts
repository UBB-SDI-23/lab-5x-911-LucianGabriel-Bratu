import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetOneComponentComponent } from './get-one-component.component';

describe('GetOneComponentComponent', () => {
  let component: GetOneComponentComponent;
  let fixture: ComponentFixture<GetOneComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetOneComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetOneComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
