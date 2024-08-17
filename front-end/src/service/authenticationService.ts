import axios from "axios";
import { AuthenticationRequest } from "../component/Login";
import { SocialAccountResponse } from "../redux/slice/socialAccountSlice";
import { ApiResponse } from "../response/ApiResponse";
import AuthenticationResponse from "../response/AuthenticationResponse";
import UrlResponse from "../response/UrlResponse";
import instance from "../util/customAxios";
const login=(data:AuthenticationRequest):Promise<ApiResponse<AuthenticationResponse>>=>{
    return instance.post('auth',{...data})
}
const loginGoogle=async (credential:string|undefined, client_id:string|undefined):Promise<ApiResponse<SocialAccountResponse>>=>{
    const formData=new FormData();
    formData.append('request',JSON.stringify({credential,client_id}));
    return await instance.post("auth/google",formData)
}
const loginGoogleCode=(code:string):Promise<UrlResponse>=>{
    return axios.get('http://localhost:8080/api/v1/auth/code/google',{params:{code}});
}
export {login, loginGoogle, loginGoogleCode}