import userReducer from './server/user/Reducer';
import todoReducer from './server/todo/Reducer';
import todoTreeReducer from './ui/todoTree/Reducer';
import representationEditorReducer from './ui/representationEditor/Reducer';
import representationReducer from './server/representation/Reducer';

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
      todoList: todoReducer(state.server.todoList, action),
      representation: representationReducer(state.server.representation, action)
    },
    ui: {
      todoTree: todoTreeReducer(state.ui.todoTree, action, state.server.todoList),
      representationEditor: representationEditorReducer(state.ui.representationEditor, action)
    }
  };
};

export default mainReducer;
