import Grid from '@mui/material/Grid';
import Container from '@mui/material/Container';
import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { useEffect, useState } from 'react';
import axios from 'axios';
import Post from './post';



export default function PostGrid() {

    const[token,setToken] = useState('');
    const[posts,setPosts] = useState([]);
    const[postHead,setPostHead] = useState('');
    const[postBody,setPostBody] = useState('');


    let base64 = require('base-64');
    var username = 'user';
    var password = '465e96a0-04e5-4eda-bd31-b49a404f0568'
    const session_url = 'http://localhost:8080/posts';

    // var config2 = {
    //   method: 'post',
    //   url: 'http://localhost:8080/login',
      //headers: { 'Authorization': 'Basic '+  base64.encode(username + ":" + password) },
    //   "userId" : 1,
    //   "userName":"user12345",
    //   "userPassword":"user12345Password",
    //   "isAdmin": false,
    //   "imageName": "
    //   data: {
    //     userId : 0,
    //     userName : 'user123455678578',
    //     userPassword : 'user12345Password',
    //     isAdmin : false,
    //     imageName : ""
    //   }
    // };
    var config = {
        method: 'get',
        url: session_url,
      };

    useEffect(()=>{
    axios(config)
        .then(function (response) {
        setPosts(response.data);
        })
        .catch(function (error) {
        console.log(error);
        });

    },[]);


    

    return (
        <Container sx={{ py: 8 }} maxWidth="lg">
            <Grid container spacing={4}>
                {posts.map((post) => (
                    <Grid item key={post} xs={12} sm={6} md={4}>
                        <Card sx={{ minWidth: 275 }}>
                        <CardContent>
                            <Typography sx={{ fontSize: 20 }} color="text.secondary" gutterBottom>                                
                                {post.postHeader}               
                            </Typography>
                            <Typography variant="body2">
                                {post.postBody.substring(0, 35) + '...'}
                            </Typography>
                        </CardContent>
                            <CardActions>
                                <Button size="small" href={'/post/'+ post.postId}>{post.userName}</Button>
                            </CardActions>
                        </Card>
                    </Grid>
                ))}
            </Grid>
        </Container>
    );
}