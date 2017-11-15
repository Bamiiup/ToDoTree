import React from 'react';
import NavigationBarContainer from './containers/NavigationBarContainer';
import WelcomeContent from './components/WelcomeContent';
import RegistrationContainer from './containers/RegistrationContainer';
import AuthenticationContainer from './containers/AuthenticationContainer';
import TodoEditorPage from './pages/TodoEditorPage';
import TodoTreeContainer from './containers/TodoTreeContainer';
import RepresentationEditorContainer from './containers/RepresentationEditorContainer';
import RepresentationListContainer from './containers/RepresentationListContainer';

import {HashRouter, Route} from 'react-router-dom'
import {Provider} from 'react-redux';
import {store} from './appContext/Context';
import {setIsAuthenticated} from './store/server/user/Actions';

class App extends React.Component {

  constructor(props) {
    super(props);
    let token = localStorage.getItem("token");
    store.dispatch(setIsAuthenticated(token !== null));
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

            <Route exact path="/representationEditor/:id?" component={RepresentationEditorContainer}/>
            <Route exact path="/representationList" component={RepresentationListContainer}/>

          </div>
        </HashRouter>
      </Provider>
    );
  }
}

export default App;
