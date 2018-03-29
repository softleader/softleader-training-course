import Parent from "./Parent"

export default class Children extends Parent {

  constructor() {
    super("a", "b");
  }

  get getX() {
    return "c";
  }

  get getY() {
    return "d";
  }

  static getZ() {
    return "z";
  }
}