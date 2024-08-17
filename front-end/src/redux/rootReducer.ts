import { combineReducers } from '@reduxjs/toolkit';
import  socialAccountReducer  from './slice/socialAccountSlice';
import counterReducer from './slice/counterSlice';
import accountReducer from './slice/accountSlice';
// Import other slices as needed

// Combine all your slices into one root slice
const rootReducer = combineReducers({
//   shoppingCart: shoppingCardReducer,
  count: counterReducer,
  account: accountReducer,
  socialAccount: socialAccountReducer
  // Add other slices here
});

export default rootReducer;