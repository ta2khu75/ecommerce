import instance from "../util/customAxios"
import { convertBlobToUrl } from "../util/imageUtil"

const readImageProductImage=async (fileName:string):Promise<string>=>{
   const response=await instance.get<Blob>(`product-image/image/${fileName}`,{responseType:"blob"})
   console.log(response);
   return convertBlobToUrl(response);
}
export {readImageProductImage}