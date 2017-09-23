import React from 'react';

const expandType = {
  isExpanded: "isExpanded",
  isNotExpanded: "isNotExpanded",
  canNotBeExpanded: "canNotBeExpanded"
}

const getExpandSpan = (expandType) => {
  if(expandType === "isExpanded") {
    return(
      <span className="oi oi-minus" style={{cursor: "pointer", color: "#C98989"}}></span>
    );
  }

  if(expandType === "isNotExpanded") {
    return(
      <span className="oi oi-plus" style={{cursor: "pointer", color: "#88a586"}}></span>
    );
  }

  if(expandType === "canNotBeExpanded") {
    return null;
  }

  return null;
}

const Todo = ({name, comment, commentIsVisible, expandType}) => {
  return(
    <div style={{marginTop: "5px", padding: "4px", borderRadius: "5px", border: "2px solid #e3e6ea"}}>
      <div style={{display: "flex", flexDirection: "row", justifyContent: "space-between",alignItems: "center"}}>
        <div style={{width: "20px", fontSize: "7pt"}}>
          {getExpandSpan(expandType)}
        </div>
        <div style={{flexGrow: 1}}>
          <span className="font-weight-bold" style={{cursor: "pointer"}}>
            {name}
          </span>
        </div>
        <div style={{width: "20px", fontSize: "10pt"}}>
          <span className="oi oi-flag" style={{color: "grey"}}></span>
        </div>
        <div style={{width: "30px"}}>
          <div className="btn-group" role="group">
            <button id="btnGroupDrop1" type="button" className="btn btn-light dropdown-toggle btn-sm" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">

            </button>
            <div className="dropdown-menu" aria-labelledby="btnGroupDrop1">
              <a className="dropdown-item" href="#">Complete</a>
              <a className="dropdown-item" href="#">New</a>
              <a className="dropdown-item" href="#">Edit</a>
              <a className="dropdown-item" href="#">Remove</a>
            </div>
          </div>
        </div>
      </div>
      {commentIsVisible ? (
        <div style={{marginLeft: "20px", marginRight: "50px", color: "grey", fontStyle: "italic"}}>
          {comment}
        </div>
      ): undefined}
    </div>
  );
};

export default Todo;
export {expandType};
