import {UPDATE, SET} from './Actions';

export const tabs = {
  filter: "filter",
  sort: "sort"
};

export const sortFields = {
  priority: "priority",
  weight: "weight",
  important: "important",
  startDate: "startDate",
  endDate: "endDate",
  name: "name"
};

const startState = {
  name: null,
  dayAmountAfterToday: null,
  tags: "",
  bottomPriority: null,
  topPriority: null,
  isImportant: null,
  bottomWeight: null,
  topWeight: null,
  sortRules: [],
  activatedTab: tabs.filter
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
