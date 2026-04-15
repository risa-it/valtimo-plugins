import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SamenwerkfunctionaliteitPluginConfigurationComponent } from './samenwerkfunctionaliteit-plugin-configuration.component';

describe('SamenwerkfunctionaliteitPluginConfigurationComponent', () => {
  let component: SamenwerkfunctionaliteitPluginConfigurationComponent;
  let fixture: ComponentFixture<SamenwerkfunctionaliteitPluginConfigurationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SamenwerkfunctionaliteitPluginConfigurationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SamenwerkfunctionaliteitPluginConfigurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
