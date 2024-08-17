import { createBrowserRouter } from "react-router-dom";
import HomeComponent from "./component/Home";
import IndexComponent from "./component/Index";
import CartComponent from "./component/Cart";
import ThankyouComponent from "./component/Thankyou";
import CheckoutComponent from "./component/Checkout";
import AdminComponent from "./component/admin/Admin";
import URComponent from "./component/UR";
import ProductComponent from "./component/Product";
import BrandAdminComponent from "./component/admin/BrandAdmin";
import ProductAdminComponent from "./component/admin/ProductAdmin";
import AccountAdminComponent from "./component/admin/AccountAdmin";
import HomeAdminComponent from "./component/admin/HomeAdmin";
import ProductViewAdmin from "./component/admin/ProductViewAdmin";
import CategoryAdminComponent from "./component/admin/CategoryAdmin";
import ProfileComponent from "./component/Profile";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <IndexComponent />,
    children: [
      {
        index: true,
        element: <HomeComponent />,
      },
      {
        path: "cart",
        element: <CartComponent />,
      },
      {
        path: "thankyou",
        element: <ThankyouComponent />,
      },
      {
        path: "checkout",
        element: <CheckoutComponent />,
      },
      {
        path: "product/:id",
        element: <ProductComponent />,
      },
      {
        path: "profile",
        element: <ProfileComponent/>
      }
    ],
  },
  {
    path: "/admin",
    element: <AdminComponent />,
    children: [
      {
        index: true,
        element: <HomeAdminComponent />,
      },
      {
        path: "category",
        element: <CategoryAdminComponent />,
      },
      {
        path: "brand",
        element: <BrandAdminComponent />,
      },
      {
        path: "product",
        element: <ProductAdminComponent />,
      },
      {
        path: "product-details/:id",
        element: <ProductViewAdmin />
      },
      {
        path: "account",
        element: <AccountAdminComponent/>
      },
    ],
  },
  {
    path: "/ur",
    element: <URComponent />,
  },
]);
