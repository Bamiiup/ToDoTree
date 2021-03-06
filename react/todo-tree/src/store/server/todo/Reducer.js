import {SET, ADD, UPDATE, REMOVE, UPDATE_IS_COMPLETED} from './Actions';

const todoByIdStates = {
  empty: "empty",
  loading: "loading",
  loaded: "loaded",
  outOfDate: "outOfDate"
};

const startState = {
  state: todoByIdStates.empty,
  todoById: {}
};

const add = (state, action) => {
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

const update = (state, action) => {
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

const remove = (state, action) => {
  let todoById = {...state.todoById};
  let todo = todoById[action.id];

  recursivelyRemoveTodo(todoById, todo);

  let result = {
    state: state.state,
    todoById
  };

  return result;
}

const recursivelyRemoveTodo = (todoById, todo) => {
  if(todo.childIds.length !== 0) {
    todo.childIds.forEach(childId => recursivelyRemoveTodo(todoById, todoById[childId]));
  }

  delete todoById[todo.id];

  const parentTodo = todoById[todo.parentId];
  if(parentTodo) {
    todoById[todo.parentId] = {
      ...parentTodo,
      childIds: parentTodo.childIds.filter(childId => (childId !== todo.id))
    };
  }
}

const updateIsCompleted = (state, action) => {
  let todoById = {...state.todoById};
  let todo = todoById[action.id];
  let isCompleted = action.isCompleted;

  recursivelyUpdateIsCompleted(todoById, todo, isCompleted);

  let result = {
    state: state.state,
    todoById
  };

  return result;
};

const recursivelyUpdateIsCompleted = (todoById, todo, isCompleted) => {
  if(todo.childIds.length !== 0) {
    todo.childIds.forEach(childId => recursivelyUpdateIsCompleted(todoById, todoById[childId], isCompleted));
  }

  todoById[todo.id] = {
    ...todo,
    isCompleted
  };
}

const todoReducer = (state = startState, action) => {
  if(action.type === SET) {
    return {
      state: todoByIdStates.loaded,
      todoById: action.todoById
    };
  }

  if(action.type === ADD) {
    return add(state, action);
  }

  if(action.type === UPDATE) {
    return update(state, action);
  }

  if(action.type === REMOVE) {
    return remove(state, action);
  }

  if(action.type === UPDATE_IS_COMPLETED) {
    return updateIsCompleted(state, action);
  }

  return state;
}

export default todoReducer;
export {todoByIdStates};
