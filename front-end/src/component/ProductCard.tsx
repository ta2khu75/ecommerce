import { Link } from "react-router-dom";
import { ProductResponse } from "../response/ProductResponse";

type Props = {
  product: ProductResponse;
};
const ProductCard = ({ product }: Props) => {
  return (
    <div className="col-12 col-md-4 col-lg-2 mb-5 mb-md-0">
      <Link to={`/product/${product.id}`} className="product-item">
        <img src={product.image} alt={product.name} className="img-fluid product-thumbnail" />
        <h3 className="product-title">{product.name}</h3>
        <strong className="product-price">${product.price}</strong>
        <span className="icon-cross">
          <img src="images/cross.svg" alt="cross" className="img-fluid" />
        </span>
      </Link>
    </div>
  );
};

export default ProductCard;
