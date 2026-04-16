import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormModule, InputModule} from '@valtimo/components';
import {PluginTranslatePipeModule} from '@valtimo/plugin';
import {SamenwerkfunctionaliteitPluginConfigurationComponent} from './components/samenwerkfunctionaliteit-plugin-configuration/samenwerkfunctionaliteit-plugin-configuration.component';

@NgModule({
  declarations: [SamenwerkfunctionaliteitPluginConfigurationComponent],
  imports: [CommonModule, InputModule, PluginTranslatePipeModule, FormModule],
  exports: [SamenwerkfunctionaliteitPluginConfigurationComponent],
})
export class SamenwerkfunctionaliteitPluginModule {
}
