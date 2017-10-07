import {expandType} from './../../../components/Todo';
import {SET_TODO_BY_ID, ADD_TODO, REMOVE_TODO} from './../../server/todo/TodoActions';
import {UPDATE_UI_PART_OF_TODO} from './UiTodoActions';


let startState = {
  uiPartOfTodoById: {}
};

const defineTodoExpandType = (todo) => {
  if(todo.childIds.length === 0) {
    return expandType.canNotBeExpanded;
  } else {
    return expandType.isExpanded;
  }
}

const handleSetTodoById = (action) => {
  let uiPartOfTodoById = {};

  let todoById = action.todoById;
  Object.keys(todoById).forEach(id => {
    uiPartOfTodoById[id] = {
      id: id,
      expandType: defineTodoExpandType(todoById[id]),
      isDetailed: false
    };
  });

  return {
    uiPartOfTodoById
  };
};

const handleAddTodo = (state, action) => {
  let todo = action.todo;
  let uiPartOfTodoById = state.uiPartOfTodoById;
  let parentTodo = uiPartOfTodoById[todo.parentId];
  let result;
  if(parentTodo) {
    result = {
      uiPartOfTodoById: {
        ...uiPartOfTodoById,
        [todo.parentId]: {
          ...parentTodo,
          expandType: expandType.isExpanded
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
      uiPartOfTodoById: {
        ...uiPartOfTodoById,
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

const handleUpdateUiPartOfTodo = (state, action) => {
  let newUiPartOfTodo = action.uiPartOfTodo;
  let oldUiPartOfTodo = state.uiPartOfTodoById[newUiPartOfTodo.id];
  let result = {
    uiPartOfTodoById: {
      ...state.uiPartOfTodoById,
      [newUiPartOfTodo.id]: {
        ...oldUiPartOfTodo,
        ...newUiPartOfTodo
      }
    }
  };


  return result;
};

const handleRemoveTodo = (state, action, serverTodoListState) => {
  let uiPartOfTodoById = {...state.uiPartOfTodoById};
  const todoById = serverTodoListState.todoById;
  const todo = todoById[action.id];

  removeTodo(uiPartOfTodoById, todoById, todo);

  let result = {
    uiPartOfTodoById
  };

  return result;
};

const removeTodo = (uiPartOfTodoById, todoById, todo) => {
  if(todo.childIds.length === 0) {
    delete uiPartOfTodoById[todo.id];
    return;
  }

  todo.childIds.forEach(child => removeTodo(uiPartOfTodoById, todoById, child));
  delete uiPartOfTodoById[todo.id];
}

const todoTreeReducer = (state = startState, action, serverTodoListState) => {
  if(action.type === SET_TODO_BY_ID) {
    return handleSetTodoById(action);
  }

  if(action.type === ADD_TODO) {
    return handleAddTodo(state, action);
  }

  if(action.type === UPDATE_UI_PART_OF_TODO) {
    return handleUpdateUiPartOfTodo(state, action);
  }

  if(action.type === REMOVE_TODO) {
    return handleRemoveTodo(state, action, serverTodoListState);
  }

  return state;
};

export default todoTreeReducer;
