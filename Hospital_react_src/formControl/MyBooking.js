import React from "react";
import Navbar from "./Navbar";
import axios from "axios";
import { useEffect } from "react";
import CurrencyRupeeIcon from "@mui/icons-material/CurrencyRupee";
import { useState } from "react";
import "./myBookings.css";
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
        if (res.data.length === 0) {
          alert("There is no records to show.");
        } else {
          console.log(res.data);
          setBookingData(res.data);
        }
      })
      .catch((e) => {});
  }, []);

  return (
    <div>
      <Navbar />

      {bookingData.map((item) => (
        <div class="center">
          <ul>
            <li>
              <div class="image">
                <img
                  src={
                    "data:image/png;base64," +
                    item.slot.hospital.hospitalImage.imageData
                  }
                  alt=""
                />

                <h4>{item.slot.hospital.hospitalName}</h4>
              </div>

              <div class="text-group">
                <h4>
                  {<MedicalInformationIcon />}{" "}
                  {item.slot.hospital.facilityList.facilityName}
                </h4>
                <br />
                <h4>
                  {<AddIcCallIcon />} {item.slot.hospital.contactNum}
                </h4>
              </div>

              <div class="text-group">
                <h4>
                  {<AccessTimeFilledIcon />} {item.slot.slotStartTime} -{" "}
                  {item.slot.slotEndTime}
                </h4>
                <br />
                <h4>
                  {<CalendarMonthIcon />} {item.slot.slotDate}
                </h4>
              </div>

              <div class="date-group">
                <Chip
                  icon={<CurrencyRupeeIcon />}
                  label={item.slot.price}
                  fontFamily="Monospace"
                  color="secondary"
                  sx={{
                    fontWeight: "bold",
                    fontSize: 20,
                    fontStyle: "italic",
                  }}
                  variant="contained"
                />
              </div>
            </li>
          </ul>
        </div>
      ))}
    </div>
  );
}

export default MyBooking;
