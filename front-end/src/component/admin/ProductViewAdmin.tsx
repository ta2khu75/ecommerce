import { useEffect, useState } from "react";
import ProductInfoDetailsElement from "../element/ProductInfoDetailsElement";
import ProductDescriptionElement from "../element/ProductDescriptionElement";
import ProductImageElement from "../element/ProductImageElement";
import ProductInfoElement from "../element/ProductInfoElement";
import { useParams } from "react-router-dom";
import ProductDetailsResponse from "../../response/ProductDetailsResponse";
import { readImageProduct, readProduct } from "../../service/productService";
import { readImageProductImage } from "../../service/productImageService";

const ProductViewAdminComponent = () => {
  const { id } = useParams<{ id: string }>();
  const [product, setProduct] = useState<ProductDetailsResponse>();
  useEffect(() => {
    fetchReadProduct();
  }, []);
  const fetchReadProduct = () => {
    if (id != undefined && !isNaN(parseInt(id))) {
      readProduct(parseInt(id)).then(async (p) => {
        if (p.data) {
          const image = await readImageProduct(p.data.image);
          const product_images = await Promise.all(
            p.data.product_images.map(
              async (image) => await readImageProductImage(image)
            )
          );
          setProduct({ ...p.data, image, product_images });
        }
      });
    }
  };
  return (
    <div>
      <section className="py-5">
        <div className="container">
          <div className="row gx-5">
            <ProductImageElement product={product} />
            <ProductInfoElement product={product} />
          </div>
        </div>
      </section>
      <section className="bg-light border-top py-4">
        <div className="container">
          <div className="row gx-4">
            <div className="border rounded-2 px-3 py-2 bg-white">
              <ProductInfoDetailsElement />
              <ProductDescriptionElement />
            </div>
          </div>
        </div>
      </section>
    </div>
  );
};

export default ProductViewAdminComponent;
