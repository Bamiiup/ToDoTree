import React from 'react';

export default class NavigationBar extends React.Component {
  render() {
    return(
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <a className="navbar-brand" href="#">ToDo-Tree</a>
        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNavDropdown">
          <ul className="navbar-nav">
            <li className="nav-item active">
              <a className="nav-link" href="#">Home<span className="sr-only">(current)</span></a>
            </li>
            <li className="nav-item active">
              <a className="nav-link" href="#">Tree</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">Log Out</a>
            </li>
          </ul>
        </div>
      </nav>
    );
  }
}
