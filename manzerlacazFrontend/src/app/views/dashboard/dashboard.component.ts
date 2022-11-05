import {Component, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import { IChartProps} from './dashboard-charts-data';
import {OrderService} from "../../services/order/order.service";
import {MenuService} from "../../services/menu/menu.service";
import {Order} from "./Order";
import {Menu} from "./Menu";
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
  menuDescriptions!: string[];
  priceDescriptions!: string[];
  formData!: FormGroup;


  constructor( public orderService: OrderService, public menuService: MenuService) {
  }

  public chart: Array<IChartProps> = [];

  ngOnInit(): void {
    //Displaying menus according to id
    let weekNum = new GetMenuList(47);
    let subscription = this.menuService.getMenus(weekNum).subscribe((data: Array<Menu>) => {
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

  calcPrice(data: any) {
    const radios = this.formData.getRawValue();
    //console.log(radios)
    this.option = [radios.optionMon, radios.optionTue, radios.optionWed, radios.optionThu, radios.optionFri]
    let count = 0;
    let total = 0;
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
    this.controlNamesMonday.map((value: string) => this.formData.get(value)?.setValue(null));
  }

  onClickResetTue(data: any) {
    if (data.optionTue != null) {
      let numberValue = Number(this.priceDescriptions[1]);
      this.orderTotal = this.orderTotal - numberValue;
    }
    this.controlNamesTuesday.map((value: string) => this.formData.get(value)?.setValue(null));
  }

  onClickResetWed(data: any) {
    if (data.optionWed != null) {
      let numberValue = Number(this.priceDescriptions[2]);
      this.orderTotal = this.orderTotal - numberValue;
    }
    this.controlNamesWednesday.map((value: string) => this.formData.get(value)?.setValue(null));
  }

  onClickResetThu(data: any) {
    if (data.optionThu != null) {
      let numberValue = Number(this.priceDescriptions[3]);
      this.orderTotal = this.orderTotal - numberValue;
    }
    this.controlNamesThursday.map((value: string) => this.formData.get(value)?.setValue(null));
  }

  onClickResetFri(data: any) {
    if (data.optionFri != null) {
      let numberValue = Number(this.priceDescriptions[4]);
      this.orderTotal = this.orderTotal - numberValue;
    }
    this.controlNamesFriday.map((value: string) => this.formData.get(value)?.setValue(null));
  }

  changeOption(data: any) {
    this.calcPrice(data)
  }

  changeInForm($event: Event) {
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
