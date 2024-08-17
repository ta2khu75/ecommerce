import { Outlet } from "react-router-dom";
import SidebarComponent from "../fragment/Sidebar";
const AdminComponent = () => {
  return (
    <div className="container-fluid">
      <div className="row">
        <div className="col-2">
          <SidebarComponent />
        </div>
        <div className="col-10">
          <Outlet />
        </div>
      </div>
    </div>
  );
};

export default AdminComponent;
