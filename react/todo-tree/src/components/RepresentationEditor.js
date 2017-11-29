import React from 'react';
import FilterEditor from './FilterEditor';
import SortEditor from './SortEditor';
import {tabs} from './../store/ui/representationEditor/Reducer';

export default class RepresentationEditor extends React.Component {
  render() {
    return(
      <div
        className="container"
        style={{marginTop: 20}}>
        <div className="row justify-content-center">
          <div className="col-8">

            <ul className="nav nav-tabs justify-content-end mb-2">
              <li className="nav-item" onClick={this.props.onClickFilter}>
                <span className={
                    this.props.activatedTab === tabs.filter ? "nav-link active" : "nav-link"
                  }>
                  Filter
                </span>
              </li>
              <li className="nav-item" onClick={this.props.onClickSort}>
                <span className={
                    this.props.activatedTab === tabs.sort ? "nav-link active" : "nav-link"
                  }>
                  Sort
                </span>
              </li>
            </ul>

            <div style={{height: '50vh'}}>
              {
                this.props.activatedTab === tabs.filter
                ? <FilterEditor {...this.props} /> : <SortEditor {...this.props}/>
            }
          </div>

          <div style={{display: 'flex', flexDirection: 'row', justifyContent: 'flex-end', alignItems: 'center'}}>
            <button type="button" className="btn"
              onClick={this.props.onClickSave}>Save</button>
          </div>
        </div>
      </div>
    </div>
  );
}
}
