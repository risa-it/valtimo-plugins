import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {PluginConfigurationComponent} from '@valtimo/plugin';
import {BehaviorSubject, combineLatest, Observable, Subscription, take} from 'rxjs';
import {SamenwerkfunctionaliteitConfig} from '../../models/samenwerkfunctionaliteitConfig';

@Component({
  selector: 'app-samenwerkfunctionaliteit-plugin-configuration',
  imports: [],
  templateUrl: './samenwerkfunctionaliteit-plugin-configuration.component.html'
})
export class SamenwerkfunctionaliteitPluginConfigurationComponent implements PluginConfigurationComponent, OnInit, OnDestroy {
  @Input() save$: Observable<void>;
  @Input() disabled$: Observable<boolean>;
  @Input() pluginId: string;
  @Input() prefillConfiguration$?: Observable<SamenwerkfunctionaliteitConfig>;

  @Output() valid: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output() configuration: EventEmitter<SamenwerkfunctionaliteitConfig> = new EventEmitter<SamenwerkfunctionaliteitConfig>();

  private saveSubscription: Subscription;

  private readonly formValue$ = new BehaviorSubject<SamenwerkfunctionaliteitConfig | null>(null);
  private readonly valid$ = new BehaviorSubject<boolean>(false);

  ngOnInit(): void {
    this.openSaveSubscription();
  }

  ngOnDestroy(): void {
    this.saveSubscription?.unsubscribe();
  }

  formValueChange(formValue: SamenwerkfunctionaliteitConfig): void {
    const valid = !!(formValue.samenwerkfunctionaliteitUrl && formValue.certificate);

    this.valid$.next(valid);
    this.valid.emit(valid);
  }

  private openSaveSubscription() {
    this.saveSubscription = this.save$?.subscribe(() => {
      combineLatest([this.formValue$, this.valid$])
        .pipe(take(1))
        .subscribe(([formValue, valid]) => {
          if (valid) {
            this.configuration.emit(formValue);
          }
        });
    });
  }
}
