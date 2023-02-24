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
import { useNavigate } from 'react-router-dom';
import Post from './post';

export default function PostGrid() {

    const[posts,setPosts] = useState([]);
    const navigate = useNavigate();
    const session_url = 'http://localhost:8080/posts';

    var config = {
        method: 'get',
        url: session_url,
      };

    useEffect(()=>{
    axios(config)
        .then(function (response) {
            console.log("Fetched Posts");
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
                    <Grid item key={post.postId} xs={12} sm={6} md={4}>
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
                                <Button size="small" href={'/post/'+post.postId}>{post.user.userName}</Button>
                                <Typography fontSize={3} color='grey' style={{marginLeft:7}}>{post.createdAt.substring(0,10)}</Typography>
                            </CardActions>
                            
                        </Card>
                    </Grid>
                ))}
            </Grid>
        </Container>
    );
}