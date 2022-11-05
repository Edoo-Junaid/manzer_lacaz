import {Component, Input, OnInit} from '@angular/core';
import {UntypedFormControl, UntypedFormGroup} from '@angular/forms';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {DashboardChartsData, IChartProps} from './dashboard-charts-data';
import {OrderService} from "../../services/order/order.service";
import {MenuService} from "../../services/menu/menu.service";
import {Order} from "./Order";
import {Menu} from "./Menu";
import {DailyConfirmation} from "./DailyConfirmation";
import {A} from "@angular/cdk/keycodes";
import {GetMenuList} from "./GetMenuList";


@Component({
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  public orderTotal!: number;
  private controlNamesMonday = ['optionMon', 'paymentMon'];
  private controlNamesTuesday = ['optionTue', 'paymentTue'];
  private controlNamesWednesday = ['optionWed', 'paymentWed'];
  private controlNamesThursday = ['optionThu', 'paymentThu'];
  private controlNamesFriday = ['optionFri', 'paymentFri'];


  option!: any[];
  orderOption!:string[];
  orderPayment!:number[];
  payment!: any[];
  menu:Menu[] = new Array(5);
  menuData!:Menu[];
  menuDescriptions!: string[];
  priceDescriptions!: string[];
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
    let weekNum = new GetMenuList(47);
    this.menuService.getMenus(weekNum).subscribe((data: Array<Menu>) => {
      for(var i in data){
        if(data[i].day=="Monday"){
          this.menu[0]=data[i];
        }
        if(data[i].day=="Tuesday"){
          this.menu[1]=data[i];
        }
        if(data[i].day=="Wednesday"){
          this.menu[2]=data[i];
        }
        if(data[i].day=="Thursday"){
          this.menu[3]=data[i];
        }
        if(data[i].day=="Friday"){
          this.menu[4]=data[i];
        }
      }
      this.menuDescriptions = [this.menu[0].description, this.menu[1].description, this.menu[2].description, this.menu[3].description, this.menu[4].description,]
      this.priceDescriptions = [this.menu[0].price, this.menu[1].price, this.menu[2].price, this.menu[3].price, this.menu[4].price,]

    });


    //Form variables

    this.formData = new FormGroup({

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
    // @ts-ignore
    this.formData.get("optionMon").setValue("veg");
  }

  calcPrice(data: any) {
    const radios = this.formData.getRawValue();
    //console.log(radios)
    this.option = [radios.optionMon, radios.optionTue, radios.optionWed, radios.optionThu, radios.optionFri]
    var count = 0
    var total = 0;
    while (count < this.option.length) {
      if (this.option[count] != null || this.option[count] != undefined) {
        let numberValue = Number(this.priceDescriptions[count]);
        total = total + numberValue
      }
      count++
    }
    //console.log(total)
    this.orderTotal = total;
  }


  //Reset btn
  onClickResetMon(data: any) {

    if (data.optionMon != null) {
      let numberValue = Number(this.priceDescriptions[0]);
      this.orderTotal = this.orderTotal - numberValue;
    }
    // @ts-ignore
    this.controlNamesMonday.map((value: string) => this.formData.get(value).setValue(null));
  }

  onClickResetTue(data: any) {
    if (data.optionTue != null) {
      let numberValue = Number(this.priceDescriptions[1]);
      this.orderTotal = this.orderTotal - numberValue;
    }
    // @ts-ignore
    this.controlNamesTuesday.map((value: string) => this.formData.get(value).setValue(null));
  }

  onClickResetWed(data: any) {
    if (data.optionWed != null) {
      let numberValue = Number(this.priceDescriptions[2]);
      this.orderTotal = this.orderTotal - numberValue;
    }
    // @ts-ignore
    this.controlNamesWednesday.map((value: string) => this.formData.get(value).setValue(null));
  }

  onClickResetThu(data: any) {
    if (data.optionThu != null) {
      let numberValue = Number(this.priceDescriptions[3]);
      this.orderTotal = this.orderTotal - numberValue;
    }
    // @ts-ignore
    this.controlNamesThursday.map((value: string) => this.formData.get(value).setValue(null));
  }

  onClickResetFri(data: any) {
    if (data.optionFri != null) {
      let numberValue = Number(this.priceDescriptions[4]);
      this.orderTotal = this.orderTotal - numberValue;
    }
    // @ts-ignore
    this.controlNamesFriday.map((value: string) => this.formData.get(value).setValue(null));

  }


  //Submit btn
  onClickSubmit(data: any) {

    //array to store confirmation from checkbox => True or false values.
    var confirmation: any[] = new Array(data.confirmMon, data.confirmTue, data.confirmWed, data.confirmThu, data.confirmFri);

    //array to store payment from checkbox => Simplified if to convert true to 1 and false to 0
    var payment: any[] = new Array(data.paymentMon ? 1 : 0, data.paymentTue ? 1 : 0, data.paymentWed ? 1 : 0, data.paymentThu ? 1 : 0, data.paymentFri ? 1 : 0)

    //array to store option from radio Button=> Veg or Non-Veg


    //array to store orders
    var orders = new Array<Order>;

    //array to store orders to be deleted
    var deleteRequests = new Array<DailyConfirmation>;

    //looping through all orders
    // for(var i in confirmation){
    //
    //   //if checkbox for confimation is selected => Order is added to orders array to be saved
    //   //if not selected goes into else branch => Order is added to deleteRequests to be deleted
    //   if(confirmation[i]){
    //     let  order= new Order(1,this.menu[i].id,payment[i],option[i]);
    //     orders.push(order);
    //   }else{
    //     let dailyConfimation = new DailyConfirmation(1,this.menu[i].id);
    //     deleteRequests.push(dailyConfimation);
    //   }
    // }

    //to test
    // console.log(orders)

    // faire appel à l'api
    //saving orders
    // this.orderService.postOrder(orders).subscribe((data) => {
    //   console.log('message::::', data);
    // });

    //removing orders
    // this.orderService.removeOrder(deleteRequests).subscribe((data)=>{
    //   console.log(data);
    // })

  }

  // initCharts(): void {
  //   this.mainChart = this.chartsData.mainChart;
  // }

  // setTrafficPeriod(value: string): void {
  //   this.trafficRadioGroup.setValue({ trafficRadio: value });
  //   this.chartsData.initMainChart(value);
  //   this.initCharts();
  // }


  changeOption(data: any) {
    this.calcPrice(data)
  }

  changeInForm($event: Event) {
    console.log(this.menu)
   // console.log(this.formData.getRawValue());
    const formVal = this.formData.getRawValue();
    //console.log(radios)
    this.orderOption = [formVal.optionMon, formVal.optionTue, formVal.optionWed, formVal.optionThu, formVal.optionFri];
    this.orderPayment=[formVal.paymentMon?1:0,formVal.paymentTue?1:0,formVal.paymentWed?1:0,formVal.paymentThu?1:0,formVal.paymentFri?1:0];
    let orders = new Array<Order>;
    for(let i in  this.orderOption){
      if(this.orderOption[i]!=null){
        let order = new Order(1,this.menu[i].id,this.orderPayment[i],this.orderOption[i]);
        orders.push(order);
      }
    }
    console.log(orders);
   // saving orders
    this.orderService.postOrder(orders).subscribe((data) => {
      console.log('message::::', data);
    });

  }

}
