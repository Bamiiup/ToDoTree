import React from 'react'
import {userService} from './../appContext/Context'
import Authentication from './../components/Authentication';

export default class AuthenticationContainer extends React.Component {
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

  onClickEnter = () => {
    userService.authentication(this.state).then(response => {
      return response.text();
    }).then(text => {
      localStorage.setItem("token", text);
      this.props.history.push("/tree");
    });
  }

  render() {
    return(
      <Authentication
        login={this.state.login}
        onChangeLogin={this.onChangeLogin}
        password={this.state.password}
        onChangePassword={this.onChangePassword}
        onClickEnter={this.onClickEnter}
        />
    );
  }
}
