const SET_IS_AUTHENTICATED = "SET_IS_AUTHENTICATED";

const setIsAuthenticated = (isAuthenticated) => {
  let action = {
    type: SET_IS_AUTHENTICATED,
    isAuthenticated: isAuthenticated
  };
  return action;
};

export {SET_IS_AUTHENTICATED, setIsAuthenticated}
