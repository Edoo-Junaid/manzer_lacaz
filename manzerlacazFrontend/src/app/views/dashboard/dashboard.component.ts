import {Component, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import { IChartProps} from './dashboard-charts-data';
import {OrderService} from "../../services/order/order.service";
import {MenuService} from "../../services/menu/menu.service";
import {Order} from "./Order";
import {Menu} from "./Menu";
import {GetMenuList} from "./GetMenuList";
import {DailyConfirmation} from "./DailyConfirmation";


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

  orderOption!:string[];
  orderPayment!:number[];

  option1!:any[];
  payment1!:any[];
  option!:any[];
  payment!:any[];
  menu:Menu[] = new Array(5);
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


  constructor( public orderService: OrderService, public menuService: MenuService) {


  }

  ngOnInit(): void {
    //Displaying menus according to id
    let weekNum = new GetMenuList(47);
    let subscription = this.menuService.getMenus(weekNum).subscribe((data: Array<Menu>) => {
      console.log(data)
      for(const datum of data){
        if(datum.day=="Monday") this.menu[0] = datum;
        if(datum.day=="Tuesday") this.menu[1] = datum;
        if(datum.day=="Wednesday") this.menu[2] = datum;
        if(datum.day=="Thursday") this.menu[3] = datum;
        if(datum.day=="Friday") this.menu[4] = datum;
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
    this.formData.get("optionMon")?.setValue("veg");
  }



//Bin icon: reset
  //Reset btn: resets option radio, payment checkbox and minus price from total when bin is clicked
  onClickResetMon(data: any) {

    if(data.optionMon!=null && data.paymentMon!=true){
      let numberValue = Number(this.priceDescriptions[0]);
      this.orderTotal=this.orderTotal-numberValue;

    }
    this.controlNamesMonday.map((value: string) => this.formData.get(value)?.setValue(null));
  }

  onClickResetTue(data: any) {
    if(data.optionTue!=null && data.paymentTue!=true){

      let numberValue = Number(this.priceDescriptions[1]);
      this.orderTotal = this.orderTotal - numberValue;
    }
    this.controlNamesTuesday.map((value: string) => this.formData.get(value)?.setValue(null));
  }

  onClickResetWed(data: any) {
    if(data.optionWed!=null && data.paymentWed!=true){

      let numberValue = Number(this.priceDescriptions[2]);
      this.orderTotal = this.orderTotal - numberValue;
    }
    this.controlNamesWednesday.map((value: string) => this.formData.get(value)?.setValue(null));
  }

  onClickResetThu(data: any) {

    if(data.optionThu!=null && data.paymentThu!=true){

      let numberValue = Number(this.priceDescriptions[3]);
      this.orderTotal = this.orderTotal - numberValue;
    }
    this.controlNamesThursday.map((value: string) => this.formData.get(value)?.setValue(null));
  }

  onClickResetFri(data: any) {
    if(data.optionFri!=null && data.paymentFri!=true){

      let numberValue = Number(this.priceDescriptions[4]);
      this.orderTotal = this.orderTotal - numberValue;
    }
    this.controlNamesFriday.map((value: string) => this.formData.get(value)?.setValue(null));
  }





  //option selection
//When option is changed price is calculated taking into consideration if payment is made or not
  changeOption(data:any) {
    this.calcPrice(data)
  }


  calcPrice(data:any){
    const radios = this.formData.getRawValue();
    console.log(radios)
    this.option=[radios.optionMon,radios.optionTue,radios.optionWed,radios.optionThu,radios.optionFri]
    this.payment=[radios.paymentMon,radios.paymentTue,radios.paymentWed,radios.paymentThu,radios.paymentFri]
    var count=0
    var total=0;
    while (count<this.option.length) {
      if((this.option[count]!=null ||this.option[count]!=undefined) &&this.payment[count]==null){
        let numberValue = Number(this.priceDescriptions[count]);
        total =total+numberValue
      }
      count++
    }
    // console.log(total)
    this.orderTotal=total;
  }





  //Payment checkbox when checked or unchecked
  changePaymentMon(data:any){
    this.onClickPaymentMon(data)

  }
  changePaymentTue(data:any){
    this.onClickPaymentTue(data)

  }
  changePaymentWed(data:any){
    this.onClickPaymentWed(data)
  }
  changePaymentThu(data:any){
    this.onClickPaymentThu(data)

  }
  changePaymentFri(data:any){
    this.onClickPaymentFri(data)

  }

  onClickPaymentMon(data:any){
    this.funcPayment(0)
  }
  onClickPaymentTue(data:any){
    this.funcPayment(1)

  }
  onClickPaymentWed(data:any){
    this.funcPayment(2)
  }
  onClickPaymentThu(data:any){
    this.funcPayment(3)

  }
  onClickPaymentFri(data:any){
    this.funcPayment(4)
  }


funcPayment(num:number){
    this.changeInForm()
  this.payment1 =[ this.formData.getRawValue().paymentMon,this.formData.getRawValue().paymentTue,this.formData.getRawValue().paymentWed,this.formData.getRawValue().paymentThu,this.formData.getRawValue().paymentFri]
  this.option1=[this.formData.getRawValue().optionMon,this.formData.getRawValue().optionTue,this.formData.getRawValue().optionWed,this.formData.getRawValue().optionThu,this.formData.getRawValue().optionFri]
  let numberValue = Number(this.priceDescriptions[num]);
  if(this.payment1[num]!=false && this.option1[num]!=null){
    this.orderTotal=this.orderTotal-numberValue
  }else if(this.payment1[num]!=true  && this.option1[num]!=null){
    this.orderTotal=this.orderTotal+numberValue
  }
}





  //Submit btn
  onClickSubmit(data: any) {

    //array to store confirmation from checkbox => True or false values.
    var confirmation:any[]= new Array(data.confirmMon,data.confirmTue,data.confirmWed,data.confirmThu,data.confirmFri);

    //array to store payment from checkbox => Simplified if to convert true to 1 and false to 0
    var payment:any[] = new Array( data.paymentMon?1:0,data.paymentTue?1:0,data.paymentWed?1:0,data.paymentThu?1:0,data.paymentFri?1:0)

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


  changeInForm() {
    console.log(this.menu)
   // console.log(this.formData.getRawValue());
    const {
      optionFri,
      optionMon,
      optionThu,
      optionTue,
      optionWed,
      paymentFri,
      paymentMon,
      paymentThu,
      paymentTue,
      paymentWed
    } = this.formData.getRawValue();
    //console.log(radios)
    this.orderOption = [optionMon, optionTue, optionWed, optionThu, optionFri];
    this.orderPayment=[paymentMon?1:0,paymentTue?1:0,paymentWed?1:0,paymentThu?1:0,paymentFri?1:0];
    let orders = new Array<Order>;
    for(let i in  this.orderOption){
      if(this.orderOption[i]!=null){
        let order = new Order(1,this.menu[i].id,this.orderPayment[i],this.orderOption[i]);
        orders.push(order);
      }
    }
    console.log(orders);
   // saving orders
    this.orderService.postOrder(orders).subscribe((data) => console.log('message::::', data));
  }

}
