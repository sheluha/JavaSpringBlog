import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import Avatar from '@mui/material/Avatar';
import { Link, redirect } from 'react-router-dom';
import { useEffect, useState } from 'react';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import Fade from '@mui/material/Fade';
import { useNavigate } from 'react-router-dom';


export default function Appbar() {

  const[logButton,setLogButton] = useState(true);
  const[postButton,setPostButton] = useState(false);

  const navigate = useNavigate();

  const [anchorEl, setAnchorEl] = React.useState(null);
  const open = Boolean(anchorEl);
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };

  const logOut = () =>{
    localStorage.clear();
    setAnchorEl(null);
    navigate("/");
    window.location.reload(false);

  }

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
            href = "/"
          >
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }} >
            Spring boot app
          </Typography>
          {postButton && <Button color="inherit" href='/newpost'>New Post</Button>}
          <Link to='/login' style={{ color: '#FFF' }}>
            {logButton && <Button color="inherit" >Login</Button>}
          </Link>
          {localStorage.getItem('token')&& 
          <Button 
            onClick={handleClick} 
            to={'profile/'+localStorage.getItem('username')} 
            aria-controls={open ? 'fade-menu' : undefined}
            aria-haspopup="true"
            aria-expanded={open ? 'true' : undefined}
            >
          <Avatar 
            src = {"http://localhost:8080/getUserImage/" + localStorage.getItem('username')} 
            sx = {{border: '0.3px solid black'}}/>  
          </Button>} 
          <Menu
            id="fade-menu"
            MenuListProps={{
              'aria-labelledby': 'fade-button',
            }}
            anchorEl={anchorEl}
            open={open}
            onClose={handleClose}
            TransitionComponent={Fade}
          >
            <MenuItem onClick={()=>navigate("/profile/1")} >Profile</MenuItem>
            <MenuItem onClick={handleClose}>My account</MenuItem>
            <MenuItem onClick={()=>logOut()}>Logout</MenuItem>
            </Menu>       
        </Toolbar>
      </AppBar>
    </Box>
  );
}
