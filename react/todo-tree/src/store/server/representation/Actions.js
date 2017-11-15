const SET = "SET_REPRESENTATION";

const set = (representations) => {
  return {
    type: SET,
    representations
  };
}

export {set, SET};

const REMOVE = "REMOVE_REPRESENTATION";

const remove = (id) => {
  return {
    type: REMOVE,
    id
  };
};

export {remove, REMOVE};

const UPDATE = "UPDATE_REPRESENTATION";

const update = (representation) => {
  return {
    type: UPDATE,
    representation
  };
};

export {update, UPDATE};
