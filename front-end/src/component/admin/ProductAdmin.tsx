import { useEffect, useState } from "react";
import ModalFormElement from "../element/ModalElement";
import ProductForm from "./form/ProductForm";
import TableElement from "./table/TableElement";
import { deleteProduct, readProductPage } from "../../service/productService";
import PageResponse from "../../response/PageResponse";
import { ProductResponse } from "../../response/ProductResponse";
import ReactPaginate from "react-paginate";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
const ProductAdminComponent = () => {
  const navigate=useNavigate();
  const [page, setPage] = useState(1);
  const [show, setShow] = useState(false);
  const [product, setProduct] = useState<ProductResponse>();
  const [productPage, setPageProduct] =
    useState<PageResponse<ProductResponse>>();
  useEffect(() => {
    fetchReadPageProduct();
  }, [page]);
  const fetchReadPageProduct = () => {
    readProductPage(page).then((t) => {
      if (t?.data?.results) setPageProduct(t.data);
      setShow(false);
    });
  };
  const handlePageClick = ({ selected }: { selected: number }) => {
    console.log(selected);
    setPage(selected + 1);
  };
  const handleEditClick = (data: ProductResponse) => {
    setProduct(data);
    setShow(true);
  };
  const handleDeleteClick = (data: ProductResponse) => {
    deleteProduct(data.id).then((t) => {
      if (t.success) {
        toast.success(t.message);
      }else{
        toast.error(t.message);
      }
      fetchReadPageProduct();
    });
  };
  const handleViewClick = (data: ProductResponse) => {
    navigate(`/admin/product-details/${data.id}`);
    console.log(data);
  };
  return (
    <>
      <button onClick={() => setShow(true)}>Add</button>
      <ModalFormElement
        show={show}
        title="Create Product"
        component={
          <ProductForm product={product} refresh={fetchReadPageProduct} />
        }
        setShow={setShow}
      />
      <TableElement
        visiableColumn={[
          "id",
          "name",
          "price",
          "remaining",
          "active",
          "category",
        ]}
        array={productPage?.results ?? []}
        handleDeleteClick={handleDeleteClick}
        handleEditClick={handleEditClick}
        handleViewClick={handleViewClick}
      />
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

export default ProductAdminComponent;
