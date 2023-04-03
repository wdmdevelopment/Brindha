import * as React from "react";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import { useEffect, useState } from "react";
import axios from "axios";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import CardMedia from "@mui/material/CardMedia";
import Button from "@mui/material/Button";
import AddIcon from "@mui/icons-material/Add";
import Fab from "@mui/material/Fab";
import TextField from "@mui/material/TextField";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import PhoneIcon from "@mui/icons-material/Phone";
import Chip from "@mui/material/Chip";
import ModeEditIcon from "@mui/icons-material/ModeEdit";
import DeleteIcon from "@mui/icons-material/Delete";
import LibraryAddIcon from "@mui/icons-material/LibraryAdd";
import CloseIcon from "@mui/icons-material/Close";
import UpgradeIcon from "@mui/icons-material/Upgrade";
import QueueIcon from "@mui/icons-material/Queue";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import LibraryAddCheckIcon from "@mui/icons-material/LibraryAddCheck";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import MedicalServicesIcon from "@mui/icons-material/MedicalServices";
import ArrowRightAltIcon from "@mui/icons-material/ArrowRightAlt";
import Alert from "@mui/material/Alert";
import Navbar from "./Navbar";
import { InputAdornment, IconButton } from "@mui/material";
import { Search } from "@mui/icons-material";
import "./hospital.css";
import { getAllHospitals } from "../apiCalls/getHospil";

export default function Hospital() {
  // search 
  const [searchText, setSearchtext] = useState("");

  const handleInputChange = (event) => {
    setSearchtext(event.target.value);
  };

  const [fullWidth, setFullWidth] = React.useState(true);
  const [maxWidth, setMaxWidth] = React.useState("md");

  // validation(add hospital)

  const [hosNameError, setHosNameameError] = useState(false);
  const [mobileError, setMobileError] = useState(false);
  const [cityError, setCityError] = useState(false);
  const [districtError, setDistrictError] = useState(false);
  const [stateError, setStateError] = useState(false);
  const [countryError, setCountryError] = useState(false);
  const [pinError, setPinError] = useState(false);
  const [facilityError, setFacilityError] = useState(false);

  const [formValid, setformValid] = useState();

  // const handleAlert = () => {
  //   setTimeout(() => {
  //     setformValid(true);
  //   }, 3000);
  // };

  const handleHosName = () => {
    if (!hosName) {
      setHosNameameError(true);
      return;
    }
    setHosNameameError(false);
  };

  const mobilePattern = /^[0-9]{10}$/;

  const handleMob = () => {
    if (!mobilePattern.test(number)) {
      setMobileError(true);
      return;
    }
    setMobileError(false);
  };

  const handleCity = () => {
    if (!city) {
      setCityError(true);
      return;
    }
    setCityError(false);
  };

  const handleDistrict = () => {
    if (!district) {
      setDistrictError(true);
      return;
    }
    setDistrictError(false);
  };

  const handleState = () => {
    if (!state) {
      setStateError(true);
      return;
    }
    setStateError(false);
  };
  const handleCountry = () => {
    if (!country) {
      setCountryError(true);
      return;
    }
    setCountryError(false);
  };

  const pinPattern = /^[0-9]{6}$/;
  const handlePin = () => {
    if (!pinPattern.test(pincode)) {
      setPinError(true);
      return;
    }
    setPinError(false);
  };
  const handleFacility = () => {
    if (!facility) {
      setFacilityError(true);
      return;
    }
    setFacilityError(false);
  };

  // validation(add slot)

  const [serviceError, setServiceError] = useState(false);
  const [amountError, setAmountError] = useState(false);
  const [startError, setStartError] = useState(false);
  const [endError, setEndError] = useState(false);
  const [dateError, setDateError] = useState(false);
  const [statusError, setStatusError] = useState(false);

  const handleServiceName = () => {
    if (!slotFacility) {
      setServiceError(true);
      return;
    }
    setServiceError(false);
  };
  const handleAmount = () => {
    if (!price) {
      setAmountError(true);
      return;
    }
    setAmountError(false);
  };
  const handleStartTime = () => {
    if (!startTime) {
      setStartError(true);
      return;
    }
    setStartError(false);
  };
  const handleEndTime = () => {
    if (!endTime) {
      setEndError(true);
      return;
    }
    setEndError(false);
  };
  const handleDate = () => {
    if (!date) {
      setDateError(true);
      return;
    }
    setDateError(false);
  };
  const handleStatus = () => {
    if (!status) {
      setStatusError(true);
      return;
    }
    setStatusError(false);
  };

  // get hospital

  const [gethospital, setHospital] = useState([]);

  useEffect(() => {
    const api = `http://localhost:8080/hospital/searchByHospitalName?hospitalName=${searchText}`;

    // const api = `http://localhost:8080/facility/searchByFacilityName?facilityName=${searchText}`;
    // const api ='http://localhost:8080/hospital/searchByHospitalName?hospitalName=${searchText}';

    axios
      .get(api, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
      .then((res) => {
        console.log(res.data);
        setHospital(res.data);
      })
      .catch((e) => {
        console.log(e);
      });
    // }, []);
  }, [searchText]);

  // add hospital function

  const [hosName, setHosName] = useState("");
  const [number, setNumber] = useState("");
  const [city, setCity] = useState("");
  const [district, setDistrict] = useState("");
  const [state, setState] = useState("");
  const [country, setCountry] = useState("");
  const [pincode, setPincode] = useState("");
  const [facility, setFacility] = useState("");
  const [file, setFile] = useState("");

  const [open, setOpen] = React.useState(false);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleSaveApi = () => {
    const formData = new FormData();

    formData.append("imagefile", file);

    formData.append(
      "data",
      JSON.stringify({
        hospitalName: hosName,
        contactNum: number,
        city: city,
        district: district,
        state: state,
        country: country,
        pincode: pincode,
        facilityName: facility,
        userId: localStorage.getItem("userId"),
      })
    );

    const api = `http://localhost:8080/hospital`;
    axios.post(api, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
      .then((res) => {
        console.log(res.data);
        getAllHospitals()
          .then((res) => {
            console.log(res.data);

            setHospital(res.data);
          })
          .catch((e) => {
            console.log(e);
          });
        setOpen(false);
      })
      .catch((e) => {
        console.log(e);
      });
    if (facilityError || !facility) {
      setformValid("Please enter your hospital facility.");
    }
    if (pinError || !pinPattern) {
      setformValid("Please enter valid 6-digit pin code.");
    }
    if (countryError || !country) {
      setformValid("Please enter country of hospital.");
    }
    if (stateError || !state) {
      setformValid("Please enter state of hospital.");
    }
    if (districtError || !district) {
      setformValid("Please enter district of hospital.");
    }
    if (cityError || !city) {
      setformValid("Please enter city of hospital.");
    }
    if (mobileError || !mobilePattern) {
      setformValid("Please enter your valid 10-digit mobile number.");
    }
    if (hosNameError || !hosName) {
      setformValid("Hospital name can't be empty.");
    }
  };

  // edit hospital

  const [editName, setEditName] = useState("");
  const [editNum, setEditNum] = useState("");
  const [editCity, setEditCity] = useState("");
  const [editDistrict, setEditDistrict] = useState("");
  const [editState, setEditState] = useState("");
  const [editCountry, setEditCountry] = useState("");
  const [editPincode, setEditPincode] = useState("");
  const [editFacility, setEditFacility] = useState("");
  const [editFile, setEditFile] = useState("");

  const [editOpen, setEditOpen] = React.useState(false);
  const [addressID, setAddressID] = useState("");
  const [facilityId, setFacilityId] = useState("");
  const [imageId, setImageId] = useState("");

  const [hospitalID, setHospitalID] = useState("");

  const handleEditOpen = (hospitalId) => {
    setHospitalID(hospitalId);

    setEditOpen(true);

    axios
      .get("http://localhost:8080/hospital/" + hospitalId, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
      .then((res) => {
        setEditName(res.data.hospitalName);
        setEditNum(res.data.contactNum);
        setEditCity(res.data.address.city);
        setEditDistrict(res.data.address.district);
        setEditState(res.data.address.state);
        setEditCountry(res.data.address.country);
        setEditPincode(res.data.address.pincode);
        setEditFacility(res.data.facilityList.facilityName);
        setAddressID(res.data.address.id);
        setFacilityId(res.data.facilityList.id);
        setImageId(res.data.hospitalImage.imageId);
        console.log(res.data);
      })
      .catch((e) => {
      });
  };

  const handleEditClose = () => {
    setEditOpen(false);
  };

  const handleEditApi = () => {
    const formData = new FormData();

    formData.append("imagefile", editFile);

    formData.append(
      "data",
      JSON.stringify({
        hospitalName: editName,
        contactNum: editNum,
        city: editCity,
        district: editDistrict,
        state: editState,
        country: editCountry,
        pincode: editPincode,
        facilityName: editFacility,
        addressId: addressID,
        facilityId: facilityId,
        imageId: imageId,
        userId: localStorage.getItem("userId"),
      })
    );

    const api = `http://localhost:8080/hospital/` + hospitalID;
    axios
      .put(api, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
      .then((res) => {
        console.log(res.data);

        getAllHospitals()
          .then((res) => {
            console.log(res.data);
            setHospital(res.data);
          })
          .catch((e) => {
            console.log(e);
          });

        setEditOpen(false);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  // delete hospital

  const handleClickHospitalDelete = (id) => {
    axios
      .delete("http://localhost:8080/hospital/" + id, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
      .then((res) => {
        console.log(res.data);
        getAllHospitals()
          .then((res) => {
            console.log(res.data);
            setHospital(res.data);
          })
          .catch((e) => {
            console.log(e);
          });
      })
      .catch((err) => {
      });
  };

  // add slot in hospital

  const [slotFacility, setSlotFacility] = useState("");
  const [price, setPrice] = useState("");
  const [startTime, setStartTime] = useState("");
  const [endTime, setEndTime] = useState("");
  const [date, setDate] = useState("");
  const [status, setStatus] = useState("");
  // const [hospitalSlotId, setHospitalSlotId] = useState([]);

  const [saveSlot, setSaveSlot] = React.useState(false);

  const handleOpenSlot = () => {
    setSaveSlot(true);
  };

  const handleCloseSlot = () => {
    setSaveSlot(false);
  };

  const handleSaveSlotApi = () => {
    const saveSlot = {
      userId: localStorage.getItem("userId"),
      facilityName: slotFacility,
      price: price,
      slotStartTime: startTime,
      slotEndTime: endTime,
      slotDate: date,
      status: status,
      hospitalId: slotHospitalId,
    };

    const slotApi = `http://localhost:8080/slot`;
    axios
      .post(slotApi, saveSlot, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
      .then((res) => {
        console.log(res.data);
        setSaveSlot(false);
      })
    
      .catch((e) => {
        console.log(e);
      });

    if (statusError || !status) {
      setformValid("Please enter status of slot. (Eg: OPEN/CLOSE)");
      return;
    }
    if (dateError || !date) {
      setformValid("Please enter slot date.");
      return;
    }
    if (endError || !endTime) {
      setformValid("Please enter slot end time.");
      return;
    }
    if (startError || !startTime) {
      setformValid("Please enter slot start time.");
      return;
    }
    if (amountError || !price) {
      setformValid("Please enter slot amount.");
      return;
    }
    if (serviceError || !slotFacility) {
      setformValid("Please enter slot Service name.");
      return;
    }
  };

  //  get all slots in user

  const [getSlot, setGetSlot] = React.useState(false);

  const [Slot, setSlot] = useState([]);
  const [slotHospitalId, setSlotHospitalId] = useState();

  const handleGetSlot = (hospitalId) => {
    
    console.log(hospitalId);
    setSlotHospitalId(hospitalId);
    setGetSlot(true);

    const getSlotApi =
      `http://localhost:8080/slot/getByHospital-status/` + hospitalId;
    axios
      .get(getSlotApi, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
      .then((res) => {
        console.log(res.data);
        setSlot(res.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };


  const handleGetSlotAdmin = (hospitalId) => {
    
    console.log(hospitalId);
    setSlotHospitalId(hospitalId);
    setGetSlot(true);

    const getSlotApi =
      `http://localhost:8080/slot/getByHospitalId/` + hospitalId;
    axios
      .get(getSlotApi, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
      .then((res) => {
        console.log(res.data);
        setSlot(res.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const handleCloseGetSlot = () => {
    setGetSlot(false);
  };

  // delete slot

  const handleClickSlotDelete = (slotId) => {
    axios
      .delete("http://localhost:8080/slot/" + slotId, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
      .then((res) => {
        console.log(res.data);
        setGetSlot(false);
      })
      .catch((err) => {
       
      });
  };

  // Book now slot(post method)

  const handleBookSlotApi = (slotId) => {
    const bookSlot = {
      userId: localStorage.getItem("userId"),
      slotId: slotId,
    };

    const bookSlotApi = `http://localhost:8080/Booking`;
    axios
      .post(bookSlotApi, bookSlot, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
      .then((res) => {
        console.log(res.data);
        handleGetSlot(slotHospitalId);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  // ?allow admin only

  const isAdmin = localStorage.getItem("Role") == "ADMIN";

  return (
    <div>
      <Navbar />

      {isAdmin ? (
        <Box className="addBtn" sx={{ "& > :not(style)": { m: 1 } }}>
          <Fab
            variant="extended"
            size="small"
            color="secondary"
            onClick={handleClickOpen}
            style={{ float: "right", marginRight: "60px", marginTop: "114px" }}
          >
            <AddIcon />
            Hospital
          </Fab>
          <br />
          <br />
          <br />
          <br />
          <TextField
            InputProps={{
              endAdornment: (
                <InputAdornment>
                  <IconButton>
                    <Search />
                  </IconButton>
                </InputAdornment>
              ),
            }}
            placeholder="search hospital name"
            className="searchBar"
            type="text"
            value={searchText}
            onChange={handleInputChange}
          />
        </Box>
      ) : (
        <Box className="addBtn" sx={{ "& > :not(style)": { m: 1 } }}>
          <br />
          <TextField
            InputProps={{
              endAdornment: (
                <InputAdornment>
                  <IconButton>
                    <Search />
                  </IconButton>
                </InputAdornment>
              ),
            }}
            style={{ marginTop: "80px" }}
            placeholder="search hospital name"
            className="userSearchBar"
            type="text"
            value={searchText}
            onChange={handleInputChange}
          />
        </Box>
      )}

      {isAdmin ? (
        <Grid
          container
          style={{ direction: "row", padding: "50px" }}
          spacing={3}
        >
          {gethospital.map((item, index) => (
            <Grid item xs={12} md={6} lg={3}>
              <Card sx={{ maxWidth: 250 }}>
                <CardMedia
                  component="img"
                  alt="product"
                  style={{}}
                  height="200"
                  padding-left="20"
                  image={
                    "data:image/png;base64," + item.hospitalImage.imageData
                  }
                />
                <CardContent>
                  <Box sx={{ textTransform: "uppercase", fontWeight: "bold" }}>
                    {item.hospitalName}
                  </Box>

                  <Button variant="text">
                    {<MedicalServicesIcon />} {<ArrowRightAltIcon />}
                    {item.facilityList.facilityName}
                  </Button>

                  <br />
                  <Typography gutterBottom variant="p" component="div">
                    {item.address.city}-{item.address.pincode}
                  </Typography>
                  <Typography gutterBottom variant="p" component="div">
                    {item.address.district}
                  </Typography>
                  <Chip
                    icon={<PhoneIcon />}
                    label={item.contactNum}
                    fontFamily="Monospace"
                    color="secondary"
                    sx={{
                      fontWeight: "bold",
                      fontSize: 20,
                      fontStyle: "italic",
                    }}
                    variant="outlined"
                  />
                </CardContent>
                <CardActions>
                  <Chip
                    icon={<ModeEditIcon />}
                    label="EDIT"
                    color="primary"
                    size="small"
                    variant="extended"
                    onClick={() => handleEditOpen(item.hospitalId)}
                  />
                  <Chip
                    icon={<DeleteIcon />}
                    label="DELETE"
                    color="error"
                    size="small"
                    variant="extended"
                    onClick={() => handleClickHospitalDelete(item.hospitalId)}
                  />
                  <Chip
                    icon={<AddIcon />}
                    label="SLOT"
                    color="success"
                    size="small"
                    onClick={() => handleGetSlotAdmin(item.hospitalId)}
                    variant="extended"
                  />
                </CardActions>
              </Card>
            </Grid>
          ))}
        </Grid>
      ) : (
        <Grid
          container
          style={{ direction: "row", padding: "50px", marginTop: "40px" }}
          spacing={3}
        >
          {gethospital.map((item) => (
            <Grid item xs={12} md={6} lg={3}>
              <Card sx={{ maxWidth: 250 }}>
                <CardMedia
                  component="img"
                  alt="product"
                  style={{}}
                  height="200"
                  padding-left="20"
                  image={
                    "data:image/png;base64," + item.hospitalImage.imageData
                  }
                />
                <CardContent>
                  <Box sx={{ textTransform: "uppercase", fontWeight: "bold" }}>
                    {item.hospitalName}
                  </Box>

                  <Button variant="text">
                    {<MedicalServicesIcon />} {<ArrowRightAltIcon />}
                    {item.facilityList.facilityName}
                  </Button>
                  <br />
                  <Typography gutterBottom variant="p" component="div">
                    {item.address.city}-{item.address.pincode}
                  </Typography>
                  <Typography gutterBottom variant="p" component="div">
                    {item.address.district}
                  </Typography>
                  <Chip
                    icon={<PhoneIcon />}
                    label={item.contactNum}
                    fontFamily="Monospace"
                    color="secondary"
                    sx={{
                      fontWeight: "bold",
                      fontSize: 20,
                      fontStyle: "italic",
                    }}
                    variant="outlined"
                  />
                </CardContent>
                <CardActions>
                  <Chip
                    icon={<QueueIcon />}
                    label="Book Your Slot"
                    color="primary"
                    size="small"
                    variant="extended"
                    onClick={() => handleGetSlot(item.hospitalId)}
                    style={{
                      padding: "15px 15px 15px 15px",
                      fontSize: "14px",
                      marginLeft: "35px",
                    }}
                  />
                </CardActions>
              </Card>
            </Grid>
          ))}
        </Grid>
      )}

      {/* add hospital modal */}

      <Dialog open={open} onClose={handleClose} style={{ padding: "30px" }}>
        <DialogTitle>
          <Typography variant="h5" align="center">
            Add Hospital
          </Typography>
        </DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            error={hosNameError}
            value={hosName}
            // onClick={handleAlert}
            onChange={(e) => {
              setHosName(e.target.value);
            }}
            label="Hospital Name"
            onBlur={handleHosName}
            type="text"
            fullWidth
            size="small"
            variant="filled"
          />
          <TextField
            autoFocus
            margin="dense"
            error={mobileError}
            value={number}
            onChange={(e) => {
              setNumber(e.target.value);
            }}
            label="Mobile number"
            onBlur={handleMob}
            type="number"
            fullWidth
            size="small"
            variant="standard"
            maxLength="10"
          />
          <TextField
            autoFocus
            margin="dense"
            error={cityError}
            value={city}
            onChange={(e) => {
              setCity(e.target.value);
            }}
            label="City"
            onBlur={handleCity}
            type="text"
            fullWidth
            size="small"
            variant="filled"
          />
          <TextField
            autoFocus
            margin="dense"
            error={districtError}
            value={district}
            onChange={(e) => {
              setDistrict(e.target.value);
            }}
            label="District"
            onBlur={handleDistrict}
            type="text"
            fullWidth
            size="small"
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            error={stateError}
            value={state}
            onChange={(e) => {
              setState(e.target.value);
            }}
            label="State"
            onBlur={handleState}
            type="text"
            fullWidth
            size="small"
            variant="filled"
          />
          <TextField
            autoFocus
            margin="dense"
            error={countryError}
            value={country}
            onChange={(e) => {
              setCountry(e.target.value);
            }}
            label="Country"
            onBlur={handleCountry}
            type="text"
            fullWidth
            size="small"
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            error={pinError}
            value={pincode}
            onChange={(e) => {
              setPincode(e.target.value);
            }}
            label="Zip COde"
            onBlur={handlePin}
            fullWidth
            size="small"
            variant="filled"
          />
          <TextField
            autoFocus
            margin="dense"
            error={facilityError}
            value={facility}
            onChange={(e) => {
              setFacility(e.target.value);
            }}
            label="Facility Name"
            onBlur={handleFacility}
            type="text"
            fullWidth
            size="small"
            variant="standard"
          />
          <br />
          <br />
          <input
            type="file"
            onChange={(e) => {
              setFile(e.target.files[0]);
            }}
          />
        </DialogContent>
        <DialogActions>
          <Chip
            icon={<LibraryAddIcon />}
            label="Save"
            color="success"
            variant="extended"
            onClick={handleSaveApi}
            size="small"
          />

          <Chip
            icon={<CloseIcon />}
            label="Cancel"
            size="small"
            color="error"
            variant="extended"
            onClick={handleClose}
          />
        </DialogActions>

        <p>{formValid && <Alert severity="error">{formValid}</Alert>}</p>
      </Dialog>

      {/* end */}

      {/* edit hospital modal */}

      <Dialog
        open={editOpen}
        onClose={handleEditClose}
        style={{ padding: "30px" }}
      >
        <DialogTitle>
          <Typography variant="h5" align="center">
            Update Hospital
          </Typography>
        </DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            value={editName}
            onChange={(e) => {
              setEditName(e.target.value);
            }}
            label="Hospital Name"
            type="text"
            fullWidth
            size="small"
            variant="filled"
          />
          <TextField
            autoFocus
            margin="dense"
            value={editNum}
            onChange={(e) => {
              setEditNum(e.target.value);
            }}
            label="Contact"
            type="number"
            fullWidth
            size="small"
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            value={editCity}
            onChange={(e) => {
              setEditCity(e.target.value);
            }}
            label="City"
            type="text"
            fullWidth
            size="small"
            variant="filled"
          />
          <TextField
            autoFocus
            margin="dense"
            value={editDistrict}
            onChange={(e) => {
              setEditDistrict(e.target.value);
            }}
            label="District"
            type="text"
            fullWidth
            size="small"
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            value={editState}
            onChange={(e) => {
              setEditState(e.target.value);
            }}
            label="State"
            type="text"
            fullWidth
            size="small"
            variant="filled"
          />
          <TextField
            autoFocus
            margin="dense"
            value={editCountry}
            onChange={(e) => {
              setEditCountry(e.target.value);
            }}
            label="Country"
            type="text"
            fullWidth
            size="small"
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            value={editPincode}
            onChange={(e) => {
              setEditPincode(e.target.value);
            }}
            label="Zip COde"
            fullWidth
            size="small"
            variant="filled"
          />
          <TextField
            autoFocus
            margin="dense"
            value={editFacility}
            onChange={(e) => {
              setEditFacility(e.target.value);
            }}
            label="Facility Name"
            type="text"
            fullWidth
            size="small"
            variant="standard"
          />
          <br />
          <br />
          <input
            type="file"
            onChange={(e) => {
              setEditFile(e.target.files[0]);
            }}
          />
        </DialogContent>
        <DialogActions>
          <Chip
            icon={<UpgradeIcon />}
            label="Update"
            color="success"
            variant="extended"
            onClick={handleEditApi}
            size="small"
          />

          <Chip
            icon={<CloseIcon />}
            label="Cancel"
            size="small"
            color="error"
            variant="extended"
            onClick={handleEditClose}
          />
        </DialogActions>
      </Dialog>

      {/* end */}

      {/* add slot hospital */}
      <Dialog
        open={saveSlot}
        onClose={handleCloseSlot}
        style={{ padding: "30px" }}
      >
        <DialogTitle>
          <Typography variant="h5" align="center">
            Add Slot
          </Typography>
        </DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            error={serviceError}
            value={slotFacility}
            onChange={(e) => {
              setSlotFacility(e.target.value);
            }}
            label="Service Name"
            type="text"
            onBlur={handleServiceName}
            fullWidth
            size="small"
            variant="filled"
          />
          <TextField
            autoFocus
            margin="dense"
            error={amountError}
            value={price}
            onChange={(e) => {
              setPrice(e.target.value);
            }}
            label="Service Amount"
            type="number"
            onBlur={handleAmount}
            fullWidth
            size="small"
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            error={startError}
            value={startTime}
            onChange={(e) => {
              setStartTime(e.target.value);
            }}
            label="Start Time"
            type="time"
            onBlur={handleStartTime}
            fullWidth
            size="small"
            variant="filled"
          />
          <br />
          <TextField
            autoFocus
            margin="dense"
            error={dateError}
            value={date}
            onChange={(e) => {
              setDate(e.target.value);
            }}
            type="date"
            onBlur={handleDate}
            fullWidth
            size="small"
            variant="standard"
          />
          <br />

          <TextField
            autoFocus
            margin="dense"
            value={endTime}
            onChange={(e) => {
              setEndTime(e.target.value);
            }}
            label="End Time"
            type="time"
            error={endError}
            onBlur={handleEndTime}
            fullWidth
            size="small"
            variant="filled"
          />

          <TextField
            autoFocus
            margin="dense"
            error={statusError}
            value={status}
            onChange={(e) => {
              setStatus(e.target.value);
            }}
            label="Service Status"
            onBlur={handleStatus}
            fullWidth
            size="small"
            variant="standard"
          />
        </DialogContent>
        <DialogActions>
          <Chip
            icon={<LibraryAddIcon />}
            label="Save"
            color="success"
            variant="extended"
            onClick={handleSaveSlotApi}
            size="small"
          />

          <Chip
            icon={<CloseIcon />}
            label="Cancel"
            size="small"
            color="error"
            variant="extended"
            onClick={handleCloseSlot}
          />
        </DialogActions>
        <p>{formValid && <Alert severity="error">{formValid}</Alert>}</p>
      </Dialog>
      {/* end */}

      {/* get slot hospital */}

      {isAdmin ? (
        <Dialog
          fullWidth={fullWidth}
          maxWidth={maxWidth}
          open={getSlot}
          onClose={handleCloseGetSlot}
          style={{ padding: "30px" }}
        >
          <DialogTitle>
            <Typography variant="h5" textEmphasisColor="red" align="center">
              Hospital Slots
            </Typography>
          </DialogTitle>

          <Box sx={{ "& > :not(style)": { m: 1 } }}>
            <Fab
              variant="extended"
              size="small"
              color="secondary"
              onClick={() => handleOpenSlot()}
              style={{ float: "right", marginRight: "30px" }}
            >
              <AddIcon />
              Add Slot
            </Fab>
          </Box>

          <DialogContent>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell>Service</TableCell>
                  <TableCell align="right">Amount</TableCell>
                  <TableCell align="right">Start</TableCell>
                  <TableCell align="right">End</TableCell>
                  <TableCell align="right">Date</TableCell>
                  <TableCell align="right">Status</TableCell>
                  <TableCell align="right">Action</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {Slot.map((item) => (
                  <TableRow key={item.slotId}>
                    <TableCell component="th" scope="item">
                      {item.facility.facilityName}
                    </TableCell>
                    <TableCell align="right">{item.price}</TableCell>
                    <TableCell align="right">{item.slotStartTime}</TableCell>
                    <TableCell align="right">{item.slotEndTime}</TableCell>
                    <TableCell align="right">{item.slotDate}</TableCell>
                    <TableCell align="right">{item.status}</TableCell>
                    <TableCell align="right">
                      <Chip
                        icon={<DeleteIcon />}
                        label="Delete"
                        fontFamily="Monospace"
                        color="error"
                        onClick={() => handleClickSlotDelete(item.slotId)}
                        sx={{
                          fontWeight: "bold",
                          fontSize: 14,
                          fontStyle: "italic",
                        }}
                        variant="outlined"
                      />
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </DialogContent>
        </Dialog>
      ) : (
        <Dialog
          fullWidth={fullWidth}
          maxWidth={maxWidth}
          open={getSlot}
          onClose={handleCloseGetSlot}
          style={{ padding: "30px" }}
        >
          <DialogTitle>
            <Typography variant="h5" align="center">
              Book Your Slot
            </Typography>
          </DialogTitle>

          <DialogContent>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell>Service</TableCell>
                  <TableCell align="right">Amount</TableCell>
                  <TableCell align="right">Start</TableCell>
                  <TableCell align="right">End</TableCell>
                  <TableCell align="right">Date</TableCell>
                  <TableCell align="right">Status</TableCell>
                  <TableCell align="right">Action</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {Slot.map((item) => (
                  <TableRow key={item.slotId}>
                    <TableCell component="th" scope="item">
                      {item.facility.facilityName}
                    </TableCell>
                    <TableCell align="right">{item.price}</TableCell>
                    <TableCell align="right">{item.slotStartTime}</TableCell>
                    <TableCell align="right">{item.slotEndTime}</TableCell>
                    <TableCell align="right">{item.slotDate}</TableCell>
                    <TableCell align="right">{item.status}</TableCell>
                    <TableCell align="right">
                      <Chip
                        icon={<LibraryAddCheckIcon />}
                        label="Book Now"
                        fontFamily="Monospace"
                        color="primary"
                        sx={{
                          fontWeight: "bold",
                          fontSize: 14,
                          fontStyle: "italic",
                        }}
                        onClick={() => handleBookSlotApi(item.slotId)}
                        variant="outlined"
                      />
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </DialogContent>
        </Dialog>
      )}

      {/* end */}
    </div>
  );
}
