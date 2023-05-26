import "./App.css";
import Login from "./js/login";
import SignUp from "./js/signup";
import Hospital from "./js/hospital";
import { Route, Routes } from "react-router-dom";
import MyBooking from "./js/MyBooking";
import Footer from "./js/footer";
import LogoPage from "./components/LogoPage";
import Service from "./js/Service/Service";

// const stripePromise = loadStripe(
//   "pk_test_51N93ltSF9C4UGoYIgfbjlknR1VPpc8yUdFc02sJ9KNrlorPcFc50JWbEB7OO426ap4ra4L1PqOehPJvTC6C5iAd600O5DBvPbL"
// );

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<SignUp />} />
        <Route path="/hospital" element={<Hospital />} />
        <Route path="/mybooking" element={<MyBooking />} />
        <Route path="/footer" element={<Footer />} />
        <Route path="/logoPage" element={<LogoPage />} />
        <Route path="/service" element={<Service />} />
        {/* <Route
          path="/pay"
          element={
            <Elements stripe={stripePromise}>
              <CheckoutForm />
            </Elements>
          }
        /> */}
      </Routes>
    </div>
  );
}

export default App;
