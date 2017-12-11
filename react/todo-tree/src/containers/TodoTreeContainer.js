import React from 'react';
import TodoTree from './../components/TodoTree';
import {connect} from 'react-redux';
import {todoByIdStates} from './../store/server/todo/Reducer';
import {todoService} from './../appContext/Context';
import {set as setTodoById, update as updateTodo, remove, updateIsCompleted} from './../store/server/todo/Actions';
import {set as setRepresentations} from './../store/server/representation/Actions';
import {update as updateUiTodo, updateSelectedRepresentationId} from './../store/ui/todoTree/Actions';
import {representationListStates} from './../store/server/representation/Reducer';
import {representationService} from './../appContext/Context';

/*
  TODO: 1. finish implementation
*/
class TodoTreeContainer extends React.Component {
  componentDidMount() {
    this.initializeTodo();
    this.initializeRepresentation();
  }

  initializeRepresentation() {
    if(this.props.state === representationListStates.loaded) {
      return;
    }

    representationService.getList().then((data) => data.json()).then(representations => {
      this.props.dispatch(setRepresentations(representations));
    });
  }

  initializeTodo() {
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
      this.props.dispatch(remove(id));
    });
  }

  onClickComplete = (id) => {
    let todo = this.props.todoById[id];

    this.props.dispatch(updateIsCompleted(id, !todo.isCompleted));

    //TODO: make updateIsCompleted method for server side

    /*todoService.update(newTodo).then(data => data.json()).then((todo) => {

    });*/
  }

  onChangeSelectedRepresentation = (event) => {
    this.props.dispatch(updateSelectedRepresentationId(event.target.value));
  }

  render() {
    return(
      <TodoTree
        indent={20}
        todoById={this.props.todoById}
        representations={Object.values(this.props.representationById)}
        selectedRepresentation={this.props.representationById[this.props.selectedRepresentationId]}
        onClickNew={this.onClickNew}
        onClickExpand={this.onClickExpand}
        onClickTodo={this.onClickTodo}
        onClickImportantFlag={this.onClickImportantFlag}
        onClickRemove={this.onClickRemove}
        onClickComplete={this.onClickComplete}
        onChangeSelectedRepresentation={this.onChangeSelectedRepresentation}/>
    );
  }
}

const mapStateToProps = (state) => {
  var todoById = {};

  Object.values(state.server.todoList.todoById).forEach(todo => {
    let uiTodo = state.ui.todoTree.uiTodoById[todo.id];
    todoById[todo.id] = {
      ...todo,
      expandType: uiTodo.expandType,
      isDetailed: uiTodo.isDetailed
    };
  });

  return {
    todoById,
    todoByIdState: state.server.todoList.state,
    representationById: state.server.representation.byId,
    representationListState: state.server.representation.state,
    selectedRepresentationId: state.ui.todoTree.selectedRepresentationId
  };
}

export default connect(mapStateToProps)(TodoTreeContainer);
