import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import red from '@mui/material/colors/red';
import { Hidden } from '@mui/material';
import { display } from '@mui/system';


const theme = createTheme();

export default function SignUp() {

  const[userName,setUserName] = useState('');
  const[password,setPassword] = useState('');
  const[password2,setPassword2] = useState('');
  const[psError,setPsError] = useState(true);
  const[lengthError,setlengthError] = useState(true);
  const navigate = useNavigate();



  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
  };

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
          <Typography component="h8" variant="h8" style={{color:"red"}} hidden={(psError)? 'hidden' : ''}  >
            Пароли не совпадают
          </Typography>
          <Typography component="h8" variant="h8" style={{color:"red"}} hidden={(lengthError)? 'hidden' : ''}  >
            Пароль и имя пользователя должны быть больше 5 символов
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
              onChange={(val)=>{
                setPassword2(val.target.value);
            }}
            />
            <Button
            color="secondary"
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
              onClick={() => {

                if(password==password2){
                    setPsError(true);
                    let config2 = {
                        method: 'post',
                        url: 'http://localhost:8080/signup',
                        data: {
                          userId : 0,
                          userName : userName,
                          userPassword : password,
                          isAdmin : false,
                          imageName : ""
                        }
                      };
                      axios(config2)
                          .then(function (response) {
                            if(response.data){
                              localStorage.setItem('auth',true);
                              localStorage.setItem('name',userName);
                              localStorage.setItem('token','Basic dXNlcjpwYXNzd29yZA==');
                              navigate('/');
                            }
                            else{
                                setlengthError(false);
                            }
                          })
                          .catch(function (error) {
                          console.log(error);
                          });
                }
                else{                 
                    setPsError(false);
                    
                }     
                }}
            >
              Sign Up
            </Button>
            
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}