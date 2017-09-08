import React from 'react';


export default class TodoEditor extends React.Component {
  render() {
    return(
      <div>
        <div className="container">

          <div className="row">
            <div className="col-8 offset-2">
              <div className="form-group row">
                <label className="col-2 col-form-label">Name</label>
                <div className="col-10">

                  <input className="form-control" type="text"
                    onChange={this.props.onChangeName}
                    value={this.props.name}/>

                </div>
              </div>
            </div>
          </div>

          <div className="row">
            <div className="col-8 offset-2">
              <div className="form-group row">
                <label className="col-2 col-form-label">Comment</label>
                <div className="col-10">

                  <textarea className="form-control" rows={3}
                    onChange={this.props.onChangeComment}
                    value={this.props.comment}/>

                </div>
              </div>
            </div>
          </div>

          <div className="row">
            <div className="col-8 offset-2">
              <div className="form-group row">
                <label className="col-2 col-form-label">Date From/To</label>
                <div className="col-10">
                  <div className="row">
                    <div className="col-6">
                      <input className="form-control" type="date"
                        onChange={this.props.onChangeStartDate}
                        value={this.props.startDate}/>
                    </div>

                    <div className="col-6">
                      <input className="form-control" type="date"
                        onChange={this.props.onChangeEndDate}
                        value={this.props.endDate}/>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div className="row">
            <div className="col-8 offset-2">
              <div className="form-group row">
                <label className="col-2 col-form-label">Tags</label>
                <div className="col-10">

                  <input className="form-control" type="text"
                    onChange={this.props.onChangeTags}
                    value={this.props.tags}/>

                </div>
              </div>
            </div>
          </div>

          <div className="row">
            <div className="col-8 offset-2">
              <div className="form-group row">
                <label className="col-2 col-form-label">Priority</label>
                <div className="col-10">

                  <select className="form-control"
                    onChange={this.props.onChangePriority}
                    value={this.props.priority}>
                    <option value="veryHigh">Very High</option>
                    <option value="high">High</option>
                    <option value="medium">Medium</option>
                    <option value="low">Low</option>
                    <option value="veryLow">Very Low</option>
                  </select>

                </div>
              </div>
            </div>
          </div>

          <div className="row">
            <div className="col-8 offset-2">
              <div className="form-group row">
                <label className="col-2 col-form-label">Important</label>
                <div className="col-10">

                  <select className="form-control"
                    onChange={this.props.onChangeImportant}
                    value={this.props.important ? "yes" : "no"}>
                    <option value="yes">Yes</option>
                    <option value="no">No</option>
                  </select>

                </div>
              </div>
            </div>
          </div>

          <div className="row">
            <div className="col-8 offset-2">
              <div className="form-group row">
                <label className="col-2 col-form-label">Weight</label>
                <div className="col-10">

                  <input className="form-control" type="number" step={1}
                    onChange={this.props.onChangeWeight}
                    value={this.props.weight}/>

                </div>
              </div>
            </div>
          </div>

          <div className="row">
            <div className="col-8 offset-2">
              <div style={{'float': 'right'}}>

                <button type="button" className="btn"
                  onClick={this.props.onClickSave}>
                  Save
                </button>

              </div>
            </div>
          </div>

        </div>
      </div>
    );
  }
}
