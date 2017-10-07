const UPDATE_UI_TODO = "UPDATE_UI_TODO";

const updateUiTodo = (uiTodo) => {
  return {
    type: UPDATE_UI_TODO,
    uiTodo: uiTodo
  };
}

export {UPDATE_UI_TODO, updateUiTodo};
