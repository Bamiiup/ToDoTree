import {SET, REMOVE, UPDATE} from './Actions';

const representationListStates = {
  empty: "empty",
  loaded: "loaded",
  outOfDate: "outOfDate"
};

const startState = {
  state: representationListStates.empty,
  byId: {

  }
};

const set = (state, action) => {
  const byId = {
    ...state.byId
  };

  action.representations.forEach(representation => {
    byId[representation.id] = representation;
  });

  return {
    state: representationListStates.loaded,
    byId
  };
};

const remove = (state, action) => {
  const byId = {
    ...state.byId
  };

  delete byId[action.id];

  return {
    state: state.state,
    byId
  };
};

const update = (state, action) => {
  let newRepresentation = action.representation;

  return {
    state: state.state,
    byId: {
      ...state.byId,
      [newRepresentation.id]: {
        ...newRepresentation
      }
    }
  };
};

const reducer = (state = startState, action) => {
  if(action.type === SET) {
    return set(state, action);
  }

  if(action.type === REMOVE) {
    return remove(state, action);
  }

  if(action.type === UPDATE) {
    return update(state, action);
  }

  return state;
};

export default reducer;
export {representationListStates};
