const UPDATE = "TODO_TREE.UPDATE_UI_TODO";

const update = (uiTodo) => {
  return {
    type: UPDATE,
    uiTodo: uiTodo
  };
}

export {UPDATE, update};

const UPDATE_SELECTED_REPRESENTATION_ID = "TODO_TREE.UPDATE_SELECTED_REPRESENTATION_ID";

const updateSelectedRepresentationId = (id) => {
  return {
    type: UPDATE_SELECTED_REPRESENTATION_ID,
    id
  };
};

export {UPDATE_SELECTED_REPRESENTATION_ID, updateSelectedRepresentationId};
