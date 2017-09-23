import React from 'react';
import NavigationBarContainer from './containers/NavigationBarContainer';
import WelcomeContent from './components/WelcomeContent';
import RegistrationContainer from './containers/RegistrationContainer';
import AuthenticationContainer from './containers/AuthenticationContainer';
import TodoEditorPage from './pages/TodoEditorPage';
import TodoTreeContainer from './containers/TodoTreeContainer';

import {HashRouter, Route} from 'react-router-dom'
import {Provider} from 'react-redux';
import store from './store/Store';

import {setIsAuthenticated} from './store/user/UserActions';

class App extends React.Component {

  constructor(props) {
    super(props);
    let token = localStorage.getItem("token");
    let isAuthenticated;
    if(token) {
      isAuthenticated = true;
    } else {
      isAuthenticated = false;
    }
    let action = setIsAuthenticated(isAuthenticated);
    store.dispatch(action);
  }

  render() {
    return(
      <Provider store={store}>
        <HashRouter>
          <div>
            <Route path="/" component={NavigationBarContainer} />
            <Route exact path="/" component={WelcomeContent} />
            <Route exact path="/authentication" component={AuthenticationContainer} />
            <Route exact path="/registration" component={RegistrationContainer} />

            <Route exact path="/todoEditor/new/:parentId?" component={TodoEditorPage} />
            <Route exact path="/todoEditor/edit/:id" component={TodoEditorPage} />

            <Route exact path="/tree" component={TodoTreeContainer} />

          </div>
        </HashRouter>
      </Provider>
    );
  }
}

export default App;
