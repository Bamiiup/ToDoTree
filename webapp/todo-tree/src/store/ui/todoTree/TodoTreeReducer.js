import {expandType} from './../../../components/Todo';
let startState = {
  expandStateOfTodoById: {
    1: expandType.isExpanded,
    2: expandType.canNotBeExpanded,
    3: expandType.canNotBeExpanded
  }
};

const todoTreeReducer = (state = startState, action) => {
  return state;
};

export default todoTreeReducer;
