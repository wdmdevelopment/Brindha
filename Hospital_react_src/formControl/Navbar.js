import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./Navbar.css";
import SortIcon from "@mui/icons-material/Sort";
import CloseIcon from "@mui/icons-material/Close";
import Avatar from "@mui/material/Avatar";
import Chip from "@mui/material/Chip";
import LogoutIcon from "@mui/icons-material/Logout";
import IconButton from "@mui/material/IconButton";
import Tooltip from "@mui/material/Tooltip";
import navLogo from "../images/navLogo.png"
import HomeIcon from '@mui/icons-material/Home';
import PostAddIcon from '@mui/icons-material/PostAdd';

import { useNavigate } from "react-router";



const Navbar = () => {
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

  return (
    <div>
      {isAdmin ? (
        <nav className="navbar fixed-top">
          <img
            className="logo"
            src={navLogo}
            style={{ width: "4rem", height: "3em"}}
          />
          <ul
            className={isMobile ? "nav-links-mob" : "nav-links"}
            onClick={() => setIsMobile(false)}
          >
            <Link to="/hospital" className="hospital">
              <li>{<HomeIcon />} Hospital</li>
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
          <img
            className="logo"
            src={navLogo}
            style={{ width: "4rem", height: "3em"}}
          />
      
          <ul
            className={isMobile ? "nav-links-mob" : "nav-links"}
            onClick={() => setIsMobile(false)}
          >
            <Link to="/hospital" className="hospital">
              <li>{<HomeIcon />} HOSPITAL</li>
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

            <Link to="/" className="logout">
              <Chip
                label="LOGOUT"
                icon={<LogoutIcon />}
                color="secondary"
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
