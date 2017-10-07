import React from 'react';
import TodoTree from './../components/TodoTree';
import {connect} from 'react-redux';
import {todoByIdStateType} from './../store/server/todo/TodoReducer';
import {todoService} from './../appContext/Context';
import {setTodoById, updateTodo, removeTodo} from './../store/server/todo/TodoActions';
import {updateUiPartOfTodo} from './../store/ui/todoTree/UiTodoActions';

/*
  TODO: 1. finish implementation
*/
class TodoTreeContainer extends React.Component {
  componentDidMount() {
    let todoByIdState = this.props.todoByIdState;

    if(todoByIdState !== todoByIdStateType.empty
      && todoByIdState !== todoByIdStateType.outOfDate) {
      return;
    }

    todoService.getList().then((data) => data.json()).then(todoList => {
      let todoById = {};
      todoList.forEach(todo => {
        todoById[todo.id] = todo;
      });

      this.props.dispatch(setTodoById(todoById));
    });
  }

  onClickNew = () => {
    this.props.history.push("/todoEditor/new");
  }

  onClickExpand = (id, expandType) => {
    this.props.dispatch(updateUiPartOfTodo({
      id,
      expandType
    }));
  }

  onClickTodo = (id) => {
    this.props.dispatch(updateUiPartOfTodo({
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

  render() {
    return(
      <TodoTree
        {...this.props}
        indent={20}
        onClickNew={this.onClickNew}
        onClickExpand={this.onClickExpand}
        onClickTodo={this.onClickTodo}
        onClickImportantFlag={this.onClickImportantFlag}
        onClickRemove={this.onClickRemove}/>
    );
  }
}

const mapStateToProps = (state) => {
  var todoById = {};

  Object.values(state.server.todoList.todoById).forEach(todo => {
    let uiPartOfTodo = state.ui.todoTree.uiPartOfTodoById[todo.id];
    todoById[todo.id] = Object.assign({}, todo, {
      expandType: uiPartOfTodo.expandType,
      isDetailed: uiPartOfTodo.isDetailed
    });
  });

  return {
    todoById,
    todoByIdState: state.server.todoList.state
  };
}

export default connect(mapStateToProps)(TodoTreeContainer);
