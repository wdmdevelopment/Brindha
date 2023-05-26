import React from "react";
import Navbar from "./Navbar";
import axios from "axios";
import { useEffect } from "react";
import CurrencyRupeeIcon from "@mui/icons-material/CurrencyRupee";
import { useState } from "react";
import "../CSS/myBookings.css";
import Chip from "@mui/material/Chip";
import AccessTimeFilledIcon from "@mui/icons-material/AccessTimeFilled";
import CalendarMonthIcon from "@mui/icons-material/CalendarMonth";
import AddIcCallIcon from "@mui/icons-material/AddIcCall";
import MedicalInformationIcon from "@mui/icons-material/MedicalInformation";

function MyBooking() {
  const [bookingData, setBookingData] = useState([]);

  useEffect(() => {
    axios
      .get(
        "http://localhost:8080/Booking/userBook/" +
          localStorage.getItem("userId"),
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
          },
        }
      )

      .then((res) => {
        console.log(res.data);
        setBookingData(res.data);
      })
      .catch((e) => {});
  }, []);

  return (
    <div>
      <Navbar />

      {bookingData.length === 0 ? (
        <div
          colSpan="6"
          style={{
            padding: "50px",
            backgroundImage: `url(${require("../images/myBookings.jpg")})`,
            height: "100vh",
            backgroundAttachment: "fixed",
            backgroundSize: "cover",
            backgroundPosition: "center",
            color: "red",
            textAlign: "center",
            fontSize: "15pt",
            marginTop: "70px",
          }}
        >
          There is no records to show.
        </div>
      ) : (
        bookingData.map((item) => (
          <div
            class="center"
            style={{
              padding: "0.5px",
              backgroundImage: `url(${require("../images/bookList.jpg")})`,
              backgroundAttachment: "fixed",
              backgroundSize: "cover",
              backgroundPosition: "center",
            }}
          >
            <ul>
              <br />
              <br />
              <br />
              <li
                style={{
                  backgroundImage: `url(${require("../images/myBookings.jpg")})`,
                  backgroundAttachment: "fixed",
                  backgroundSize: "cover",
                  backgroundPosition: "center",
                }}
              >
                <div class="image">
                  <img
                    src={
                      "data:image/png;base64," +
                      item.slot.hospital.hospitalImage.imageData
                    }
                  />
                </div>

                <div class="text-group">
                  <h4 style={{ fontSize: "14pt", textTransform: "uppercase" }}>
                    {item.slot.hospital.hospitalName}
                  </h4>
                  <br />
                  <h4 style={{ fontSize: "10pt", marginLeft: "46px" }}>
                    {<AccessTimeFilledIcon />} {item.slot.slotStartTime} -{" "}
                    {item.slot.slotEndTime}
                  </h4>
                  {/* <br /> */}
                  <h4 style={{ fontSize: "10pt", marginLeft: "3px" }}>
                    {<CalendarMonthIcon />}
                    {new Date(
                      item.slot.slotDate
                    ).toLocaleDateString("en-GB", {
                      day: "2-digit",
                      month: "2-digit",
                      year: "numeric",
                    })}
                  </h4>

                  <h4 style={{ fontSize: "10pt", marginLeft: "10px" }}>
                    {<AddIcCallIcon />} {item.slot.hospital.contactNum}
                  </h4>
                </div>

                <div class="date-group">
                  <Chip
                    icon={<CurrencyRupeeIcon />}
                    label={item.slot.price}
                    fontFamily="Monospace"
                    color="secondary"
                    size="small"
                    sx={{
                      fontWeight: "bold",
                      fontSize: 20,
                    }}
                    variant="contained"
                  />
                </div>
              </li>
            </ul>
          </div>
        ))
      )}
    </div>
  );
}

export default MyBooking;
