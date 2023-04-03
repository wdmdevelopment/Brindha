import React from "react";
import Paper from "@mui/material/Paper";
import Chip from "@mui/material/Chip";
import FaceIcon from "@mui/icons-material/Face";
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
import MenuItem from "@mui/material/MenuItem";
import { useState } from "react";
import Alert from "@mui/material/Alert";
import axios from "axios";
import "./register.css";
import { useNavigate } from "react-router";

const isEmail = (email) =>
  /[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(email);

const role = [
  {
    value: "USER",
  },
  {
    value: "ADMIN",
  },
];

function SignUp() {
  // password field

  const navigate = useNavigate();

  const [showPassword, setShowPassword] = React.useState(false);

  //   Inputs
  const [usernameInput, setUsernameInput] = useState();
  const [ageInput, setAgeInput] = useState();
  const [roleInput, setRoleInput] = useState();
  const [emailInput, setEmailInput] = useState();
  const [passwordInput, setPasswordInput] = useState();

  //  form validity
  const [formValid, setformValid] = useState();
  const [success, setSuccess] = useState();

  //   Inputs error
  const [usernameError, setUsernameError] = useState(false);
  const [ageError, setAgeError] = useState(false);
  // const [roleError, setRoleError] = useState(false);
  const [emailError, setEmailError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);

  //   validation for onblur username
  const handleUsernamme = () => {
    if (!usernameInput || usernameInput.length < 5) {
      setUsernameError(true);
      return;
    }
    setUsernameError(false);
  };

  //    age field to prevent from e, E, +, -, .
  const exceptThisSymbols = ["e", "E", "+", "-", "."];

  //   validation for onblur age
  const handleAge = () => {
    if (!ageInput || ageInput.length > 2) {
      setAgeError(true);
      return;
    }
    setAgeError(false);
  };

  //   validation for onblur email
  const handleEmail = () => {
    if (!isEmail(emailInput)) {
      setEmailError(true);
      return;
    }
    setEmailError(false);
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

  const handleApi = () => {
    console.log({
      usernameInput,
      ageInput,
      roleInput,
      emailInput,
      passwordInput,
    });
    axios
      .post("http://localhost:8080/api/auth/signup", {
        username: usernameInput,
        age: ageInput,
        role: roleInput,
        email: emailInput,
        password: passwordInput,
      })
      .then((result) => {
        localStorage.setItem("accessToken", result.data.accessToken);

        navigate("/");
      })
      .catch((error) => {
        console.log(error);
      });

    setSuccess(null);

    if (usernameError || !usernameInput) {
      setformValid("Username should be above 5 characters.");
      return;
    }

    if (ageError || !ageInput) {
      setformValid("Age should not be empty.");
      return;
    }

    if (emailError || !emailInput) {
      setformValid("Email is invalid. Please Re-Enter");
      return;
    }

    if (passwordError || !passwordInput) {
      setformValid("Password must be 8-20 characters. Please Re-Enter");
      return;
    }
  };

  const handleClickShowPassword = () => setShowPassword((show) => !show);
  const handleMouseDownPassword = (event) => {
    event.preventDefault();
  };

  const handleLogin = () => {
    navigate("/");
  };

  return (
    <div className="register">
      <Paper elevatiion={3} style={{ padding: "30px", marginTop: "20%" }}>
        <Chip
          icon={<FaceIcon />}
          label="Sign Up"
          color="primary"
          variant="outlined"
        />
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
          <TextField
            id="standard-basic"
            error={ageError}
            label="Age"
            value={ageInput}
            onChange={(event) => setAgeInput(event.target.value)}
            onBlur={handleAge}
            type="number"
            onKeyDown={(e) =>
              exceptThisSymbols.includes(e.key) && e.preventDefault()
            }
            //   pattern="/^[0-9\b]+$/"
            variant="standard"
            fullWidth
            size="small"
          />
        </p>

        <p>
          <TextField
            id="standard-select-role"
            // error={roleError}
            select
            label="Role"
            value={roleInput}
            onChange={(event) => setRoleInput(event.target.value)}
            defaultValue="None"
            helperText="Please select your role"
            variant="standard"
            fullWidth
            size="small"
          >
            {role.map((option) => (
              <MenuItem key={option.value} value={option.value}>
                {option.value}
              </MenuItem>
            ))}
          </TextField>
        </p>

        <p>
          <TextField
            id="standard-basic"
            error={emailError}
            label="Email"
            value={emailInput}
            onChange={(event) => setEmailInput(event.target.value)}
            onBlur={handleEmail}
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
                    {showPassword ? <VisibilityOff /> : <Visibility />}
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
            SIGN UP
          </Button>
        </p>

        <div align="center">
          <p>
            Have an account?
            <a href="#" onClick={handleLogin} style={{ textDecoration: 'none' }}>
              Login
            </a>
          </p>
        </div>

        <p>{formValid && <Alert severity="error">{formValid}</Alert>}</p>
        <p>{success && <Alert severity="success">{success}</Alert>}</p>
      </Paper>
    </div>
  );
}

export default SignUp;
