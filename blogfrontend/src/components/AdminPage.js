import * as React from 'react';
import Link from '@mui/material/Link';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import { useState , useEffect} from 'react';
import axios from 'axios';
import Avatar from '@mui/material/Avatar';
import Appbar from './Appbar';
import { Typography } from '@mui/material';
import Paper from '@mui/material/Paper';
import Button from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';


export default function AdminPage() {

    const[user,setUsers] = useState([]);
    const session_url = 'http://localhost:8080/users';

    let config = {
        method: 'get',
        url: session_url,
        headers: { 'Authorization': localStorage.getItem('token') }
      };

    useEffect(()=>{
    axios(config)
        .then(function (response) {
            console.log("Fetched Users");
            setUsers(response.data);
        })
        .catch(function (error) {
            console.log(error);
        });

    },[]);

    const deleteUser = (id) =>{
        let config2 = {
            method: 'delete',
            url: 'http://localhost:8080/deleteUser/' + id,
            headers: { 'Authorization': localStorage.getItem('token') },
          };
          axios(config2)
              .then(function (response) {
                if(response.status == 200){
                    console.log(response.status)
                    console.log("Deleted")
                    setUsers(user.filter(user => user.userId !== id));
                }
              })
              .catch(function (error) {
                console.log(error);
              });
    }




    return (
      <React.Fragment>
        <Appbar/>
        <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
        <Typography component="h1" variant="h6" color="secondary" gutterBottom textAlign='center'>
            Admin Page
        </Typography>
        <Table size="small">
          <TableHead>
            <TableRow>
              <TableCell>Date</TableCell>
              <TableCell>Id</TableCell>
              <TableCell>Name</TableCell>
              <TableCell>Image</TableCell>
              <TableCell>Delete user</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {user.map((user) => (
              <TableRow key={user.userId}>
                <TableCell>{user.registerDate}</TableCell>
                <TableCell>{user.userId}</TableCell>
                <TableCell>{user.userName}</TableCell>
                <TableCell>
                  <Avatar alt="AAA" 
                        src = {user.imageName!=null ? "http://localhost:8080/getUserImage/" + user.userName : undefined} 
                        sx = {{border: '0.3px solid black'}}/>
                        {console.log(user.userName)}
                </TableCell>
                <TableCell>
                  <Button variant="contained" startIcon={<DeleteIcon />} color='secondary' size='small' onClick={()=>deleteUser(user.userId)}>
                    Delete
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
        </Paper>
      </React.Fragment>
    );
  }
  