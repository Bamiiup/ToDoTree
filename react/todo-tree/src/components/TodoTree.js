import React from 'react';
import Todo from './Todo';
import {expandTypes} from './../store/ui/todoTree/Reducer';
import DateUtils from './../utils/DateUtils';
import PriorityUtils from './../utils/PriorityUtils';

export default class TodoTree extends React.Component {
  createTree(todoById) {
    let result = [];
    Object.values(todoById).filter(todo => todo.parentId == null).forEach(todo => {
      this.createTreeFromNode(todoById, todo, 0, result);
    });
    return result;
  }

  createTreeFromNode(todoById, todo, depth, result) {
    if(!this.isPassedFilter(todo, this.props.selectedRepresentation)) {
      return;
    }

    let paddingLeft = this.props.indent * depth;
    result.push(
      <div style={{paddingLeft: paddingLeft}} key={todo.id}>
        <Todo
          {...todo}
          onClickExpand={this.props.onClickExpand}
          onClickTodo={this.props.onClickTodo}
          onClickImportantFlag={this.props.onClickImportantFlag}
          onClickRemove={this.props.onClickRemove}
          onClickComplete={this.props.onClickComplete}
          />
      </div>
    );
    if(todo.expandType === expandTypes.isExpanded) {
      todo.childIds.forEach(id => {
        let child = todoById[id];
        this.createTreeFromNode(todoById, child, depth + 1, result);
      });
    }
  }

  isPassedFilter(todo, representation) {
    if(!representation) {
      return true;
    }

    if(!this.isPassedDateFilter(todo, representation)) {
      return false;
    }

    if(!this.isPassedTagFilter(todo, representation)) {
      return false;
    }

    if(!this.isPassedBottomPriority(todo, representation)) {
      return false;
    }

    if(!this.isPassedTopPriority(todo, representation)) {
      return false;
    }

    if(!this.isPassedBottomWeight(todo, representation)) {
      return false;
    }

    if(!this.isPassedTopWeight(todo, representation)) {
      return false;
    }

    if(!this.isPassedIsImportant(todo, representation)) {
      return false;
    }

    return true;
  }

  isPassedIsImportant(todo, representation) {
    if(representation.isImportant === null || representation.isImportant === undefined) {
      return true;
    }

    return todo.important === representation.isImportant;
  }

  isPassedTopWeight(todo, representation) {
    if(!representation.topWeight) {
      return true;
    }

    if(!todo.weight) {
      return false;
    }

    return todo.weight <= representation.topWeight;
  }

  isPassedBottomWeight(todo, representation) {
    if(!representation.bottomWeight) {
      return true;
    }

    if(!todo.weight) {
      return false;
    }

    return todo.weight >= representation.bottomWeight;
  }

  isPassedTopPriority(todo, representation) {
    if(!representation.topPriority) {
      return true;
    }

    if(!todo.priority) {
      return false;
    }

    return PriorityUtils.ordinal(todo.priority) <= PriorityUtils.ordinal(representation.topPriority);
  }

  isPassedBottomPriority(todo, representation) {
    if(!representation.bottomPriority) {
      return true;
    }

    if(!todo.priority) {
      return false;
    }

    return PriorityUtils.ordinal(todo.priority) >= PriorityUtils.ordinal(representation.bottomPriority);
  }

  isPassedTagFilter(todo, representation) {
    if(representation.tags.length === 0) {
      return true;
    }

    let tagThatRepresentationHasButTodoDoesnt = representation.tags.find(representationTag => {
      let result = todo.tags.find(todoTag => {
        let result = todoTag.name === representationTag.name;
        return result;
      }) === undefined;

      return result;
    });

    return tagThatRepresentationHasButTodoDoesnt === undefined;
  }

  isPassedDateFilter(todo, representation) {
    if(!representation.dayAmountAfterToday) {
      return true;
    }

    if(!todo.endDate) {
      return false;
    }

    let currentDate = new Date();
    let endDate = DateUtils.dateWithoutTime(currentDate);
    endDate = DateUtils.addDays(endDate, representation.dayAmountAfterToday);

    if(endDate.getTime() < new Date(todo.endDate).getTime()) {
      return false;
    }

    if(currentDate.getTime() > new Date(todo.endDate).getTime()) {
      return false;
    }

    return true;
  }

  getSelectedRepresentationId() {
    if(this.props.selectedRepresentation) {
      return this.props.selectedRepresentation.id;
    } else {
      return "";
    }
  }

  render() {
    return(
      <div className="container" style={{marginTop: "20px"}}>
        <div style={{display: "flex", flexDirection: "row", justifyContent: "space-between",alignItems: "center"}}>

          <div className="row">
            <label className="col-6 col-form-label">Representation:</label>
            <div className="col-6">

              <select className="form-control form-control-sm"
                value={this.getSelectedRepresentationId()}
                onChange={this.props.onChangeSelectedRepresentation}>
                <option value="" key="">All</option>
                {this.props.representations.map(representation => {
                  return(<option value={representation.id} key={representation.id}>{representation.name}</option>);
                })}
              </select>

            </div>
          </div>

          <button type="button" className="btn btn-light btn-sm" onClick={this.props.onClickNew}>
            <span className="oi oi-plus" style={{color: "#88a586", fontSize: "10pt"}}></span> New
          </button>
        </div>
        {this.createTree(this.props.todoById)}
      </div>
    );
  }
}
