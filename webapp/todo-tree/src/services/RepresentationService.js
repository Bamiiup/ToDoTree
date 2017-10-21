export default class RepresentationService {
  constructor(startUrl) {
    this.startUrl = startUrl;
  }

  create(representation) {
    return fetch(this.startUrl + "representation", {
      method: "post",
      headers: new Headers({
        "Content-type": "application/json;chartSet=UTF-8",
        "token": localStorage.getItem("token")
	    }),
      body: JSON.stringify(representation)
    });
  }

  get(id) {
    return fetch(this.startUrl + "representation/" + id, {
      method: "get",
      headers: new Headers({
        "Content-type": "application/json;chartSet=UTF-8",
        "token": localStorage.getItem("token")
	    })
    });
  }

  update(representation) {
    return fetch(this.startUrl + "representation", {
      method: "put",
      headers: new Headers({
        "Content-type": "application/json;chartSet=UTF-8",
        "token": localStorage.getItem("token")
	    }),
      body: JSON.stringify(representation)
    });
  }
}
