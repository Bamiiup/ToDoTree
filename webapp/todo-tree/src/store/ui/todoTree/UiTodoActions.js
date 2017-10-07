const UPDATE_UI_PART_OF_TODO = "UPDATE_UI_PART_OF_TODO";

const updateUiPartOfTodo = (uiPartOfTodo) => {
  return {
    type: UPDATE_UI_PART_OF_TODO,
    uiPartOfTodo: uiPartOfTodo
  };
}

export {UPDATE_UI_PART_OF_TODO, updateUiPartOfTodo};
