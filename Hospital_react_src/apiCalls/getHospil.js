import axios from "axios";

export function getAllHospitals() {
  const api = `http://localhost:8080/hospital`;

  return axios.get(api, {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
    },
  });
}
