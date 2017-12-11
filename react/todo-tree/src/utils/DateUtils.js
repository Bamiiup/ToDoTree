export default class DateUtils {
  static formatToHtmlDateInput(date) {

    let year = date.getFullYear();
    let monthIndex = date.getMonth();
    let month = monthIndex < 9 ? ("0" + monthIndex) : monthIndex;
    let dayIndex = date.getDate();
    let day = dayIndex < 9 ? ("0" + dayIndex) : dayIndex;

    return year + "-" + month + "-" + day;
  }

  static formatToDate(htmlDateInput) {
    let htmlDateInputParts = htmlDateInput.split("-");
    let year = parseInt(htmlDateInputParts[0], 10);
    let month = parseInt(htmlDateInputParts[1], 10);
    let day = parseInt(htmlDateInputParts[2], 10);

    let date = new Date();
    date.setFullYear(year);
    date.setMonth(month);
    date.setDate(day);

    return date;
  }

  static cloneDate(date) {
    let time = date.getTime();
    let result = new Date();
    result.setDate(time);
    
    return result;
  }

  static dateWithoutTime(rawDate) {
    let result = DateUtils.cloneDate(rawDate);
    result.setHours(0);
    result.setMinutes(0);
    result.setSeconds(0);
    result.setMilliseconds(0);

    return result;
  }

  static addDays(date, dayNumber) {
    let result = DateUtils.cloneDate(date);
    let resultTime = result.getTime() + 24 * 60 * 60 * 1000 * dayNumber;
    result.setTime(resultTime);

    return result;
  }
}
