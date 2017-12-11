import {SET, ADD, REMOVE} from './../../server/todo/Actions';
import {UPDATE, UPDATE_SELECTED_REPRESENTATION_ID} from './Actions';

const expandTypes = {
  isExpanded: "isExpanded",
  isNotExpanded: "isNotExpanded",
  canNotBeExpanded: "canNotBeExpanded"
}

let startState = {
  uiTodoById: {},
  selectedRepresentationId: null
};

const defineTodoExpandType = (todo) => {
  if(todo.childIds.length === 0) {
    return expandTypes.canNotBeExpanded;
  } else {
    return expandTypes.isExpanded;
  }
}

const set = (action) => {
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

const add = (state, action) => {
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

const update = (state, action) => {
  let newUiTodo = action.uiTodo;
  let oldUiTodo = state.uiTodoById[newUiTodo.id];
  let result = {
    ...state,
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

const updateSelectedRepresentationId = (state, action) => {
  let result = {
    ...state,
    selectedRepresentationId: action.id
  };

  return result;
};

const remove = (state, action, serverTodoListState) => {
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
  if(action.type === SET) {
    return set(action);
  }

  if(action.type === ADD) {
    return add(state, action);
  }

  if(action.type === UPDATE) {
    return update(state, action);
  }

  if(action.type === UPDATE_SELECTED_REPRESENTATION_ID) {
    return updateSelectedRepresentationId(state, action);
  }

  if(action.type === REMOVE) {
    return remove(state, action, serverTodoListState);
  }

  return state;
};

export default todoTreeReducer;
export {expandTypes};
