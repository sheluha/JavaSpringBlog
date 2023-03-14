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
import Avatar from '@mui/material/Avatar';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import { Paper } from '@mui/material';
import { useParams } from 'react-router-dom';
import { Pagination } from '@mui/material';
import ChatBubbleOutlineIcon from '@mui/icons-material/ChatBubbleOutline';

export default function PostGrid(props) {
    //console.log(isNaN(parseInt(sessionStorage.getItem('currentPage'))) ?  1 : sessionStorage.getItem('currentPage'));

    const[posts,setPosts] = useState([]);
    const[pages,setPages] = useState(0);
    const navigate = useNavigate();

    if(sessionStorage.getItem('currentPage') == null){
        sessionStorage.setItem('currentPage',1);
    }
    
    //const session_url = 'http://localhost:8080/posts/' + (sessionStorage.getItem('currentPage')-1) ?? '0';
    const session_url = 'http://localhost:8080/posts/' 
        + (sessionStorage.getItem('currentPage')-1 ?? 0);
    console.log(session_url);

    let config = {
        method: 'get',
        url: session_url,
      };

    useEffect(()=>{
    axios(config)
        .then(function (response) {
            console.log("Fetched Posts");
            setPosts(response.data.content);
            console.log(response.data.content);
            setPages(response.data.totalPages)
        })
        .catch(function (error) {
            console.log(error);
        });

    },[]);

    const handleChange = (event, value) => {
        let config2 = {
            method: 'get',
            url: 'http://localhost:8080/posts/' + (value-1),
          };
        axios(config2)
        .then(function (response) {
            console.log('Current page: ' + parseInt(sessionStorage.getItem('currentPage')));
            setPosts(response.data.content);
            sessionStorage.setItem('currentPage',value);
            
        })
        .catch(function (error) {
            console.log(error);
        });
      };

    return (
        <Paper>
          <div style={{justifyContent:'center',display:'flex'}}>
            <Pagination 
                count={pages} 
                color="secondary" 
                style={{marginTop:15}}
                page = {parseInt(sessionStorage.getItem('currentPage')) ?? 1}
                onChange={handleChange}/> 
           </div>
        <Paper sx={{textAlign:'left'}}>
         {posts.map((post) => (
             <Card sx={{ minWidth: 275  ,marginTop:"23px",marginLeft:2,marginRight:2, backgroundColor:'#f5f5f5'}} key={post.post.postId}>
                 <CardContent>
                     <div style={{display: 'flex'}}>
                         <Avatar
                         alt="Ted talk"
                         src={ "http://localhost:8080/image/" + post.post.user.imageName }
                         sx={{ width: 56, height: 56 }}
                         />
                         <Typography sx={{ fontSize: 20 }} style={{fontWeight:'bold', fontSize:28, marginLeft:15}} gutterBottom>                                
                         {post.post.postHeader}               
                     </Typography>
                     </div>
                     <Typography variant="body2" color="text.secondary" style={{fontSize:20}}>
                         {post.post.postBody.substring(0, 100) + '...'}
                     </Typography>
                 </CardContent>
                 <CardActions>
                     {/*<Button size="small" href={'/post/'+post.postId}>{post.user.userName}</Button> */}
                     <AccountCircleIcon />
                     <Typography fontSize={19} color='grey' style={{marginLeft:5}}>{post.post.user.userName}</Typography>
                     <CalendarMonthIcon />
                     <Typography fontSize={19} color='grey' style={{marginLeft:7}}>{post.post.createdAt.substring(0,10)}</Typography>
                     <ChatBubbleOutlineIcon />
                     <Typography fontSize={19} color='grey' style={{marginLeft:7}}>{post.commentsCount}</Typography>
                     <Button style={{marginLeft:8}}  size='small' href={'/post/'+post.post.postId}>Read more</Button>
                 </CardActions>
                 
                    </Card>
                ))}
        </Paper>
        </Paper>
    );
}