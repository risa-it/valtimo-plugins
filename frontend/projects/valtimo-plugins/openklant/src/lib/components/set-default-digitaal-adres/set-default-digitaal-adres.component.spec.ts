import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetDefaultDigitaalAdresComponent } from './set-default-digitaal-adres.component';

describe('SetDefaultDigitaalAdresComponent', () => {
  let component: SetDefaultDigitaalAdresComponent;
  let fixture: ComponentFixture<SetDefaultDigitaalAdresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SetDefaultDigitaalAdresComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SetDefaultDigitaalAdresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
