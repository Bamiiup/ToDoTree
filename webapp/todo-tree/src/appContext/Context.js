import UserService from "./../services/UserService";
import TodoService from "./../services/TodoService";
import DateUtils from "./../utils/DateUtils";
import { createStore } from 'redux';
import mainReducer from './../store/MainReducer';

const store = createStore(mainReducer);

const startUrl = "http://localhost:8080/ToDoTree/";

const userService = new UserService(startUrl);
const todoService = new TodoService(startUrl);

export {userService, todoService, store};
