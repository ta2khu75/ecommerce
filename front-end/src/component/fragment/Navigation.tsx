import { Link } from "react-router-dom";
import { resetAccount } from "../../redux/slice/accountSlice";
import { useAppDispatch, useAppSelector } from "../../redux/hooks";
import { useEffect, useState } from "react";
import ModalElement from "../element/ModalElement";
import RegisterComponent from "../Register";
import LoginComponent from "../Login";
import { resetSocialAccount } from "../../redux/slice/socialAccountSlice";

const NavigationFragment = () => {
  const authentication = useAppSelector(
    (state) => state.account.authentication
  );
  const dispatch = useAppDispatch();
  const [showLogin, setShowLogin] = useState(false);
  const [showRegister, setShowRegister] = useState(false);
  useEffect(() => {
    setShowLogin(false);
  }, [authentication]);
  useEffect(() => {
    if (showLogin) {
      dispatch(resetAccount());
    }
  }, [showLogin]);
  const handleClickLogout = () => {
    dispatch(resetAccount());
  };
  const handlerLoginClick = () => {
    setShowLogin(true);
    setShowRegister(false);
    dispatch(resetSocialAccount());
  };
  const handlerRegisterClick = () => {
    setShowRegister(true);
    setShowLogin(false);
  };
  return (
    <>
      <nav
        className="custom-navbar navbar navbar navbar-expand-md navbar-dark bg-dark"
        arial-label="Furni navigation bar"
      >
        <div className="container">
          <Link to={"/"} className="navbar-brand">
            SHOP
          </Link>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarsFurni"
            aria-controls="navbarsFurni"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon" />
          </button>
          <div className="collapse navbar-collapse" id="navbarsFurni">
            <ul className="custom-navbar-nav navbar-nav ms-auto mb-2 mb-md-0">
              <li className="nav-item active">
                <a className="nav-link" href="index.html">
                  Home
                </a>
              </li>
              <li>
                <a className="nav-link" href="shop.html">
                  Shop
                </a>
              </li>
              <li>
                <a className="nav-link" href="about.html">
                  About us
                </a>
              </li>
              <li>
                <a className="nav-link" href="services.html">
                  Services
                </a>
              </li>
              <li>
                <a className="nav-link" href="blog.html">
                  Blog
                </a>
              </li>
              <li>
                <a className="nav-link" href="contact.html">
                  Contact us
                </a>
              </li>
              {!authentication && (
                <li>
                  <a className="nav-link" onClick={() => setShowLogin(true)}>
                    <img src="images/user.svg" />
                  </a>
                </li>
              )}
              {authentication && (
                <li className="nav-item dropdown">
                  <a
                    className="nav-link dropdown-toggle"
                    href="#"
                    role="button"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    <img src="images/user.svg" />
                  </a>
                  <ul className="dropdown-menu">
                    <li>
                      <Link className="dropdown-item text-dark" to={"/profile"}>
                        Account
                      </Link>
                    </li>
                    <li>
                      <button
                        className="dropdown-item"
                        onClick={() => handleClickLogout()}
                      >
                        Logout
                      </button>
                    </li>
                    <li>
                      <hr className="dropdown-divider" />
                    </li>
                    <li>
                      <a className="dropdown-item" href="#">
                        Something else here
                      </a>
                    </li>
                  </ul>
                </li>
              )}
              <li>
                <a className="nav-link" href="cart.html">
                  <img src="images/cart.svg" />
                </a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <ModalElement
        setShow={setShowRegister}
        show={showRegister}
        component={<RegisterComponent showLogin={handlerLoginClick} />}
      />
      <ModalElement
        setShow={setShowLogin}
        show={showLogin}
        component={<LoginComponent showRegister={handlerRegisterClick} />}
      />
    </>
  );
};

export default NavigationFragment;
