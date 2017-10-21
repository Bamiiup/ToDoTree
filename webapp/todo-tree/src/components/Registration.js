import React from 'react'

export default class Registration extends React.Component {

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
              <label className="col-3 col-form-label">Email</label>
              <div className="col-9">

                <input className="form-control" type="email" onChange={this.props.onChangePassword} value={this.props.password}/>

              </div>
            </div>

            <div className="form-group row">
              <label className="col-3 col-form-label">Password</label>
              <div className="col-9">

                <input className="form-control" type="password" onChange={this.props.onChangePassword} value={this.props.password}/>

              </div>
            </div>

            <div style={{float: 'right'}}>

              <button type="button" className="btn" onClick={this.props.onClickSignUp}>Sign up</button>

            </div>

          </div>
        </div>
      </div>
    );
  }
}
