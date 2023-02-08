import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';


const theme = createTheme();

export default function SignUp() {

  const[userName,setUserName] = useState('');
  const[password,setPassword] = useState('');
  const[passwordConfirm,setPasswordConfirm] = useState('');
  const navigate = useNavigate();

  let base64 = require('base-64');



  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
  };

  function signIn() {
      if(password==passwordConfirm){
          let config = {
              method: 'post',
              url: 'http://localhost:8080/newuser',
              data: {
                userName : userName,
                userPassword : password
              }
            };
            axios(config)
                .then(function (response) {
                  if(response.status == 200){
                    localStorage.setItem('token', 'Basic ' +  base64.encode(userName + ":" + password));
                    localStorage.setItem('username',userName);
                    navigate('/');
                  }
                })
                .catch(function (error) {
                  console.log(error);
                });
      }    
  }

  function passwordValidation(){
    if(password.includes(' ') || passwordConfirm.includes(' ')){
      return 'Password must not contain whitespace';
    }
    else if(password.length< 5){
      return 'Password need to be longer than 5';
    }
    else if(password != passwordConfirm){
      return 'Passwords must match';
    }
    return '';
  }

  function usernameValidation(){
    if(userName.includes(' ')){
      return 'Username must not contain whitespace';
    }
    else if(userName.length< 5){
      return 'Username need to be longer than 5';
    }
    return '';
  }

  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign Up
          </Typography>
          <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
            <TextField
              color="secondary"
              margin="normal"
              required
              fullWidth
              id="email"
              label="User Name"
              name="email"
              autoComplete="email"
              autoFocus
              error = {usernameValidation()!=''}
              helperText={usernameValidation()}
              onChange={(val)=>{
                  setUserName(val.target.value);
              }}
            />
            <TextField
              color="secondary"
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password2"
              autoComplete="current-password"
              error = {passwordValidation()!=''}
              onChange={(val)=>{
                setPassword(val.target.value);
            }}
            />
            <TextField
            color="secondary"
              margin="normal"
              required
              fullWidth
              name="password"
              label="Confirm password"
              type="password"
              id="password"
              autoComplete="current-password"
              error = {passwordValidation()!=''}
              helperText={passwordValidation()}
              onChange={(val)=>{
                setPasswordConfirm(val.target.value);
            }}
            />
            <Button
            color="secondary"
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
              onClick={() => signIn()}
            >
              Sign Up
            </Button>
            
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}