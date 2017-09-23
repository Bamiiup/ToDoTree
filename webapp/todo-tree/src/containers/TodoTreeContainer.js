import React from 'react';
import TodoTree from './../components/TodoTree';
import {connect} from 'react-redux';

class TodoTreeContainer extends React.Component {
  render() {
    return(
      <TodoTree todoById={this.props.todoById}/>
    );
  }
}

const mapStateToProps = (state) => {
  var todoById = {};

  Object.getOwnPropertyNames(state.server.todoList.todoById).forEach(id => {
    let todo = state.server.todoList.todoById[id];
    let expandType = state.ui.todoTree.expandStateOfTodoById[id];
    todoById[id] = Object.assign({}, todo, {
      expandType: expandType,
      childIds: []
    });
  });

  Object.getOwnPropertyNames(todoById).forEach(id => {
    let todo = todoById[id];
    if(todo.parentId) {
      let parent = todoById[todo.parentId];
      parent.childIds.push(todo.id);
    }
  });

  return {
    todoById
  };
}

export default connect(mapStateToProps)(TodoTreeContainer);
