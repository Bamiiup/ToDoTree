export default class PriorityUtils {
  static ordinal(priority) {
    if(priority === "veryHigh") {
      return 4;
    }

    if(priority === "high") {
      return 3;
    }

    if(priority === "medium") {
      return 2;
    }

    if(priority === "low") {
      return 1;
    }

    if(priority === "veryLow") {
      return 0;
    }

    return null;
  }
}
