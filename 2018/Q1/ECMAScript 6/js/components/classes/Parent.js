export default class Parent {

  constructor(x, y) {
    this.x = x;
    this.y = y;
  }

  get getX() {
    return this.x;
  }

  set setX(x) {
    this.x = x;
  }

  get getY() {
    return this.y;
  }

  set setY(y) {
    this.y = y;
  }
}