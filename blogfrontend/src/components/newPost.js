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
import AddCircleRoundedIcon from '@mui/icons-material/AddCircleRounded';
import { getActiveElement } from '@testing-library/user-event/dist/utils';


const theme = createTheme();

export default function NewPost() {


    const[posthead,setpostHead] = useState('');
    const[postbody,setpostBody] = useState('');
    const[userId,setuserId] = useState(0);
    const navigate = useNavigate();

    var config = {
        method: 'post',
        url: 'http://localhost:8080/getId',
        headers: { 'Authorization': localStorage.getItem('token') },
        data: {
            userId : 0,
            userName : localStorage.getItem('name'),
            userPassword : "",
            isAdmin : false,
            imageName : ""
          }
        
      };
      axios(config)
        .then(function (response) {
            setuserId(response.data);
        })
        .catch(function (error) {
            console.log(error);
        });


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
              helperText={posthead.length>58 ? "Post head must be < 60 smb" : ''}
              error = {posthead.length>58}
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
              error = {postbody.length>200}
              helperText={postbody.length>200 ? "Post body must be < 200 smb" : ''}
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
              onClick={() => {
                if(posthead.length >= 60){
                }
                else{
                let config2 = {
                  method: 'post',
                  url: 'http://localhost:8080/newpost',
                  headers: { 'Authorization': localStorage.getItem('token') },
                  data: {
                    postId : 0,
                    userId : userId,
                    postHeader : posthead,
                    postBody : postbody,
                    userName : localStorage.getItem('name')         
                  }
                };
                axios(config2)
                    .then(function (response) {
                      
                    })
                    .catch(function (error) {
                    console.log(error);
                    });
                    navigate('/');
                    window.location.reload(false);
                  }
              }}           
            >
              Send
            </Button>
            
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}