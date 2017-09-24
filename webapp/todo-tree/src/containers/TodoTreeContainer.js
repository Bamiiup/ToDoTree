import React from 'react';
import TodoTree from './../components/TodoTree';
import {connect} from 'react-redux';

/*
  TODO: 1. finish implementation
*/
class TodoTreeContainer extends React.Component {
  render() {
    return(
      <TodoTree
        {...this.props}
        indent={20}/>
    );
  }
}

const mapStateToProps = (state) => {
  var todoById = {};

  Object.values(state.server.todoList.todoById).forEach(todo => {
    let uiPartOfTodo = state.ui.todoTree.uiPartOfTodoById[todo.id];
    todoById[todo.id] = Object.assign({}, todo, {
      expandType: uiPartOfTodo.expandType
    });
  });

  return {
    todoById
  };
}

export default connect(mapStateToProps)(TodoTreeContainer);
