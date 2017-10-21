import userReducer from './server/user/UserReducer';
import todoReducer from './server/todo/TodoReducer';
import todoTreeReducer from './ui/todoTree/TodoTreeReducer';
import representationEditorReducer from './ui/representationEditor/Reducer';

let startState = {
  server: {

  },
  ui: {

  }
};

const mainReducer = (state = startState, action) => {
  return {
    server: {
      user: userReducer(state.server.user, action),
      todoList: todoReducer(state.server.todoList, action)
    },
    ui: {
      todoTree: todoTreeReducer(state.ui.todoTree, action, state.server.todoList),
      representationEditor: representationEditorReducer(state.ui.representationEditor, action)
    }
  };
};

export default mainReducer;
