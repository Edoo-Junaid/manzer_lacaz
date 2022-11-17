export class Option{
  option:string;
  day:string;
  weekNum: number;

  constructor(option:string, day:string, weekNum: number) {
    this.day = day;
    this.option = option;
    this.weekNum = weekNum;
  }
}
