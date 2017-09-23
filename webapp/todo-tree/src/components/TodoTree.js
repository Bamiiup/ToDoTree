import React from 'react';
import Todo from './Todo';
import {expandType} from './Todo';

export default class TodoTree extends React.Component {
  createTree(todoById) {
    return Object.values(todoById).filter(todo => todo.parentId == null).map(todo => {
      return this.createTreeFromNode(todoById, todo, 0);
    })
  }

  createTreeFromNode(todoById, todo, depth) {
    let paddingLeft = 20 * depth;
    return (
      <div>
        <div style={{paddingLeft: paddingLeft}}>
          <Todo
            key={todo.id}
            name={todo.name}
            commentIsVisible={true}
            comment={todo.comment}
            expandType={todo.expandType}
            />
        </div>
        {todo.expandType === expandType.isExpanded ?
          todo.childIds.map(id => {
            let child = todoById[id];
            return this.createTreeFromNode(todoById, child, depth + 1);
          }) :
          null
        }
      </div>
    );
  }

  render() {
    return(
      <div className="container" style={{marginTop: "20px"}}>
        <div style={{display: "flex", flexDirection: "row", justifyContent: "flex-end",alignItems: "center"}}>
          <button type="button" className="btn btn-light btn-sm">
            <span className="oi oi-plus" style={{color: "#88a586", fontSize: "10pt"}}></span> New
          </button>
        </div>
        {this.createTree(this.props.todoById)}
      </div>
    );
  }
}
