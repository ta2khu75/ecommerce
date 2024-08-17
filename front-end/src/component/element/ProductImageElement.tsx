import ProductDetailsResponse from "../../response/ProductDetailsResponse";

type Props={
  product?:ProductDetailsResponse
}
const ProductImageElement = ({product}:Props) => {
  return (
    <aside className="col-lg-6">
      <div className="border rounded-4 mb-3 d-flex justify-content-center">
        <a
          data-fslightbox="mygalley"
          className="rounded-4"
          target="_blank"
          data-type="image"
          href={product?.image}
        >
          <img
            style={{
              maxWidth: "100%",
              maxHeight: "100vh",
              margin: "auto",
            }}
            className="rounded-4 fit"
            src={product?.image}
          />
        </a>
      </div>
      <div className="d-flex justify-content-center mb-3">
        {product?.product_images.map((image, index) => (
          <a
            key={"link-image" + index}
            data-fslightbox="mygalley"
            className="border mx-1 rounded-2"
            target="_blank"
            data-type="image"
            href={image}
          >
            <img width={60} height={60} className="rounded-2" src={image} />
          </a>
        ))}
      </div>
      {/* thumbs-wrap.// */}
      {/* gallery-wrap .end// */}
    </aside>
  );
};

export default ProductImageElement;
