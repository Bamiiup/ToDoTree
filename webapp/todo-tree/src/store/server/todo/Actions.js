const SET = "SET_TODO_BY_ID";

const set = (todoById) => {
  return {
    type: SET,
    todoById
  };
};

export {SET, set};

const ADD = "ADD_TODO";

const add = (todo) => {
  return {
    type: ADD,
    todo
  };
};

export {ADD, add};

const UPDATE = "UPDATE_TODO";

const update = (todo) => {
  return {
    type: UPDATE,
    todo
  };
};

export {update, UPDATE};

const REMOVE = "REMOVE_TODO";

const remove = (id) => {
  return {
    type: REMOVE,
    id
  };
};

export {remove, REMOVE};

const UPDATE_IS_COMPLETED  = "UPDATE_IS_COMPLETED";

const updateIsCompleted = (id, isCompleted) => {
  return {
    type: UPDATE_IS_COMPLETED,
    id,
    isCompleted
  }
};

export {updateIsCompleted, UPDATE_IS_COMPLETED};
