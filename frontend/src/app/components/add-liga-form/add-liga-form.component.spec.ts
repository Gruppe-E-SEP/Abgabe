import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLigaFormComponent } from './add-liga-form.component';

describe('AddLigaFormComponent', () => {
  let component: AddLigaFormComponent;
  let fixture: ComponentFixture<AddLigaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddLigaFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddLigaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
