export default class Parent {

  constructor(x, y) {
    this.x = x;
    this.y = y;
  }

  get getX() {
    return this.x;
  }

  get getY() {
    return this.y;
  }
}