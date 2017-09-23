import {expandType} from './../../../components/Todo';
let startState = {
  uiPartOfTodoById: {
    1: {
      expandType: expandType.isExpanded
    },
    2: {
      expandType: expandType.canNotBeExpanded
    },
    3: {
      expandType: expandType.canNotBeExpanded
    }
  }
};

const todoTreeReducer = (state = startState, action) => {
  return state;
};

export default todoTreeReducer;
