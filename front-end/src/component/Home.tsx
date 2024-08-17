import { useEffect, useState } from "react";
import SectionFragment from "./fragment/Section";
import ProductCard from "./ProductCard";
import PageResponse from "../response/PageResponse";
import { readImageProduct, readProductPage } from "../service/productService";
import { ProductResponse } from "../response/ProductResponse";
import ReactPaginate from "react-paginate";

const HomeComponent = () => {
  const [productPage, setProducts] = useState<PageResponse<ProductResponse>>();
  const [page, setPage] = useState(1);
  useEffect(() => {
    fetchReadPageProduct();
  }, [page]);
  const fetchReadPageProduct = () => {
    readProductPage(page).then(async (t) => {
      if (t?.data) {
        const results = await Promise.all(
          t.data.results.map((product) => {
            return readImageProduct(product.image).then((d) => {
              return { ...product, image: d };
            });
          })
        );
        setProducts({ ...t.data, results });
      }
    });
  };
  const handlePageClick = ({ selected }: { selected: number }) => {
    setPage(selected + 1);
  };
  return (
    <>
      <SectionFragment />
      <div className="product-section">
        <div className="container">
          <div className="row">
            {productPage?.results.map((p, index) => (
              <ProductCard key={`product-${index}`} product={p} />
            ))}
          </div>
        </div>
      </div>
      <ReactPaginate
        className="d-flex justify-content-center"
        nextLabel="next >"
        onPageChange={handlePageClick}
        pageRangeDisplayed={3}
        marginPagesDisplayed={2}
        pageCount={productPage?.total_pages ?? 0}
        previousLabel="< previous"
        pageClassName="page-item"
        pageLinkClassName="page-link"
        previousClassName="page-item"
        previousLinkClassName="page-link"
        nextClassName="page-item"
        nextLinkClassName="page-link"
        breakLabel="..."
        breakClassName="page-item"
        breakLinkClassName="page-link"
        containerClassName="pagination"
        activeClassName="active"
        renderOnZeroPageCount={null}
      />
    </>
  );
};

export default HomeComponent;
