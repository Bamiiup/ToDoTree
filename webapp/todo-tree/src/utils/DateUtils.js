export default class DateUtils {
  formatToHtmlDateInput(date) {

    let year = date.getFullYear();
    let monthIndex = date.getMonth();
    let month = monthIndex < 9 ? ("0" + monthIndex) : monthIndex;
    let dayIndex = date.getDate();
    let day = dayIndex < 9 ? ("0" + dayIndex) : dayIndex;

    return year + "-" + month + "-" + day;
  }

  formatToDate(htmlDateInput) {
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
}
