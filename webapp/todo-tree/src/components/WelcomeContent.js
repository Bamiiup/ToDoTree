import React from 'react'
import {Link} from 'react-router-dom'

export default class WelcomeContent extends React.Component {
  render() {
    return(
      <div>
        <div className="container">
          <div className="jumbotron">
            <h1>Welcome!</h1>
            <p>
              Here is ToDo-Tree web application! We use react + redux and spring + hibernate to make you happy!
              And other intresting description...
              <br/>
              <Link to="/registration">Sign up</Link> or
              <Link to="/authentication"> Sign in</Link>
            </p>
          </div>
        </div>
      </div>
    );
  }
}
