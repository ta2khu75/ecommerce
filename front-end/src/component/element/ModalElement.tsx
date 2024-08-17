import React from 'react'
import { Modal } from 'react-bootstrap'
type Props={
    title?: string;
    show:boolean;
    setShow:React.Dispatch<React.SetStateAction<boolean>>;
    component?:JSX.Element,
}
const ModalElement = ({title, show, setShow, component}:Props) => {
    const handleClose=()=>{
      setShow(false)
    }
  return (
      <Modal show={show} size='xl' onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>{title}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {component}
        </Modal.Body>
      </Modal>
  )
}

export default ModalElement