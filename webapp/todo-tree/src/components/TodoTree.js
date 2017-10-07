import React from 'react';
import Todo from './Todo';
import {expandTypes} from './../store/ui/todoTree/TodoTreeReducer';

export default class TodoTree extends React.Component {
  createTree(todoById) {
    let result = [];
    Object.values(todoById).filter(todo => todo.parentId == null).forEach(todo => {
      this.createTreeFromNode(todoById, todo, 0, result);
    });
    return result;
  }

  createTreeFromNode(todoById, todo, depth, result) {
    let paddingLeft = this.props.indent * depth;
    result.push(
      <div style={{paddingLeft: paddingLeft}} key={todo.id}>
        <Todo
          {...todo}
          onClickExpand={this.props.onClickExpand}
          onClickTodo={this.props.onClickTodo}
          onClickImportantFlag={this.props.onClickImportantFlag}
          onClickRemove={this.props.onClickRemove}
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

  render() {
    return(
      <div className="container" style={{marginTop: "20px"}}>
        <div style={{display: "flex", flexDirection: "row", justifyContent: "flex-end",alignItems: "center"}}>
          <button type="button" className="btn btn-light btn-sm" onClick={this.props.onClickNew}>
            <span className="oi oi-plus" style={{color: "#88a586", fontSize: "10pt"}}></span> New
          </button>
        </div>
        {this.createTree(this.props.todoById)}
      </div>
    );
  }
}
