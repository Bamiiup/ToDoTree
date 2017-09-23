let startState = {
  state: "loaded",
  todoById: {
    1: {
      id: 1,
      parentId: null,
      childIds: [2],
      name: "Todo",
      comment: "some comment",
      important: true
    },
    2: {
      id: 2,
      parentId: 1,
      childIds: [],
      name: "Todo 2",
      comment: "some comment 2",
      important: true
    },
    3: {
      id: 3,
      parentId: null,
      childIds: [],
      name: "Todo 3",
      comment: "some comment 3",
      important: true
    },
  }
};

const todoReducer = (state = startState, action) => {
  return state;
}

export default todoReducer;
