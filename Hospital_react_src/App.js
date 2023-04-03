 
import "./App.css";
 
import Login from './formControl/login';
import SignUp from './formControl/signup';
import Hospital from './formControl/hospital'
import Bookings from './formControl/Bookings'
import { Route, Routes } from "react-router-dom";
import MyBooking from "./formControl/MyBooking";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<SignUp />} />
        <Route path="/hospital" element={<Hospital />} />
        <Route path="/bookings" element={<Bookings />} />
        <Route path="/mybooking" element={<MyBooking />}/>
      </Routes>
     
    </div>
  );
}

export default App;
