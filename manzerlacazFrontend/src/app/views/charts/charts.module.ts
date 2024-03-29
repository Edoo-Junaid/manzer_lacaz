import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {AlertModule, BadgeModule, CardModule, GridModule, ModalModule} from '@coreui/angular';
import { ChartjsModule } from '@coreui/angular-chartjs';

import { ChartsComponent } from './charts.component';
import { ChartsRoutingModule } from './charts-routing.module';
import { DocsComponentsModule } from '@docs-components/docs-components.module';
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [ChartsComponent],
    imports: [
        CommonModule,
        ChartsRoutingModule,
        ChartjsModule,
        CardModule,
        GridModule,
        BadgeModule,
        DocsComponentsModule,
        ReactiveFormsModule,
        ModalModule,
        AlertModule
    ]
})
export class ChartsModule {
}
