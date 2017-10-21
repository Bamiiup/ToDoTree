import UserService from "./../services/UserService";
import TodoService from "./../services/TodoService";
import RepresentationService from "./../services/RepresentationService";
import { createStore } from 'redux';
import mainReducer from './../store/MainReducer';

const store = createStore(mainReducer,
  window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__());

const startUrl = "http://localhost:8080/ToDoTree/";

const userService = new UserService(startUrl);
const todoService = new TodoService(startUrl);
const representationService = new RepresentationService(startUrl);

export {userService, todoService, representationService, store};
