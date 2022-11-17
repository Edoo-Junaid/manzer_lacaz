export class GetOrderList{
  private user_id:number;
  private menu_id:number;


  constructor(user_id: number,menu_id: number) {
    this.user_id = user_id;
    this.menu_id = menu_id;
  }
}
