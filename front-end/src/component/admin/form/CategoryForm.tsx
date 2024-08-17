import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { handleChangeUploadImage } from "../../../util/imageUtil";
import {
  createCategory,
  readAllCategoryByLevelAndName,
  updateCategory,
} from "../../../service/categoryService";
import { toast } from "react-toastify";
import CategoryResponse from "../../../response/CategoryResponse";
import { useDebounce } from "../../../hook/useDebounce";
const schema = z.object({
  name: z.string(),
  level: z.number().min(0).optional(),
  parent: z.number().optional(),
});
type Props={
  refetch: () => void;
  category: CategoryResponse | undefined;
}
export type CategoryRequest = z.infer<typeof schema>;
const CategoryForm = ({refetch, category}:Props) => {
  const [image, setImage] = useState<File>();
  const [imageUrl, setImageUrl] = useState<string>();
  const [categoryResponses, setCategoryResponses] =
    useState<CategoryResponse[]>();
  const [level, setLevel] = useState<number>(0);
  const [parentString, setParentString] = useState<string>("");
  const name = useDebounce(parentString);
  const {
    reset,
    register,
    handleSubmit,
    formState: { errors, isSubmitting },
  } = useForm<CategoryRequest>();
  useEffect(() => {
    console.log("123");
    if (level && level > 0) {
      readAllCategoryByLevelAndName(level-1, name).then((d) => {
        setCategoryResponses(d.data);
      });
    }
  }, [level, name]);
  useEffect(() => {
    if (category) {
      setLevel(category.level);
      reset(category); //{name:brand.name, description: brand.description})
    }
  }, [category]);
  const onSubmit = (data: CategoryRequest) => {
    if (image && !category) {
      createCategory({ ...data, level }, image).then((d) => {
        if (d.success) {
          toast.success("Successfully");
          refetch();
        } else {
          toast.error(d.message);
        }
      });
    }else if(category){
      updateCategory(category.id, {...data,level}, image).then((d) => {
        if (d.success) {
          toast.success("Successfully");
          refetch();
        } else {
          toast.error(d.message);
        }
      })
    }
  };
  const handleLevelChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value: number = Number(e.target.value);
    if (!isNaN(value) && value >= 0) {
      setLevel(value);
    }
  };
  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <div className="mb-3">
        <label htmlFor="name" className="form-label">
          Name
        </label>
        <input
          id="name"
          className={`form-control ${errors.name ? "is-invalid" : ""}`}
          {...register("name")}
        />
        {errors.name && (
          <div className="invalid-feedback">{errors.name.message}</div>
        )}
      </div>
      <div className="mb-3">
        <label htmlFor="level" className="form-label">
          Level
        </label>
        <input
          type="number"
          className="form-control"
          onChange={(e) => handleLevelChange(e)}
          value={level}
          min={0}
        />
      </div>
      <div className="row">
        <div className="mb-3 col">
          <label htmlFor="find-name" className="form-label">
            Find Name
          </label>
          <input
            id="find-name"
            className="form-control"
            type="text"
            onChange={(e) => setParentString(e.target.value)}
            value={parentString}
          />
        </div>

        <div className="mb-3 col">
          <label htmlFor="parent" className="form-label">
            Parent
          </label>
          <select
            {...register("parent")}
            className="form-select"
            id="category"
            defaultValue=""
          >
            <option value="" disabled>
              Choose a category
            </option>
            {categoryResponses?.map((categoryResponses) => (
              <option
                key={`option-category-${categoryResponses.id}`}
                value={categoryResponses.id}
              >
                {categoryResponses.name}
              </option>
            ))}
          </select>
        </div>
      </div>
      <div className="mb-3">
        <label htmlFor="image" className="form-label">
          Image
        </label>
        <input
          id="image"
          type="file"
          onChange={(e) => handleChangeUploadImage(e, setImage, setImageUrl)}
          className={`form-control ${errors.parent ? "is-invalid" : ""}`}
        />
      </div>
      <img src={imageUrl} alt="" width={"90px"} height={"90px"} />
      <button disabled={isSubmitting} type="submit" className="btn btn-primary">
        Submit
      </button>
    </form>
  );
};

export default CategoryForm;
