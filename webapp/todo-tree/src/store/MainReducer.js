import userReducer from './user/UserReducer';
import todoReducer from './todo/TodoReducer';
import todoTreeReducer from './ui/todoTree/TodoTreeReducer';

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
      todoTree: todoTreeReducer(state.ui.todoTree, action)
    }
  };
};

export default mainReducer;


/*let state = {
  server: {
    user: {
      isAuthenticated: false
    },
    todoById: {
      12: {
        parentId: 11,
        name: "some name"
      },
      11: {
        parentId: null,
        name: "some name"
      }
    }
  },
  ui: {
    authentication: {

    },
    navigationBar: {

    }
  }
};*/
