import React from "react";
import Paper from "@mui/material/Paper";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import Typography from "@mui/material/Typography";
import FaceIcon from "@mui/icons-material/Face";
import Chip from "@mui/material/Chip";
import LockIcon from "@mui/icons-material/Lock";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Input from "@mui/material/Input";
import InputLabel from "@mui/material/InputLabel";
import InputAdornment from "@mui/material/InputAdornment";
import FormControl from "@mui/material/FormControl";
import Visibility from "@mui/icons-material/Visibility";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import IconButton from "@mui/material/IconButton";
import LoginIcon from "@mui/icons-material/Login";
import { useState, useEffect } from "react";
import Alert from "@mui/material/Alert";
import axios from "axios";
import "../CSS/login.css";
import { useNavigate } from "react-router";
import { GoogleLogin} from "react-google-login";
import FacebookButton from "./FbLogin";
import { gapi } from "gapi-script";
import OtpInput from "react-otp-input";
import Box from "@mui/material/Box";
import { CircularProgress } from "@mui/material";

import Snackbar from "@mui/material/Snackbar";

import CloseIcon from "@mui/icons-material/Close";

function Login() {
  // dialog width

  const [fullWidth, setFullWidth] = React.useState(true);
  const [maxWidth, setMaxWidth] = React.useState("xs");

  // snackbar
  const [openmessage, setopenmessage] = useState(false);

  const [message, setmessage] = useState("");

  const handlemessage = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }

    setopenmessage(false);
  };

  const action = (
    <React.Fragment>
      <IconButton
        size="small"
        aria-label="close"
        color="inherit"
        onClick={handlemessage}
      >
        <CloseIcon fontSize="small" />
      </IconButton>
    </React.Fragment>
  );

  //  loading

  const [loading, setLoading] = useState(false);

  // FB login


  // google login
  const clientId =
    "151312583537-a4c84kqo8v2vjmm4gn8b7ine9492v814.apps.googleusercontent.com";
  const onLoginSuccess = (e) => {
    console.log(e.profileObj);
    axios
      .post(`http://localhost:8080/api/auth/socialLogin`, {
        email: e.profileObj.email,
        username: e.profileObj.givenName,
        password: e.profileObj.googleId,
      role: "user",
        
      })
      .then((e) => {
        console.log(e);
        localStorage.setItem("userId", e.data.id);
        localStorage.setItem("Email", e.data.email);
        localStorage.setItem("Username", e.data.username);
        localStorage.setItem("Role", e.data.role);
        localStorage.setItem("accessToken", e.data.accessToken);

        if (localStorage.getItem("accessToken") !== "") {
          setTimeout(function () {
            navigate("/logoPage");
          }, 1000);
        } else {
          return navigate("/");
        }
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const onLoginFailure = (event) => {
    console.log(event);
  };

  useEffect(() => {
    gapi.load("client:auth2", () => {
      gapi.auth2.init({
        clientId: clientId,
      });
    });
  }, []);

  // post email to get otp
  const [open, setOpen] = React.useState(false);

  const [email, setEmail] = useState("");

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleSendEmailApi = () => {
    setLoading(true);
    setopenmessage(true);

    const sendEmailApi = `http://localhost:8080/forget/password?email=` + email;

    axios
      .post(sendEmailApi)
      .then((res) => {
        if (!email) {
          setmessage("Please enter your email");
          setLoading(false);
        } else {
          setmessage("OTP send to your email.");

          console.log(res.data);
          setOpenOtp(true);
          setOpenOtp(true);
          setLoading(false);
        }
      })
      .catch((e) => {
        setmessage("Please enter your email");
        setLoading(false);
        console.log(e);
      });
  };

  // get and save otp to reset pwd

  const [openOtp, setOpenOtp] = React.useState(false);

  const [getOtp, setGetOtp] = useState("");

  const handleCloseOtp = () => {
    setOpenOtp(false);
  };

  const handleOtpVerifyApi = () => {
    setopenmessage(true);
    const otpVerifyApi =
      `http://localhost:8080/forget/password/otp?otp=` +
      getOtp +
      "&email=" +
      email;
    axios
      .post(otpVerifyApi)
      .then((res) => {
        setmessage("OTP is verified.");
        console.log(res.data);
        setOpenReset(true);
      })
      .catch((e) => {
        setmessage("Please enter correct otp.");

        console.log(e);
      });
  };

  // reset password
  const [openReset, setOpenReset] = React.useState(false);

  const [resetPassword, setResetPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const handleCloseReset = () => {
    setOpenReset(false);
  };

  const handleResetPasswordApi = () => {
    setopenmessage(true);

    const otpVerifyApi =
      "http://localhost:8080/forget/password/reset-password?email=" +
      email +
      "&newPassword=" +
      confirmPassword;

    axios
      .post(otpVerifyApi)
      .then((res) => {
        setmessage("Your new password successfully changed.");

        console.log(res.data);
        setOpenReset(false);
        setOpenOtp(false);
        setOpen(false);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const navigate = useNavigate();

  // password field
  const [showPassword, setShowPassword] = React.useState(false);

  const handleClickShowPassword = () => setShowPassword((show) => !show);
  const handleMouseDownPassword = (event) => {
    event.preventDefault();
  };

  // confirm password field
  const [showConfirmPassword, setShowConfirmPassword] = React.useState(false);

  const handleClickShowConfirmPassword = () =>
    setShowConfirmPassword((show) => !show);
  const handleMouseDownConfirmPassword = (event) => {
    event.preventDefault();
  };

  //   Inputs
  const [usernameInput, setUsernameInput] = useState();
  const [passwordInput, setPasswordInput] = useState();

  //  form validation
  const [formValid, setformValid] = useState();
  const [success, setSuccess] = useState();

  //   Inputs error
  const [usernameError, setUsernameError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);

  //   validation for onblur username
  const handleUsernamme = () => {
    if (!usernameInput || usernameInput.length < 4) {
      setUsernameError(true);
      return;
    }
    setUsernameError(false);
  };

  //   validation for onblur password
  const handlePassword = () => {
    if (
      !passwordInput ||
      passwordInput.length < 8 ||
      passwordInput.length > 20
    ) {
      setPasswordError(true);
      return;
    }
    setPasswordError(false);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
  };

  //  post API call from hospital module
  const handleApi = () => {
    setopenmessage(true);

    if (!usernameInput) {
      setmessage("Username can't be empty");
      return;
    }

    if (!passwordInput) {
      setmessage("Password can't be empty");
      return;
    }

    console.log({ usernameInput, passwordInput });
    axios
      .post("http://localhost:8080/api/auth/signin", {
        username: usernameInput,
        password: passwordInput,
      })
      .then((result) => {
        setmessage("Login Successfully");

        localStorage.setItem("accessToken", result.data.accessToken);
        localStorage.setItem("Email", result.data.email);
        localStorage.setItem("userId", result.data.id);
        localStorage.setItem("Role", result.data.role);
        localStorage.setItem("Username", result.data.username);

        if (localStorage.getItem("accessToken") !== "") {
          setTimeout(function () {
            navigate("/logoPage");
          }, 300);
        } else {
          return navigate("/");
        }
      })
      .catch((error) => {
        console.log(error);
        setmessage("Invalid email & password.");
      });

    // setSuccess(null);

    // if (usernameError || !usernameInput) {
    //   setformValid("This field can't be empty.");
    //   return;
    // }

    // if (passwordError || !passwordInput) {
    //   setformValid("Please enter the valid password.");
    //   return;
    // }
  };

  // redirect register page

  const handleRegister = () => {
    navigate("/register");
  };

  return (
   
  <div className="login" style={{overflowY: 'hidden'}}>
      

    
      <Paper
        elevatiion={3}
        style={{
          padding: "50px",
          marginTop: "9.2%",
          backgroundImage: `url(${require("../images/login.jpg")})`,
          backgroundSize: "cover",
          backgroundPosition: "center",
        }}
      >
        <Chip
          icon={<LockIcon />}
          label="Login"
          color="primary"
          variant="outlined"
        />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        {/* <Chip
          icon={<FaceIcon />}
          onClick={handleRegister}
          label="Sign Up"
          color="primary"
          variant="outlined"
        /> */}

        <br />

        <p>
          <TextField
            id="standard-basic"
            error={usernameError}
            label="Username"
            value={usernameInput}
            onChange={(event) => setUsernameInput(event.target.value)}
            onBlur={handleUsernamme}
            variant="standard"
            fullWidth
            size="small"
          />
        </p>

        <p>
          <FormControl sx={{ width: "100%" }} variant="standard">
            <InputLabel
              error={passwordError}
              htmlFor="standard-adornment-password"
            >
              Password
            </InputLabel>
            <Input
              fullWidth
              error={passwordError}
              id="standard-adornment-password"
              type={showPassword ? "text" : "password"}
              value={passwordInput}
              onChange={(event) => setPasswordInput(event.target.value)}
              onBlur={handlePassword}
              endAdornment={
                <InputAdornment position="end">
                  <IconButton
                    aria-label="toggle password visibility"
                    onClick={handleClickShowPassword}
                    onMouseDown={handleMouseDownPassword}
                  >
                    {showPassword ? <Visibility /> : <VisibilityOff />}
                  </IconButton>
                </InputAdornment>
              }
            />
          </FormControl>
        </p>

        <p>
          <Button
            onClick={handleApi}
            variant="contained"
            startIcon={<LoginIcon />}
          >
            LOGIN
          </Button>
        </p>
        <hr></hr>

        <p style={{ textAlign: "left" }}>
          <a
            href="#"
            onClick={handleClickOpen}
            style={{ textDecoration: "none", color: "#01579B" }}
          >
            Forget Password?
          </a>
        </p>

        <GoogleLogin
          clientId={clientId}
          buttonText="Sign In with Google"
          onSuccess={onLoginSuccess}
          onFailure={onLoginFailure}
          cookiePolicy={"single_host_origin"}
          style={{
            border: "none",
            borderRadius: 5,
            fontSize: 16,
            fontWeight: "bold",
            backgroundColor: "#4285f4",
            color: "#fff",
          }}
        />
<br />
<br />
        
  <FacebookButton
    appId="3109137125899182"
    autoLoad={false}
    fields="name,email,picture"
    icon="fa-facebook"
    cssClass= "kep-login-facebook"
  />
        <div align="left">
          <br />
          <p>
            Don't have an account?
            <a
              href="#"
              onClick={handleRegister}
              style={{ textDecoration: "none", color: "#01579B" }}
            >
              &nbsp; Register
            </a>
          </p>
        </div>

        <p>{formValid && <Alert severity="error">{formValid}</Alert>}</p>

        <p>{success && <Alert severity="success">{success}</Alert>}</p>
      </Paper>

      {/* otp for mail */}
      <Dialog
        fullWidth={fullWidth}
        maxWidth={maxWidth}
        open={open}
        onClose={handleClose}
        style={{
          padding: "50px",
          backgroundImage: `url(${require("../images/email.jpg")})`,
          height: "100vh",
          backgroundAttachment: "fixed",
          backgroundSize: "cover",
          backgroundPosition: "center",
        }}
      >
        <DialogTitle>
          <Typography variant="h5" align="center">
            Forget Password
          </Typography>
        </DialogTitle>
        <DialogContent>
          <Typography variant="inherit" color="#B0BEC5" align="left">
            Enter your email and we'll send you 6-digit OTP to reset your
            password.
          </Typography>
          <br />
          <br />
          <TextField
            type="email"
            fullWidth
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            id="outlined-basic"
            label="Enter email address"
            variant="outlined"
          />
          <br />

          <br />
        </DialogContent>
        <DialogActions>
          {loading && (
            <Box sx={{ display: "flex" }}>
              <CircularProgress />
            </Box>
          )}
          <Button
            onClick={() => {
              handleSendEmailApi();
            }}
            variant="contained"
          >
            send otp
          </Button>
        </DialogActions>
      </Dialog>
      {/* end */}

      {/* enter otp for reset password */}
      <Dialog
        fullWidth={fullWidth}
        maxWidth={maxWidth}
        open={openOtp}
        onClose={handleCloseOtp}
        style={{
          padding: "50px",
          backgroundImage: `url(${require("../images/otp.jpg")})`,
          height: "100vh",
          backgroundAttachment: "fixed",
          backgroundSize: "cover",
          backgroundPosition: "center",
        }}
      >
        <DialogTitle>
          <Typography variant="h5" align="center">
            Verification
          </Typography>
        </DialogTitle>
        <DialogContent>
          <Typography variant="inherit" color="#B0BEC5" align="left">
            Please enter the 6-digit OTP code sent to your email....
          </Typography>
          <br />

          <div className="otp-input">
            <OtpInput
              value={getOtp}
              onChange={setGetOtp}
              numInputs={6}
              // renderSeparator={<span> - </span>}
              renderInput={(props) => <input {...props} />}
            />
          </div>

          <br />
        </DialogContent>
        <DialogActions>
          {loading && (
            <Box sx={{ display: "flex" }}>
              <CircularProgress />
            </Box>
          )}
          <Button
            onClick={() => {
              handleOtpVerifyApi();
            }}
            variant="contained"
          >
            Continue
          </Button>
        </DialogActions>
      </Dialog>
      {/* end */}

      {/* reset password for email */}
      <Dialog
        open={openReset}
        onClose={handleCloseReset}
        fullWidth={fullWidth}
        maxWidth={maxWidth}
        style={{
          padding: "50px",
          backgroundImage: `url(${require("../images/reset.jpg")})`,
          height: "100vh",
          backgroundAttachment: "fixed",
          backgroundSize: "cover",
          backgroundPosition: "center",
        }}
      >
        <DialogTitle>
          <Typography variant="h5" align="center">
            Reset Your Password
          </Typography>
        </DialogTitle>
        <DialogContent>
          <br />
          <br />

          <p>
            <FormControl sx={{ width: "100%" }} variant="standard">
              <InputLabel htmlFor="standard-adornment-password">
                New Password
              </InputLabel>
              <Input
                fullWidth
                id="standard-adornment-password"
                type={showPassword ? "text" : "password"}
                value={resetPassword}
                onChange={(event) => setResetPassword(event.target.value)}
                endAdornment={
                  <InputAdornment position="end">
                    <IconButton
                      aria-label="toggle password visibility"
                      onClick={handleClickShowPassword}
                      onMouseDown={handleMouseDownPassword}
                    >
                      {showPassword ? <Visibility /> : <VisibilityOff />}
                    </IconButton>
                  </InputAdornment>
                }
              />
            </FormControl>
          </p>
          <br />
          <br />
          <p>
            <FormControl sx={{ width: "100%" }} variant="standard">
              <InputLabel htmlFor="standard-adornment-password">
                Confirm Password
              </InputLabel>
              <Input
                fullWidth
                id="standard-adornment-password"
                type={showConfirmPassword ? "text" : "password"}
                value={confirmPassword}
                onChange={(event) => setConfirmPassword(event.target.value)}
              />
            </FormControl>
          </p>

          <br />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleResetPasswordApi} variant="contained">
            Change Password
          </Button>
        </DialogActions>
      </Dialog>
      {/* end */}

      <Snackbar
        open={openmessage}
        autoHideDuration={6000}
        onClose={handlemessage}
        message={message}
        action={action}
      />
    </div>
  );
}

export default Login;
