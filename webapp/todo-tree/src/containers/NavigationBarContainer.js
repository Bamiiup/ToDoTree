import React from 'react';
import NavigationBar from './../components/NavigationBar';
import {connect} from 'react-redux';
import {setIsAuthenticated} from './../store/server/user/Actions';

class NavigationBarContainer extends React.Component {

  onClickLogIn = () => {
    this.props.history.push("");
  }

  onClickLogOut = () => {
    this.props.dispatch(setIsAuthenticated(false));
    localStorage.removeItem("token");
  }

  render() {
    return(
      <NavigationBar
        {...this.props}
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

export default connect(mapStateToProps)(NavigationBarContainer);
