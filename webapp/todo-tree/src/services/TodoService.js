export default class TodoService {
  constructor(startUrl) {
    this.startUrl = startUrl;
  }

  create(todo) {
    return fetch(this.startUrl + "todo", {
      method: "post",
      headers: new Headers({
        "Content-type": "application/json;chartSet=UTF-8",
        "token": localStorage.getItem("token")
	    }),
      body: JSON.stringify(todo)
    });
  }

  get(id) {
    return fetch(this.startUrl + "todo/" + id, {
      method: "get",
      headers: new Headers({
        "Content-type": "application/json;chartSet=UTF-8",
        "token": localStorage.getItem("token")
	    })
    });
  }

  update(todo) {
    return fetch(this.startUrl + "todo", {
      method: "put",
      headers: new Headers({
        "Content-type": "application/json;chartSet=UTF-8",
        "token": localStorage.getItem("token")
	    }),
      body: JSON.stringify(todo)
    });
  }
}
