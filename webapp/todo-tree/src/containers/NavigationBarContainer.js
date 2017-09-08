import React from 'react';
import NavigationBar from './../components/NavigationBar';

export default class NavigationBarContainer extends React.Component {
  constructor(props) {
    super(props);
    let token = localStorage.getItem("token");
    this.state = {
      isAuthenticated: Boolean(token)
    }
  }

  onClickLogOutOrIn = () => {
    if(this.state.isAuthenticated) {
      localStorage.removeItem("token");
      this.setState({
        isAuthenticated: false
      });
    } else {
      this.props.history.push("/");
    }
  }

  render() {
    return(
      <NavigationBar
        isAuthenticated={this.state.isAuthenticated}
        onClickLogOutOrIn={this.onClickLogOutOrIn}
        />
    );
  }
}
