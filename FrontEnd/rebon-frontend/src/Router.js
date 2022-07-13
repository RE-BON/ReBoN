import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Detail from './pages/Detail';
import Main from './pages/Main';
// import MainData from './pages/Main/MainData';
import MainCategoryData from './pages/Main/MainCategoryData';
import Search from './pages/Search';
import Post from './pages/Post';
import Mypage from './pages/Mypage';
import ReviewDropdown from './pages/Detail/Review/ReviewDropdown';
import Join from './pages/Join';
import Login from './pages/Login';
import Register from './pages/Join/Register';
import Logout from './pages/Logout';
import Terms from './pages/Terms';
/*추후변경필요*/
import TermsModal from './pages/Terms/termsModal';

function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/detail/:id" element={<Detail />} />
        {/* <Route path="/main" element={<MainData />} /> */}
        <Route path="/main" element={<MainCategoryData />} />
        <Route path="/post" element={<Post />} />
        <Route path="/reviewdropdown" element={<ReviewDropdown />} />
        <Route path="/login" element={<Login />} />
        <Route path="/mypage/*" element={<Mypage />} />
        <Route path="/join" element={<Join />} />
        <Route path="/join/register" element={<Register />} />
        <Route path="/logout" element={<Logout />} />
        <Route path="/login" element={<Login />} />
        <Route path="/terms" element={<Terms />} />
        <Route path="/termsModal" element={<TermsModal />} />
        <Route path="/" element={<Search />} />
      </Routes>
    </BrowserRouter>
  );
}

export default Router;
