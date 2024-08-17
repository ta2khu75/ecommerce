import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import AuthenticationResponse from "../../response/AuthenticationResponse";
const initialState:AuthenticationResponse={
    authentication:false
}
export const accountSlice=createSlice({
    name:"account",
    initialState,
    reducers:{
        setAccount:(state={...initialState}, action:PayloadAction<AuthenticationResponse>)=>{
            state.token=action.payload.token;
            state.account=action.payload.account
            state.authentication=action.payload.authentication;
        },
        resetAccount:(state)=>{
            state.token=initialState.token;
            state.account=initialState.account;
            state.authentication=initialState.authentication;
        }
    }
})
export const {setAccount, resetAccount}=accountSlice.actions
export default accountSlice.reducer;