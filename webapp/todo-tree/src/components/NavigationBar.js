import React from 'react';
import {Link} from 'react-router-dom';

const createLinks = (props) =>  {
  if(props.isAuthenticated) {
    return(
      <ul className="navbar-nav">
        <li className="nav-item active">
          <Link className="nav-link" to="/">Home<span className="sr-only">(current)</span></Link>
        </li>
        <li className="nav-item active">
          <Link className="nav-link" to="/tree">Tree</Link>
        </li>
        <li className="nav-item active">
          <Link className="nav-link" to="/">
            Representations
          </Link>
        </li>
        <li className="nav-item">
          <Link className="nav-link" to="/"
            onClick={props.onClickLogOut}>
            Log out
          </Link>
        </li>
      </ul>
    );
  } else {
    return(
      <ul className="navbar-nav">
        <li className="nav-item active">
          <Link className="nav-link" to="/">Home<span className="sr-only">(current)</span></Link>
        </li>
        <li className="nav-item active">
          <Link className="nav-link" to="/registration">Registration</Link>
        </li>
        <li className="nav-item">
          <Link className="nav-link" to="/authentication"
            onClick={props.onClickLogIn}>
            Log in
          </Link>
        </li>
      </ul>
    );
  }
};

const NavigationBar = (props) => {
  return(
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <Link className="navbar-brand" to="/">ToDo-Tree</Link>
      <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarNavDropdown">
        {createLinks(props)}
      </div>
    </nav>
  );
};


export default NavigationBar;
