import { ProductRequest } from "../component/admin/form/ProductForm";
import { ApiResponse } from "../response/ApiResponse";
import PageResponse from "../response/PageResponse";
import ProductDetailsResponse from "../response/ProductDetailsResponse";
import { ProductResponse } from "../response/ProductResponse";
import instance from "../util/customAxios";
import { convertBlobToUrl } from "../util/imageUtil";

const createProduct = (
  data: ProductRequest,
  file: File
): Promise<ApiResponse<ProductResponse>> => {
  const formData = new FormData();
  formData.append("product_request", JSON.stringify(data));
  formData.append("file", file);
  return instance.post("product", formData);
};
const readProductPage = (
  page: number
): Promise<ApiResponse<PageResponse<ProductResponse>>> => {
  return instance.get("product", { params: { page } });
};
const readProduct = (
  id: number
): Promise<ApiResponse<ProductDetailsResponse>> => {
  return instance.get(`product/${id}`);
};
const deleteProduct=(id:number):Promise<ApiResponse<void>>=>{
  return instance.delete(`product/${id}`);
}

const readImageProduct = async (fileName: string): Promise<string> => {
  const response = await instance.get<Blob>(`product/image/${fileName}`, {
    responseType: "blob",
  });
  return convertBlobToUrl(response);
};
export { createProduct, readProductPage, readImageProduct, readProduct, deleteProduct };
