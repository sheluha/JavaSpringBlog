import './App.css';
import Appbar from './components/Appbar';
import PostGrid from './components/PostsGrid'
import { Pagination } from '@mui/material';
import { flattenOptionGroups } from '@mui/base';
import { useParams } from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <Appbar />
      <PostGrid/>
    </div>
  );
}

export default App;
