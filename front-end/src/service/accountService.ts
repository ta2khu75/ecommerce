import { AccountRequest } from "../component/Register";
import { SocialAccount } from "../model/SocialAccount";
import AccountResponse from "../response/AccountResponse";
import { ApiResponse } from "../response/ApiResponse";
import PageResponse from "../response/PageResponse";
import instance from "../util/customAxios";
const baseUrl="account";
const createAccount=(data:AccountRequest, socialAccount:SocialAccount|undefined):Promise<ApiResponse<AccountResponse>>=>{
    const formData=new FormData();
    formData.append("account", JSON.stringify(data));
    formData.append("social_account", JSON.stringify(socialAccount));
    return instance.post(baseUrl, formData);
}
const readPageAccount=(page:number):Promise<ApiResponse<PageResponse<AccountResponse>>>=>{
    return instance.get(baseUrl, {params:{page}});
}
const readMyInfoAccount=()=>{
    return instance.get(`${baseUrl}/my-info`);
}
export {createAccount, readPageAccount, readMyInfoAccount}