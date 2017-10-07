import React from 'react';
import {Link} from 'react-router-dom';

const expandType = {
  isExpanded: "isExpanded",
  isNotExpanded: "isNotExpanded",
  canNotBeExpanded: "canNotBeExpanded"
}

export default class Todo extends React.Component {

  onClickMinus = () => {
    let id = this.props.id;
    this.props.onClickExpand(id, expandType.isNotExpanded);
  }

  onClickPlus = () => {
    let id = this.props.id;
    this.props.onClickExpand(id, expandType.isExpanded);
  }

  onClickTodo = () => {
    let id = this.props.id;
    this.props.onClickTodo(id);
  }

  onClickImportantFlag = () => {
    let id = this.props.id;
    this.props.onClickImportantFlag(id);
  }

  onClickRemove = () => {
    let id = this.props.id;
    this.props.onClickRemove(id);
  }

  getExpandSpan(expandType) {
    if(expandType === "isExpanded") {
      return(
        <span className="oi oi-minus" style={{cursor: "pointer", color: "#C98989"}}
          onClick={this.onClickMinus}>
        </span>
      );
    }
    if(expandType === "isNotExpanded") {
      return(
        <span className="oi oi-plus" style={{cursor: "pointer", color: "#88a586"}}
          onClick={this.onClickPlus}></span>
      );
    }
    if(expandType === "canNotBeExpanded") {
      return null;
    }

    return null;
  }

  getImportantFlagStyle() {
    if(this.props.important) {
      return {color: "red"};
    } else {
      return {color: "grey"};
    }
  }

  render() {
    const {name, isDetailed, comment, expandType} = this.props;
    return(
      <div style={{marginTop: "5px", padding: "4px", borderRadius: "5px", border: "2px solid #e3e6ea"}}>
        <div style={{display: "flex", flexDirection: "row", justifyContent: "space-between",alignItems: "center"}}>
          <div style={{width: "20px", fontSize: "7pt"}}>
            {this.getExpandSpan(expandType)}
          </div>
          <div style={{flexGrow: 1}}>
            <span className="font-weight-bold" style={{cursor: "pointer"}}
              onClick={this.onClickTodo}>
              {name}
            </span>
          </div>
          <div style={{width: "20px", fontSize: "10pt"}}>
            <span className="oi oi-flag" style={this.getImportantFlagStyle()}
              onClick={this.onClickImportantFlag}>
            </span>
          </div>
          <div style={{width: "30px"}}>
            <div className="btn-group" role="group">
              <button id="btnGroupDrop1" type="button" className="btn btn-light dropdown-toggle btn-sm" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">

              </button>
              <div className="dropdown-menu" aria-labelledby="btnGroupDrop1">
                <Link className="dropdown-item" to="/">Complete</Link>
                <Link className="dropdown-item" to={"/todoEditor/new/" + this.props.id}>New</Link>
                <Link className="dropdown-item" to={"/todoEditor/edit/" + this.props.id}>Edit</Link>
                <span className="dropdown-item" onClick={this.onClickRemove}>Remove</span>
              </div>
            </div>
          </div>
        </div>
        {isDetailed ? (
          <div style={{marginLeft: "20px", marginRight: "50px", color: "grey", fontStyle: "italic"}}>
            {comment}
          </div>
        ): undefined}
      </div>
    );
  }
};

export {expandType};
