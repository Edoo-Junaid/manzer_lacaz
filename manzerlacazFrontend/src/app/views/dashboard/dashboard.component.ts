import { Component, OnInit } from '@angular/core';
import {UntypedFormControl, UntypedFormGroup} from '@angular/forms';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { DashboardChartsData, IChartProps } from './dashboard-charts-data';
import {UserService} from "../../services/user/user.service";
import {Order} from "./Order";
import {Menu} from "./Menu";
import {DailyConfirmation} from "./DailyConfirmation";

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

  constructor(private chartsData: DashboardChartsData, public userService: UserService) {
  }

  public mainChart: IChartProps = {};
  public chart: Array<IChartProps> = [];
  public trafficRadioGroup = new UntypedFormGroup({
    trafficRadio: new UntypedFormControl('Month')
  });

  ngOnInit(): void {
    //Displaying menus according to id
    this.userService.getMenus().subscribe((data: Array<Menu>) => {
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
    var confirmation:any[]= new Array(data.confirmMon,data.confirmTue,data.confirmWed,data.confirmThu,data.confirmFri);

    //array to store payment from checkbox => Simplified if to convert true to 1 and false to 0
    var payment:any[] = new Array( data.confirmMon?1:0,data.confirmTue?1:0,data.confirmWed?1:0,data.confirmThu?1:0,data.confirmFri?1:0)

    //array to store option from radio Button=> Veg or Non-Veg
    var option:any[] = new Array(data.optionMon,data.optionTue,data.optionWed,data.optionThu,data.optionFri)

    //array to store orders
    var orders = new Array<Order>;

    //array to store orders to be deleted
    var deleteRequests = new Array<DailyConfirmation>;

    //looping throug all orders
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
    console.log("These are orders")
    console.log(orders)
    console.log("These are orders")

    // faire appel à l'api
    //saving orders
    this.userService.postOrder(orders).subscribe((data) => {
      console.log('message::::', data);
    });

    //removing orders
    this.userService.removeOrder(deleteRequests).subscribe((data)=>{
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
