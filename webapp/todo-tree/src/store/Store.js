import { createStore } from 'redux';
import mainReducer from './MainReducer';

let store = createStore(mainReducer);

export default store;
