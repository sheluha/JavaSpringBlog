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
import axios from 'axios';
import { useState , useEffect} from 'react';
import { useParams } from 'react-router-dom'
import { useNavigate } from 'react-router-dom';


const theme = createTheme();


export default function Profile() {

   const[fileUp,setFileUp] = useState(null);
   const { id } = useParams();
   const navigate = useNavigate();

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
          <Avatar sx={{ m: 3 ,width:'150px', height:'150px',border: '0.3px solid black'}} src={"http://localhost:8080/getUserImage/" + localStorage.getItem('username')} >
          </Avatar>
          <Typography component="h1" variant="h5">
            User name : {localStorage.getItem('username')}
          </Typography>
          <Box component="form" noValidate sx={{ mt: 1 }}>

            <input type = "file" onChange={(e)=>{setFileUp(e.target.files[0]);}}/>
            
            <Button
            color="secondary"
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
              onClick={() => {
                var configFileUpload = {
                    method: 'post',
                    url: 'http://localhost:8080/upload',
                    headers: { 'Authorization': localStorage.getItem('token'),
                    "Content-Type": "multipart/form-data" },
                    data:{
                      file:fileUp,
                      userId:id
                        
                    }  
                  };
                  axios(configFileUpload)
                    .then(function (response) {
                      navigate('/');
                      window.location.reload(false);
                      console.log("send");
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
                
                }}
            >
              Send photo
            </Button>
            
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}