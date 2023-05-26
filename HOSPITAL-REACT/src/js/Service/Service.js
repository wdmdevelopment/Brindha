import React from "react";
import Navbar from "../Navbar";
import AddIcon from "@mui/icons-material/Add";
import axios from "axios";
import sweetAlert from "sweetalert";
import { useState } from "react";
import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import DialogTitle from "@mui/material/DialogTitle";
import DeleteIcon from "@mui/icons-material/Delete";

import {
  Chip,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
} from "@mui/material";
import { useEffect } from "react";

function Service() {
  // service validation
  const [serviceError, setServiceError] = useState(false);

  const handleService = () => {
    if (!serviceName) {
      setServiceError(true);
      return;
    }
    setServiceError(false);
  };
  // service dialog box open

  const [isOpen, setIsOpen] = useState(false);

  const handleButtonClick = () => {
    setIsOpen(true);
  };

  const handleClose = () => {
    setIsOpen(false);
  };

  const [inFormation, setInFormation] = useState([]);

  const getAllServices = () => {
    const Api = `http://localhost:8080/hospital/facility/services`;
    axios
      .get(Api, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
      .then((res) => {
        setInFormation(res.data);
      })

      .catch((e) => {
        alert(e);
      });
  };

  //   Save service Api

  const [serviceName, setServiceName] = useState("");
  const [hospitalId, setHospitalID] = useState("");

  const handleSaveServiceApi = () => {
    if (serviceError || !serviceName) {
      sweetAlert("warning!", "Please Enter the Valid Service Name", "warning", {
        timer: 1500,
      });

      return;
    }
    const saveService = {
      userId: localStorage.getItem("userId"),
      facilityName: serviceName,
      hospitalId: hospitalId,
    };

    const serviceApi = `http://localhost:8080/hospital/facility`;
    axios
      .post(serviceApi, saveService, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
      .then((res) => {
        sweetAlert("Success!", "Service added successfully", "success");
        console.log(res.data);
        setIsOpen(false);
        getAllServices();
      })

      .catch((e) => {
        console.log(e);
      });
  };

  useEffect(() => {
    getAllServices();
  }, []);

  // delete service
  const handleClickServiceDelete = (id) => {
    axios
      .delete("http://localhost:8080/hospital/facility/ "  + id, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
      .then((res) => {
        sweetAlert("Deleted!", "Service deleted successfully", "success");
        console.log(res.data);
        getAllServices();
      })
      .catch((err) => {});
    sweetAlert("warning!", "Oops! Please avoid deleting & add a new Service", "warning", {timer: 1500});
  };

  return (
    <div>
      <Navbar />

      <button
        onClick={handleButtonClick}
        style={{
          float: "left",
          marginLeft: "60px",
          marginTop: "116.5px",
          border: "2px solid #ab47bc",
          color: "#d500f9",
          padding: "13px 20px",
          backgroundColor: "transparent",
          cursor: "pointer",
          fontSize: "16px",
          borderRadius: "4px",
        }}
      >
        <AddIcon />
        Services
      </button>

      <Dialog
        open={isOpen}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
          {"Save Hospital Service"}
        </DialogTitle>
        <DialogContent>
          <Box
            component="form"
            sx={{
              "& > :not(style)": { m: 1, width: "25ch" },
            }}
            noValidate
            autoComplete="off"
          >
            <TextField
              id="outlined-basic"
              label="Enter Service Name"
              variant="outlined"
              error={serviceError}
              onBlur={handleService}
              onChange={(e) => {
                setServiceName(e.target.value);
              }}
            />
          </Box>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>CANCEL</Button>
          <Button onClick={handleSaveServiceApi} autoFocus>
            SAVE
          </Button>
        </DialogActions>
      </Dialog>
      <br />
      <br />
      <br />
      <br />
      <br />

      <Table
        style={{
          width: "700px",
          height: "100px",
          border: "1px solid #ddd",
          marginLeft: "auto",
          marginRight: "230px",
        }}
      >
        <TableHead>
          <TableRow>
            <TableCell
              align="left"
              style={{
                backgroundColor: "#FFF3E0",
                color: "#607D8B",
                fontWeight: "bold",
                border: "1px solid #ddd",
              }}
            >
              SERVICE NAME
            </TableCell>

            <TableCell
              align="center"
              style={{
                backgroundColor: "#FFF3E0",
                color: "#607D8B",
                fontWeight: "bold",
                border: "1px solid #ddd",
              }}
            >
              ACTION
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {inFormation.map((item) => (
            <TableRow>
              <TableCell>{item.facilityName}</TableCell>
              
              &nbsp;&nbsp;&nbsp;&nbsp;
              <Chip
                icon={<DeleteIcon />}
                label="DELETE"
                fontFamily="Monospace"
                color="error"
                onClick={() => handleClickServiceDelete(item.id)}
                sx={{
                  fontSize: 10,
                }}
                style={{ marginTop: "11px" }}
                variant="outlined"
              />
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
}

export default Service;
