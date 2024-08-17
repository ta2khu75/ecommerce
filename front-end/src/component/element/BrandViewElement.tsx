import { useEffect, useState } from "react";
import BrandResponse from "../../response/BrandResponse";
import { readImageBrand } from "../../service/brandService";
type Props = {
  brand?: BrandResponse;
};
const BrandViewElement = ({ brand }: Props) => {
  const [imageUrl, setImageUrl] = useState("");
  useEffect(() => {
    if (brand) readImageBrand(brand.image).then((d) => setImageUrl(d));
  });
  return (
    <div>
      {brand && (
        <div className="text-center">
          <img src={imageUrl} alt={brand.name} width={"300px"}/>
          <h3>{brand.name}</h3>
        </div>
      )}
    </div>
  );
};

export default BrandViewElement;
