import Parent from "./Parent"

export default class Children extends Parent {

  constructor() {
    super("a", "b");
  }

  get getX() {
    return "c";
  }

  static getZ() {
    return "z";
  }
}