import {SET_TODO_BY_ID, ADD_TODO, REMOVE_TODO} from './../../server/todo/TodoActions';
import {UPDATE_UI_TODO} from './UiTodoActions';

const expandTypes = {
  isExpanded: "isExpanded",
  isNotExpanded: "isNotExpanded",
  canNotBeExpanded: "canNotBeExpanded"
}

let startState = {
  uiTodoById: {}
};

const defineTodoExpandType = (todo) => {
  if(todo.childIds.length === 0) {
    return expandTypes.canNotBeExpanded;
  } else {
    return expandTypes.isExpanded;
  }
}

const setTodoById = (action) => {
  let uiTodoById = {};

  let todoById = action.todoById;
  Object.keys(todoById).forEach(id => {
    uiTodoById[id] = {
      id: id,
      expandType: defineTodoExpandType(todoById[id]),
      isDetailed: false
    };
  });

  return {
    uiTodoById
  };
};

const addTodo = (state, action) => {
  let todo = action.todo;
  let uiTodoById = state.uiTodoById;
  let parentTodo = uiTodoById[todo.parentId];
  let result;
  if(parentTodo) {
    result = {
      uiTodoById: {
        ...uiTodoById,
        [todo.parentId]: {
          ...parentTodo,
          expandType: expandTypes.isExpanded
        },
        [todo.id]: {
          id: todo.id,
          expandType: defineTodoExpandType(todo),
          isDetailed: false
        }
      }
    };
  } else {
    result = {
      uiTodoById: {
        ...uiTodoById,
        [todo.id]: {
          id: todo.id,
          expandType: defineTodoExpandType(todo),
          isDetailed: false
        }
      }
    };
  }

  return result;
};

const updateUiTodo = (state, action) => {
  let newUiTodo = action.uiTodo;
  let oldUiTodo = state.uiTodoById[newUiTodo.id];
  let result = {
    uiTodoById: {
      ...state.uiTodoById,
      [newUiTodo.id]: {
        ...oldUiTodo,
        ...newUiTodo
      }
    }
  };


  return result;
};

const removeTodo = (state, action, serverTodoListState) => {
  let uiTodoById = {...state.uiTodoById};
  const todoById = serverTodoListState.todoById;
  const todo = todoById[action.id];

  recursivelyRemoveTodo(uiTodoById, todoById, todo);

  let result = {
    uiTodoById
  };

  return result;
};

const recursivelyRemoveTodo = (uiTodoById, todoById, todo) => {
  if(todo.childIds.length !== 0) {
    todo.childIds.forEach(childId => recursivelyRemoveTodo(uiTodoById, todoById, todoById[childId]));
  }

  delete uiTodoById[todo.id];
  
  const parentUiTodo = uiTodoById[todo.parentId];
  if(parentUiTodo) {
    uiTodoById[todo.parentId] = {
      ...parentUiTodo,
      expandType: expandTypes.canNotBeExpanded
    };
  }
}

const todoTreeReducer = (state = startState, action, serverTodoListState) => {
  if(action.type === SET_TODO_BY_ID) {
    return setTodoById(action);
  }

  if(action.type === ADD_TODO) {
    return addTodo(state, action);
  }

  if(action.type === UPDATE_UI_TODO) {
    return updateUiTodo(state, action);
  }

  if(action.type === REMOVE_TODO) {
    return removeTodo(state, action, serverTodoListState);
  }

  return state;
};

export default todoTreeReducer;
export {expandTypes};
