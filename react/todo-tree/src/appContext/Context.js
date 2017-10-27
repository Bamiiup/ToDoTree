import UserService from "./../services/UserService";
import TodoService from "./../services/TodoService";
import RepresentationService from "./../services/RepresentationService";
import { createStore } from 'redux';
import mainReducer from './../store/MainReducer';

let store; 

let startUrl;

if(process.env.NODE_ENV === "production") {
	startUrl = "./";
	store = createStore(mainReducer);
}

if(process.env.NODE_ENV === "development") {
	startUrl = "http://localhost:8080/ToDoTree/";
	store = createStore(mainReducer,
		window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__());
}

const userService = new UserService(startUrl);
const todoService = new TodoService(startUrl);
const representationService = new RepresentationService(startUrl);

export {userService, todoService, representationService, store};
