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


function useForceUpdate() {
    let [value, setState] = useState(true);
    return () => setState(!value);
}

const theme = createTheme({
    typography: {
      fontSize : 20,
    },
  });

function Post() {

    const[post,setPost] = useState('');
    const[value0,setValue0] = useState(0);
    const[comments,setComments] = useState([]);
    const[commentBody,setCommentBody] = useState('');
    const { id } = useParams();
    const imgLink ="";

    var config = {
        method: 'get',
        url: 'http://localhost:8080/post/' + id,
      };

    useEffect(()=>{
    axios(config)
        .then(function (response) {
            setPost(response.data);
        })
        .catch(function (error) {
        console.log(error);
        });

    },[]);

    var configComment = {
        method: 'get',
        url: 'http://localhost:8080/comments/' + id,
      };

    // const fetchAll = React.useCallback(async() => {
    //     axios(configComment)
    //     .then(function (response) {
    //         console.log(1);
    //     setComments(response.data);
    //     })
    //     .catch(function (error) {
    //     console.log(error);
    //     });
    // },[id]);


    useEffect(()=>{
    axios(configComment)
        .then(function (response) {
            console.log(value0);
        setComments(response.data);
        })
        .catch(function (error) {
        console.log(error);
        });

    },[]);

    const[userId,setuserId] = useState(0);

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
            <Typography variant="subtitle1" color='black'>
              {post.userName}
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
                onClick={()=>{
                    let config = {
                        method: 'post',
                        url: 'http://localhost:8080/comment',
                        headers: { 'Authorization': localStorage.getItem('token') },
                        data: {
                          commentId : 0,
                          postId : id,
                          userId : userId,
                          commentBody : commentBody,
                          userName : localStorage.getItem('name')
                        }
                    }

                    axios(config)
                    .then(function (response) {
                        
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
                    setCommentBody('');
                    window.location.reload(false);

                }}>
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
            <Avatar alt="Remy Sharp" />
          </Grid>
          <Grid justifyContent="left" item xs zeroMinWidth>
            <h4 style={{ margin: 0, textAlign: "left", fontSize: 20}}>{comment.userName}</h4>
            <p style={{ textAlign: "left", fontSize: 16}}>
              {comment.commentBody}
            </p>
            {/* <p style={{ textAlign: "left", color: "gray" }}>
              posted 1 minute ago
            </p> */}
          </Grid>
        </Grid>
        
      </Paper>
        ))}

     
      
      
      {/* <Paper style={{ padding: "40px 20px", marginTop: 10 }}>
        <Grid container wrap="nowrap" spacing={2}>
          <Grid item>
            <Avatar alt="Remy Sharp" src={imgLink} />
          </Grid>
          <Grid justifyContent="left" item xs zeroMinWidth>
            <h4 style={{ margin: 0, textAlign: "left" }}>Michel Michel</h4>
            <p style={{ textAlign: "left", fontSize: 10}}>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean
              luctus ut est sed faucibus. Duis bibendum ac ex vehicula laoreet.
              Suspendisse congue vulputate lobortis. Pellentesque at interdum
              tortor. Quisque arcu quam, malesuada vel mauris et, posuere
              sagittis ipsum. Aliquam ultricies a ligula nec faucibus. In elit
              metus, efficitur lobortis nisi quis, molestie porttitor metus.
              Pellentesque et neque risus. Aliquam vulputate, mauris vitae
              tincidunt interdum, mauris mi vehicula urna, nec feugiat quam
              lectus vitae ex.{" "}
            </p>
            <p style={{ textAlign: "left", color: "gray" }}>
              posted 1 minute ago
            </p>
          </Grid>
        </Grid>
      </Paper> */}
    </div>
    </div>
  );
}

export default Post;
