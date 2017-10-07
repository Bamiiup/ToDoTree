import {SET_TODO_BY_ID, ADD_TODO, UPDATE_TODO, REMOVE_TODO} from './TodoActions';

const todoByIdStateType = {
  empty: "empty",
  loading: "loading",
  loaded: "loaded",
  outOfDate: "outOfDate"
};

const startState = {
  state: todoByIdStateType.empty,
  todoById: {}
};

const handleAddTodo = (state, action) => {
  let todo = action.todo;
  let todoById = state.todoById;
  let parentTodo = todoById[todo.parentId];
  if(parentTodo) {
    return {
      state: state.state,
      todoById: {
        ...todoById,
        [parentTodo.id]: {
          ...parentTodo,
          childIds: [
            ...parentTodo.childIds,
            todo.id
          ]
        },
        [todo.id]: todo
      }
    };
  } else {
    return {
      state: state.state,
      todoById: {
        ...todoById,
        [todo.id]: todo
      }
    };
  }
};

const handleUpdateTodo = (state, action) => {
  let todoById = state.todoById;
  let newTodo = action.todo;
  let oldTodo = todoById[newTodo.id];

  return {
    state: state.state,
    todoById: {
      ...todoById,
      [newTodo.id]: {
        ...oldTodo,
        ...newTodo
      }
    }
  };
};

const handleRemoveTodo = (state, action) => {
  let todoById = {...state.todoById};
  let todo = todoById[action.id];

  removeTodo(todoById, todo);

  let result = {
    state: state.state,
    todoById
  };

  return result;
}

const removeTodo = (todoById, todo) => {
  if(todo.childIds.length === 0) {
    delete todoById[todo.id];

    const parentTodo = todoById[todo.parentId];
    if(parentTodo) {
      todoById[todo.parentId] = {
        ...parentTodo,
        childIds: parentTodo.childIds.filter(childId => (childId != todo.id))
      };

    }
    return;
  }

  todo.childIds.forEach(child => removeTodo(todoById, child));
  delete todoById[todo.id];

  const parentTodo = todoById[todo.parentId];
  if(parentTodo) {
    todoById[todo.parentId] = {
      ...parentTodo,
      childIds: parentTodo.childIds.filter(child => child.id != todo.id)
    };
  }
}

const todoReducer = (state = startState, action) => {
  if(action.type === SET_TODO_BY_ID) {
    return {
      state: todoByIdStateType.loaded,
      todoById: action.todoById
    };
  }

  if(action.type === ADD_TODO) {
    return handleAddTodo(state, action);
  }

  if(action.type === UPDATE_TODO) {
    return handleUpdateTodo(state, action);
  }

  if(action.type === REMOVE_TODO) {
    return handleRemoveTodo(state, action);
  }

  return state;
}

export default todoReducer;
export {todoByIdStateType};
