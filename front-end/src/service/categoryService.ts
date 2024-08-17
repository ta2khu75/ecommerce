import { CategoryRequest } from "../component/admin/form/CategoryForm";
import { ApiResponse } from "../response/ApiResponse";
import CategoryResponse from "../response/CategoryResponse";
import instance from "../util/customAxios";
import { convertBlobToUrl } from "../util/imageUtil";
const url = "category";
const readAllCategory = (): Promise<ApiResponse<CategoryResponse[]>> => {
  return instance.get(url);
};
const createCategory = (
  category_request: CategoryRequest,
  image: File
): Promise<ApiResponse<CategoryResponse>> => {
  const formData = new FormData();
  formData.append("category", JSON.stringify(category_request));
  formData.append("image", image);
  return instance.post(url, formData);
};
const readAllCategoryByLevelAndName=(level:number, name:string): Promise<ApiResponse<CategoryResponse[]>> => {
  return instance.get(url + `/level-name`,{params:{level,name}});
}
const deleteCategory=(id:number): Promise<ApiResponse<void>> => {
  return instance.delete(`${url}/${id}`);
}
const updateCategory=(id:number, category_request: CategoryRequest, image?:File): Promise<ApiResponse<CategoryResponse>> => {
  const formData=new FormData();
  formData.append("category", JSON.stringify(category_request));
  if(image) formData.append("image", image);
  return instance.put(url + `/${id}`, formData)
}
const readImageCategory=async(imageName: string):Promise<string> =>{
  const response = await instance.get<Blob>(`/${url}/image/${imageName}`, {
    responseType: "blob",
  });
  return convertBlobToUrl(response);
}
export { readAllCategory, createCategory, readImageCategory , readAllCategoryByLevelAndName, deleteCategory, updateCategory};
