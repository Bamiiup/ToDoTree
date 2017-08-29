import UserService from "./../services/UserService";

const startUrl = "http://localhost:8080/ToDoTree/";

const userService = new UserService();

userService.setStartUrl(startUrl);

export {userService};
