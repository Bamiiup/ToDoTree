import React from 'react';

export default class RepresentationEditor extends React.Component {
  getIsImportantValue() {
    if(this.props.isImportant === false) {
      return "no";
    }

    if(this.props.isImportant === true) {
      return "yes";
    }

    return "";
  }

  render() {
    return(
      <div
        className="container"
        style={{marginTop: 20}}>
        <div className="row justify-content-center">
          <div className="col-8">
            <div className="form-group row">
              <label className="col-2 col-form-label">
                End after days
              </label>
              <div className="col-10">
                <input
                  type="number"
                  className="form-control"
                  value={this.props.dayAmountAfterToday === null ? "" : this.props.dayAmountAfterToday}
                  onChange={this.props.onChangeEndAfterDays}/>
              </div>
            </div>
            <div className="form-group row">
              <label className="col-2 col-form-label">Tags</label>
              <div className="col-10">
                <input
                  type="text"
                  className="form-control"
                  value={this.props.tags}
                  onChange={this.props.onChangeTags}/>
              </div>
            </div>
            <div className="form-group row">
              <label className="col-2 col-form-label">
                Priority From/To
              </label>
              <div className="col-10">
                <div className="row">
                  <div className="col-6">
                    <select className="form-control"
                      value={this.props.bottomPriority === null ? "" : this.props.bottomPriority}
                      onChange={this.props.onChangePriorityFrom}>
                      <option value=""> -- select an option -- </option>
                      <option value="veryHigh">
                        Very High
                      </option>
                      <option value="high">High</option>
                      <option value="medium">Medium</option>
                      <option value="low">Low</option>
                      <option value="veryLow">
                        Very Low
                      </option>
                    </select>
                  </div>
                  <div className="col-6">
                    <select className="form-control"
                      value={this.props.topPriority === null ? "" : this.props.topPriority}
                      onChange={this.props.onChangePriorityTo}>
                      <option value=""> -- select an option -- </option>
                      <option value="veryHigh">
                        Very High
                      </option>
                      <option value="high">High</option>
                      <option value="medium">Medium</option>
                      <option value="low">Low</option>
                      <option value="veryLow">
                        Very Low
                      </option>
                    </select>
                  </div>
                </div>
              </div>
            </div>
            <div className="form-group row">
              <label className="col-2 col-form-label">Important</label>
              <div className="col-10">
                <select className="form-control"
                  value={this.getIsImportantValue()}
                  onChange={this.props.onChangeImportant}>
                  <option value=""> -- select an option -- </option>
                  <option value="yes">Yes</option>
                  <option value="no">No</option>
                </select>
              </div>
            </div>
            <div className="form-group row">
              <label className="col-2 col-form-label">Weight From/To</label>
              <div className="col-5">
                <input
                  type="number"
                  step={1}
                  className="form-control"
                  value={this.props.bottomWeight === null ? "" : this.props.bottomWeight}
                  onChange={this.props.onChangeWeightFrom}/>
              </div>
              <div className="col-5">
                <input
                  type="number"
                  step={1}
                  className="form-control"
                  value={this.props.topWeight === null ? "" : this.props.topWeight}
                  onChange={this.props.onChangeWeightTo}/>
              </div>
            </div>
            <div style={{float: 'right'}}>
              <button type="button" className="btn"
                onClick={this.props.onClickSave}>Save</button>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
