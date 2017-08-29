import React from 'react';
import NavigationBar from './components/NavigationBar'
import WelcomeContent from './components/WelcomeContent'
import RegistrationContainer from './containers/RegistrationContainer'
import AuthenticationContainer from './containers/AuthenticationContainer'

import {HashRouter, Route} from 'react-router-dom'

class App extends React.Component {
  render() {
    return(
      <HashRouter>
        <div>
          <Route path="/" component={NavigationBar} />
          <Route exact path="/" component={WelcomeContent} />
          <Route exact path="/authentication" component={AuthenticationContainer} />
          <Route exact path="/registration" component={RegistrationContainer} />
        </div>
      </HashRouter>
    );
  }
}

export default App;
