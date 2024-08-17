import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { readImageProduct, readProduct } from "../service/productService";
import { readImageProductImage } from "../service/productImageService";
import ProductDetailsResponse from "../response/ProductDetailsResponse";
import ProductImageElement from "./element/ProductImageElement";
import ProductInfoElement from "./element/ProductInfoElement";
import ProductInfoDetailsElement from "./element/ProductInfoDetailsElement";
import ProductDescriptionElement from "./element/ProductDescriptionElement";

const ProductComponent = () => {
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
      {/* content */}
      <section className="bg-light border-top py-4">
        <div className="container">
          <div className="row gx-4">
            {/* <div className="col-lg-12 mb-4"> */}
              <div className="border rounded-2 px-3 py-2 bg-white">
                <ProductInfoDetailsElement/>
                <ProductDescriptionElement/>
              </div>
            </div>
            {/* <div className="col-lg-4">
              <div className="px-0 border rounded-2 shadow-0">
                <div className="card">
                  <div className="card-body">
                    <h5 className="card-title">Similar items</h5>
                    <div className="d-flex mb-3">
                      <a href="#" className="me-3">
                        <img
                          src="https://mdbcdn.b-cdn.net/img/bootstrap-ecommerce/items/8.webp"
                          style={{ minWidth: 96, height: 96 }}
                          className="img-md img-thumbnail"
                        />
                      </a>
                      <div className="info">
                        <a href="#" className="nav-link mb-1">
                          Rucksack Backpack Large <br />
                          Line Mounts
                        </a>
                        <strong className="text-dark"> $38.90</strong>
                      </div>
                    </div>
                    <div className="d-flex mb-3">
                      <a href="#" className="me-3">
                        <img
                          src="https://mdbcdn.b-cdn.net/img/bootstrap-ecommerce/items/9.webp"
                          style={{ minWidth: 96, height: 96 }}
                          className="img-md img-thumbnail"
                        />
                      </a>
                      <div className="info">
                        <a href="#" className="nav-link mb-1">
                          Summer New Men's Denim <br />
                          Jeans Shorts
                        </a>
                        <strong className="text-dark"> $29.50</strong>
                      </div>
                    </div>
                    <div className="d-flex mb-3">
                      <a href="#" className="me-3">
                        <img
                          src="https://mdbcdn.b-cdn.net/img/bootstrap-ecommerce/items/10.webp"
                          style={{ minWidth: 96, height: 96 }}
                          className="img-md img-thumbnail"
                        />
                      </a>
                      <div className="info">
                        <a href="#" className="nav-link mb-1">
                          {" "}
                          T-shirts with multiple colors, for men and lady{" "}
                        </a>
                        <strong className="text-dark"> $120.00</strong>
                      </div>
                    </div>
                    <div className="d-flex">
                      <a href="#" className="me-3">
                        <img
                          src="https://mdbcdn.b-cdn.net/img/bootstrap-ecommerce/items/11.webp"
                          style={{ minWidth: 96, height: 96 }}
                          className="img-md img-thumbnail"
                        />
                      </a>
                      <div className="info">
                        <a href="#" className="nav-link mb-1">
                          {" "}
                          Blazer Suit Dress Jacket for Men, Blue color{" "}
                        </a>
                        <strong className="text-dark"> $339.90</strong>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div> */}
          {/* </div> */}
        </div>
      </section>
    </div>
  );
};

export default ProductComponent;
