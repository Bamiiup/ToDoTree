import UserService from "./../services/UserService";
import TodoService from "./../services/TodoService";
import DateUtils from "./../utils/DateUtils";

const startUrl = "http://localhost:8080/ToDoTree/";

const userService = new UserService(startUrl);
const todoService = new TodoService(startUrl);

const dateUtils = new DateUtils();

export {userService, todoService, dateUtils};
