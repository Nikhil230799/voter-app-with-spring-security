import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RightDivComponent } from './right-div.component';

describe('RightDivComponent', () => {
  let component: RightDivComponent;
  let fixture: ComponentFixture<RightDivComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RightDivComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RightDivComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
