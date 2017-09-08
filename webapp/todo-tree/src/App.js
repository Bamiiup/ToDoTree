import React from 'react';
import NavigationBarContainer from './containers/NavigationBarContainer';
import WelcomeContent from './components/WelcomeContent';
import RegistrationContainer from './containers/RegistrationContainer';
import AuthenticationContainer from './containers/AuthenticationContainer';
import TodoEditorPage from './pages/TodoEditorPage';

import {HashRouter, Route} from 'react-router-dom'

class App extends React.Component {
  render() {
    return(
      <HashRouter>
        <div>
          <Route path="/" component={NavigationBarContainer} />
          <Route exact path="/" component={WelcomeContent} />
          <Route exact path="/authentication" component={AuthenticationContainer} />
          <Route exact path="/registration" component={RegistrationContainer} />

          <Route exact path="/todoEditor/new/:parentId?" component={TodoEditorPage} />
          <Route exact path="/todoEditor/edit/:id" component={TodoEditorPage} />

        </div>
      </HashRouter>
    );
  }
}

export default App;
