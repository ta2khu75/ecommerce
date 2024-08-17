import { AxiosResponse } from "axios";

const convertBlobToUrl = (response: AxiosResponse<Blob>): string => {
  try {
    // Tạo URL từ Blob để sử dụng trong thẻ <img>
    if (response instanceof Blob) {
      return URL.createObjectURL(response);
    }
    throw new Error("response not instanceof blob");
  } catch (error) {
    console.error("Error fetching the image:", error);
    throw error;
  }
};
const handleChangeUploadImage = (e: React.ChangeEvent<HTMLInputElement>, setImage:React.Dispatch<React.SetStateAction<File | undefined>>, setImageUrl: React.Dispatch<React.SetStateAction<string | undefined>>) => {
    if (e?.target?.files?.[0]) {
      setImageUrl(URL.createObjectURL(e.target.files[0]));
      setImage(e.target.files[0]);
    }
  };
export { convertBlobToUrl , handleChangeUploadImage};
