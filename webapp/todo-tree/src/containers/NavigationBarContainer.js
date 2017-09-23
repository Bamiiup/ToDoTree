import React from 'react';
import NavigationBar from './../components/NavigationBar';
import {connect} from 'react-redux';
import {setIsAuthenticated} from './../store/user/UserActions';

class NavigationBarContainer extends React.Component {

  onClickLogIn = () => {
    this.props.history.push("/authentication");
  }

  onClickLogOut = () => {
    let action = setIsAuthenticated(false);
    this.props.dispatch(action);
    localStorage.removeItem("token");

    this.props.history.push("/");
  }

  render() {
    return(
      <NavigationBar
        isAuthenticated={this.props.isAuthenticated}
        onClickLogIn={this.onClickLogIn}
        onClickLogOut={this.onClickLogOut}
        />
    );
  }
}

const mapStateToProps = (state) => {
  let props = {
    isAuthenticated: state.server.user.isAuthenticated
  };
  return props;
};

//function
let reduxContainerCreator = connect(mapStateToProps);
//React component class
let ReduxNavigationBarContainer = reduxContainerCreator(NavigationBarContainer);

export default ReduxNavigationBarContainer;
