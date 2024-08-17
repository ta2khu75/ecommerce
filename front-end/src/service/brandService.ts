import { BrandRequest } from "../component/admin/form/BrandForm";
import { ApiResponse } from "../response/ApiResponse";
import BrandResponse from "../response/BrandResponse";
import instance from "../util/customAxios";
import { convertBlobToUrl } from "../util/imageUtil";

const createBrand = (
  data: BrandRequest,
  file: File
): Promise<ApiResponse<BrandResponse>> => {
  const formData = new FormData();
  formData.append("brand", JSON.stringify(data));
  formData.append("file", file);
  return instance.post("/brand", formData);
};
const updateBrand = (id:number, data:BrandRequest, file?:File): Promise<ApiResponse<BrandResponse>> => {
    const formData=new FormData();
    formData.append("brand", JSON.stringify(data));
    if(file)formData.append("file", file);
    return instance.put(`/brand/${id}`, formData);
}
const readAllBrand = (): Promise<ApiResponse<BrandResponse[]>> => {
  return instance.get("/brand");
};
const deleteBrand = (id: number): Promise<ApiResponse<void>> => {
  return instance.delete(`/brand/${id}`);
};
const readImageBrand = async (fileName: string): Promise<string> => {
  const response = await instance.get<Blob>(`/brand/image/${fileName}`, {
    responseType: "blob",
  });
  return convertBlobToUrl(response);
};
export { createBrand, readAllBrand, deleteBrand, readImageBrand, updateBrand };
