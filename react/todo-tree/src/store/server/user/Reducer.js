import {SET_IS_AUTHENTICATED} from './Actions';

const userReducer = (state = {isAuthenticated: false}, action) => {
  if(action.type === SET_IS_AUTHENTICATED) {
    return {
      ...state,
      isAuthenticated: action.isAuthenticated
    };
  }

  return state;
};

export default userReducer;
