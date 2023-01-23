import Appbar from './components/Appbar';
import PostGrid from './components/PostsGrid'

function login() {
  return (
    <div className="login">
      <Appbar />
      <PostGrid />
    </div>
  );
}

export default login;