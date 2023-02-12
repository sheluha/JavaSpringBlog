import { useState , useEffect} from 'react';
import Appbar from './Appbar';
import Typography from '@mui/material/Typography';
import axios from 'axios';
import { createTheme} from '@mui/material/styles';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import { useParams } from 'react-router-dom'
import ChatBubbleIcon from '@mui/icons-material/ChatBubble';
import TextField from '@mui/material/TextField';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import Divider from '@mui/material/Divider';
import React from 'react';




function Post() {

    const[post,setPost] = useState('');
    const[comments,setComments] = useState([]);
    const[commentBody,setCommentBody] = useState('');
    const { id } = useParams();

    let configPost = {
        method: 'get',
        url: 'http://localhost:8080/post/' + id,
      };
    useEffect(()=>{
      axios(configPost)
        .then(function (response) {
            setPost(response.data);
        })
        .catch(function (error) {
          console.log(error);
        });
    },[]);

    let configComment = {
        method: 'get',
        url: 'http://localhost:8080/comments/' + id,
      };
    useEffect(()=>{
      axios(configComment)
        .then(function (response) {
          console.log('fetched comments');
          setComments(response.data);
        })
        .catch(function (error) {
          console.log(error);
        });

    },[]);

    function newComment(){
      let config = {
        method: 'post',
        url: 'http://localhost:8080/newcomment',
        headers: { 'Authorization': localStorage.getItem('token') },
        data: {
          postId : id,
          commentBody : commentBody,
        }
      }
    
      axios(config)
        .then(function (response) {
          console.log('fetched comments');
        })
        .catch(function (error) {
          console.log(error);
        });
      setCommentBody('');
      window.location.reload(false);
    }

  return (
     <div className="Post">
     <Appbar/>
    <Grid item xs={12} md={6}>
        <Card sx={{ display: 'flex'}} >
          <CardContent sx={{ flex: 1 }}>
            <Typography component="h2" variant="h5">
              {post.postHeader}
            </Typography>
            <Typography variant="subtitle1" paragraph>
              {post.postBody}
            </Typography>
            <Typography variant="subtitle1" color='grey'>
              Post is written by: {post.userName}
            </Typography>
            <Typography variant="subtitle1" color='grey'>
              {typeof post.createdAt === 'string' ? post.createdAt.substring(0,19).replace("T"," ") : "" }
            </Typography>
          </CardContent>

        </Card>
        {localStorage.getItem('token') && <Paper style={{ padding: "10px 10px", margin:"10px 10px",display:'inline-block'}}>
            <Stack direction="row" spacing={2} >
                <TextField 
                id="commentId" 
                label="Write comment " 
                variant="outlined"
                value={commentBody} 
                size='small' 
                sx={{width: 500, maxWidth: '100%'}} 
                onChange={(val)=>{
                    setCommentBody(val.target.value);
                }}/>
                <Button 
                variant="contained" 
                sx={{height:46}}
                onClick={()=>newComment()}>
                    Send
                </Button>
            </Stack>
        </Paper>}
    </Grid>
    <div style={{ padding: 10 }} className="App">
    {comments.map((comment) => (
      <Paper style={{ padding: "10px 10px" , marginBottom:"5px"}}>
        <Grid container wrap="nowrap" spacing={2}>
          <Grid item>
          
            <Avatar alt="Remy Sharp" src = {"http://localhost:8080/getUserImage/" + comment.userName}>
              
            </Avatar>
          </Grid>
          <Grid justifyContent="left" item xs zeroMinWidth>
            <div style={{display:"flex"}}>
              <Typography variant="subtitle1" color='black'>{comment.userName}</Typography>
              <Typography variant="title" color="inherit" noWrap>
                &nbsp;
                &nbsp;
              </Typography>
              <Typography variant="subtitle1" fontSize={3} color='grey' style={{marginTop:7}}>
                {typeof comment.createdAt === 'string' ? comment.createdAt.substring(0,19).replace("T"," ") : "" }
                </Typography>
            </div>
            <p style={{ textAlign: "left", fontSize: 16}}>
              {comment.commentBody}
            </p>
          </Grid>
        </Grid>
        
      </Paper>
        ))}
    </div>
    </div>
  );
}

export default Post;
