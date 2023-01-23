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


const theme = createTheme();

export default function SignIn() {

  const[userName,setUserName] = useState('');
  const[password,setPassword] = useState('');
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
            Sign in
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
              id="password"
              autoComplete="current-password"
              onChange={(val)=>{
                setPassword(val.target.value);
            }}
            />
            <Button
            color="secondary"
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
              onClick={() => {
                let config2 = {
                  method: 'post',
                  url: 'http://localhost:8080/login',
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
                    })
                    .catch(function (error) {
                    console.log(error);
                    });
              }}
            >
              Sign In
            </Button>
            <Grid container>
              
              <Grid item>
                <Link href="/signup" variant="body2" color="secondary">
                  {"Don't have an account? Sign Up"}
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}