import React from 'react';
import TodoTree from './../components/TodoTree';
import {connect} from 'react-redux';
import {todoByIdStates} from './../store/server/todo/TodoReducer';
import {todoService} from './../appContext/Context';
import {setTodoById, updateTodo, removeTodo, updateIsCompleted} from './../store/server/todo/TodoActions';
import {updateUiTodo} from './../store/ui/todoTree/UiTodoActions';

/*
  TODO: 1. finish implementation
*/
class TodoTreeContainer extends React.Component {
  componentDidMount() {
    let todoByIdState = this.props.todoByIdState;

    if(todoByIdState !== todoByIdStates.empty
      && todoByIdState !== todoByIdStates.outOfDate) {
      return;
    }

    todoService.getList().then((data) => data.json()).then(todoList => {
      let todoById = {};
      todoList.forEach(todo => {
        todoById[todo.id] = {
          ...todo,
          //TODO: Remove it when we add this field to todo at the server side
          isCompleted: false
        };
      });

      this.props.dispatch(setTodoById(todoById));
    });
  }

  onClickNew = () => {
    this.props.history.push("/todoEditor/new");
  }

  onClickExpand = (id, expandType) => {
    this.props.dispatch(updateUiTodo({
      id,
      expandType
    }));
  }

  onClickTodo = (id) => {
    this.props.dispatch(updateUiTodo({
      id,
      isDetailed: !this.props.todoById[id].isDetailed
    }));
  }

  onClickImportantFlag = (id) => {
    let oldTodo = this.props.todoById[id];
    let newTodo = {
      ...oldTodo,
      important: !oldTodo.important
    };

    todoService.update(newTodo).then(data => data.json()).then((todo) => {
      this.props.dispatch(updateTodo(todo));
    });
  }

  onClickRemove = (id) => {
    todoService.remove(id).then(() => {
      this.props.dispatch(removeTodo(id));
    });
  }

  onClickComplete = (id) => {
    let todo = this.props.todoById[id];
    
    this.props.dispatch(updateIsCompleted(id, !todo.isCompleted));

    //TODO: make updateIsCompleted method for server side

    /*todoService.update(newTodo).then(data => data.json()).then((todo) => {

    });*/
  }

  render() {
    return(
      <TodoTree
        {...this.props}
        indent={20}
        onClickNew={this.onClickNew}
        onClickExpand={this.onClickExpand}
        onClickTodo={this.onClickTodo}
        onClickImportantFlag={this.onClickImportantFlag}
        onClickRemove={this.onClickRemove}
        onClickComplete={this.onClickComplete}/>
    );
  }
}

const mapStateToProps = (state) => {
  var todoById = {};

  Object.values(state.server.todoList.todoById).forEach(todo => {
    let uiTodo = state.ui.todoTree.uiTodoById[todo.id];
    todoById[todo.id] = Object.assign({}, todo, {
      expandType: uiTodo.expandType,
      isDetailed: uiTodo.isDetailed
    });
  });

  return {
    todoById,
    todoByIdState: state.server.todoList.state
  };
}

export default connect(mapStateToProps)(TodoTreeContainer);
