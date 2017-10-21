const UPDATE = "REPRESENTATION_EDITOR_UPDATE";

const update = (representation) => {
  return {
    type: UPDATE,
    representation
  };
};

export {update, UPDATE}

const SET = "REPRESENTATION_SET";

const set = (representation) => {
  return {
    type: SET,
    representation
  };
};

export {set, SET};
