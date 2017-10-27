export default class UserService {
  constructor(startUrl) {
    this.startUrl = startUrl;
  }

  create(user) {
    return fetch(this.startUrl + "user", {
      method: "post",
      headers: new Headers({
        "Content-type": "application/json;chartSet=UTF-8"
	    }),
      body: JSON.stringify(user)
    });
  }

  authentication(user) {
    return fetch(this.startUrl + "authentication", {
      method: "post",
      headers: new Headers({
        "Content-type": "application/json;chartSet=UTF-8"
	    }),
      body: JSON.stringify(user)
    });
  }
}
