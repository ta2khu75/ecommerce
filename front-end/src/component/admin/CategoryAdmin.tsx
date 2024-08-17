import { useEffect, useState } from 'react'
import CategoryForm from './form/CategoryForm'
import ModalElement from '../element/ModalElement'
import CategoryResponse from '../../response/CategoryResponse';
import { deleteCategory, readAllCategory } from '../../service/categoryService';
import TableElement from './table/TableElement';
import { toast } from 'react-toastify';
import CategoryViewElement from '../element/CategoryViewElement';

const CategoryAdminComponent = () => {
    const [show, setShow]=useState(false);
    const [showView, setShowView]=useState(false);
    const [categoryResponse, setCategoryResponse]=useState<CategoryResponse>();
    const [categoryResponses, setCategoryResponses]=useState<CategoryResponse[]>([]);
   useEffect(() => {
    fetchReadAllCategory();
   },[]); 
    const fetchReadAllCategory= () => {
        readAllCategory().then((d) => {
            if (d.data) setCategoryResponses(d.data);
            setShow(false);
        });
    }
    const handleEditClick=(data:CategoryResponse)=>{
        setCategoryResponse(data);
        setShow(true);
        
    }
    const handleViewClick=(data:CategoryResponse)=>{
        setCategoryResponse(data);
        setShowView(true);
    }
    const handleDeleteClick=(data:CategoryResponse)=>{
        deleteCategory(data.id).then((d) => {
            if (d.success) {
                toast.success(d.message);
            } else {
                toast.error(d.message);
            }
            fetchReadAllCategory();
        });
    }
    const handleAddClick=()=>{
        setShow(true);
        setCategoryResponse(undefined);
    }
  return (
    <>
    <button onClick={()=>handleAddClick()}>Create</button>
    <ModalElement component={<CategoryForm  refetch={fetchReadAllCategory} category={categoryResponse}/>} setShow={setShow} show={show} />
    <ModalElement component={<CategoryViewElement category={categoryResponse} />} setShow={setShowView} show={showView} />
    <TableElement array={categoryResponses} handleViewClick={handleViewClick} handleEditClick={handleEditClick} handleDeleteClick={handleDeleteClick}/>
    </>
  )
}

export default CategoryAdminComponent