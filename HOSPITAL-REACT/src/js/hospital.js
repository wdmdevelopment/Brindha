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
import Alert from "@mui/material/Alert";
import Navbar from "./Navbar";
import {
  InputAdornment,
  IconButton,
  Checkbox,
  ListItemText,
} from "@mui/material";
import { Search } from "@mui/icons-material";
import "../CSS/hospital.css";
import { getAllHospitals } from "../apiCalls/getHospital";
import sweetAlert from "sweetalert";
import Footer from "./footer";
import { DemoContainer, DemoItem } from "@mui/x-date-pickers/internals/demo";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import CurrencyRupeeIcon from "@mui/icons-material/CurrencyRupee";
import { MobileTimePicker } from "@mui/x-date-pickers/MobileTimePicker";
import StripeCheckout from "react-stripe-checkout";
import Multiselect from "multiselect-react-dropdown";

import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";

import OutlinedInput from "@mui/material/OutlinedInput";

import Select from "@mui/material/Select";

import { useTheme } from "@mui/material/styles";
import { useNavigate } from "react-router";
import StripeForm from "../stripe/StripeForm";

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
};

function getStyles(name, personName, theme) {
  return {
    fontWeight:
      personName.indexOf(name) === -1
        ? theme.typography.fontWeightRegular
        : theme.typography.fontWeightMedium,
  };
}

// import MultiSelect from "react-multiple-select-dropdown-lite";
// import "react-multiple-select-dropdown-lite/dist/index.css";

export default function Hospital() {
  // stripe

  const [openStripe, setOpenStripe] = React.useState(false);
  const [stripePrice, setStripePrice] = useState();
  const [slotId, setSlotId] = useState();

  const handleClickOpenStripe = (price, slotId) => {
    // console.log(slotId);
    setSlotId(slotId);
    setStripePrice(price);
    setOpenStripe(true);
  };

  const handleCloseStripe = () => {
    setOpenStripe(false);
  };

  // const navigate = useNavigate();

  // const handleStripe = (slotPrice) => {
  //   console.log(price);

  //   navigate("/pay", { price: { argument: slotPrice } });
  // };

  const theme = useTheme();
  const [personName, setPersonName] = React.useState([]);

  const handleChange = (event) => {
    setFacility(event.target.value);
    console.log(event.target.value);

    const {
      target: { value },
    } = event;
    setPersonName(
      // On autofill we get a stringified value.
      typeof value === "string" ? value.split(",") : value
    );

    console.log(personName);
  };

  // search
  const [searchText, setSearchtext] = useState("");

  const handleInputChange = (event) => {
    setSearchtext(event.target.value);
  };

  const [fullWidth, setFullWidth] = React.useState(true);
  const [maxWidth, setMaxWidth] = React.useState("md");

  // MULTI SELECT SERVICE FIELD

  const [value, setValue] = useState([]);

  useEffect(() => {
    const getServiceData = async () => {
      const getFacilityName = [];
      const facilityId = [];
      const reqData = await fetch(
        "http://localhost:8080/hospital/facility/services"
      );
      const resData = await reqData.json();
      console.log(resData);

      setValue(resData);

      for (let i = 0; i < resData.length; i++) {
        getFacilityName.push(resData[i].facilityName);
        facilityId.push(resData[i].id);
      }
    };
    getServiceData();
  }, []);

  const [selectedItemId, setSelectedItemId] = useState(null);
  const handleMultiselectValue = (selectedList, selectedItem) => {
    console.log(selectedList);

    setFacility(selectedList);

    setSelectedItemId(selectedItem.identifier);
    console.log(selectedItemId);
    // console.log(selectedItemId);
    //  console.log(selectedItemId);
  };

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
    const api = `http://localhost:8080/hospital/search?query=${searchText}`;

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

    getService();
  }, [searchText]);

  function getService() {
    axios
      .get("http://localhost:8080/hospital/facility/services", {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
      .then((res) => {
        console.log(res.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  // add hospital function

  const [hosName, setHosName] = useState("");
  const [number, setNumber] = useState("");
  const [city, setCity] = useState("");
  const [district, setDistrict] = useState("");
  const [state, setState] = useState("");
  const [country, setCountry] = useState("");
  const [pincode, setPincode] = useState("");
  const [facility, setFacility] = useState([]);
  const [file, setFile] = useState("");

  const handleMobileNumber = (event) => {
    const inputValue = event.target.value;

    // Allow only numbers and a dot (for decimal places)
    const numericValue = inputValue.replace(/[^0-9]/g, "");

    // Update the state with the numeric value
    setNumber(numericValue);
  };

  const [open, setOpen] = React.useState(false);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleSaveApi = () => {
    if (hosNameError || !hosName) {
      setformValid("Hospital name can't be empty.");
      return;
    }
    if (mobileError || !mobilePattern) {
      setformValid("Please enter your valid 10-digit mobile number.");
      return;
    }

    if (cityError || !city) {
      setformValid("Please enter city of hospital.");
      return;
    }
    if (districtError || !district) {
      setformValid("Please enter district of hospital.");
      return;
    }

    if (stateError || !state) {
      setformValid("Please enter state of hospital.");
      return;
    }
    if (countryError || !country) {
      setformValid("Please enter country of hospital.");
      return;
    }
    if (pinError || !pinPattern) {
      setformValid("Please enter valid 6-digit pin code.");
      return;
    }
    if (facilityError || !facility) {
      setformValid("Please enter your hospital facility.");
      return;
    }

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
        userId: localStorage.getItem("userId"),
        facility: facility,
      })
    );

    formData.append("data1", facility);

    const api = `http://localhost:8080/hospital`;
    axios
      .post(api, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
      .then((res) => {
        sweetAlert("Success!", "Hospital added successfully", "success");
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
        setEditFacility(res.data.facility.facilityName);
        setAddressID(res.data.address.id);
        setFacilityId(res.data.facility.id);
        setImageId(res.data.hospitalImage.imageId);

        console.log(res.data);
      })
      .catch((e) => {
        console.log(e);
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
        sweetAlert("Updated!", "Hospital updated successfully", "success");

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
        sweetAlert("Deleted!", "Hospital deleted successfully", "success");

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
      .catch((err) => {});
    sweetAlert("warning!", "Can't Delete, Hospital Contains Datas.", "warning");
  };

  // add slot in hospital

  const [slotFacility, setSlotFacility] = useState("");
  const [price, setPrice] = useState("");
  const [startTime, setStartTime] = useState();
  const [endTime, setEndTime] = useState("");
  const [date, setDate] = useState("");
  const [status, setStatus] = useState("");

  const handlePrice = (event) => {
    const priceValue = event.target.value;

    // Allow only numbers and a dot (for decimal places)
    const numericPriceValue = priceValue.replace(/[^0-9]/g, "");

    // Update the state with the numeric value
    setPrice(numericPriceValue);
  };

  // const [hospitalSlotId, setHospitalSlotId] = useState([]);

  const [saveSlot, setSaveSlot] = React.useState(false);

  const handleOpenSlot = () => {
    setSaveSlot(true);
  };

  const handleCloseSlot = () => {
    setSaveSlot(false);
  };

  const handleSaveSlotApi = () => {
    // if (serviceError || !slotFacility) {
    //   setformValid("Please enter slot Service name.");
    //   return;
    // }
    if (dateError || !date) {
      setformValid("Please enter slot date.");
      return;
    }
    if (startError || !startTime) {
      setformValid("Please enter slot start time.");
      return;
    }
    if (endError || !endTime || startTime.isAfter(endTime)) {
      setformValid("End time must be after the start time.");
      return;
    }
    if (amountError || !price) {
      setformValid("Please enter slot amount.");
      return;
    }

    if (statusError || !status) {
      setformValid("Please enter status of slot. (Eg: OPEN/CLOSE)");
      return;
    }

    const LocalTime = new Date(startTime).toLocaleString("en-US", {
      timeZone: "Asia/Kolkata",
    });
    const endDate1 = new Date(LocalTime);
    const formattedStartTime = endDate1.toLocaleTimeString([], {
      hour: "numeric",
      minute: "2-digit",
    });

    const endLocalTime = new Date(endTime).toLocaleString("en-US", {
      timeZone: "Asia/Kolkata",
    });
    const endDate2 = new Date(endLocalTime);
    const formattedEndTime = endDate2.toLocaleTimeString([], {
      hour: "numeric",
      minute: "2-digit",
    });
    console.log(formattedStartTime);
    console.log(formattedEndTime);
    const saveSlot = {
      userId: localStorage.getItem("userId"),
      facilityName: slotFacility,
      price: price,
      slotStartTime: formattedStartTime,
      slotEndTime: formattedEndTime,
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
        sweetAlert("Success!", "Slot added successfully", "success");
        console.log(res.data);
        setSaveSlot(false);
        handleGetSlot(slotHospitalId);
      })

      .catch((e) => {
        console.log(e);
      });
  };

  //  get all slots in user

  const [getSlot, setGetSlot] = React.useState(false);

  const [Slot, setSlot] = useState([]);
  const [slotHospitalId, setSlotHospitalId] = useState();
  const [slotPrice, setSlotPrice] = useState();

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
        sweetAlert("Deleted!", "Slot deleted successfully", "success");
        console.log(res.data);
        setGetSlot(false);
      })
      .catch((err) => {});
    sweetAlert("warning!", "Can't delete booked slot", "warning");
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
        sweetAlert("Success!", "Slot booked successfully", "success");

        console.log(res.data);
        handleGetSlot(slotHospitalId);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  // ?allow admin only

  const isAdmin = localStorage.getItem("Role") === "ADMIN";

  const handleTimeChange = (startTime) => {
    setStartTime(startTime);
  };
  const handleEndTimeChange = (endTime) => {
    setEndTime(endTime);
  };

  return (
    <div>
      <Navbar />

      {isAdmin ? (
        <Box className="addBtn" sx={{ "& > :not(style)": { m: 1 } }}>
          <button
            onClick={handleClickOpen}
            style={{
              float: "left",
              marginLeft: "60px",
              marginTop: "98.5px",
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
            Hospital
          </button>
          <br />
          <br />
          <br />
          <br />
          <TextField
            style={{
              marginRight: "90px",
              width: "350px",
              marginBottom: "10px",
            }}
            InputProps={{
              endAdornment: (
                <InputAdornment>
                  <IconButton>
                    <Search />
                  </IconButton>
                </InputAdornment>
              ),
            }}
            placeholder="Search Hospitals, Facilites, etc...."
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
            placeholder="Search Hospitals, Facilites, etc...."
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
                  image={"data:image/png;base64," + item.data}
                />
                <CardContent>
                  <Box sx={{ textTransform: "uppercase", fontWeight: "bold" }}>
                    {item.hospitalName}
                  </Box>

                  {/* <Button variant="text">
                    {<MedicalServicesIcon />} {<ArrowRightAltIcon />}
                    {item.facilityList.facilityName}
                  </Button> */}

                  <br />
                  <Typography
                    gutterBottom
                    variant="p"
                    component="div"
                    style={{ textTransform: "capitalize" }}
                  >
                    {item.city}
                  </Typography>
                  <Typography
                    gutterBottom
                    variant="p"
                    component="div"
                    style={{ textTransform: "capitalize" }}
                  >
                    {item.district}-{item.pincode}
                  </Typography>
                  <Chip
                    icon={<PhoneIcon style={{ fontSize: "medium" }} />}
                    label={item.contactNum}
                    fontFamily="Monospace"
                    color="secondary"
                    sx={{
                      fontWeight: "bold",
                      fontSize: 14,
                    }}
                    style={{
                      height: "10%",
                      marginLeft: "1%",
                    }}
                    variant="outlined"
                  />
                </CardContent>
                <CardActions style={{ paddingLeft: "22px" }}>
                  <Chip
                    icon={<ModeEditIcon />}
                    label="Edit"
                    color="primary"
                    size="small"
                    variant="extended"
                    onClick={() => handleEditOpen(item.hospitalId)}
                  />
                  <Chip
                    icon={<DeleteIcon />}
                    label="Delete"
                    color="error"
                    size="small"
                    variant="extended"
                    onClick={() => handleClickHospitalDelete(item.hospitalId)}
                  />
                  <Chip
                    icon={<AddIcon />}
                    label="Slot"
                    color="success"
                    size="small"
                    onClick={() => handleGetSlotAdmin(item.hospitalId)}
                    variant="extended"
                    style={{ paddingLeft: "2px" }}
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
                  image={"data:image/png;base64," + item.data}
                />
                <CardContent>
                  <Box sx={{ textTransform: "uppercase", fontWeight: "bold" }}>
                    {item.hospitalName}
                  </Box>

                  {/* <Button variant="text">
                    {<MedicalServicesIcon />} {<ArrowRightAltIcon />}
                    {item.facilityList.facilityName}
                  </Button> */}
                  <br />
                  <Typography
                    gutterBottom
                    variant="p"
                    component="div"
                    style={{ textTransform: "capitalize" }}
                  >
                    {item.city}
                  </Typography>
                  <Typography
                    gutterBottom
                    variant="p"
                    component="div"
                    style={{ textTransform: "capitalize" }}
                  >
                    {item.district}-{item.pincode}
                  </Typography>
                  <Chip
                    icon={<PhoneIcon style={{ fontSize: "small" }} />}
                    label={item.contactNum}
                    fontFamily="Monospace"
                    color="secondary"
                    sx={{
                      fontWeight: "bold",
                      fontSize: 14,
                    }}
                    style={{
                      height: "10%",
                      marginLeft: "1%",
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

      <Dialog open={open} onClose={handleClose} style={{ padding: "20px" }}>
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
            size="small"
            variant="outlined"
          />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <TextField
            autoFocus
            margin="dense"
            error={mobileError}
            value={number}
            onChange={handleMobileNumber}
            label="Mobile number"
            onBlur={handleMob}
            size="small"
            variant="outlined"
            maxLength="10"
            placeholder="+91 xxxxx xxxxx"
          />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
            size="small"
            variant="outlined"
          />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
            size="small"
            variant="outlined"
          />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
            size="small"
            variant="outlined"
          />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
            size="small"
            variant="outlined"
          />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
            size="small"
            variant="outlined"
          />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          {/* multi select field */}
          <div
            style={{
              justifyContent: "left",
              display: "flex",
              paddingTop: "8px",
              marginLeft: "-9px",
              width: "45%",
            }}
          >
            <FormControl sx={{ m: 1, width: 300 }}>
              <InputLabel id="demo-multiple-chip-label">Services...</InputLabel>
              <Select
                labelId="demo-multiple-chip-label"
                id="demo-multiple-chip"
                multiple
                value={personName}
                onChange={handleChange}
                input={<OutlinedInput id="select-multiple-chip" label="Chip" />}
                renderValue={(selected) => (
                  <Box sx={{ display: "flex", flexWrap: "wrap", gap: 0.5 }}>
                    {selected.map((value) => (
                      <Chip key={value} label={value} />
                    ))}
                  </Box>
                )}
                MenuProps={MenuProps}
              >
                {value.map((item) => (
                  <MenuItem
                    key={item.id}
                    value={item.facilityName}

                    // style={getStyles(item.id, item.facilityName, theme)}
                  >
                    {item.facilityName}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>

        
          </div>
          {/* end */}
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
            size="small"
            variant="outlined"
          />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <TextField
            autoFocus
            margin="dense"
            value={editNum}
            onChange={(e) => {
              setEditNum(e.target.value);
            }}
            label="Contact"
            size="small"
            variant="outlined"
          />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <TextField
            autoFocus
            margin="dense"
            value={editCity}
            onChange={(e) => {
              setEditCity(e.target.value);
            }}
            type="text"
            label="City"
            size="small"
            variant="outlined"
          />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <TextField
            autoFocus
            margin="dense"
            value={editDistrict}
            onChange={(e) => {
              setEditDistrict(e.target.value);
            }}
            type="text"
            label="District"
            size="small"
            variant="outlined"
          />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <TextField
            autoFocus
            margin="dense"
            value={editDistrict}
            onChange={(e) => {
              setEditDistrict(e.target.value);
            }}
            label="District"
            size="small"
            variant="outlined"
          />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <TextField
            autoFocus
            margin="dense"
            value={editState}
            onChange={(e) => {
              setEditState(e.target.value);
            }}
            label="State"
            size="small"
            variant="outlined"
          />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <TextField
            autoFocus
            margin="dense"
            value={editCountry}
            onChange={(e) => {
              setEditCountry(e.target.value);
            }}
            label="Country"
            size="small"
            variant="outlined"
          />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <TextField
            autoFocus
            margin="dense"
            value={editPincode}
            onChange={(e) => {
              setEditPincode(e.target.value);
            }}
            label="Zip COde"
            size="small"
            variant="outlined"
          />
          {/* multi select field */}
          <div
            style={{
              justifyContent: "left",
              display: "flex",
              paddingTop: "8px",
              marginLeft: "-9px",
              width: "45%",
            }}
          >
            <FormControl sx={{ m: 1, width: 300 }}>
              <InputLabel id="demo-multiple-checkbox-label">
                Services...
              </InputLabel>
              <Select
                labelId="demo-multiple-checkbox-label"
                id="demo-multiple-chip"
                multiple
                value={personName}
                onChange={handleChange}
                input={<OutlinedInput label="Tag" />}
                renderValue={(selected) => selected.join(", ")}
                MenuProps={MenuProps}
              >
                {value.map((item) => (
                  <MenuItem key={item.facilityName} value={item.facilityName}>
                    <Checkbox checked={personName.indexOf(item) > -1} />
                    <ListItemText primary={item.facilityName} />
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
          </div>
          {/* end */}
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
            variant="outlined"
          />

          <TextField
            variant="outlined"
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
          />
          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DemoContainer
              components={[
                "TimePicker",
                "MobileTimePicker",
                "DesktopTimePicker",
                "StaticTimePicker",
              ]}
            >
              <DemoItem label="Start Time">
                <MobileTimePicker
                  value={startTime}
                  error={startError}
                  onBlur={handleStartTime}
                  onChange={handleTimeChange}
                />
              </DemoItem>
            </DemoContainer>
          </LocalizationProvider>

          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DemoContainer
              components={[
                "TimePicker",
                "MobileTimePicker",
                "DesktopTimePicker",
                "StaticTimePicker",
              ]}
            >
              <DemoItem label="End Time">
                <MobileTimePicker
                  value={endTime}
                  error={endError}
                  onBlur={handleEndTime}
                  onChange={handleEndTimeChange}
                />
              </DemoItem>
            </DemoContainer>
          </LocalizationProvider>

          {/* <TextField
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
          /> */}

          <br />

          <TextField
            variant="outlined"
            autoFocus
            margin="dense"
            error={amountError}
            value={price}
            onChange={handlePrice}
            label="Price"
            onBlur={handleAmount}
            fullWidth
            size="small"
          />

          <TextField
            autoFocus
            margin="dense"
            error={statusError}
            value={status}
            onChange={(e) => {
              setStatus(e.target.value);
            }}
            label="Status"
            onBlur={handleStatus}
            fullWidth
            size="small"
            variant="outlined"
          />
        </DialogContent>
        <DialogActions>
          <Chip
            icon={<LibraryAddIcon />}
            label="Save"
            color="success"
            variant="outlined"
            onClick={handleSaveSlotApi}
            size="medium"
          />

          <Chip
            icon={<CloseIcon />}
            label="Cancel"
            size="medium"
            color="error"
            variant="outlined"
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
          <DialogTitle style={{ backgroundColor: "#FFF3E0" }}>
            <Typography
              variant="h5"
              textEmphasisColor="red"
              align="left"
              color="#01579B"
            >
              HOSPITAL SLOTS
            </Typography>

            {/* <Box sx={{ "& > :not(style)": { m: 1 } }}> */}
            <Fab
              variant="extended"
              size="small"
              color="secondary"
              onClick={() => handleOpenSlot()}
              style={{
                float: "right",
                marginRight: "30px",
                marginTop: "-33px",
              }}
            >
              <AddIcon />
              Add Slot
            </Fab>
            {/* </Box> */}
          </DialogTitle>

          <DialogContent
            style={{
              padding: "50px",
              backgroundImage: `url(${require("../images/addSlot.jpg")})`,
              height: "100vh",
              backgroundAttachment: "fixed",
              backgroundSize: "cover",
              backgroundPosition: "center",
            }}
          >
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell
                    align="center"
                    style={{
                      backgroundColor: "#FFF3E0",
                      color: "#607D8B",
                      fontWeight: "bold",
                      border: "1px solid #ddd",
                    }}
                  >
                    DATE
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
                    STARTS
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
                    ENDS
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
                    PRICE
                  </TableCell>
                  {/* <TableCell
                    align="center"
                    style={{
                      backgroundColor: "#FFF3E0",
                      color: "#607D8B",

                      fontWeight: "bold",
                      border: "1px solid #ddd",
                    }}
                  >
                    STATUS
                  </TableCell> */}
                  <TableCell
                    align="left"
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
                {Slot.length === 0 ? (
                  <TableRow>
                    <td
                      colSpan="6"
                      style={{
                        color: "red",
                        textAlign: "center",
                        fontSize: "15pt",
                      }}
                    >
                      There is no record to show.
                    </td>
                  </TableRow>
                ) : (
                  Slot.map((item) => (
                    <TableRow key={item.slotId}>
                      <TableCell
                        component="th"
                        scope="item"
                        style={{ fontWeight: "bolder", color: "#37474F" }}
                      >
                        <button
                          style={{
                            backgroundColor: "#b3e5fc",
                            cursor: "text",
                            color: "black",
                            fontSize: "14px",
                            padding: "10px 20px",
                            borderRadius: "5px",
                            border: "3px double gray",
                          }}
                        >
                          {new Date(item.slotDate).toLocaleDateString("en-GB", {
                            day: "2-digit",
                            month: "2-digit",
                            year: "numeric",
                          })}
                        </button>
                      </TableCell>
                      <TableCell
                        align="left"
                        style={{ fontWeight: "bolder", color: "#37474F" }}
                      >
                        <button
                          style={{
                            backgroundColor: "#b3e5fc",
                            cursor: "text",
                            color: "black",
                            fontSize: "14px",
                            padding: "10px 20px",
                            borderRadius: "5px",
                            border: "3px double gray",
                          }}
                        >
                          {item.slotStartTime}
                        </button>
                      </TableCell>
                      <TableCell
                        align="left"
                        style={{ fontWeight: "bolder", color: "#37474F" }}
                      >
                        <button
                          style={{
                            backgroundColor: "#b3e5fc",
                            color: "black",
                            cursor: "text",
                            fontSize: "14px",
                            padding: "10px 20px",
                            borderRadius: "5px",
                            border: "3px double gray",
                          }}
                        >
                          {item.slotEndTime}
                        </button>
                      </TableCell>
                      <TableCell
                        align="left"
                        style={{ fontWeight: "bolder", color: "#37474F" }}
                      >
                        <button
                          style={{
                            backgroundColor: "#b3e5fc",
                            color: "black",
                            cursor: "text",
                            fontSize: "14px",
                            padding: "10px 20px",
                            borderRadius: "5px",
                            border: "3px double gray",
                          }}
                        >
                          <CurrencyRupeeIcon style={{ fontSize: "medium" }} />{" "}
                          {item.price}
                        </button>
                      </TableCell>
                      {/* <TableCell
                        align="left"
                        style={{
                          fontWeight: "bolder",
                          color: "#37474F",
                          textTransform: "capitalize",
                        }}
                      >
                        <button
                          style={{
                            backgroundColor: "#b3e5fc",
                            cursor: "text",
                            color: "black",
                            fontSize: "16px",
                            padding: "10px 20px",
                            borderRadius: "5px",
                            border: "3px double gray",
                          }}
                        >
                          {" "}
                          {item.status}
                        </button>
                      </TableCell> */}
                      <TableCell align="left">
                        <Chip
                          icon={<DeleteIcon style={{ fontSize: "medium" }} />}
                          label="DELETE"
                          fontFamily="Monospace"
                          color="error"
                          onClick={() => handleClickSlotDelete(item.slotId)}
                          sx={{
                            fontSize: 10,
                          }}
                          variant="outlined"
                        />
                      </TableCell>
                    </TableRow>
                  ))
                )}
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
          <DialogTitle
            style={{
              backgroundColor: "#FFF3E0",
            }}
          >
            <Typography variant="h5" align="center" color="#01579B">
              BOOK YOUR SLOT
            </Typography>
          </DialogTitle>

          <DialogContent
            style={{
              padding: "50px",
              backgroundImage: `url(${require("../images/addSlot.jpg")})`,
              height: "100vh",
              backgroundAttachment: "fixed",
              backgroundSize: "cover",
              backgroundPosition: "center",
            }}
          >
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell
                    align="center"
                    style={{
                      backgroundColor: "#FFF3E0",
                      color: "#607D8B",
                      fontWeight: "bold",
                      border: "1px solid #ddd",
                    }}
                  >
                    DATE
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
                    STARTS
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
                    ENDS
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
                    PRICE
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
                    PAY BY
                  </TableCell>
                  {/* <TableCell
                    align="center"
                    style={{
                      backgroundColor: "#FFF3E0",
                      color: "#607D8B",

                      fontWeight: "bold",
                      border: "1px solid #ddd",
                    }}
                  >
                    ACTION
                  </TableCell> */}
                </TableRow>
              </TableHead>
              <TableBody>
                {Slot.length === 0 ? (
                  <TableRow>
                    <td
                      colSpan="6"
                      style={{
                        color: "red",
                        textAlign: "center",
                        fontSize: "15pt",
                      }}
                    >
                      There is no record to show.
                    </td>
                  </TableRow>
                ) : (
                  Slot.map((item) => (
                    <TableRow key={item.slotId}>
                      <TableCell
                        component="th"
                        scope="item"
                        style={{ fontWeight: "bolder", color: "#37474F" }}
                      >
                        <button
                          style={{
                            backgroundColor: "#c5e1a5",
                            cursor: "text",
                            color: "black",
                            fontSize: "12px",
                            padding: "10px 20px",
                            borderRadius: "5px",
                            border: "3px double gray",
                          }}
                        >
                          {new Date(item.slotDate).toLocaleDateString("en-GB", {
                            day: "2-digit",
                            month: "2-digit",
                            year: "numeric",
                          })}
                        </button>
                      </TableCell>
                      <TableCell
                        align="left"
                        style={{ fontWeight: "bolder", color: "#37474F" }}
                      >
                        <button
                          style={{
                            backgroundColor: "#c5e1a5",
                            cursor: "text",
                            color: "black",
                            fontSize: "12px",
                            padding: "10px 20px",
                            borderRadius: "5px",
                            border: "3px double gray",
                          }}
                        >
                          {item.slotStartTime}
                        </button>
                      </TableCell>
                      <TableCell
                        align="left"
                        style={{ fontWeight: "bolder", color: "#37474F" }}
                      >
                        <button
                          style={{
                            backgroundColor: "#c5e1a5",
                            cursor: "text",
                            color: "black",
                            fontSize: "12px",
                            padding: "10px 20px",
                            borderRadius: "5px",
                            border: "3px double gray",
                          }}
                        >
                          {item.slotEndTime}
                        </button>
                      </TableCell>
                      <TableCell
                        align="left"
                        style={{ fontWeight: "bolder", color: "#37474F" }}
                      >
                        <button
                          style={{
                            backgroundColor: "#c5e1a5",
                            cursor: "text",
                            color: "black",
                            fontSize: "12px",
                            padding: "10px 20px",
                            borderRadius: "5px",
                            border: "3px double gray",
                          }}
                        >
                          <CurrencyRupeeIcon style={{ fontSize: "medium" }} />{" "}
                          {item.price}
                        </button>
                      </TableCell>
                      <TableCell align="left">
                        <button
                          onClick={() =>
                            handleClickOpenStripe(item.price, item.slotId)
                          }
                          style={{
                            whiteSpace: "nowrap",
                            display: "inline-block",
                            height: "40px",
                            lineHeight: "40px",
                            padding: "0 14px",
                            borderRadius: "4px",
                            boxShadow:
                              "0 4px 6px rgba(50, 50, 93, 0.11), 0 1px 3px rgba(0, 0, 0, 0.08)",
                            color: "#fff",
                            backgroundColor: "#6772e5",
                            fontWeight: "600",
                          }}
                        >
                          PAY
                        </button>
                      </TableCell>
                      {/* <TableCell align="left">
                        <Chip
                          icon={
                            <LibraryAddCheckIcon
                              style={{ fontSize: "small" }}
                            />
                          }
                          label="BOOK NOW"
                          fontFamily="Monospace"
                          color="primary"
                          sx={{
                            fontSize: 10,
                          }}
                          onClick={() => handleBookSlotApi(item.slotId)}
                          variant="outlined"
                        />
                      </TableCell> */}
                    </TableRow>
                  ))
                )}
              </TableBody>
            </Table>
          </DialogContent>
        </Dialog>
      )}

      {/* end */}

      {/* stripe modal */}

      <Dialog
        open={openStripe}
        onClose={handleCloseStripe}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
        fullWidth
        maxWidth={"sm"}
        // style={{backgroundColor:"#1A2027"  }}
        PaperProps={{
          sx: {
            minHeight: "500px",
            backgroundColor: "#1a2027",
            color: "white",
          },
        }}
      >
        <DialogTitle id="alert-dialog-title" style={{ textAlign: "center" }}>
          <h1
            style={{ color: "Blue", fontSize: "62px", fontFamily: "cursive" }}
          >
            Stripe
          </h1>
        </DialogTitle>
        <DialogTitle id="alert-dialog-title" style={{ textAlign: "center" }}>
          <h4 style={{ color: "violet", fontFamily: "-moz-initial" }}>
            Slot Price: <CurrencyRupeeIcon style={{ fontSize: "large" }} />
            {stripePrice}
          </h4>
        </DialogTitle>

        <StripeForm
          stripePrice={stripePrice}
          slotId={slotId}
          handleBookSlotApi={handleBookSlotApi}
        />
      </Dialog>

      {/* end */}

      <Footer />
    </div>
  );
}
