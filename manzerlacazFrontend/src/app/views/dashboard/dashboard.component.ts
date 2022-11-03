import { Component, OnInit } from '@angular/core';
import {UntypedFormControl, UntypedFormGroup} from '@angular/forms';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { DashboardChartsData, IChartProps } from './dashboard-charts-data';
import {OrderService} from "../../services/order/order.service";
import {MenuService} from "../../services/menu/menu.service";
import {Order} from "./Order";
import {Menu} from "./Menu";
import {DailyConfirmation} from "./DailyConfirmation";

@Component({
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  private controlNamesMonday = ['optionMon', 'paymentMon'];
  private controlNamesTuesday = ['optionTue', 'paymentTue'];
  private controlNamesWednesday = ['optionWed', 'paymentWed'];
  private controlNamesThursday = ['optionThu', 'paymentThu'];
  private controlNamesFriday = ['optionFri', 'paymentFri'];




  menu!: Menu[] ;
  menuDescriptions!:string[];
  priceDescriptions!:string[];
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


  constructor(private chartsData: DashboardChartsData, public orderService: OrderService, public menuService: MenuService) {
  }

  public mainChart: IChartProps = {};
  public chart: Array<IChartProps> = [];
  public trafficRadioGroup = new UntypedFormGroup({
    trafficRadio: new UntypedFormControl('Month')
  });

  ngOnInit(): void {
    //Displaying menus according to id
    this.menuService.getMenus().subscribe((data: Array<Menu>) => {
      this.menu=[data[0],data[1],data[2],data[3],data[4]];
      this.menuDescriptions=[data[0].description,data[1].description,data[2].description,data[3].description,data[4].description,]
      this.priceDescriptions=[data[0].price,data[1].price,data[2].price,data[3].price,data[4].price,]
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
      paymentFri: new FormControl(),
      //Bin buttons
      binMon: new FormControl(),
      binTue: new FormControl(),
      binWed: new FormControl(),
      binThu: new FormControl(),
      binFri: new FormControl()
    });

    // this.initCharts();
  }

  //Reset btn
  onClickResetMon(data: any) {

    // @ts-ignore
    this.controlNamesMonday.map((value: string) => this.formData.get(value).setValue(null));
  }

  onClickResetTue(data: any) {

    // @ts-ignore
    this.controlNamesTuesday.map((value: string) => this.formData.get(value).setValue(null));
  }

  onClickResetWed(data: any) {
    // @ts-ignore
    this.controlNamesWednesday.map((value: string) => this.formData.get(value).setValue(null));
  }

  onClickResetThu(data: any) {

    // @ts-ignore
    this.controlNamesThursday.map((value: string) => this.formData.get(value).setValue(null));
  }

  onClickResetFri(data: any) {
    this.menuService.getMenus().subscribe((data: Array<Menu>) => {
      this.priceDescriptions=[data[0].price,data[1].price,data[2].price,data[3].price,data[4].price,]
    });
    var option:any[] = new Array(data.optionMon,data.optionTue,data.optionWed,data.optionThu,data.optionFri), index=option.length
    while (index-- && !option[index]);
    console.log(this.priceDescriptions[index]);

    // @ts-ignore
    this.controlNamesFriday.map((value: string) => this.formData.get(value).setValue(null));
  }



  //Submit btn
  onClickSubmit(data: any) {
    //console.log(data)
    //array to store confirmation from checkbox => True or false values.
    var confirmation:any[]= new Array(data.confirmMon,data.confirmTue,data.confirmWed,data.confirmThu,data.confirmFri);

    //array to store payment from checkbox => Simplified if to convert true to 1 and false to 0
    var payment:any[] = new Array( data.paymentMon?1:0,data.paymentTue?1:0,data.paymentWed?1:0,data.paymentThu?1:0,data.paymentFri?1:0)

    //array to store option from radio Button=> Veg or Non-Veg
    var option:any[] = new Array(data.optionMon,data.optionTue,data.optionWed,data.optionThu,data.optionFri)

    //array to store orders
    var orders = new Array<Order>;

    //array to store orders to be deleted
    var deleteRequests = new Array<DailyConfirmation>;

    //looping through all orders
    for(var i in confirmation){

      //if checkbox for confimation is selected => Order is added to orders array to be saved
      //if not selected goes into else branch => Order is added to deleteRequests to be deleted
      if(confirmation[i]){
        let  order= new Order(1,this.menu[i].id,payment[i],option[i]);
        orders.push(order);
      }else{
        let dailyConfimation = new DailyConfirmation(1,this.menu[i].id);
        deleteRequests.push(dailyConfimation);
      }
    }

    //to test
    console.log(orders)

    // faire appel à l'api
    //saving orders
    this.orderService.postOrder(orders).subscribe((data) => {
      console.log('message::::', data);
    });

    //removing orders
    this.orderService.removeOrder(deleteRequests).subscribe((data)=>{
      console.log(data);
    })

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
