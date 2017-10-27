import {UPDATE, SET} from './Actions';

const startState = {
  dayAmountAfterToday: null,
  tags: "",
  bottomPriority: null,
  topPriority: null,
  isImportant: null,
  bottomWeight: null,
  topWeight: null
};

const update = (state, action) => {
  return {
    ...state,
    ...action.representation
  };
};

const set = (state, action) => {
  return {
    ...startState,
    ...action.representation
  };
};

const reducer = (state = startState, action) => {
  if(action.type === UPDATE) {
    return update(state, action);
  }

  if(action.type === SET) {
    return set(state, action);
  }

  return state;
};

export default reducer;
