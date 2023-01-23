import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import Avatar from '@mui/material/Avatar';
import { Link } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';


export default function Appbar() {

  const[logButton,setLogButton] = useState(true);
  const[postButton,setPostButton] = useState(false);
  const[userId,setuserId] = useState(0);

  useEffect(()=>{
    if(localStorage.getItem('auth') != null){
      setLogButton(false);
      setPostButton(true);
    }
  },[])

  var configUserId = {
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
  axios(configUserId)
    .then(function (response) {
        setuserId(response.data);
    })
    .catch(function (error) {
        console.log(error);
    });

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static" color="secondary">
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
          >
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Spring boot app
          </Typography>
          {postButton && <Button color="inherit" href='/newpost'>New Post</Button>}
          <Link to='/login' style={{ color: '#FFF' }}>
            {logButton && <Button color="inherit" >Login</Button>}
          </Link>
          {localStorage.getItem('auth')&&<Link to={'profile/'+userId}>
          <Avatar alt="Remy Sharp" src = {"http://localhost:8080/getUserImage/" + userId} sx = {{border: '0.3px solid black'}}/>  
          </Link>}        
        </Toolbar>
      </AppBar>
    </Box>
  );
}
