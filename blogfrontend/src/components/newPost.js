import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import AddCircleRoundedIcon from '@mui/icons-material/AddCircleRounded';

const theme = createTheme();

export default function NewPost() {


    const[posthead,setpostHead] = useState('');
    const[postbody,setpostBody] = useState('');
    const navigate = useNavigate();



  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
  };

  const newpost = () => {
    let config = {
      method: 'post',
      url: 'http://localhost:8080/post',
      headers: { 'Authorization': localStorage.getItem('token') },
      data: {
        postHeader : posthead,
        postBody : postbody        
      }
    };
    // .then(function (response) {
      
    // })
    axios(config)
        .catch(function (error) {
        console.log(error);
        });
        navigate('/');
        window.location.reload(false);
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
            <AddCircleRoundedIcon/>
          </Avatar>
          <Typography component="h1" variant="h5">
            Add new post
          </Typography>
          <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
            <TextField
            color="secondary"
              margin="normal"
              required
              fullWidth
              id="email"
              label="Post head"
              name="email"
              autoComplete="email"
              helperText={posthead.length>58|| posthead.length<10? "Must be between 10 and 60 smb" : ''}
              error = {posthead.length>58|| posthead.length<10}
              autoFocus
              onChange={(val)=>{
                setpostHead(val.target.value);
            }}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Post body"
              type="password"
              id="password"
              autoComplete="current-password"
              multiline
              rows={8}
              color="secondary"
              error = {postbody.length>200|| postbody.length<10}
              helperText={postbody.length>200|| postbody.length<10 ? "Must be between 10 and 200 smb" : ''}
              onChange={(val)=>{
                setpostBody(val.target.value);
            }}
            />
            <Button
            error = {posthead.length>58 || postbody > 200}
            color="secondary"
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}  
              onClick={() => {newpost()}}           
            >
              Send
            </Button>
            
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}