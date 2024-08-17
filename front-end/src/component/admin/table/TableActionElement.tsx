import React from "react";
import { Button, Table } from "react-bootstrap";
type Props<T> = {
  array: T[];
};
const TableActionElement = <T,>({ array }: Props<T>) => {
  return (
    <Table striped bordered hover>
      <thead>
        <tr>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        {array.map((item, index) => (
          <tr key={"action" + index}>
            <Button variant="info text-dark">View</Button>
            <Button variant="warning text-dark">Edit</Button>
            <Button variant="danger text-dark">Delete</Button>
          </tr>
        ))}
      </tbody>
    </Table>
  );
};

export default TableActionElement;
