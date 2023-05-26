import axios from "axios";

export function getAllHospitals() {
  const api = `http://localhost:8080/hospital/search?query=`;

  return axios.get(api, {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
    },
  });
}
