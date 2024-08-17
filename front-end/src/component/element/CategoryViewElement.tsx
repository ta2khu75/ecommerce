import React, { useEffect, useState } from "react";
import CategoryResponse from "../../response/CategoryResponse";
import { readImageCategory } from "../../service/categoryService";
import { Image } from "react-bootstrap";
type Props = {
  category?: CategoryResponse;
};
const CategoryViewElement = ({ category }: Props) => {
  const [imageUrl, setImageUrl] = useState("");
  useEffect(() => {
    if (category) readImageCategory(category.image).then((d) => setImageUrl(d));
  });
  return (
    <div>
      {category && (
        <div className="text-center">
          <Image src={imageUrl} roundedCircle alt={category.name} width={"300px"} />
          <h3>{category.name}</h3>
        </div>
      )}
    </div>
  );
};

export default CategoryViewElement;
