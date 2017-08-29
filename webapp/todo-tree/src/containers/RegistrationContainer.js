import React from 'react'
import {userService} from './../appContext/Context'
import Registration from './../components/Registration';

export default class RegistrationContainer extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      login: "",
      password: ""
    };
  }

  onChangeLogin = (event) => {
    this.setState({login: event.target.value});
  }

  onChangePassword = (event) => {
    this.setState({password: event.target.value});
  }

  onClickSignUp = () => {
    userService.create(this.state).then(() => {
      return userService.authentication(this.state);
    }).then((response) => {
      return response.text();
    }).then(token => {
      localStorage.setItem("token", token);
      this.props.history.push("/tree");
    });
  }

  render() {
    return(
      <Registration
        login={this.state.login}
        onChangeLogin={this.onChangeLogin}
        password={this.state.password}
        onChangePassword={this.onChangePassword}
        onClickSignUp={this.onClickSignUp}
        />
    );
  }
}
