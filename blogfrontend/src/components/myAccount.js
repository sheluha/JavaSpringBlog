import { useState , useEffect} from 'react';
import Appbar from './Appbar';
import Typography from '@mui/material/Typography';
import axios from 'axios';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import { useParams } from 'react-router-dom'
import TextField from '@mui/material/TextField';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import React from 'react';
import DeleteIcon from '@mui/icons-material/Delete';
import { useNavigate } from 'react-router-dom';



function MyAccount() {

  const[username1,setUserName] = useState('');
  const[userpassword,setUserPassword] = useState('');
  const[account,setAccount] = useState();
  const navigate = useNavigate();


  let accountConfig = {
    method: 'get',
    url: 'http://localhost:8080/userinfo',
    headers: { 'Authorization': localStorage.getItem('token') }
  };
    useEffect(()=>{
    axios(accountConfig)
        .then(function (response) {
            setAccount(response.data);
        })
        .catch(function (error) {
            console.log(error);
        });

    },[]);



  const updateUserName = () => {
    let config = {
      method: 'put',
      url: 'http://localhost:8080/user',
      headers: { 'Authorization': localStorage.getItem('token') },
      data: {
        userName : username1,
        userPassword : '',
      }
    }
    axios(config)
      .then(function (response) {
        if(response.status==200){
            localStorage.clear();
            navigate('/login');
            window.location.reload(false);
        }

      })
      .catch(function (error) {
        console.log(error);
      });
      
  }

  const updateUserPassword= () => {
    let config = {
      method: 'put',
      url: 'http://localhost:8080/user',
      headers: { 'Authorization': localStorage.getItem('token') },
      data: {
        userName : '',
        userPassword : userpassword,
      }
    }
    axios(config)
      .then(function (response) {
        if(response.status==200){
            localStorage.clear();
            navigate('/login');
            window.location.reload(false);
        }
      })
      .catch(function (error) {
        console.log(error);
      });
  }



  return (
     <div>
        <Appbar/>
        <Paper style={{margin:10,padding:10}}>
            <Avatar src={"http://localhost:8080/image/" + account?.imageName}></Avatar>
            <Typography>Account name: {account?.userName}</Typography>
            <Typography>Registration date: {account?.registerDate} </Typography>
            <Typography>Role: {account?.role}</Typography>
        </Paper>
        <Paper style={{margin:20,padding:10}} elevation={16}>
            <Typography>Change username :</Typography>
            <TextField
            color="secondary"
              margin="normal"
              required
              fullWidth
              name="username"
              label="username"
              id="username"
            //   error={false}
            //   helperText={false && 'Incorrect login or password'}
              autoComplete="current-username"
              onChange={(val)=>{
                setUserName(val.target.value);
            }}
            />
            <Button
            color="secondary"
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
              onClick={() => {updateUserName()}}
            >
              Change username
            </Button>
        </Paper>
        <Paper style={{margin:20,padding:10}} elevation={16}>
            <Typography>Change password :</Typography>
            <TextField
            color="secondary"
              margin="normal"
              required
              fullWidth
              name="password"
              label="password"
              id="password"
              error={false}
              helperText={false && 'Incorrect login or password'}
              autoComplete="current-password"
              onChange={(val)=>{
                setUserPassword(val.target.value);
            }}
            />
            <Button
            color="secondary"
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
              onClick={() => {updateUserPassword()}}
            >
              Change password
            </Button>
        </Paper>
    </div>
    )
}

export default MyAccount;
