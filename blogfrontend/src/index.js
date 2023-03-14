import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import SignIn from './components/signin';
import SignUp from './components/signup';
import NewPost from './components/newPost';
import Post from './components/post';
import Profile from './components/profile';
import AdminPage from './components/AdminPage';
import MyAccount from './components/myAccount'


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />} />
        <Route path="/login" element={<SignIn />} />
        <Route path="/signUp" element={<SignUp />} />  
        <Route path="/newpost" element={localStorage.getItem('token') != null ?<NewPost /> : <SignIn />} /> 
        <Route path="/post/:id" element={<Post />} />
        <Route path="/profile/:id" element={localStorage.getItem('token') != null ?<Profile/> : <SignIn />} />
        <Route path="/admin" element={localStorage.getItem('token') != null ?<AdminPage/> : <SignIn />} />
        <Route path="/account" element={<MyAccount />} />
      </Routes>
    </BrowserRouter>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
