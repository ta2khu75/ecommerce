import { useEffect, useState } from "react";
import ModalElement from "../element/ModalElement";
import BrandForm from "./form/BrandForm";
import BrandResponse from "../../response/BrandResponse";
import { deleteBrand, readAllBrand } from "../../service/brandService";
import TableElement from "./table/TableElement";
import { toast } from "react-toastify";
import BrandViewElement from "../element/BrandViewElement";

const BrandAdminComponent = () => {
  const [show, setShow] = useState(false);
  const [brands, setBrands] = useState<BrandResponse[]>([]);
  const [brand, setBrand] = useState<BrandResponse>();
  const [showView, setShowView] = useState(false);
  useEffect(() => {
    fetchReadAllBrand();
  }, []);
  const fetchReadAllBrand = () => {
    readAllBrand().then((t) => {
      if (t.data) setBrands(t.data);
      setShow(false);
    });
  };
  const handleViewClick = (brand: BrandResponse) => {
    setBrand(brand);
    setShowView(true);
  };
  const handleEditClick = (brand: BrandResponse) => {
    setBrand(brand);
    setShow(true);
  };
  const handleDeleteClick = (brand: BrandResponse) => {
    deleteBrand(brand.id).then((t) => {
      if (t.success) {
        toast.success(t.message);
        fetchReadAllBrand();
      } else {
        toast.error(t.message);
      }
    });
  };
  const handleClickAdd=()=>{
    setShow(true);
    setBrand(undefined)
  }

  return (
    <>
      <button onClick={() => handleClickAdd()}>Add</button>
      <ModalElement
        component={<BrandForm brand={brand} refetch={fetchReadAllBrand} />}
        setShow={setShow}
        show={show}
      />
      <ModalElement show={showView}  setShow={setShowView} component={<BrandViewElement brand={brand}/>} />
      <TableElement
        array={brands}
        handleViewClick={handleViewClick}
        handleEditClick={handleEditClick}
        handleDeleteClick={handleDeleteClick}
      />
    </>
  );
};

export default BrandAdminComponent;
