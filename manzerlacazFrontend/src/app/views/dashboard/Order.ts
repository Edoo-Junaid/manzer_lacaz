export class Order{
  user_id:number;
  menu_id:number;
  payment:number;
  option:string;


  constructor(user_id: number, menu_id: number, payment: number, option: string) {
    this.user_id = user_id;
    this.menu_id = menu_id;
    this.payment = payment;
    this.option = option;
  }
}
