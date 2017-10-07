const SET_TODO_BY_ID = "SET_TODO_BY_ID";

const setTodoById = (todoById) => {
  return {
    type: SET_TODO_BY_ID,
    todoById
  };
};

export {SET_TODO_BY_ID, setTodoById};

const ADD_TODO = "ADD_TODO";

const addTodo = (todo) => {
  return {
    type: ADD_TODO,
    todo
  };
};

export {ADD_TODO, addTodo};

const UPDATE_TODO = "UPDATE_TODO";

const updateTodo = (todo) => {
  return {
    type: UPDATE_TODO,
    todo
  };
};

export {updateTodo, UPDATE_TODO};

const REMOVE_TODO = "REMOVE_TODO";

const removeTodo = (id) => {
  return {
    type: REMOVE_TODO,
    id
  };
};

export {removeTodo, REMOVE_TODO};
