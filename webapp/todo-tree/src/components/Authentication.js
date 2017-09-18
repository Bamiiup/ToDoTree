import React from 'react'

export default class Authentication extends React.Component  {
  render() {
    return(
      <div className="container" style={{marginTop: "20px"}}>
        <div className="row justify-content-center">
          <div className="col-4">
            <div className="form-group row">
              <label className="col-3 col-form-label">Login</label>
              <div className="col-9">

                <input className="form-control" type="text" onChange={this.props.onChangeLogin} value={this.props.login}/>

              </div>
            </div>

            <div className="form-group row">
              <label className="col-3 col-form-label">Password</label>
              <div className="col-9">

                <input className="form-control" type="password" onChange={this.props.onChangePassword} value={this.props.password}/>

              </div>
            </div>
          </div>
        </div>

        <div className="row justify-content-center">
          <div className="col-1"></div>
          <div className="col-3">
            <div style={{float: 'left'}}>

              <a href="#/registration">Sign up</a>

            </div>
            <div style={{float: 'right'}}>

              <button type="button" className="btn" onClick={this.props.onClickEnter}>Enter</button>

            </div>
          </div>
        </div>
      </div>
    );
  }
}
