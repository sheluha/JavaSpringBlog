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

  useEffect(()=>{
    if(localStorage.getItem('token') != null){
      setLogButton(false);
      setPostButton(true);
    }
  },[])

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
          {localStorage.getItem('token')&&<Link to={'profile/'+localStorage.getItem('username')}>
          <Avatar alt="Remy Sharp" src = {"http://localhost:8080/getUserImage/" + localStorage.getItem('username')} sx = {{border: '0.3px solid black'}}/>  
          </Link>}        
        </Toolbar>
      </AppBar>
    </Box>
  );
}
