import React, { useEffect, useState } from "react";
import { SubmitHandler, useForm } from "react-hook-form";
import { z } from "zod";
import { createProduct } from "../../../service/productService";
import BrandResponse from "../../../response/BrandResponse";
import { readAllBrand } from "../../../service/brandService";
import { readAllCategory } from "../../../service/categoryService";
import { ApiResponse } from "../../../response/ApiResponse";
import { ProductResponse } from "../../../response/ProductResponse";
import { toast } from "react-toastify";
import CategoryResponse from "../../../response/CategoryResponse";
const schema = z.object({
  name: z.string(),
  price: z.number(),
  remaining: z.number(),
  brand_id: z.number(),
  description: z.string(),
  category: z.string(),
});
export type ProductRequest = z.infer<typeof schema>;
type Props = {
  refresh: () => void;
  product:ProductResponse|undefined
};
const ProductForm = ({ refresh, product }: Props) => {
  const [brands, setBrands] = useState<BrandResponse[]>();
  const [categories, setCategories] = useState<CategoryResponse[]>();
  const [image, setImage] = useState<File | null>(null);
  const [imageUrl, setImageUrl] = useState<string>("");
  const {
    register,
    reset,
    handleSubmit,
    formState: { isSubmitting },
  } = useForm<ProductRequest>();

  useEffect(() => {
    readAllBrand().then((t) => {
      if (t.data) setBrands(t.data);
    });
    readAllCategory().then((t) => {
      if (t.data) setCategories(t.data);
    });
    reset(product)
  }, []);

  const handleSubmitCreate: SubmitHandler<ProductRequest> = (data) => {
    if (image != null && data.brand_id && data.category !== "") {
      createProduct(data, image).then((d: ApiResponse<ProductResponse>) => {
        if (d.success) {
          toast.success("Successfully");
          refresh();
        } else {
          toast.error(d.message);
        }
      });
    }
  };
  const handleChangeImage = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e?.target?.files?.[0]) {
      setImageUrl(URL.createObjectURL(e.target.files[0]));
      setImage(e.target.files[0]);
    }
  };
  return (
    <form onSubmit={handleSubmit(handleSubmitCreate)}>
      <div className="mb-3">
        <label htmlFor="name" className="form-label">
          Name
        </label>
        <input
          type="text"
          {...register("name")}
          className="form-control"
          id="name"
          name="name"
          required
        />
      </div>
      <div className="mb-3">
        <label htmlFor="price" className="form-label">
          Price
        </label>
        <input
          type="number"
          {...register("price")}
          step="0.01"
          className="form-control"
          id="price"
          name="price"
          required
          min={1}
        />
      </div>
      <div className="mb-3">
        <label htmlFor="remaining" className="form-label">
          Remaining
        </label>
        <input
          type="number"
          {...register("remaining")}
          className="form-control"
          id="remaining"
          name="remaining"
          required
          min={0}
        />
      </div>
      <div className="mb-3">
        <label htmlFor="brand" className="form-label">
          Brand
        </label>
        <select
          {...register("brand_id")}
          className="form-select"
          id="brand"
          defaultValue=""
        >
          <option value="" disabled>
            Choose a brand
          </option>
          {brands?.map((brand) => (
            <option key={brand.id} value={brand.id}>
              {brand.name}
            </option>
          ))}
        </select>
      </div>
      <div className="mb-3">
        <label htmlFor="description" className="form-label">
          Description
        </label>
        <textarea
          className="form-control"
          {...register("description")}
          id="description"
          name="description"
          rows={3}
          defaultValue={""}
        />
      </div>
      <div className="mb-3">
        <label htmlFor="category" className="form-label">
          Category
        </label>
        <select
          {...register("category")}
          className="form-select"
          id="category"
          defaultValue=""
        >
          <option value="" disabled>
            Choose a category
          </option>
          {categories?.map((category) => (
            <option key={`option-category-${category.id}`} value={category.id}>
              {category.name}
            </option>
          ))}
        </select>
      </div>
      <div className="mb-3">
        <label htmlFor="image" className="form-label">
          Category
        </label>
        <input
          type="file"
          className="form-control"
          id="image"
          name="image"
          required
          onChange={(e) => handleChangeImage(e)}
        />
      </div>
      <img src={imageUrl} alt="" height={"90px"} width={"90px"} />
      <button disabled={isSubmitting} type="submit" className="btn btn-primary">
        Submit
      </button>
    </form>
  );
};

export default ProductForm;
