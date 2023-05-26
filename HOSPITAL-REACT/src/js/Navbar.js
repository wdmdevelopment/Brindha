import React, { useState } from "react";
import { Link } from "react-router-dom";
import "../CSS/Navbar.css";
import SortIcon from "@mui/icons-material/Sort";
import CloseIcon from "@mui/icons-material/Close";
import Avatar from "@mui/material/Avatar";
import Chip from "@mui/material/Chip";
import LogoutIcon from "@mui/icons-material/Logout";
import IconButton from "@mui/material/IconButton";
import Tooltip from "@mui/material/Tooltip";
import hosLogo from "../images/hosLogo.png";
import HomeIcon from "@mui/icons-material/Home";
import PostAddIcon from "@mui/icons-material/PostAdd";
import LocalHospitalIcon from '@mui/icons-material/LocalHospital';
import MedicalServicesIcon from '@mui/icons-material/MedicalServices';

import { useNavigate } from "react-router";

const Navbar = () => {

  // scroll effect 

  const [changeHeader, setChangeHeader] = useState(false);

  const changeHeaderColor = () =>{
    if(window.scrollY >= 50){
      setChangeHeader(true)
    }else {
      setChangeHeader(false)
    }
  }

window.addEventListener('scroll', changeHeaderColor)


  const navigate = useNavigate();

  const [isMobile, setIsMobile] = useState(false);

  // ?allow admin only

  const isAdmin = localStorage.getItem("Role") == "ADMIN";

  // navigate("/");

  const [initial, setInitial] = useState(localStorage.getItem("Username"));

  const handleLogout = () => {
    localStorage.removeItem("userId");
    localStorage.removeItem("Email");
    localStorage.removeItem("Username");
    localStorage.removeItem("Role");
    localStorage.removeItem("accessToken");
    navigate("/");
  };

  // my bookings modal

  const [open, setOpen] = React.useState(false);

  const handleClickOpen = () => {
    navigate("/mybooking");
  };

  const handleClose = () => {
    setOpen(false);
  };

  // logo page open

  const [openLogo, setOpenLogo] = React.useState(false);

  const handleClickOpenLogo = () => {
    navigate("");
  };

  const handleCloseLogo = () => {
    setOpenLogo(false);
  };

  return (
    <div>
      {isAdmin ? (
        <nav className="navbar fixed-top">
          <Link to="/logoPage" onClick={handleClickOpenLogo}>
            <img
              className="logo"
              src={hosLogo}
              style={{ width: "10rem", height: "4em" }}
            />
          </Link>

          <ul
            className={isMobile ? "nav-links-mob" : "nav-links"}
            onClick={() => setIsMobile(false)}
          >
            <Link to="/logoPage" className="hospital">
              <li>{<HomeIcon />} HOME</li>
            </Link>
            <Link to="/hospital" className="hospital">
              <li>{<LocalHospitalIcon />} HOSPITAL</li>
            </Link>
            <Link to="/service" className="hospital">
              <li>{<MedicalServicesIcon />} SERVICES</li>
            </Link>
            <Tooltip>
              <IconButton sx={{ p: 0 }}>
                <Avatar>{initial[0]}</Avatar>
              </IconButton>
            </Tooltip>

            <Link to="/" className="logout">
              <Chip
                label="LOGOUT"
                icon={<LogoutIcon />}
                color="secondary"
                style={{ color: "#37474F" }}
                variant="outlined"
              />
            </Link>
          </ul>
          <button
            className="mob-menu-icon"
            onClick={() => setIsMobile(!isMobile)}
          >
            {isMobile ? <CloseIcon /> : <SortIcon />}
          </button>
        </nav>
      ) : (
        <nav className="navbar fixed-top">
          <Link to="/logoPage" onClick={handleClickOpenLogo}>
            <img
              className="logo"
              src={hosLogo}
              style={{ width: "10rem", height: "4em" }}
            />
          </Link>

          <ul
            className={isMobile ? "nav-links-mob" : "nav-links"}
            onClick={() => setIsMobile(false)}
          >
            <Link to="/logoPage" className="hospital">
              <li>{<HomeIcon />} HOME</li>
            </Link>
            <Link to="/hospital" className="hospital">
              <li>{<LocalHospitalIcon />} HOSPITAL</li>
            </Link>

            <Link
              to="/mybooking"
              className="hospital"
              onClick={handleClickOpen}
            >
              <li>{<PostAddIcon />} MY BOOKINGS</li>
            </Link>
            <Tooltip>
              <IconButton sx={{ p: 0 }}>
                <Avatar>{initial[0]}</Avatar>
              </IconButton>
            </Tooltip>

            <Link to="/" className="logout" onClick={handleLogout}>
              <Chip
                label="LOGOUT"
                icon={<LogoutIcon />}
                color="secondary"
                style={{ color: "#37474F", cursor:'pointer' }}
                variant="outlined"
              />
            </Link>
          </ul>
          <button
            className="mob-menu-icon"
            onClick={() => setIsMobile(!isMobile)}
          >
            {isMobile ? <CloseIcon /> : <SortIcon />}
          </button>
        </nav>
      )}
    </div>
  );
};

export default Navbar;
