import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {BadgeModule, CardModule, GridModule, ModalModule} from '@coreui/angular';
import { ChartjsModule } from '@coreui/angular-chartjs';

import { ViewcountComponent } from './viewcount.component';
import { ViewcountRoutingModule } from './viewcount-routing.module';
import { DocsComponentsModule } from '@docs-components/docs-components.module';
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [ViewcountComponent],
  imports: [
    CommonModule,
    ViewcountRoutingModule,
    ChartjsModule,
    CardModule,
    GridModule,
    BadgeModule,
    DocsComponentsModule,
    ReactiveFormsModule,
    ModalModule
  ]
})
export class ViewcountModule {
}
