import { Component, OnInit } from '@angular/core';
import {UntypedFormControl, UntypedFormGroup} from '@angular/forms';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { DashboardChartsData, IChartProps } from './dashboard-charts-data';
import {Order} from "./Order";
import {Menu} from "./Menu";
import {DailyConfirmation} from "./DailyConfirmation";
import {MenuService} from "../../services/Menu/menu.service";
import {OrderService} from "../../services/Order/order.service";

@Component({
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  menu!: Menu[] ;
  menuDescriptions!:string[];
  //menu id
  menuMonDesc!: string;
  menuTueDesc!: string;
  menuWedDesc!: string;
  menuThuDesc!: string;
  menuFriDesc!: string;

  menuMon!: Menu;
  menuTue!: Menu;
  menuWed!: Menu;
  menuThu!: Menu;
  menuFri!: Menu;

  formData!: FormGroup;

  constructor(private chartsData: DashboardChartsData, public orderService: OrderService,public menuService: MenuService) {
  }

  public mainChart: IChartProps = {};
  public chart: Array<IChartProps> = [];
  public trafficRadioGroup = new UntypedFormGroup({
    trafficRadio: new UntypedFormControl('Month')
  });

  ngOnInit(): void {
    //Displaying menus according to id

    this.menuService.getMenus().subscribe((data:Array<Menu>)=>{
      this.menu=[data[0],data[1],data[2],data[3],data[4]];
      this.menuDescriptions=[data[0].description,data[1].description,data[2].description,data[3].description,data[4].description,]
      console.log(this.menu)
    });

    //Form variables
    this.formData = new FormGroup({
      //Checkbox confirmation
      confirmMon: new FormControl(),
      confirmTue: new FormControl(),
      confirmWed: new FormControl(),
      confirmThu: new FormControl(),
      confirmFri: new FormControl(),
      //Radio buttons: veg & non-veg
      optionMon: new FormControl(),
      optionTue: new FormControl(),
      optionWed: new FormControl(),
      optionThu: new FormControl(),
      optionFri: new FormControl(),
      //Checkbox paid
      paymentMon: new FormControl(),
      paymentTue: new FormControl(),
      paymentWed: new FormControl(),
      paymentThu: new FormControl(),
      paymentFri: new FormControl()
    });

    // this.initCharts();
  }

  //Reset btn
  onClickReset(data: any){
    this.formData.reset();
  }

  //Submit btn
  onClickSubmit(data: any) {
    //array to store confirmation from checkbox => True or false values.

    //array to store payment from checkbox => Simplified if to convert true to 1 and false to 0
    var payment:any[] = new Array( data.confirmMon?1:0,data.confirmTue?1:0,data.confirmWed?1:0,data.confirmThu?1:0,data.confirmFri?1:0)

    //array to store option from radio Button=> Veg or Non-Veg
    var option:any[] = new Array(data.optionMon,data.optionTue,data.optionWed,data.optionThu,data.optionFri)

    //array to store orders
    var orders = new Array<Order>;

    //array to store orders to be deleted
    var deleteRequests = new Array<DailyConfirmation>;

  }

  // initCharts(): void {
  //   this.mainChart = this.chartsData.mainChart;
  // }

  // setTrafficPeriod(value: string): void {
  //   this.trafficRadioGroup.setValue({ trafficRadio: value });
  //   this.chartsData.initMainChart(value);
  //   this.initCharts();
  // }
}
