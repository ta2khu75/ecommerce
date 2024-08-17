import React, { useEffect, useState } from "react";
import { SubmitHandler, useForm } from "react-hook-form";
import { z } from "zod";
import { createBrand, updateBrand } from "../../../service/brandService";
import { toast } from "react-toastify";
import { ApiResponse } from "../../../response/ApiResponse";
import BrandResponse from "../../../response/BrandResponse";
const schema = z.object({
  name: z.string(),
  description: z.string(),
});
export type BrandRequest = z.infer<typeof schema>;
type Props = {
  refetch: () => void;
  brand: BrandResponse | undefined;
  // setShoww: React.Dispatch<React.SetStateAction<boolean>>
};
const BrandForm = ({ refetch, brand }: Props) => {
  const [image, setImage] = useState<File>();
  const [imageUrl, setImageUrl] = useState<string>("");
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors, isSubmitting },
  } = useForm<BrandRequest>();
  useEffect(() => {
    if (brand) {
      reset(brand); //{name:brand.name, description: brand.description})
    }
  }, [brand]);
  const onSubmit: SubmitHandler<BrandRequest> = (data) => {
    if (image && !brand) {
      createBrand(data, image).then((d: ApiResponse<BrandResponse>) => {
        if (d.success) {
          toast.success("successfully");
          refetch();
        } else {
          toast.error(d.message);
        }
      });
    } else if (brand) {
      console.log("update");
      updateBrand(brand.id, data, image).then(
        (d: ApiResponse<BrandResponse>) => {
          if (d.success) {
            toast.success("Successfully");
            refetch();
          } else {
            toast.error(d.message);
          }
        }
      );
    }
  };
  const handleChangeImage = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files && e.target.files[0]) {
      setImageUrl(URL.createObjectURL(e.target.files[0]));
      setImage(e.target.files[0]);
    }
  };
  return (
    <form onSubmit={handleSubmit(onSubmit)} className="container mt-5">
      <div className="mb-3">
        <label htmlFor="name" className="form-label">
          Name
        </label>
        <input
          id="name"
          type="text"
          className={`form-control ${errors.name ? "is-invalid" : ""}`}
          {...register("name")}
        />
        {errors.name && (
          <div className="invalid-feedback">{errors.name.message}</div>
        )}
      </div>

      <div className="mb-3">
        <label htmlFor="description" className="form-label">
          Description
        </label>
        <textarea
          id="description"
          className={`form-control ${errors.description ? "is-invalid" : ""}`}
          {...register("description")}
        />
        {errors.description && (
          <div className="invalid-feedback">{errors.description.message}</div>
        )}
      </div>
      <div className="mb-3">
        <label htmlFor="image" className="form-label">
          image
        </label>
        <input
          id="image"
          type="file"
          className={`form-control`}
          onChange={(e) => handleChangeImage(e)}
        />
      </div>
      <img src={imageUrl} alt="" />
      <button type="submit" disabled={isSubmitting} className="btn btn-primary">
        Submit
      </button>
    </form>
  );
};

export default BrandForm;
