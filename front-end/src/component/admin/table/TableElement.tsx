import { Button, Table } from "react-bootstrap";
type Props<T extends object> = {
  handleViewClick?: (data: T) => void;
  handleEditClick?: (data: T) => void;
  handleDeleteClick?: (data: T) => void;
  array: T[];
  visiableColumn?: string[];
};
const TableElement = <T extends object>({
  array,
  visiableColumn,
  handleDeleteClick,
  handleEditClick,
  handleViewClick,
}: Props<T>) => {
  const fieldKey =
    array.length > 0
      ? visiableColumn
        ? Object.keys(array[0]).filter((t) => visiableColumn.includes(t))
        : Object.keys(array[0])
      : [];
  return (
    <Table striped bordered hover>
      <thead>
        <tr>
          {fieldKey.map((value, index) => (
            <th key={value + index}>{value.toUpperCase()}</th>
          ))}
          {(handleViewClick || handleEditClick || handleDeleteClick) && (
            <th>Action</th>
          )}
        </tr>
      </thead>
      <tbody>
        {array.map((item, index) => (
          <tr key={"record" + index}>
            {fieldKey.map((key) => {
              const value = item[key];
              if (typeof value === "object") {
                return <td key={key + index}>{value?.name}</td>;
              } else if (typeof value === "boolean") {
                return <td key={key + index}>{value ? "true" : "false"}</td>;
              } else {
                return <td key={key + index}>{value}</td>;
              }
            })}
            {(handleViewClick || handleEditClick || handleDeleteClick) && (
              <td className="d-flex justify-content-around">
                {handleViewClick && (
                  <Button variant="info" onClick={() => handleViewClick(item)}>
                    View
                  </Button>
                )}
                {handleEditClick && (
                  <Button
                    variant="warning"
                    onClick={() => handleEditClick(item)}
                  >
                    Edit
                  </Button>
                )}
                {handleDeleteClick && (
                  <Button
                    onClick={() => handleDeleteClick(item)}
                    variant="danger"
                  >
                    Delete
                  </Button>
                )}
              </td>
            )}
          </tr>
        ))}
      </tbody>
    </Table>
  );
};

export default TableElement;
