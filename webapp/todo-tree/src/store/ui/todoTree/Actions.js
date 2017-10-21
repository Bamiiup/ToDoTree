const UPDATE = "UPDATE_UI_TODO";

const update = (uiTodo) => {
  return {
    type: UPDATE,
    uiTodo: uiTodo
  };
}

export {UPDATE, update};
