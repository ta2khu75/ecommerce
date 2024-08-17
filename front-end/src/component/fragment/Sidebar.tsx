import React from "react";
import { NavLink } from "react-router-dom";

const SidebarComponent = () => {
  return (
    <nav
      id="sidebarMenu"
      className="collapse d-lg-block sidebar collapse bg-white"
    >
      <div className="position-sticky">
        <div className="list-group list-group-flush mx-3 mt-4">
          <a
            href="#"
            className="list-group-item list-group-item-action py-2"
            data-mdb-ripple-init
            aria-current="true"
          >
            <i className="fas fa-tachometer-alt fa-fw me-3" />
            <span>Main dashboard</span>
          </a>
          <a
            href="#"
            className="list-group-item list-group-item-action py-2 active"
            data-mdb-ripple-init
          >
            <i className="fas fa-chart-area fa-fw me-3" />
            <span>Website traffic </span>
          </a>
          <a
            href="#"
            className="list-group-item list-group-item-action py-2"
            data-mdb-ripple-init
          >
            <i className="fas fa-lock fa-fw me-3" />
            <span>Password</span>
          </a>
          <a
            href="#"
            className="list-group-item list-group-item-action py-2"
            data-mdb-ripple-init
          >
            <i className="fas fa-chart-line fa-fw me-3" />
            <span>Analytics</span>
          </a>
          <a
            href="#"
            className="list-group-item list-group-item-action py-2"
            data-mdb-ripple-init
          >
            <i className="fas fa-chart-pie fa-fw me-3" />
            <span>SEO</span>
          </a>
          <a
            href="#"
            className="list-group-item list-group-item-action py-2"
            data-mdb-ripple-init
          >
            <i className="fas fa-chart-bar fa-fw me-3" />
            <span>Orders</span>
          </a>
          <a
            href="#"
            className="list-group-item list-group-item-action py-2"
            data-mdb-ripple-init
          >
            <i className="fas fa-globe fa-fw me-3" />
            <span>International</span>
          </a>
          <a
            href="#"
            className="list-group-item list-group-item-action py-2"
            data-mdb-ripple-init
          >
            <i className="fas fa-building fa-fw me-3" />
            <span>Partners</span>
          </a>
          <a
            href="#"
            className="list-group-item list-group-item-action py-2"
            data-mdb-ripple-init
          >
            <i className="fas fa-calendar fa-fw me-3" />
            <span>Calendar</span>
          </a>
          <NavLink
            className="list-group-item list-group-item-action py-2"
            to={"/admin/account"}
          >
            <i className="fas fa-users fa-fw me-3" />
            <span>Users</span>
          </NavLink>
          <NavLink
            className="list-group-item list-group-item-action py-2"
            to={"/admin/product"}
          >
            <i className="fa-solid fa-boxes-stacked me-3"></i>
            <span>Products</span>
          </NavLink>
          <NavLink
            className="list-group-item list-group-item-action py-2"
            to={"/admin/brand"}
          >
            <i className="fa-solid fa-tags me-3"></i>
            <span>Brands</span>
          </NavLink>
          <NavLink
          to={"/admin/category"}
            className="list-group-item list-group-item-action py-2"
            data-mdb-ripple-init
          >
            <i className="fa-solid fa-layer-group me-3"></i>
            <span>Categories</span>
          </NavLink>
        </div>
      </div>
    </nav>
  );
};

export default SidebarComponent;
