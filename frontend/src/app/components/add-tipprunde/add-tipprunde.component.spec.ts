import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTipprundeComponent } from './add-tipprunde.component';

describe('AddTipprundeComponent', () => {
  let component: AddTipprundeComponent;
  let fixture: ComponentFixture<AddTipprundeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddTipprundeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddTipprundeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
