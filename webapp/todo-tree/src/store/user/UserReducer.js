import {SET_IS_AUTHENTICATED} from './UserActions';

const userReducer = (state = {isAuthenticated: false}, action) => {
  if(action.type === SET_IS_AUTHENTICATED) {
    return Object.assign({}, state, {isAuthenticated: action.isAuthenticated});
  }

  return state;
};

export default userReducer;
